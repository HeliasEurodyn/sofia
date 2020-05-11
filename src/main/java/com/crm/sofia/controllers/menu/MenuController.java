package com.crm.sofia.controllers.menu;

import com.crm.sofia.dto.component.CustomComponentDTO;
import com.crm.sofia.dto.menu.MenuComponentDTO;
import com.crm.sofia.services.menu.MenuComponentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/menu")
public class MenuController {

    private final MenuComponentService menuComponentService;

    public MenuController(MenuComponentService menuComponentService) {
        this.menuComponentService = menuComponentService;
    }

    @GetMapping
    List<MenuComponentDTO> getObject() {
        return this.menuComponentService.getObject();
    }

    @GetMapping(path="/by-id")
    MenuComponentDTO getObject(@RequestParam("id") Long id) {
        return this.menuComponentService.getObject(id);
    }


}
