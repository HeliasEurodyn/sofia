package com.crm.sofia.controllers.menu;

import com.crm.sofia.dto.menu.MenuDTO;
import com.crm.sofia.services.menu.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Validated
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuComponentService) {
        this.menuService = menuComponentService;
    }

    @GetMapping(path="/by-id")
    MenuDTO getObject(@RequestParam("id") String id,
                      @RequestParam(defaultValue = "0", name = "language-id") String languageId) {
        return this.menuService.getObject(id, languageId);
    }

}
