package com.crm.sofia.services.component.crud;

import com.crm.sofia.dto.sofia.component.designer.ComponentDTO;
import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.model.sofia.expression.ExprResponse;
import com.crm.sofia.native_repository.component.ComponentRetrieverNativeRepository;
import com.crm.sofia.services.component.ComponentService;
import com.crm.sofia.services.expression.ExpressionService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ComponentRetrieverService {

    private final ComponentService componentService;
    private final ExpressionService expressionService;
    private final ComponentRetrieverNativeRepository componentRetrieverNativeRepository;

    public ComponentRetrieverService(ComponentService componentService,
                                     ExpressionService expressionService,
                                     ComponentRetrieverNativeRepository componentRetrieverNativeRepository) {
        this.componentService = componentService;
        this.expressionService = expressionService;
        this.componentRetrieverNativeRepository = componentRetrieverNativeRepository;
    }

    public ComponentDTO retrieveComponentWithData(String componentId,
                                                  String selectionId) {

        /* Retrieve form from Database */
        ComponentDTO componentDTO = componentService.getObject(componentId);

        return this.retrieveComponentWithData(componentDTO, selectionId);
    }

    public ComponentDTO retrieveComponentWithData(ComponentDTO componentDTO,
                                                  String selectionId) {

        this.runDefaultExpressions(componentDTO.getComponentPersistEntityList(), selectionId);

        if (selectionId.equals("")) selectionId = "0";

        this.componentRetrieverNativeRepository.retrieveComponentData(componentDTO, selectionId);

        return componentDTO;
    }

    private void runDefaultExpressions(List<ComponentPersistEntityDTO> componentPersistEntityList, String selectionId) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("selectionId", selectionId);
        this.runDefaultExpressionsOnTree(componentPersistEntityList, parameters);
    }

    private void runDefaultExpressionsOnTree(List<ComponentPersistEntityDTO> componentPersistEntityList, Map<String, Object> parameters) {

        componentPersistEntityList
                .forEach(cpe -> {
                    cpe.getComponentPersistEntityFieldList()
                            .stream()
                            .filter(cpef -> cpef.getAssignment() != null)
                            .filter(cpef -> cpef.getAssignment().getDefaultValue() != null)
                            .filter(cpef -> !cpef.getAssignment().getDefaultValue().equals(""))
                            .forEach(cpef -> {
                                ExprResponse exprResponse = expressionService.create(cpef.getAssignment().getDefaultValue(), parameters);
                                if (!exprResponse.getError()) {
                                    Object fieldValue = exprResponse.getExprUnit().getResult();
                                    cpef.setValue(fieldValue);
                                }
                            });

                    if (cpe.getComponentPersistEntityList() != null) {
                        this.runDefaultExpressionsOnTree(cpe.getComponentPersistEntityList(), parameters);
                    }

                });

        componentPersistEntityList
                .forEach(cpe -> cpe.setDefaultComponentPersistEntityFieldList(cpe.getComponentPersistEntityFieldList()));

        componentPersistEntityList
                .forEach(cpe -> cpe.setDefaultComponentPersistEntityList(cpe.getComponentPersistEntityList()));

    }

}
