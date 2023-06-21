package com.crm.sofia.service.html_template;

import com.crm.sofia.dto.component.designer.ComponentDTO;
import com.crm.sofia.dto.html_template.HtmlTemplateDTO;
import com.crm.sofia.mapper.html_template.HtmlTemplateMapper;
import com.crm.sofia.mapper.html_template.HtmlTemplateMapperImpl;
import com.crm.sofia.model.component.Component;
import com.crm.sofia.model.html_template.HtmlTemplate;
import com.crm.sofia.repository.html_template.HtmlTemplateRepository;
import com.crm.sofia.services.auth.JWTService;
import com.crm.sofia.services.html_template.HtmlTemplateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class HtmlTemplateServiceTest {
    @Mock
    private HtmlTemplateRepository htmlTemplateRepository;

    @InjectMocks
    private HtmlTemplateService htmlTemplateService;

    @Mock
    private JWTService jwtService;

    private List<HtmlTemplate> htmlTemplateList;

    private HtmlTemplate htmlTemplate;

    private HtmlTemplateDTO htmlTemplateDTO;

    private List<HtmlTemplateDTO> htmlTemplateDTOList;


    @Spy
    private final HtmlTemplateMapper htmlTemplateMapper = new HtmlTemplateMapperImpl();


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        htmlTemplateList = new ArrayList<>();
        htmlTemplateDTOList = new ArrayList<>();
        htmlTemplate = new HtmlTemplate().setTitle("dummyTitle").setHtml("dummyHtml").setComponent(new Component());
        htmlTemplateList.add(htmlTemplate);
        htmlTemplateDTO = new HtmlTemplateDTO().setTitle("dummyTitleDTO").setHtml(Base64.getEncoder().encodeToString("dummyHtmlDTO".getBytes(StandardCharsets.UTF_8)));
        htmlTemplateDTO.setId("1");
        htmlTemplateDTOList.add(htmlTemplateDTO);

    }

    @Test
    public void getObjectByIdTest() {
        given(htmlTemplateRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.of(htmlTemplate));
        HtmlTemplateDTO dto = htmlTemplateService.getObject("1");
        assertThat(htmlTemplateDTO).isNotNull();
        assertThat(htmlTemplateDTO.getId().equals("1"));
    }
}
