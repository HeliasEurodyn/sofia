package com.crm.sofia.model.sse_notification;

import com.crm.sofia.model.common.MainEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Accessors(chain = true)
@Entity(name = "SseNotification")
@Table(name = "sse_notification")
public class SseNotification extends MainEntity {

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String query;


}
