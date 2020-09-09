package com.crm.sofia.services.user;

import com.crm.sofia.config.AppConstants;
import com.crm.sofia.dto.auth.JWTResponseDTO;
import com.crm.sofia.dto.user.UserDTO;
import com.crm.sofia.mapper.user.UserMapper;
import com.crm.sofia.model.user.User;
import com.crm.sofia.repository.user.UserRepository;
import com.crm.sofia.services.auth.JWTService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public UserService(UserRepository userRepository,
                       UserMapper userMapper,
                       PasswordEncoder passwordEncoder,
                       JWTService jwtService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
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

        user.setCreatedBy(this.getLoggedInUser().getUsername());
        user.setCreatedOn(Instant.now());
        user.setModifiedBy(this.getLoggedInUser().getUsername());
        user.setModifiedOn(Instant.now());

        User createdUser = userRepository.save(user);

        UserDTO responseUserDTO = userMapper.map(createdUser);
        responseUserDTO.setPassword("");

        return responseUserDTO;

    }

    public UserDTO putUser(UserDTO userDTO) {
//        if (userDTO.getPassword().equals("") || !userDTO.getPassword().equals(userDTO.getRepeatPassword())) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Password error!!");
//        }
        User user = userMapper.map(userDTO);

        if (userDTO.getPassword() == null && userDTO.getRepeatPassword() == null) {
            String password = userRepository.findPasswordById(user.getId());
            user.setPassword(password);
        } else if (userDTO.getPassword().equals("") && userDTO.getRepeatPassword().equals("")) {
            String password = userRepository.findPasswordById(user.getId());
            user.setPassword(password);
        } else if (!userDTO.getPassword().equals(userDTO.getRepeatPassword())) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Password error!!");
        } else {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        user.setCreatedBy("");
        user.setCreatedOn(Instant.now());
        user.setModifiedBy("");
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

        String jwt = "";
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            log.debug("User exists");
            User user = userOptional.get();

            if (verifyPassword(user, enteredPassword)) {
                jwt = jwtService.generateJwt(user.getUsername(), userOptional.get().getId());
            }
        }
        return new JWTResponseDTO().setJwt(jwt);
    }

    private boolean verifyPassword(User user, String enteredPassword) {
        return passwordEncoder.matches(enteredPassword, user.getPassword());
    }

    public UserDTO getCurrentUser() {
        User user = getLoggedInUser();
        return this.userMapper.map(user);
    }

    public User getLoggedInUser() {

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return null;
        }

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        Optional<User> userOptional = this.userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            return null;
        }

        return userOptional.get();
    }

//    public Boolean updatePassword(@NotBlank String oldPassword, @NotBlank String newPassword) {
//        User user = this.getLoggedInUser();
//
//        if (verifyPassword(user, oldPassword)) {
//            user.setPassword(passwordEncoder.encode(newPassword));
//            userRepository.save(user);
//            return true;
//        }
//
//        return false;
//    }
}
