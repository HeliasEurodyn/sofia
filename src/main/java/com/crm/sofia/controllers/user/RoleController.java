package com.crm.sofia.controllers.user;

import com.crm.sofia.dto.sofia.user.RoleDTO;
import com.crm.sofia.services.user.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    List<RoleDTO> getObject() {
        return roleService.getObject();
    }

    @GetMapping(path = "/by-id")
    RoleDTO getObject(@RequestParam("id") String id) {
        return roleService.getObject(id);
    }

    @PostMapping
    public RoleDTO postObject(@RequestBody RoleDTO dto) throws IOException {
        return roleService.postObject(dto);
    }

    @PutMapping
    public RoleDTO putObject(@RequestBody RoleDTO dto) {
        return roleService.postObject(dto);
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") String id) {
        roleService.deleteObject(id);
    }

}
