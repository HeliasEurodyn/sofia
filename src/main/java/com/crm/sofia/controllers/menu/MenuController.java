package com.crm.sofia.controllers.menu;

import com.crm.sofia.dto.component.CustomComponentDTO;
import com.crm.sofia.dto.component.CustomComponentFieldDTO;
import com.crm.sofia.dto.menu.MenuComponentDTO;
import com.crm.sofia.services.menu.MenuComponentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public MenuComponentDTO postObject(@RequestBody MenuComponentDTO dto) {
        MenuComponentDTO createdDTO = this.menuComponentService.postObject(dto);
        return createdDTO;
    }


    @PutMapping
    public MenuComponentDTO putObject(@RequestBody MenuComponentDTO dto) {
        MenuComponentDTO createdDTO = this.menuComponentService.putObject(dto);
        //List<CustomComponentFieldDTO> fields = this.componentService.putNewObjectFields(componentDTO);
        return createdDTO;
    }

    @DeleteMapping
    public void deleteObject(@RequestParam("id") Long id) {
          this.menuComponentService.deleteObject(id);
    }


}
