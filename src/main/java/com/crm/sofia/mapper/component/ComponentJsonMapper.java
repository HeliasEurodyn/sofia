package com.crm.sofia.mapper.component;

import com.crm.sofia.dto.sofia.component.designer.ComponentDTO;
import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.sofia.component.designer.ComponentPersistEntityDataLineDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class ComponentJsonMapper {

    public Map mapToJson(ComponentDTO componentDTO) {

        Map jsonMap = new HashMap();

        if (componentDTO.getComponentPersistEntityList() != null)
            componentDTO.getComponentPersistEntityList().forEach(cpe -> {
                Map jsonCpe = this.mapCpe(cpe);
                jsonMap.put(cpe.getCode(), jsonCpe);
            });

        return jsonMap;
    }

    public Map mapCpe(ComponentPersistEntityDTO cpe) {

        Map jsonMap = new HashMap();

        if (cpe.getMultiDataLine() == null ? false : cpe.getMultiDataLine()) {
            /* Map DataLines */
            if (cpe.getComponentPersistEntityDataLines() != null) {
                List<Object> dataLines = new ArrayList<>();
                cpe.getComponentPersistEntityDataLines().forEach(dl -> {
                    Map jsonDl = this.mapDl(dl);
                    dataLines.add(jsonDl);
                });
                jsonMap.put("array", dataLines.toArray());
            }
        } else {
            /* Map Cpef */
            if (cpe.getComponentPersistEntityFieldList() != null)
                cpe.getComponentPersistEntityFieldList().forEach(cpef -> {
                    Object value = cpef.getValue();
                    Object valueString = (value == null ? "" : value.toString());
                    jsonMap.put(cpef.getPersistEntityField().getName(), valueString);
                });

            /* Map sub containing Cpes */
            if (cpe.getComponentPersistEntityList() != null) {
                cpe.getComponentPersistEntityList().stream().forEach(cpeItem -> {
                    Map jsonCpe = this.mapCpe(cpeItem);
                    jsonMap.put(cpeItem.getCode(), jsonCpe);
                });
            }
        }

        return jsonMap;
    }

    public Map mapDl(ComponentPersistEntityDataLineDTO dl) {

        Map jsonMap = new HashMap();

        /*Map DataLines Cpef*/
        if (dl.getComponentPersistEntityFieldList() != null)
            dl.getComponentPersistEntityFieldList().forEach(dlCpef -> {
                Object value = dlCpef.getValue();
                Object valueString = (value == null ? "" : value.toString());
                jsonMap.put(dlCpef.getPersistEntityField().getName(), valueString);
            });

        /*Map DataLines Cpe*/
        if (dl.getComponentPersistEntityList() != null)
            dl.getComponentPersistEntityList().forEach(dlCpe -> {
                Map jsonCpe = this.mapCpe(dlCpe);
                jsonMap.put(dlCpe.getCode(), jsonCpe);
            });

        return jsonMap;
    }

}
