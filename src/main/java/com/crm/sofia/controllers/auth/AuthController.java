package com.crm.sofia.controllers.auth;

import com.crm.sofia.dto.user.JwtAuthenticationResponse;
import com.crm.sofia.dto.user.LoginRequest;
import com.crm.sofia.dto.user.UserDTO;
import com.crm.sofia.mapper.user.UserMapper;
import com.crm.sofia.model.user.LocalUser;
import com.crm.sofia.security.jwt.TokenProvider;
import com.crm.sofia.services.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/auth")
@Tag(name = "Authenticate", description = "Authentication endpoint to gain access to DRAS.")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    TokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication);
        String refreshToken = tokenProvider.createRefreshToken(authentication);

        LocalUser localUser = (LocalUser) authentication.getPrincipal();

        UserDTO userDTO = this.userMapper.mapUserToDtoWithMenu(localUser.getUser());
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, refreshToken, userDTO));
    }

//    @PostMapping("/signup")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
//        try {
//            userService.registerNewUser(signUpRequest);
//        } catch (UserAlreadyExistAuthenticationException e) {
//            log.error("Exception Ocurred", e);
//            return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
//        }
//        return ResponseEntity.ok().body(new ApiResponse(true, "User registered successfully"));
//    }

}
