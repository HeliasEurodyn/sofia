package com.crm.sofia.service.xls_import;

import com.crm.sofia.dto.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.xls_import.XlsImportDTO;
import com.crm.sofia.mapper.xls_import.XlsImportMapper;
import com.crm.sofia.mapper.xls_import.XlsImportMapperImpl;
import com.crm.sofia.model.component.Component;
import com.crm.sofia.model.xls_import.XlsImport;
import com.crm.sofia.repository.xls_import.XlsImportRepository;
import com.crm.sofia.services.component.ComponentPersistEntityFieldAssignmentService;
import com.crm.sofia.services.xls_import.XlsImportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class XlsImportServiceTest {

    @Spy
    private final XlsImportMapper xlsImportMapper = new XlsImportMapperImpl();

    @InjectMocks
    private XlsImportService xlsImportService;
    @Mock
    private ComponentPersistEntityFieldAssignmentService componentPersistEntityFieldAssignmentService;

    @Mock
    private XlsImportRepository xlsImportRepository;

    private List<ComponentPersistEntityDTO> componentPersistEntityList;

    private XlsImport dto;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        componentPersistEntityList = new ArrayList<>();
        dto = new XlsImport().setName("dummyName").setComponent(new Component());
        dto.setId("1");

    }


    @Test
    public void getObjectTest() {
        given(xlsImportRepository.findById(any())).willReturn(Optional.of(dto));
        given(componentPersistEntityFieldAssignmentService.retrieveFieldAssignments(any(), anyString(), anyString())).willReturn(componentPersistEntityList);
        XlsImportDTO dto = xlsImportService.getObject("id");
        assertThat(dto).isNotNull();
        assertThat(dto.getId().equals("1"));
        assertThat(dto.getName().equals("dummyName"));

    }
}
