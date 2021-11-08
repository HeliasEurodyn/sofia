package com.crm.sofia.services.sofia.user;

import com.crm.sofia.config.AppConstants;
import com.crm.sofia.dto.sofia.auth.JWTResponseDTO;
import com.crm.sofia.dto.sofia.user.SignUpRequest;
import com.crm.sofia.dto.sofia.user.SocialProvider;
import com.crm.sofia.dto.sofia.user.UserDTO;
import com.crm.sofia.exception.OAuth2AuthenticationProcessingException;
import com.crm.sofia.exception.UserAlreadyExistAuthenticationException;
import com.crm.sofia.mapper.sofia.user.UserMapper;
import com.crm.sofia.model.sofia.menu.Menu;
import com.crm.sofia.model.sofia.user.LocalUser;
import com.crm.sofia.model.sofia.user.Role;
import com.crm.sofia.model.sofia.user.User;
import com.crm.sofia.repository.sofia.user.RoleRepository;
import com.crm.sofia.repository.sofia.user.UserRepository;
import com.crm.sofia.security.oauth2.user.OAuth2UserInfo;
import com.crm.sofia.security.oauth2.user.OAuth2UserInfoFactory;
import com.crm.sofia.services.sofia.auth.JWTService;
import com.crm.sofia.services.sofia.menu.MenuFieldService;
import com.crm.sofia.utils.GeneralUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final MenuFieldService menuFieldService;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository,
                       UserMapper userMapper,
                       PasswordEncoder passwordEncoder,
                       JWTService jwtService,
                       MenuFieldService menuFieldService,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.menuFieldService = menuFieldService;
        this.roleRepository = roleRepository;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAllByStatusIsNotLike(AppConstants.Types.UserStatus.deleted);
        return userMapper.mapUsersToDtos(users);
    }

    public UserDTO getUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userMapper.mapUserToDto(userOptional.get());
        } else {
            return null;
        }
    }

    public UserDTO postUser(UserDTO userDTO) {

        if (userDTO.getPassword().equals("") || !userDTO.getPassword().equals(userDTO.getRepeatPassword())) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Password error!!");
        }

        User user = userMapper.map(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        boolean existingUser = userRepository.userExists(userDTO.getUsername());
        if (existingUser) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User already exists!!");
        }

        user.setCreatedBy(this.jwtService.getUserId());
        user.setCreatedOn(Instant.now());
        user.setModifiedBy(this.jwtService.getUserId());
        user.setModifiedOn(Instant.now());
        User createdUser = userRepository.save(user);
        UserDTO responseUserDTO = userMapper.map(createdUser);
        responseUserDTO.setPassword("");

        return responseUserDTO;

    }

    public UserDTO putUser(UserDTO userDTO) {

        User user = userMapper.map(userDTO);

        if ((userDTO.getPassword()==null?"":userDTO.getPassword()).equals("") && (userDTO.getRepeatPassword()==null?"":userDTO.getRepeatPassword()).equals("")) {
            String password = userRepository.findPasswordById(user.getId());
            user.setPassword(password);
        } else if (!userDTO.getPassword().equals(userDTO.getRepeatPassword())) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Password error!!");
        } else {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        user.setCreatedBy(this.jwtService.getUserId());
        user.setCreatedOn(Instant.now());
        user.setModifiedBy(this.jwtService.getUserId());
        user.setModifiedOn(Instant.now());

        User createdUser = userRepository.save(user);

        UserDTO responseUserDTO = userMapper.map(createdUser);
        responseUserDTO.setPassword("");

        return responseUserDTO;

    }

    public Boolean delete(Long id) {
        userRepository.deleteById(id);
        return true;
    }

    /**
     * Attempts to authenticate a user.
     *
     * @param username        The email to authenticate with.
     * @param enteredPassword The password to authenticate with.
     * @return Returns a JWT if authentication was successful, or null otherwise.
     */
    @Transactional
    public JWTResponseDTO authenticate(@NotBlank String username, @NotBlank String enteredPassword) {
//
//        String jwt = "";
//        Optional<User> userOptional = userRepository.findByUsername(username);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            if (verifyPassword(user, enteredPassword)) {
//                jwt = jwtService.generateJwt(user.getUsername(), userOptional.get().getId());
//                UserDTO userDTO = userMapper.mapUserToDtoWithMenu(user);
//
//                return new JWTResponseDTO(jwt, userDTO);
//            }
//        }
//
//        return new JWTResponseDTO().setJwt(jwt);
        return null;
    }

    private boolean verifyPassword(User user, String enteredPassword) {
        return passwordEncoder.matches(enteredPassword, user.getPassword());
    }

    public UserDTO getCurrentUser() {
        User user = this.getLoggedInUser();
        UserDTO userDTO = this.userMapper.mapUserToDtoWithMenu(user);
        return userDTO;
    }

    public User getLoggedInUser() {
        Long id = jwtService.getUserId();
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
        roles.add(roleRepository.findByName(Role.ROLE_USER));
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
//        if (StringUtils.isEmpty(oAuth2UserInfo.getName())) {
//            throw new OAuth2AuthenticationProcessingException("Name not found from OAuth2 provider");
//        } else if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
//            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
//        }
        SignUpRequest userDetails = toUserRegistrationObject(registrationId, oAuth2UserInfo);
        User user = findUserByProviderId(oAuth2UserInfo.getProvider(), oAuth2UserInfo.getId());
        if (user != null) {
            if (!user.getProvider().equals(registrationId) && !user.getProvider().equals(SocialProvider.LOCAL.getProviderType())) {
                throw new OAuth2AuthenticationProcessingException(
                        "Looks like you're signed up with " + user.getProvider() + " account. Please use your " + user.getProvider() + " account to login.");
            }
          //  user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(userDetails);
        }

        return LocalUser.create(user, attributes, idToken, userInfo);
    }

    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setUsername(oAuth2UserInfo.getName());
        return userRepository.save(existingUser);
    }

    private SignUpRequest toUserRegistrationObject(String registrationId, OAuth2UserInfo oAuth2UserInfo) {
        return SignUpRequest.getBuilder()
                .addProviderUserID(oAuth2UserInfo.getId())
                .addDisplayName(oAuth2UserInfo.getName())
                .addEmail(oAuth2UserInfo.getEmail())
                .addSocialProvider(GeneralUtils.toSocialProvider(registrationId)).addPassword("changeit").build();
    }


    public Optional<User> findUserById(Long id) {
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
