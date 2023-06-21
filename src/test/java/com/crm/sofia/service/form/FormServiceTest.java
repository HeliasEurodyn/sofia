package com.crm.sofia.service.form;

import com.crm.sofia.dto.component.designer.ComponentDTO;
import com.crm.sofia.dto.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.form.base.FormDTO;
import com.crm.sofia.mapper.component.ComponentPersistEntityFieldAssignmentMapper;
import com.crm.sofia.mapper.component.ComponentPersistEntityFieldAssignmentMapperImpl;
import com.crm.sofia.mapper.form.designer.FormMapper;
import com.crm.sofia.mapper.form.designer.FormMapperImpl;
import com.crm.sofia.model.component.Component;
import com.crm.sofia.model.component.ComponentPersistEntityFieldAssignment;
import com.crm.sofia.model.form.FormEntity;
import com.crm.sofia.native_repository.component.ComponentRetrieverNativeRepository;
import com.crm.sofia.repository.component.ComponentPersistEntityFieldAssignmentRepository;
import com.crm.sofia.repository.form.FormRepository;
import com.crm.sofia.services.auth.JWTService;
import com.crm.sofia.services.component.ComponentPersistEntityFieldAssignmentService;
import com.crm.sofia.services.component.crud.ComponentDeleterService;
import com.crm.sofia.services.form.FormService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class FormServiceTest {

    @Spy
    private final FormMapper formMapper = new FormMapperImpl();


    @InjectMocks
    private FormService formService;

    @Mock
    private ComponentPersistEntityFieldAssignmentService componentPersistEntityFieldAssignmentService;

    @Mock
    private ComponentDeleterService componentDeleterService;

    @Mock
    private FormRepository formRepository;

    @Mock
    private ComponentRetrieverNativeRepository componentRetrieverNativeRepository;

    @Mock
    private ComponentPersistEntityFieldAssignmentRepository componentPersistEntityFieldAssignmentRepository;

    @Mock
    private JWTService jwtService;

    private FormEntity formEntity;

    private String entityType;

    private String entityId;

    private List<ComponentPersistEntityFieldAssignment> componentPersistEntityFieldAssignmentList;

    private List<ComponentPersistEntityFieldAssignment> fieldAssignments;

    private ComponentDTO componentDTO;

    private List<ComponentPersistEntityDTO> componentPersistEntityList;

    private List<ComponentPersistEntityDTO> componentPersistEntityDTOList;


    private List<FormDTO> formDTOList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        formDTOList = new ArrayList<>();
        componentPersistEntityDTOList = new ArrayList<>();
        fieldAssignments = new ArrayList<>();
        componentDTO = new ComponentDTO().setDescription("dummyDescription").setComponentPersistEntityList(new ArrayList<>()).setComponentPersistEntityList(componentPersistEntityList);
        formEntity = new FormEntity().setDescription("dummyDescription").setName("dummyName").setFormScripts(new ArrayList<>())
                .setComponent(new Component().setDescription("dummyDescription")).setFormTabs(new ArrayList<>())
                .setFormPopups(new ArrayList<>()).setFormActionButtons(new ArrayList<>());
        formEntity.setId("1");
        entityType = "form";
        entityId = "1";


    }

    @Test
    public void getObjectTest() {
        given(formRepository.findById(anyString())).willReturn(Optional.of(formEntity));
        given(componentPersistEntityFieldAssignmentRepository.findByEntityIdAndEntityType(anyString(), anyString())).willReturn(componentPersistEntityFieldAssignmentList);
        given(componentPersistEntityFieldAssignmentService.retrieveFieldAssignments(any(), anyString(), anyString())).willReturn(componentPersistEntityList);
        FormDTO dto = formService.getObject("id");
        assertThat(dto).isNotNull();
        assertThat(dto.getId().equals("1"));
        assertThat(dto.getComponent().getDescription().equals("dummyDescription"));
    }


}
