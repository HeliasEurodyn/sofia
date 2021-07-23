package com.crm.sofia.services.sofia.xls_import;

import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.sofia.xls_import.XlsImportDTO;
import com.crm.sofia.mapper.sofia.xls_import.XlsImportMapper;
import com.crm.sofia.model.sofia.xls_import.XlsImport;
import com.crm.sofia.repository.sofia.xls_import.XlsImportRepository;
import com.crm.sofia.services.sofia.component.ComponentPersistEntityFieldAssignmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class XlsImportDesignerService {

    private final XlsImportRepository xlsImportRepository;
    private final XlsImportMapper xlsImportMapper;
    private final ComponentPersistEntityFieldAssignmentService componentPersistEntityFieldAssignmentService;

    public XlsImportDesignerService(XlsImportRepository xlsImportRepository,
                                    XlsImportMapper xlsImportMapper, ComponentPersistEntityFieldAssignmentService componentPersistEntityFieldAssignmentService) {
        this.xlsImportRepository = xlsImportRepository;
        this.xlsImportMapper = xlsImportMapper;
        this.componentPersistEntityFieldAssignmentService = componentPersistEntityFieldAssignmentService;
    }

    public List<XlsImportDTO> getObject() {
        List<XlsImport> charts = this.xlsImportRepository.findAll();
        return this.xlsImportMapper.map(charts);
    }

    public XlsImportDTO getObject(Long id) {
        Optional<XlsImport> optionalchart = this.xlsImportRepository.findById(id);
        if (!optionalchart.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        XlsImportDTO dto = this.xlsImportMapper.map(optionalchart.get());

        List<ComponentPersistEntityDTO> componentPersistEntityList =
                this.componentPersistEntityFieldAssignmentService.retrieveFormFieldAssignments(
                        dto.getComponent().getComponentPersistEntityList(),
                        "xls_import",
                        dto.getId()
                );

        dto.getComponent().setComponentPersistEntityList(componentPersistEntityList);

        return dto;
    }

    @Transactional
    public XlsImportDTO postObject(XlsImportDTO dto) {
        XlsImport chart = this.xlsImportMapper.map(dto);
        XlsImport savedChart = this.xlsImportRepository.save(chart);

        this.componentPersistEntityFieldAssignmentService
                .saveFieldAssignments(dto.getComponent().getComponentPersistEntityList(),"xls_import",
                        savedChart.getId());

        return this.xlsImportMapper.map(savedChart);
    }

    @Transactional
    @Modifying
    public void deleteObject(Long id) {
        Optional<XlsImport> optionalChart = this.xlsImportRepository.findById(id);
        if (!optionalChart.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object does not exist");
        }
        this.componentPersistEntityFieldAssignmentService.deleteByFormIdAndEntityType(optionalChart.get().getId(),"xls_import");
        this.xlsImportRepository.deleteById(optionalChart.get().getId());
    }

}
