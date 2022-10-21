package com.crm.sofia.mapper.data_transfer;

import com.crm.sofia.dto.data_transfer.DataTransferDTO;
import com.crm.sofia.mapper.common.BaseMapper;
import com.crm.sofia.model.sofia.data_transfer.DataTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class DataTransferMapper extends BaseMapper<DataTransferDTO, DataTransfer> {
}

