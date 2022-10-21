package com.crm.sofia.mapper.download;

import com.crm.sofia.dto.download.DownloadDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.download.Download;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class DownloadMapper extends BaseMapper<DownloadDTO, Download> {
}
