package com.crm.sofia.mapper.sofia.xls_import;

import com.crm.sofia.dto.sofia.xls_import.XlsImportDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.xls_import.XlsImport;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class XlsImportMapper extends BaseMapper<XlsImportDTO, XlsImport> {
}
