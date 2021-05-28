package com.crm.sofia.controllers.user;

import com.crm.sofia.dto.auth.JWTResponseDTO;
import com.crm.sofia.dto.auth.LoginDTO;
import com.crm.sofia.dto.user.UserDTO;
import com.crm.sofia.services.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collection;
import java.util.List;

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
    public JWTResponseDTO authenticate(@RequestBody LoginDTO loginDTO) {
        return userService.authenticate(loginDTO.getUsername(), loginDTO.getPassword());
    }

    @GetMapping(path = "/current")
    public UserDTO getCurrentUser() {
        return userService.getCurrentUser();
    }

}
