package com.crm.sofia.services.business_unit;

import com.crm.sofia.dto.business_unit.BusinessUnitDTO;
import com.crm.sofia.mapper.business_unit.BusinessUnitMapper;
import com.crm.sofia.model.business_unit.BusinessUnit;
import com.crm.sofia.repository.business_unit.BusinessUnitRepository;
import com.crm.sofia.services.auth.JWTService;
import com.crm.sofia.services.business_unit.BusinessUnitDesignerService;
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
public class BusinessUnitDesignerServiceTest {

    @Mock
    private BusinessUnitRepository businessUnitRepository;

    @InjectMocks
    private BusinessUnitDesignerService businessUnitDesignerService;

    @Mock
    private JWTService jwtService;

    private List<BusinessUnit> businessUnitList;

    private BusinessUnit businessUnit;

    private BusinessUnitDTO businessUnitDTO;

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private BusinessUnitMapper businessUnitMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        businessUnitList = new ArrayList<>();
        businessUnit = new BusinessUnit().setTitle("dummyTitle").setDescription("dummyDescription");
        businessUnitList.add(businessUnit);
        businessUnitDTO = new BusinessUnitDTO().setTitle("dummyTitleDTO").setDescription("dummyDescriptionDTO");
    }

    @Test
    public void getObjectTest() {
        given(businessUnitRepository.findAll()).willReturn(businessUnitList);
        List<BusinessUnitDTO> list = businessUnitDesignerService.getObject();
        assertThat(list).isNotEmpty();
    }


    @Test
    public void getObjectByIdTest(){
        given(businessUnitRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.of(businessUnit));
        given(businessUnitMapper.map(ArgumentMatchers.any(BusinessUnit.class))).willReturn(businessUnitDTO);
        BusinessUnitDTO dto = businessUnitDesignerService.getObject("1");
    }

    @Test
    public void getObjectByIdWhenEmptyTest(){
        given(businessUnitRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.empty());
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            businessUnitDesignerService.getObject("1");
        });

        String expectedMessage = "500 INTERNAL_SERVER_ERROR \"Object does not exist\"";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage,expectedMessage);
    }

    @Test
    public void postObjectTest(){
        given(businessUnitMapper.map(ArgumentMatchers.any(BusinessUnitDTO.class))).willReturn(businessUnit);
        given(businessUnitRepository.save(ArgumentMatchers.any(BusinessUnit.class))).willReturn(businessUnit);
        businessUnitDesignerService.postObject(businessUnitDTO);
    }

    @Test
    public void getDeleteByIdTest(){
        given(businessUnitRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.of(businessUnit));
        businessUnitDesignerService.deleteObject("1");

    }

    @Test
    public void getDeleteByIdWhenEmptyTest(){
        given(businessUnitRepository.findById(ArgumentMatchers.anyString())).willReturn(Optional.empty());
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            businessUnitDesignerService.deleteObject("1");
        });
        String expectedMessage = "500 INTERNAL_SERVER_ERROR \"Object does not exist\"";
        String actualMessage = exception.getMessage();
        assertEquals(actualMessage,expectedMessage);
    }

}
