package com.crm.sofia.services.component;

import com.crm.sofia.dto.component.CustomComponentFieldDTO;
import com.crm.sofia.mapper.component.CustomComponentFieldMapper;
import com.crm.sofia.model.component.CustomComponent;
import com.crm.sofia.model.component.CustomComponentField;
import com.crm.sofia.repository.component.ComponentFieldRepository;
import com.crm.sofia.repository.component.ComponentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CustomComponentFieldService {

    private final ComponentRepository componentRepository;
    private final ComponentFieldRepository componentFieldRepository;
    private final CustomComponentFieldMapper componentFieldMapper;


    public CustomComponentFieldService(ComponentRepository componentRepository,
                                       ComponentFieldRepository componentFieldRepository,
                                       CustomComponentFieldMapper componentFieldMapper) {
        this.componentRepository = componentRepository;
        this.componentFieldRepository = componentFieldRepository;
        this.componentFieldMapper = componentFieldMapper;
    }

    public CustomComponentFieldDTO saveCustomComponentField(CustomComponentFieldDTO customComponentFieldDTO, Long customComponentId) {
        Optional<CustomComponent> optionalComponent = this.componentRepository.findById(customComponentId);
        if (!optionalComponent.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Component does not exist");
        }
        CustomComponent customComponent = optionalComponent.get();

        CustomComponentField customComponentField;
        Long id = 0L;
        if (customComponentFieldDTO.getId() != null) id = customComponentFieldDTO.getId();
        Optional<CustomComponentField> optionalCustomComponentField = this.componentFieldRepository.findById(id);

        if (optionalCustomComponentField.isPresent()) {
            customComponentField = optionalCustomComponentField.get();
            componentFieldMapper.setDtoToEntity(customComponentFieldDTO, customComponentField);

        } else {
            customComponentField = componentFieldMapper.map(customComponentFieldDTO);
            customComponentField.setId(null);
        }
        customComponentField.setCustomComponent(customComponent);
        return componentFieldMapper.map(this.componentFieldRepository.save(customComponentField));
    }


    public void deleteObjectsNotInListForCustomComponent(List<Long> ids, Long id) {
        componentFieldRepository.deleteObjectsNotInListForCustomComponent(ids,id);
    }
}



