package com.crm.sofia.services.sofia.component.crud;

import com.crm.sofia.dto.sofia.component.designer.ComponentDTO;
import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.model.sofia.expression.ExprResponce;
import com.crm.sofia.native_repository.sofia.component.ComponentRetrieverNativeRepository;
import com.crm.sofia.services.sofia.component.ComponentService;
import com.crm.sofia.services.sofia.expression.ExpressionService;
import org.springframework.stereotype.Service;

import java.util.List;

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

        this.runDefaultExpressionsOnTree(componentDTO.getComponentPersistEntityList());
        if (selectionId.equals("")) selectionId = "0";

        this.componentRetrieverNativeRepository.retrieveComponentData(componentDTO, selectionId);

        return componentDTO;
    }

    private void runDefaultExpressionsOnTree(List<ComponentPersistEntityDTO> componentPersistEntityList) {

        componentPersistEntityList
                .forEach(cpe -> {
                    cpe.getComponentPersistEntityFieldList()
                            .stream()
                            .filter(cpef -> cpef.getAssignment() != null)
                            .filter(cpef -> cpef.getAssignment().getDefaultValue() != null)
                            .filter(cpef -> !cpef.getAssignment().getDefaultValue().equals(""))
                            .forEach(cpef -> {
                                ExprResponce exprResponce = expressionService.create(cpef.getAssignment().getDefaultValue());
                                if (!exprResponce.getError()) {
                                    Object fieldValue = exprResponce.getExprUnit().getResult();
                                    cpef.setValue(fieldValue);
                                }
                            });

                    if (cpe.getComponentPersistEntityList() != null) {
                        this.runDefaultExpressionsOnTree(cpe.getComponentPersistEntityList());
                    }

                });

        componentPersistEntityList
                .forEach(cpe -> cpe.setDefaultComponentPersistEntityFieldList(cpe.getComponentPersistEntityFieldList()));

        componentPersistEntityList
                .forEach(cpe -> cpe.setDefaultComponentPersistEntityList(cpe.getComponentPersistEntityList()));

    }

}
