package com.crm.sofia.model.notification;


import com.crm.sofia.model.common.MainEntity;
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
@Entity(name = "Notification")
@Table(name = "notification")
public class Notification extends MainEntity {

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Column
    private String icon;

    @Column(columnDefinition = "VARCHAR(36)")
    private String senderId;

    @Column(columnDefinition = "VARCHAR(36)")
    private String receiverId;

    @Column
    private String status;

    @Column
    private String command;

}
