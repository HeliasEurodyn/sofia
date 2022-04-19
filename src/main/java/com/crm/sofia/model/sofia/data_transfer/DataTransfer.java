package com.crm.sofia.model.sofia.data_transfer;

import com.crm.sofia.model.common.BaseEntity;
import com.crm.sofia.utils.LongArrayToStringConverter;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "DataTransfer")
@Table(name = "data_transfer")
public class DataTransfer extends BaseEntity {

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Integer currentVersion;

    @Column
    @Convert(converter = LongArrayToStringConverter.class)
    List<Long> formIds;

    @Column
    @Convert(converter = LongArrayToStringConverter.class)
    List<Long> listIds;

}
