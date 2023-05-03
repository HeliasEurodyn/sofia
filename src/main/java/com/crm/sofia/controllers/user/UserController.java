package com.crm.sofia.controllers.user;

import com.crm.sofia.config.CurrentUser;
import com.crm.sofia.dto.auth.LoginDTO;
import com.crm.sofia.dto.auth.LogoutDTO;
import com.crm.sofia.dto.user.ChangePasswordRequest;
import com.crm.sofia.dto.user.UserDTO;
import com.crm.sofia.model.user.LocalUser;
import com.crm.sofia.services.security.BlacklistingService;
import com.crm.sofia.services.user.UserService;
import com.crm.sofia.utils.GeneralUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.IconMultiStateFormatting;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    final BlacklistingService blacklistingService;

    public UserController(UserService userService, BlacklistingService blacklistingService) {
        this.userService = userService;
        this.blacklistingService = blacklistingService;
    }

    @PutMapping(value = "/current-language")
    public void updateCurrentLanguage(@RequestParam("language-id") String languageId) {
        this.userService.updateCurrentLanguage(languageId);
    }

    @PutMapping(value = "/change-password")
    public UserDTO changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        return this.userService.changePassword(changePasswordRequest);
    }

    /**
     * Authenticates a user and returns a JWT if authentication was successful.
     *
     * @param loginDTO The email and password of the user to authenticate.
     * @return Returns the JWT.
     */
    @PostMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<?> authenticate(@RequestBody LoginDTO loginDTO) {
        return userService.authenticate(loginDTO.getUsername(), loginDTO.getPassword());
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<?> logout(@RequestBody LogoutDTO logoutDTO) {
        blacklistingService.blackListJwt(logoutDTO.getJwt());
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/current")
    public UserDTO getCurrentUser() {
        return userService.getCurrentUser();
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getCurrentUser(@CurrentUser LocalUser user) {
        return ResponseEntity.ok(GeneralUtils.buildUserInfo(user));
    }

    @MessageMapping("/hello")
    public void handleMessage(String message) {
        String responseMessage = "Hello, " + message + "!";
        System.out.println(responseMessage);
        // send the response message to the /topic/greetings destination
        //  messagingTemplate.convertAndSend("/topic/greetings", responseMessage);
    }
}
