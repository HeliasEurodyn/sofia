package com.crm.sofia.controllers.security;

import com.crm.sofia.dto.sofia.security.SecurityDTO;
import com.crm.sofia.services.security.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/security")
public class SecurityController {
    @Autowired
    private SecurityService securityService;

    @GetMapping
    List<SecurityDTO> getObject() {
        return securityService.getObject();
    }

    @GetMapping(path = "/by-id")
    SecurityDTO getObject(@RequestParam("id") String id) {
        return securityService.getObject(id);
    }

    @PostMapping
    public SecurityDTO postObject(@RequestBody SecurityDTO securityDto) throws IOException {
        return securityService.postObject(securityDto);
    }

    @PutMapping
    public SecurityDTO putObject(@RequestBody SecurityDTO securityDto) {
        return securityService.postObject(securityDto);
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") String id) {
        securityService.deleteObject(id);
    }

}
