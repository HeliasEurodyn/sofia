package com.crm.sofia.controllers.sofia.user;
import com.crm.sofia.dto.sofia.user.UserGroupDTO;
import com.crm.sofia.services.sofia.user.UserGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/usergroup")
public class UserGroupController {
    @Autowired
    private UserGroupService userGroupService;

    @GetMapping
    List<UserGroupDTO> getObject() {
        return userGroupService.getObject();
    }

    @GetMapping(path = "/by-id")
    UserGroupDTO getObject(@RequestParam("id") Long id) {

        return userGroupService.getObject(id);
    }

    @PostMapping
    public UserGroupDTO postObject(@RequestBody UserGroupDTO userGroupDto) throws IOException {
        return userGroupService.postObject(userGroupDto);
    }

    @PutMapping
    public UserGroupDTO putObject(@RequestBody UserGroupDTO userGroupDto) {
        return userGroupService.postObject(userGroupDto);
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") Long id) {
        userGroupService.deleteObject(id);
    }



}
