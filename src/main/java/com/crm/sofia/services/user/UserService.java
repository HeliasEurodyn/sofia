package com.crm.sofia.services.user;

import com.crm.sofia.dto.user.*;
import com.crm.sofia.exception.OAuth2AuthenticationProcessingException;
import com.crm.sofia.exception.UserAlreadyExistAuthenticationException;
import com.crm.sofia.mapper.user.UserMapper;
import com.crm.sofia.model.user.LocalUser;
import com.crm.sofia.model.user.Role;
import com.crm.sofia.model.user.User;
import com.crm.sofia.repository.user.RoleRepository;
import com.crm.sofia.repository.user.UserRepository;
import com.crm.sofia.security.jwt.TokenProvider;
import com.crm.sofia.security.oauth2.user.OAuth2UserInfo;
import com.crm.sofia.security.oauth2.user.OAuth2UserInfoFactory;
import com.crm.sofia.services.auth.JWTService;
import com.crm.sofia.services.menu.MenuFieldService;
import com.crm.sofia.utils.GeneralUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import java.nio.charset.Charset;
import java.time.Instant;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

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

    public UserService(UserRepository userRepository,
                       UserMapper userMapper,
                       PasswordEncoder passwordEncoder,
                       JWTService jwtService,
                       MenuFieldService menuFieldService,
                       RoleRepository roleRepository,
                       AuthenticationManager authenticationManager,
                       TokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @Transactional
    public ResponseEntity<?> authenticate(@NotBlank String username, @NotBlank String enteredPassword) {

        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(enteredPassword, user.getPassword())) {
                user.setEnabled(true);
                UserDetails userDetails =
                        new LocalUser(user.getEmail(), user.getPassword(),
                                true, true, true,
                                true,
                                GeneralUtils.buildSimpleGrantedAuthorities(user.getRolesSet()), user, user.getRoles() );


                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(username, enteredPassword)
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = tokenProvider.createToken(authentication);
                LocalUser localUser = (LocalUser) authentication.getPrincipal();

                UserDTO userDTO = this.userMapper.mapUserToDtoWithMenu(localUser.getUser());
                return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, userDTO));
            }
        }

        return new ResponseEntity<>(new ApiResponse(false, "Login error!"), HttpStatus.BAD_REQUEST);
    }

    @Transactional
    public void updateCurrentLanguage(Long languageId) {
        String userId = this.jwtService.getUserId();

       this.userRepository.updateCurrentLanguage(userId, languageId);
    }

    public UserDTO getCurrentUser() {
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

    @Transactional
    public User registerNewUser(final SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException {
        User user = buildUser(signUpRequest);

        user.setCreatedOn(Instant.now());
        user.setModifiedOn(Instant.now());
        user.setEnabled(true);
        user = userRepository.save(user);
        userRepository.initiateUserInfo(user.getId());
        return user;
    }

    private User buildUser(final SignUpRequest formDTO) {
        User user = new User();
        user.setUsername(formDTO.getDisplayName());
        user.setEmail(formDTO.getEmail());
        user.setPassword(passwordEncoder.encode(formDTO.getPassword()));
        final HashSet<Role> roles = new HashSet<Role>();
      //  roles.add(roleRepository.findFirstByName(Role.ROLE_USER));
        user.setRolesSet(roles);
        user.setProvider(formDTO.getSocialProvider().getProviderType());

        user.setEnabled(true);
        user.setProviderUserId(formDTO.getProviderUserId());
        return user;
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
        User user = findUserByProviderId(oAuth2UserInfo.getProvider(), oAuth2UserInfo.getId());
        if (user != null) {
            if (!user.getProvider().equals(registrationId) && !user.getProvider().equals(SocialProvider.LOCAL.getProviderType())) {
                throw new OAuth2AuthenticationProcessingException(
                        "Looks like you're signed up with " + user.getProvider() + " account. Please use your " + user.getProvider() + " account to login.");
            }
        } else {
            user = registerNewUser(userDetails);
        }
        return LocalUser.create(user, attributes, idToken, userInfo);
    }

    private SignUpRequest toUserRegistrationObject(String registrationId, OAuth2UserInfo oAuth2UserInfo) {
        byte[] array = new byte[10]; // length is bounded by 7
        new Random().nextBytes(array);
        String passwordString = new String(array, Charset.forName("UTF-8"));

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
        if(optionalUser.isEmpty()){
            return null;
        } else {
            return optionalUser.get();
        }
    }

}
