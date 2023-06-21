package com.crm.sofia.service.component;

import com.crm.sofia.dto.component.designer.ComponentDTO;
import com.crm.sofia.mapper.component.ComponentMapper;
import com.crm.sofia.mapper.component.ComponentMapperImpl;
import com.crm.sofia.model.component.Component;
import com.crm.sofia.repository.component.ComponentRepository;
import com.crm.sofia.services.auth.JWTService;
import com.crm.sofia.services.component.ComponentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ComponentServiceTest {

    @Spy
    private final ComponentMapper componentMapper = new ComponentMapperImpl();

    @Mock
    private ComponentRepository componentRepository;

    @InjectMocks
    private ComponentService componentService;

    @Mock
    private JWTService jwtService;

    private Component component;

    private ComponentDTO componentDTO;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        component = new Component().setName("dummyName").setDescription("dummyDescription");
        component.setId("1");
        componentDTO = new ComponentDTO().setName("dummyNameDTO").setDescription(Base64.getEncoder().encodeToString("dummyDescriptionDTO".getBytes(StandardCharsets.UTF_8)));
        componentDTO.setId("1");

    }

    @Test
    public void getObjectByIdTest() {
        given(componentRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.of(component));
        ComponentDTO dto = componentService.getObject("id");

        assertThat(dto).isNotNull();
        assertThat(dto.getId().equals("1"));
        assertThat(dto.getName().equals("dummyName"));
        assertThat(dto.getDescription().equals("dummyDescription"));
    }
}
