package com.crm.sofia.controllers.menu;

import com.crm.sofia.dto.sofia.menu.MenuDTO;
import com.crm.sofia.services.sofia.menu.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuComponentService) {
        this.menuService = menuComponentService;
    }

    @GetMapping
    List<MenuDTO> getObject() {
        return this.menuService.getObject();
    }

    @GetMapping(path="/by-id")
    MenuDTO getObject(@RequestParam("id") String id,
                      @RequestParam(defaultValue = "0", name = "language-id") String languageId) {
        return this.menuService.getObject(id, languageId);
    }

    @PostMapping
    public MenuDTO postObject(@RequestBody MenuDTO dto) {
        MenuDTO createdDTO = this.menuService.postObject(dto);
        return createdDTO;
    }

    @PutMapping
    public MenuDTO putObject(@RequestBody MenuDTO dto) {
        MenuDTO createdDTO = this.menuService.putObject(dto);
        return createdDTO;
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") String id) {
          this.menuService.deleteObject(id);
    }


}
