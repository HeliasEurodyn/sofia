package com.crm.sofia.service.menu;

import com.crm.sofia.dto.menu.MenuDTO;
import com.crm.sofia.mapper.menu.MenuMapper;
import com.crm.sofia.model.menu.Menu;
import com.crm.sofia.repository.menu.MenuRepository;
import com.crm.sofia.services.auth.JWTService;
import com.crm.sofia.services.menu.MenuFieldService;
import com.crm.sofia.services.menu.MenuService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class MenuServiceTest1 {

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private MenuMapper menuMapper;

    @Mock
    private MenuFieldService menuFieldService;

    @Mock
    private JWTService jwtService;

    @InjectMocks
    private MenuService menuService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetObject_existingIdAndLanguageId_returnsMenuDTO() {
        // Arrange
        String id = "1";
        String languageId = "en";
        Menu menu = new Menu(); // Create a Menu object with test data
        MenuDTO expectedDTO = new MenuDTO().setMenuFieldList(new ArrayList<>()); // Create an expected MenuDTO object with test data


        Mockito.when(menuRepository.findTreeById(id)).thenReturn(Optional.of(menu));
        Mockito.when(menuMapper.mapMenu(menu, languageId)).thenReturn(expectedDTO);
        Mockito.when(menuFieldService.getObjectTree(Mockito.anyString())).thenReturn(Collections.emptyList());

        // Act
        MenuDTO actualDTO = menuService.getObject(id, languageId);

        // Assert
        Assertions.assertEquals(expectedDTO, actualDTO);
    }

    @Test
    public void testGetObject_nonExistingIdAndLanguageId_throwsResponseStatusException() {
        // Arrange
        String id = "1";
        String languageId = "en";

        Mockito.when(menuRepository.findTreeById(id)).thenReturn(Optional.empty());

        // Act and Assert
        Assertions.assertThrows(ResponseStatusException.class, () -> menuService.getObject(id, languageId));
    }
}


