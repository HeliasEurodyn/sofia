package com.crm.sofia.mapper.xls_import;

import com.crm.sofia.dto.xls_import.XlsImportDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.xls_import.XlsImport;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class XlsImportMapper extends BaseMapper<XlsImportDTO, XlsImport> {
}
