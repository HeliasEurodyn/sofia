package com.crm.sofia.services.user;

import com.crm.sofia.config.AppConstants;
import com.crm.sofia.dto.user.ChangePasswordRequest;
import com.crm.sofia.dto.user.JwtAuthenticationResponse;
import com.crm.sofia.dto.user.SignUpRequest;
import com.crm.sofia.dto.user.UserDTO;
import com.crm.sofia.exception.DoesNotExistException;
import com.crm.sofia.exception.UserAlreadyExistAuthenticationException;
import com.crm.sofia.exception.login.ChangePasswordException;
import com.crm.sofia.exception.login.IncorrectPasswordException;
import com.crm.sofia.exception.login.UserNotFoundException;
import com.crm.sofia.mapper.user.UserMapper;
import com.crm.sofia.model.user.LocalUser;
import com.crm.sofia.model.user.Token;
import com.crm.sofia.model.user.User;
import com.crm.sofia.repository.user.RoleRepository;
import com.crm.sofia.repository.user.TokenRepository;
import com.crm.sofia.repository.user.UserRepository;
import com.crm.sofia.security.jwt.TokenProvider;
import com.crm.sofia.security.oauth2.user.OAuth2UserInfo;
import com.crm.sofia.security.oauth2.user.OAuth2UserInfoFactory;
import com.crm.sofia.services.auth.JWTService;
import com.crm.sofia.services.menu.MenuFieldService;
import com.crm.sofia.utils.GeneralUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final EntityManager entityManager;
    private final PlatformTransactionManager transactionManager;

    private final TokenRepository tokenRepository;

    private final TransactionTemplate transactionTemplate;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public UserService(UserRepository userRepository,
                       UserMapper userMapper,
                       PasswordEncoder passwordEncoder,
                       JWTService jwtService,
                       MenuFieldService menuFieldService,
                       RoleRepository roleRepository,
                       AuthenticationManager authenticationManager,
                       TokenProvider tokenProvider,
                       EntityManager entityManager,
                       PlatformTransactionManager transactionManager,
                       TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.entityManager = entityManager;
        this.transactionManager = transactionManager;

        transactionTemplate = new TransactionTemplate(transactionManager);
        this.tokenRepository = tokenRepository;
    }

    @Transactional
    public ResponseEntity<?> authenticate(@NotBlank String username, @NotBlank String enteredPassword) {

        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        if (passwordEncoder.matches(enteredPassword, user.getPassword())) {
            user.setEnabled(true);
            UserDetails userDetails =
                    new LocalUser(user.getEmail(), user.getPassword(),
                            true, true, true,
                            true,
                            GeneralUtils.buildSimpleGrantedAuthorities(user.getRolesSet()), user, user.getRoles());

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, enteredPassword)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.createToken(authentication);
            String refreshToken = tokenProvider.createRefreshToken(authentication);

            revokeAllTokensOfUser(user);
            saveUserToken(user, jwt);

            LocalUser localUser = (LocalUser) authentication.getPrincipal();
            UserDTO userDTO = this.userMapper.mapUserToDtoWithMenu(localUser.getUser());
            return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, refreshToken, userDTO));
        } else {
            throw new IncorrectPasswordException();
        }
    }

    private void revokeAllTokensOfUser(User user) {
        var validTokens = tokenRepository.findAllValidTokensByUser(user.getId());
        if (validTokens.isEmpty()) {
            return;
        }
        validTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validTokens);
    }

    private void saveUserToken(User user, String jwtToken) {
        var expirationDate = jwtService.extractExpiration(jwtToken);
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType("BEARER")
                .revoked(false)
                .expired(false)
                .expirationDate(expirationDate)
                .build();
        tokenRepository.save(token);
    }

    @Transactional
    public void updateCurrentLanguage(String languageId) {
        String userId = this.jwtService.getUserId();

        this.userRepository.updateCurrentLanguage(userId, languageId);
    }

    @Transactional
    public UserDTO changePassword(ChangePasswordRequest optionalChangePasswordRequest) {

        ChangePasswordRequest changePasswordRequest = Optional.ofNullable(optionalChangePasswordRequest)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error!"));

        User currentUser = userRepository.findByUsername(changePasswordRequest.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error!"));


        if (StringUtils.isNotBlank(changePasswordRequest.getPassword()) || StringUtils.isNotBlank(changePasswordRequest.getRepeatPassword())) {
            if (!changePasswordRequest.getPassword().equals(changePasswordRequest.getRepeatPassword())) {
                throw new ChangePasswordException();
            } else {
                currentUser.setEnabled(true);
                currentUser.setPassword(passwordEncoder.encode(changePasswordRequest.getPassword()));
            }
        }

        UserDTO responseUserDTO = userMapper.mapUserToDto(currentUser);
        return responseUserDTO;
    }

    public UserDTO getCurrentUser() {

        messagingTemplate.convertAndSend("/topic/messages/10", "Hello From Backend!");

        User user = this.getLoggedInUser();
        UserDTO userDTO = this.userMapper.mapUserToDtoWithMenu(user);
        return userDTO;
    }

    public User getLoggedInUser() {
        String id = jwtService.getUserId();
        Optional<User> userOptional = this.userRepository.findById(id);
        if (!userOptional.isPresent()) {
            return null;
        }
        return userOptional.get();
    }

    public User registerNewUser(final SignUpRequest signUpRequest) {
        List<Object[]> fields = this.getUserFields();
        String oauth2UserId = this.getDefaultOauth2User();
        //    User tmpUser = this.buildUser(signUpRequest);
        String newId = this.saveUser(fields, oauth2UserId, signUpRequest);
        User user = this.userRepository.findById(newId).orElseThrow(UserNotFoundException::new);
        return user;
    }

    public List<Object[]> getUserFields() {
        Query query = this.entityManager.createNativeQuery("DESCRIBE user");
        return query.getResultList();
    }

    public String getDefaultOauth2User() {
        Query query = this.entityManager.createNativeQuery("SELECT `oauth_prototype_user_id` FROM `settings` WHERE id = '1'");
        List<String> oauth2UserResults = query.getResultList();
        return oauth2UserResults.get(0);
    }

    public String saveUser(List<Object[]> fields, String oauth2UserId, SignUpRequest signUpRequest) {

        List<String> queryFields =
                fields.stream()
                        .filter(field -> !field[0].toString().equals("id"))
                        .filter(field -> !field[0].toString().equals("created_by"))
                        .filter(field -> !field[0].toString().equals("created_on"))
                        .filter(field -> !field[0].toString().equals("modified_by"))
                        .filter(field -> !field[0].toString().equals("modified_on"))
                        .filter(field -> !field[0].toString().equals("email"))
                        .filter(field -> !field[0].toString().equals("username"))
                        .filter(field -> !field[0].toString().equals("provider"))
                        .filter(field -> !field[0].toString().equals("password"))
                        .map(field -> field[0].toString())
                        .collect(Collectors.toList());

        String fieldsString = String.join(",", queryFields);
        String newId = UUID.randomUUID().toString();

        String userQueryString =
                " INSERT INTO user (id, created_by, created_on, modified_by, modified_on, email, username, provider, password , " + fieldsString + " ) " +
                        " SELECT '" + newId + "', 'oauth2', CURRENT_TIMESTAMP, 'oauth2', CURRENT_TIMESTAMP," +
                        " '" + signUpRequest.getEmail() + "', '" + signUpRequest.getDisplayName() + "', '" + signUpRequest.getSocialProvider().getProviderType() +
                        "', '" + passwordEncoder.encode(signUpRequest.getPassword()) + "', " + fieldsString +
                        " FROM user " +
                        " WHERE id = '" + oauth2UserId + "' ";

        String roleQueryString =
                " INSERT INTO user_role (user_id, role_id) " +
                        " SELECT '" + newId + "', role_id " +
                        " FROM user_role " +
                        " WHERE user_id = '" + oauth2UserId + "'";

        String languageQueryString =
                " INSERT INTO user_language (user_id, language_id) " +
                        " SELECT '" + newId + "', language_id " +
                        " FROM user_language " +
                        " WHERE user_id = '" + oauth2UserId + "'";

        transactionTemplate.execute(transactionStatus -> {
            entityManager.createNativeQuery(userQueryString)
                    .executeUpdate();

            entityManager.createNativeQuery(roleQueryString)
                    .executeUpdate();

            entityManager.createNativeQuery(languageQueryString)
                    .executeUpdate();
            transactionStatus.flush();
            return null;
        });

        return newId;
    }

    public User findUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByProviderId(final String provider, final String providerId) {
        return userRepository.findByProviderAndProviderUserId(provider, providerId);
    }

    @Transactional
    public LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo) throws UserAlreadyExistAuthenticationException {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, attributes);
        SignUpRequest userDetails = toUserRegistrationObject(registrationId, oAuth2UserInfo);
        User user = this.findUserByUsername(oAuth2UserInfo.getName());

        if (user == null) {
            user = registerNewUser(userDetails);
        }

        return LocalUser.create(user, attributes, idToken, userInfo);
    }

    private SignUpRequest toUserRegistrationObject(String registrationId, OAuth2UserInfo oAuth2UserInfo) {
        byte[] array = new byte[10]; // length is bounded by 7
        new Random().nextBytes(array);
        String passwordString = new String(array, StandardCharsets.UTF_8);

        return SignUpRequest.getBuilder()
                .addProviderUserID(oAuth2UserInfo.getId())
                .addDisplayName(oAuth2UserInfo.getName())
                .addEmail(oAuth2UserInfo.getEmail())
                .addSocialProvider(GeneralUtils.toSocialProvider(registrationId)).addPassword(passwordString).build();
    }

    public Optional<User> findUserById(String id) {
        return userRepository.findById(id);
    }

    public User findUserByUsername(final String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            return null;
        } else {
            return optionalUser.get();
        }
    }

    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = userRepository.getAllUsers(AppConstants.Types.UserStatus.deleted);
        return users;
    }

    public ResponseEntity<?> refreshToken(HttpServletRequest request) throws Exception {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String userId;

       // System.out.println(authHeader);
        if (authHeader == null || !authHeader.startsWith("RefreshBearer ")) {
            throw new Exception();
        }

        String refreshToken = authHeader.substring(14);
        userId = jwtService.getUserIdByToken(refreshToken);
        // if (userId != null) {

        if (!jwtService.isTokenValid(refreshToken)) {
            new IllegalArgumentException("Refresh Toke Error");
        }

        var user = this.userRepository.findById(userId).orElseThrow(() -> new DoesNotExistException("User Does Not Exist"));
        String jwt = tokenProvider.createToken(user.getId());
        refreshToken = tokenProvider.createRefreshToken(user.getId());

        revokeAllTokensOfUser(user);
        saveUserToken(user, jwt);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, refreshToken, null));
    }
    //   }
    //  }
}
