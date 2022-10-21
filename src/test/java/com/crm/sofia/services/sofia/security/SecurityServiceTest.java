package com.crm.sofia.services.sofia.security;

import com.crm.sofia.dto.sofia.security.SecurityDTO;
import com.crm.sofia.mapper.security.AccessControlMapper;
import com.crm.sofia.model.sofia.security.Security;
import com.crm.sofia.repository.security.SecurityRepository;
import com.crm.sofia.services.auth.JWTService;
import com.crm.sofia.services.security.SecurityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)

public class SecurityServiceTest {
    @Mock
    private SecurityRepository securityRepository;
    @InjectMocks
    private SecurityService securityService;

    @Mock
    private JWTService jwtService;
    private List<Security> securityList;

    private Security security;
    private SecurityDTO securityDto;

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private AccessControlMapper accessControlMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        securityList = new ArrayList<>();
        security = new Security();
        securityDto = new SecurityDTO();
        security.setCreate(Boolean.TRUE);
        security.setDelete(Boolean.TRUE);
        securityList.add(security);
        securityDto.setUpdate(Boolean.TRUE);
        securityDto.setRead(Boolean.TRUE);
    }


    @Test
    public void getObjectTest() {
        given(securityRepository.findAll()).willReturn(securityList);
        List<SecurityDTO> list = securityService.getObject();
        assertThat(list).isNotNull();
    }

    @Test
    public void getObjectByIdTest() {
        given(securityRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.of(security));
        SecurityDTO dto = securityService.getObject("6L");
    }

    @Test
    public void getObjectByIdWhenEmptyTest() {
        given(securityRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.empty());
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            securityService.getObject("6L");
        });
        String expectedMessage = "500 INTERNAL_SERVER_ERROR \"Object does not exist\"";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage,expectedMessage);
    }

    @Test
    public void postObjectTest(){
        given(securityRepository.save(ArgumentMatchers.any(Security.class))).willReturn(security);
        given(accessControlMapper.map(ArgumentMatchers.any(SecurityDTO.class))).willReturn(security);
        securityService.postObject(securityDto);
    }

    @Test
    public void getDeleteByIdTest(){
        given(securityRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.of(security));
        securityService.deleteObject("6L");

    }

    @Test
    public void getDeleteByIdWhenEmptyTest(){
        given(securityRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.empty());
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            securityService.deleteObject("6L");
        });
        String expectedMessage = "500 INTERNAL_SERVER_ERROR \"Object does not exist\"";
        String actualMessage = exception.getMessage();
        assertEquals(actualMessage,expectedMessage);
    }


}
