package com.crm.sofia.services.component.crud;

import com.crm.sofia.dto.sofia.component.designer.ComponentDTO;
import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.model.sofia.expression.ExprResponse;
import com.crm.sofia.native_repository.sofia.component.ComponentSaverNativeRepository;
import com.crm.sofia.services.component.ComponentService;
import com.crm.sofia.services.expression.ExpressionService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class ComponentSaverService {

    private final ComponentSaverNativeRepository componentSaverNativeRepository;
    private final ComponentService componentService;
    private final ExpressionService expressionService;

    public ComponentSaverService(ComponentSaverNativeRepository componentSaverNativeRepository,
                                 ComponentService componentService,
                                 ExpressionService expressionService) {
        this.componentSaverNativeRepository = componentSaverNativeRepository;
        this.componentService = componentService;
        this.expressionService = expressionService;
    }

    public String save(String componentId, Map<String, Map<String, Object>> parameters) {
        /* Retrieve form from Database */
        ComponentDTO componentDTO = componentService.getObject(componentId);

        /* Map Parameters and Save */
        return this.save(componentDTO, parameters);
    }

    public String save(ComponentDTO componentDTO, Map<String, Map<String, Object>> parameters) {

        /* Μap parameters to component */
        componentService.mapParametersToComponentDTO(componentDTO.getComponentPersistEntityList(), parameters);

        /* Save */
        return this.save(componentDTO);
    }

    public String save(ComponentDTO componentDTO) {


        this.runOnSaveExpressions(componentDTO.getComponentPersistEntityList());

        /* Save */
        return this.componentSaverNativeRepository.save(componentDTO);
    }

    private void runOnSaveExpressions(List<ComponentPersistEntityDTO> componentPersistEntityList) {
        componentPersistEntityList
                .forEach(cpe -> {
                    cpe.getComponentPersistEntityFieldList()
                            .stream()
                            .filter(cpef -> cpef.getAssignment() != null)
                            .filter(cpef -> cpef.getAssignment().getOnSaveValue() != null)
                            .filter(cpef -> !cpef.getAssignment().getOnSaveValue().equals(""))
                            .forEach(cpef -> {
                                ExprResponse exprResponse = expressionService.create(cpef.getAssignment().getOnSaveValue(),
                                        Collections.singletonMap("fieldValue", cpef.getValue()));
                                if (!exprResponse.getError()) {
                                    Object fieldValue = exprResponse.getExprUnit().getResult();
                                    cpef.setValue(fieldValue);
                                }
                            });

                    if (cpe.getComponentPersistEntityList() != null) {
                        this.runOnSaveExpressions(cpe.getComponentPersistEntityList());
                    }

                    cpe.getComponentPersistEntityDataLines().forEach(cpedl -> {

                        cpedl.getComponentPersistEntityFieldList()
                                .stream()
                                .filter(cpef -> cpef.getAssignment() != null)
                                .filter(cpef -> cpef.getAssignment().getOnSaveValue() != null)
                                .filter(cpef -> !cpef.getAssignment().getOnSaveValue().equals(""))
                                .forEach(cpef -> {
                                    ExprResponse exprResponse = expressionService.create(cpef.getAssignment().getOnSaveValue(),
                                            Collections.singletonMap("fieldValue", cpef.getValue()));
                                    if (!exprResponse.getError()) {
                                        Object fieldValue = exprResponse.getExprUnit().getResult();
                                        cpef.setValue(fieldValue);
                                    }
                                });

                        this.runOnSaveExpressions(cpedl.getComponentPersistEntityList());
                    });
                });
    }

}
