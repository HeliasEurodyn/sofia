package com.crm.sofia.controllers.sofia.user;
import com.crm.sofia.controllers.user.UserGroupController;
import com.crm.sofia.dto.user.UserGroupDTO;
import com.crm.sofia.filters.JWTAuthFilter;
import com.crm.sofia.services.user.UserGroupService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@ExtendWith(MockitoExtension.class)
public class UserGroupControllerTest {

    private MockMvc mvc;
    private UserGroupDTO dto;

    @InjectMocks
    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private UserGroupService userGroupService;

    private List<UserGroupDTO> userGroupDTOList;

    @Mock
    private JWTAuthFilter filter;

    @InjectMocks
    private UserGroupController userGroupController;

    @BeforeEach
    void setUp() {
        this.userGroupDTOList = new ArrayList<>();
        dto = new UserGroupDTO();
        dto.setName("Admin");
        this.userGroupDTOList.add(dto);

        mvc = MockMvcBuilders.standaloneSetup(userGroupController)
                .build();
    }

    @Test
    void getObjectTest() throws Exception {
        given(userGroupService.getObject()).willReturn(userGroupDTOList);
        MockHttpServletResponse response = mvc.perform(get("/usergroup")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$[0].name")  ,"Admin");
    }

    @Test
    void getUserGroupByIdTest() throws Exception {
        given(userGroupService.getObject(any())).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(get("/usergroup/by-id?id=0")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.name")  ,"Admin");
    }

    @Test
    void postObjectTest() throws Exception {
        given(userGroupService.postObject(any())).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(post("/usergroup")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(dto))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.name")  ,"Admin");
    }

    @Test
    void putObjectTest() throws Exception {
        given(userGroupService.postObject(any())).willReturn(dto);
        MockHttpServletResponse response = mvc.perform(put("/usergroup")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(dto))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(JsonPath.parse(response.getContentAsString()).read("$.name")  ,"Admin");
    }

    @Test
    void deleteObjectTest() throws Exception {
        doNothing().when(userGroupService).deleteObject(any());
        MockHttpServletResponse response = mvc.perform(delete("/usergroup?id=0")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(dto))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
    }






}
