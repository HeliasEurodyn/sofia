package com.crm.sofia.mapper.sofia.timeline;

import com.crm.sofia.dto.sofia.timeline.TimelineDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.timeline.Timeline;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class TimelineMapper extends BaseMapper<TimelineDTO, Timeline> {

}
