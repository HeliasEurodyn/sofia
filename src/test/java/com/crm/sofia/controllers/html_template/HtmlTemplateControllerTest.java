package com.crm.sofia.controllers.html_template;

import com.crm.sofia.dto.html_template.HtmlTemplateDTO;
import com.crm.sofia.services.html_template.HtmlTemplateService;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
public class HtmlTemplateControllerTest {
    private MockMvc mvc;

    private HtmlTemplateDTO dto;

    @Mock
    private HtmlTemplateService htmlTemplateService;

    private List<HtmlTemplateDTO> htmlTemplateDTOList;

    @InjectMocks
    private HtmlTemplateController htmlTemplateController;

    @BeforeEach
    void setup() {

        this.htmlTemplateDTOList = new ArrayList<>();
        dto = new HtmlTemplateDTO().setTitle("dummyTitleDTO").setHtml("dummyHtmlDTO");
        this.htmlTemplateDTOList.add(dto);

        mvc = MockMvcBuilders.standaloneSetup(htmlTemplateController).build();
    }

    @Test
    void getByIdTest() throws Exception {
        given(htmlTemplateService.getObject("0")).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(get("/html-template/by-id?id=0").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.title"), "dummyTitleDTO");
    }
}
