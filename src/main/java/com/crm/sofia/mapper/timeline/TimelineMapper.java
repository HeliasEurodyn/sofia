package com.crm.sofia.mapper.timeline;

import com.crm.sofia.dto.timeline.TimelineDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.timeline.Timeline;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class TimelineMapper extends BaseMapper<TimelineDTO, Timeline> {

    public List<TimelineDTO> mapEntitiesForList(List<Timeline> entities) {
        return entities.stream().map(this::mapEntityIgnoringQuery).collect(Collectors.toList());
    }

    @Mapping(ignore = true, target = "query")
    public abstract TimelineDTO mapEntityIgnoringQuery(Timeline entity);

}
