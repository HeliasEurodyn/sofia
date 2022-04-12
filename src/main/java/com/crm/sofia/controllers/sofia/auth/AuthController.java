package com.crm.sofia.controllers.sofia.auth;

import javax.validation.Valid;

import com.crm.sofia.dto.sofia.user.*;
import com.crm.sofia.exception.UserAlreadyExistAuthenticationException;
import com.crm.sofia.mapper.sofia.user.UserMapper;
import com.crm.sofia.model.sofia.user.LocalUser;
import com.crm.sofia.security.jwt.TokenProvider;
import com.crm.sofia.services.sofia.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

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
        LocalUser localUser = (LocalUser) authentication.getPrincipal();

        UserDTO userDTO = this.userMapper.mapUserToDtoWithMenu(localUser.getUser());
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, userDTO));
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
