package com.crm.sofia.controllers.menu;

import com.crm.sofia.dto.menu.MenuDTO;
import com.crm.sofia.services.menu.MenuService;
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
    MenuDTO getObject(@RequestParam("id") Long id) {
        return this.menuService.getObject(id);
    }

    @PostMapping
    public MenuDTO postObject(@RequestBody MenuDTO dto) {
        MenuDTO createdDTO = this.menuService.postObject(dto);
        return createdDTO;
    }


    @PutMapping
    public MenuDTO putObject(@RequestBody MenuDTO dto) {
        MenuDTO createdDTO = this.menuService.putObject(dto);
        //List<CustomComponentFieldDTO> fields = this.componentService.putNewObjectFields(componentDTO);
        return createdDTO;
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") Long id) {
          this.menuService.deleteObject(id);
    }


}
