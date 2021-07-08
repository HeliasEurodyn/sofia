package com.crm.sofia.services.component.crud;

import com.crm.sofia.dto.component.ComponentDTO;
import com.crm.sofia.dto.component.ComponentPersistEntityDTO;
import com.crm.sofia.model.expression.ExprResponce;
import com.crm.sofia.native_repository.component.ComponentRetrieverNativeRepository;
import com.crm.sofia.services.component.ComponentService;
import com.crm.sofia.services.expression.ExpressionService;
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

    public ComponentDTO retrieveComponentWithData(Long componentId,
                                                  String selectionId) {

        /* Retrieve form from Database */
        ComponentDTO componentDTO = componentService.getObject(componentId);

        return this.retrieveComponentWithData(componentDTO, selectionId);
    }

    public ComponentDTO retrieveComponentWithData(ComponentDTO componentDTO,
                                                  String selectionId) {

        if (selectionId.equals("") || selectionId.equals("0")) {
            componentDTO = this.retrieveDefaultValueExpressions(componentDTO);
        } else {
            this.runDefaultValueExpressionsOnTreeTables(componentDTO.getComponentPersistEntityList());
            this.componentRetrieverNativeRepository.retrieveComponentData(componentDTO, selectionId);
        }

        return componentDTO;
    }

    private ComponentDTO retrieveDefaultValueExpressions(ComponentDTO componentDTO) {
        this.retrieveDefaultValueExpressionsOnTree(componentDTO.getComponentPersistEntityList());
        return componentDTO;
    }

    private void retrieveDefaultValueExpressionsOnTree(List<ComponentPersistEntityDTO> componentPersistEntityList) {

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
                        this.retrieveDefaultValueExpressionsOnTree(cpe.getComponentPersistEntityList());
                    }

                });

        componentPersistEntityList
                .forEach(cpe -> cpe.setDefaultComponentPersistEntityFieldList(cpe.getComponentPersistEntityFieldList()));

        componentPersistEntityList
                .forEach(cpe -> cpe.setDefaultComponentPersistEntityList(cpe.getComponentPersistEntityList()));

    }

//    private ComponentDTO setDefaultValueExpressionsOnTableComponents(ComponentDTO componentDTO) {
////        List<ComponentPersistEntityDTO> filteredComponentPersistEntityList =
////                componentDTO.getComponentPersistEntityList()
////                        .stream()
////                        .filter(cpe -> (cpe.getMultiDataLine() == null ? false : cpe.getMultiDataLine()) == true)
////                        .collect(Collectors.toList());
//
////        this.runDefaultValueExpressionsOnTree(filteredComponentPersistEntityList);
//        this.runDefaultValueExpressionsOnTreeTables( componentDTO.getComponentPersistEntityList());
//
//        return componentDTO;
//    }

    private void runDefaultValueExpressionsOnTreeTables(List<ComponentPersistEntityDTO> componentPersistEntityList) {

        /* Filter fields of Table Entities and calculate Default Values */
        componentPersistEntityList
                .stream()
                .filter(cpe -> (cpe.getMultiDataLine() == null ? false : cpe.getMultiDataLine()) == true)
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
                });

        /* Assign Fields & sub ComponentPersistEntities to Default Fields & sub ComponentPersistEntities */
        componentPersistEntityList
                .stream()
                .filter(cpe -> (cpe.getMultiDataLine() == null ? false : cpe.getMultiDataLine()) == true)
                .forEach(cpe -> cpe.setDefaultComponentPersistEntityFieldList(cpe.getComponentPersistEntityFieldList()));

        componentPersistEntityList
                .stream()
                .filter(cpe -> (cpe.getMultiDataLine() == null ? false : cpe.getMultiDataLine()) == true)
                .forEach(cpe -> cpe.setDefaultComponentPersistEntityList(cpe.getComponentPersistEntityList()));

        /* Run for SubComponents */
        componentPersistEntityList
                .stream()
                .filter(cpe -> cpe.getComponentPersistEntityList() != null)
                .forEach(cpe ->
                        {
                            this.runDefaultValueExpressionsOnTreeTables(cpe.getComponentPersistEntityList());
                        }
                );

    }

}
