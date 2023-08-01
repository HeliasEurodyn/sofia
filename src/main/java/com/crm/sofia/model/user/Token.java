package com.crm.sofia.model.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="token")
public class Token {

    @Id
    @Column( updatable = false, nullable = false, columnDefinition = "VARCHAR(250)")
    private String token;

    private String tokenType;

    private boolean expired;

    private  boolean revoked;

    private Date expirationDate;

    @ManyToOne
    @JoinColumn(name ="User_id")
    private User user;

}
