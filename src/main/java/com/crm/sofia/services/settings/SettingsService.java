package com.crm.sofia.services.settings;

import com.crm.sofia.dto.sofia.settings.SettingsDto;
import com.crm.sofia.mapper.sofia.settings.SettingsMapper;
import com.crm.sofia.model.sofia.settings.Settings;
import com.crm.sofia.repository.settings.SettingsRepository;
import com.crm.sofia.services.auth.JWTService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class SettingsService {

    private final SettingsRepository settingsRepository;
    private final SettingsMapper settingsMapper;
    private final JWTService jwtService;

    public SettingsService(SettingsRepository settingsRepository,
                           SettingsMapper settingsMapper,
                           JWTService jwtService) {
        this.settingsRepository = settingsRepository;
        this.settingsMapper = settingsMapper;
        this.jwtService = jwtService;
    }

    public String getSidebarImage() {
        String img = this.settingsRepository.getSidebarImage("1");
        return img;
    }

    public String getLoginImage() {
        String img = this.settingsRepository.getLoginImage("1");
        return img;
    }

    public String getIcon() {
        String icon = this.settingsRepository.getIcon("1");
        return icon;
    }

    public SettingsDto getObject() {
        Optional<Settings> settingsList = this.settingsRepository.findById("1");
        Settings settings;

        if(!settingsList.isPresent()){
            settings = new Settings();
        } else {
            settings = settingsList.get();
        }

        return settingsMapper.map(settings);
    }

    @Transactional
    public void postObject(SettingsDto settingsDto) {
        Settings settings = this.settingsMapper.map(settingsDto);
        settings.setId("1");
        this.settingsRepository.save(settings);
    }

    public String getName() {
        String name = this.settingsRepository.getName("1");
        return name;
    }
}
