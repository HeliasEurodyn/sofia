package com.crm.sofia.service.menu;

import com.crm.sofia.dto.menu.MenuDTO;
import com.crm.sofia.dto.menu.MenuFieldDTO;
import com.crm.sofia.mapper.menu.MenuFieldMapper;
import com.crm.sofia.mapper.menu.MenuFieldMapperImpl;
import com.crm.sofia.mapper.menu.MenuMapper;
import com.crm.sofia.mapper.menu.MenuMapperImpl;
import com.crm.sofia.model.menu.Menu;
import com.crm.sofia.model.menu.MenuField;
import com.crm.sofia.repository.menu.MenuRepository;
import com.crm.sofia.services.auth.JWTService;
import com.crm.sofia.services.menu.MenuFieldService;
import com.crm.sofia.services.menu.MenuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;


import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class MenuServiceTest {

    @Spy
    private final MenuMapper menuMapper = new MenuMapperImpl();

    @Spy
    private MenuFieldMapper menuFieldMapper = new MenuFieldMapperImpl();


    @InjectMocks
    private MenuService menuService;

    @Mock
    private MenuFieldService menuFiledService;

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private JWTService jwtService;

    private Menu dto;

    private MenuDTO menuDTO;

    private MenuFieldDTO menuFieldDTO;

    private List<Menu> menuList;

    private List<MenuDTO> menuDTOList;

    private char data[];

    private Entity entity;

    private List<MenuField> menuFieldList;

    private List<MenuFieldDTO> menuFieldDTOList;

    private String languageId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        menuList = new ArrayList<>();
        menuDTOList = new ArrayList<>();
        menuFieldList = new ArrayList<>();
        menuFieldDTOList = new ArrayList<>();
        entity = new Entity("entityName",10,data);
        dto = new Menu().setName("dummyName").setMenuFieldList(new ArrayList<>()).setMenuFieldList(menuFieldList);
        dto.setId("2");
        menuList.add(dto);
        menuDTO = new MenuDTO().setName("dummyName").setMenuFieldList(new ArrayList<>()).setMenuFieldList(menuFieldDTOList);
        menuDTO.setId("1");
        menuDTOList.add(menuDTO);
        languageId = "1";

    }

    @Test
    public void getObjectTest() {
        given(menuRepository.findTreeById(ArgumentMatchers.anyString())).willReturn(Optional.of(dto));
        MenuDTO dto = menuService.getObject("1", "1");
        assertThat(dto).isNotNull();
        assertThat(dto.getId().equals("2"));


    }
}
