package com.crm.sofia.controllers.settings;

import com.crm.sofia.dto.settings.SettingsDto;
import com.crm.sofia.services.settings.SettingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Validated
@RequestMapping("/settings")
public class SettingsController {

    private final SettingsService settingsService;

    public SettingsController(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @GetMapping
    public SettingsDto getObjectById() {
        return this.settingsService.getObject();
    }

    @GetMapping(value = "login-image")
    public String getLoginImage() {
        return this.settingsService.getLoginImage();
    }

    @GetMapping(value = "sidebar-image")
    public String getSidebarImage() {
        return this.settingsService.getSidebarImage();
    }

    @GetMapping(value = "icon")
    public String getIcon() {
        return this.settingsService.getIcon();
    }

    @GetMapping(value = "name")
    public String getName() {
        return this.settingsService.getName();
    }

}
