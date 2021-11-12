package com.crm.sofia.controllers.sofia.user;

import com.crm.sofia.config.CurrentUser;
import com.crm.sofia.dto.sofia.auth.JWTResponseDTO;
import com.crm.sofia.dto.sofia.auth.LoginDTO;
import com.crm.sofia.dto.sofia.user.UserDTO;
import com.crm.sofia.model.sofia.user.LocalUser;
import com.crm.sofia.services.sofia.user.UserService;
import com.crm.sofia.utils.GeneralUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public @ResponseBody
    Collection<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path="/by-id")
    public UserDTO getUser(@RequestParam("id") Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.postUser(userDTO);
    }

    @PutMapping
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
        return this.userService.putUser(userDTO);
    }

    @DeleteMapping
    public Boolean delete(@RequestParam("id") Long id) {
        return this.userService.delete(id);
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

    @GetMapping(path = "/current")
    public UserDTO getCurrentUser() {
        return userService.getCurrentUser();
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getCurrentUser(@CurrentUser LocalUser user) {
        return ResponseEntity.ok(GeneralUtils.buildUserInfo(user));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getContent() {
        return ResponseEntity.ok("Public content goes here");
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getUserContent() {
        return ResponseEntity.ok("User content goes here");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAdminContent() {
        return ResponseEntity.ok("Admin content goes here");
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<?> getModeratorContent() {
        return ResponseEntity.ok("Moderator content goes here");
    }

}
