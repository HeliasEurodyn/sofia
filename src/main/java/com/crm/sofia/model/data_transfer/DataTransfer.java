package com.crm.sofia.model.data_transfer;

import com.crm.sofia.model.common.MainEntity;
import com.crm.sofia.utils.jpa.StringArrayToStringConverter;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Data
@Accessors(chain = true)
@DynamicUpdate
@DynamicInsert
@Entity(name = "DataTransfer")
@Table(name = "data_transfer")
public class DataTransfer extends MainEntity {

    @Column
    private String name;

    @Column
    private String description;

    @Column(columnDefinition = "TEXT")
    private String query;

    @Column
    private Integer currentVersion;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = StringArrayToStringConverter.class)
    List<String> formIds;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = StringArrayToStringConverter.class)
    List<String> listIds;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = StringArrayToStringConverter.class)
    List<String> tableIds;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = StringArrayToStringConverter.class)
    List<String> viewIds;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = StringArrayToStringConverter.class)
    List<String> appViewIds;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = StringArrayToStringConverter.class)
    List<String> menuIds;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = StringArrayToStringConverter.class)
    List<String> componentIds;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = StringArrayToStringConverter.class)
    List<String> chartIds;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = StringArrayToStringConverter.class)
    List<String> infoCardIds;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = StringArrayToStringConverter.class)
    List<String> htmlPartIds;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = StringArrayToStringConverter.class)
    List<String> dashboardIds;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = StringArrayToStringConverter.class)
    List<String> reportIds;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = StringArrayToStringConverter.class)
    List<String> xlsImportIds;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = StringArrayToStringConverter.class)
    List<String> searchIds;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = StringArrayToStringConverter.class)
    List<String> customQueryIds;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = StringArrayToStringConverter.class)
    List<String> languageIds;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = StringArrayToStringConverter.class)
    List<String> roleIds;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = StringArrayToStringConverter.class)
    List<String> userIds;

}
