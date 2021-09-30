package com.crm.sofia.mapper.sofia.component;

import com.crm.sofia.dto.sofia.component.designer.ComponentDTO;
import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityDataLineDTO;
import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityFieldDTO;
import com.crm.sofia.dto.sofia.component.user.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class ComponentUiMapper {

    public ComponentUiDTO clearIdsAndMapToUi(ComponentDTO componentDTO) {
        this.clearIds(componentDTO.getComponentPersistEntityList());
        ComponentUiDTO componentUiDTO = this.mapToUi(componentDTO);
        return componentUiDTO;
    }

    public ComponentUiDTO mapToUi(ComponentDTO componentDTO) {

        ComponentUiDTO componentUiDTO = new ComponentUiDTO();
        componentUiDTO.setId(componentDTO.getId());
        componentUiDTO.setComponentPersistEntityList(new ArrayList<>());

        if (componentDTO.getComponentPersistEntityList() != null)
            componentDTO.getComponentPersistEntityList().forEach(cpe -> {
                ComponentPersistEntityUiDTO cpeUiDTO = this.mapComponentPE(cpe);
                componentUiDTO.getComponentPersistEntityList().add(cpeUiDTO);
            });

        return componentUiDTO;
    }

    private List<ComponentPersistEntityDTO> clearIds(List<ComponentPersistEntityDTO> componentPersistEntityList) {

        if (componentPersistEntityList == null) {
            return componentPersistEntityList;
        }

        componentPersistEntityList
                .stream()
                .filter(cpe -> cpe.getComponentPersistEntityFieldList() != null)
                .forEach(cpe -> {
                    cpe.getComponentPersistEntityFieldList()
                            .stream()
                            .filter(x -> x.getPersistEntityField().getPrimaryKey())
                            .forEach(x -> x.setValue(null));
                });

        componentPersistEntityList
                .stream()
                .filter(cpe -> cpe.getDefaultComponentPersistEntityFieldList() != null)
                .forEach(cpe -> {
                    cpe.getDefaultComponentPersistEntityFieldList()
                            .stream()
                            .filter(x -> x.getPersistEntityField().getPrimaryKey())
                            .forEach(x -> x.setValue(null));
                });

        componentPersistEntityList
                .stream()
                .filter(cpe -> cpe.getComponentPersistEntityDataLines() != null)
                .filter(cpe -> (cpe.getMultiDataLine() == null ? false : cpe.getMultiDataLine()) == true)
                .forEach(cpe -> {
                    cpe.getComponentPersistEntityDataLines().forEach(cpeDl -> {
                        cpeDl.getComponentPersistEntityFieldList()
                                .stream()
                                .filter(x -> x.getPersistEntityField().getPrimaryKey())
                                .forEach(x -> x.setValue(null));
                    });
                });

        componentPersistEntityList
                .forEach(cpe -> {
                    this.clearIds(cpe.getComponentPersistEntityList());
                });

        componentPersistEntityList
                .forEach(cpe -> {
                    this.clearIds(cpe.getDefaultComponentPersistEntityList());
                });

        return componentPersistEntityList;
    }

    private ComponentPersistEntityUiDTO mapComponentPE(ComponentPersistEntityDTO cpe) {

        /*Create Mapped CPE*/
        ComponentPersistEntityUiDTO cpeUi = this.mapCpe(cpe);
        cpeUi.setComponentPersistEntityFieldList(new ArrayList<>());
        cpeUi.setDefaultComponentPersistEntityFieldList(new ArrayList<>());
        cpeUi.setComponentPersistEntityDataLines(new ArrayList<>());
        cpeUi.setComponentPersistEntityList(new ArrayList<>());
        cpeUi.setDefaultComponentPersistEntityList(new ArrayList<>());

        /* Map Cpef */
        if (cpe.getComponentPersistEntityFieldList() != null)
            cpe.getComponentPersistEntityFieldList().forEach(cpef -> {
                ComponentPersistEntityFieldUiDTO cpefUiDTO = this.mapCpef(cpef);
                cpefUiDTO.setCode(cpef.getPersistEntityField().getName());
                cpeUi.getComponentPersistEntityFieldList().add(cpefUiDTO);
            });

        /* Map Default Cpef */
        if (cpe.getDefaultComponentPersistEntityFieldList() != null)
            cpe.getDefaultComponentPersistEntityFieldList().forEach(cpef -> {
                ComponentPersistEntityFieldUiDTO cpefUiDTO = this.mapCpef(cpef);
                cpefUiDTO.setCode(cpef.getPersistEntityField().getName());
                cpeUi.getDefaultComponentPersistEntityFieldList().add(cpefUiDTO);
            });

        /* Map Cpe */
        if (cpe.getComponentPersistEntityList() != null)
            cpe.getComponentPersistEntityList().forEach(cpeItem -> {
                ComponentPersistEntityUiDTO cpeUiDTO = this.mapComponentPE(cpeItem);
                cpeUi.getComponentPersistEntityList().add(cpeUiDTO);
            });

        /* Map Default Cpe */
        if (cpe.getDefaultComponentPersistEntityList() != null)
            cpe.getDefaultComponentPersistEntityList().forEach(defaultCpeItem -> {
                ComponentPersistEntityUiDTO defaultCpeUiDTO = this.mapComponentPE(defaultCpeItem);
                cpeUi.getDefaultComponentPersistEntityList().add(defaultCpeUiDTO);
            });

        /* Map DataLines */
        if (cpe.getComponentPersistEntityDataLines() != null)
            cpe.getComponentPersistEntityDataLines().forEach(dl -> {
                ComponentPersistEntityDataLineUiDTO dlUi = this.mapDl(dl);
                cpeUi.getComponentPersistEntityDataLines().add(dlUi);
            });

        return cpeUi;
    }

    private ComponentPersistEntityDataLineUiDTO mapDl(ComponentPersistEntityDataLineDTO dl) {
        ComponentPersistEntityDataLineUiDTO uiDl = new ComponentPersistEntityDataLineUiDTO();
        uiDl.setComponentPersistEntityFieldList(new ArrayList<>());
        uiDl.setComponentPersistEntityList(new ArrayList<>());

        /*Map DataLines Cpef*/
        if (dl.getComponentPersistEntityFieldList() != null)
            dl.getComponentPersistEntityFieldList().forEach(dlCpef -> {
                ComponentPersistEntityDataLineFieldUiDTO dlCpefUi = this.mapCpeDlf(dlCpef);
                dlCpefUi.setCode(dlCpef.getPersistEntityField().getName());
                uiDl.getComponentPersistEntityFieldList().add(dlCpefUi);
            });

        /*Map DataLines Cpe*/
        if (dl.getComponentPersistEntityList() != null)
            dl.getComponentPersistEntityList().forEach(dlCpe -> {
                ComponentPersistEntityUiDTO dlCpeUi = this.mapComponentPE(dlCpe);
                uiDl.getComponentPersistEntityList().add(dlCpeUi);
            });

        return uiDl;
    }

    @Mapping(ignore = true, target = "componentPersistEntityFieldList")
    @Mapping(ignore = true, target = "defaultComponentPersistEntityFieldList")
    @Mapping(ignore = true, target = "componentPersistEntityDataLines")
    @Mapping(ignore = true, target = "componentPersistEntityList")
    @Mapping(ignore = true, target = "defaultComponentPersistEntityList")
    abstract ComponentPersistEntityUiDTO mapCpe(ComponentPersistEntityDTO entity);

    abstract ComponentPersistEntityFieldUiDTO mapCpef(ComponentPersistEntityFieldDTO entity);

    abstract ComponentPersistEntityDataLineFieldUiDTO mapCpeDlf(ComponentPersistEntityFieldDTO entity);

}
