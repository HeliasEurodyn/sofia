package com.crm.sofia.model.expression;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExprInitParameters {

    Map<String, Object> systemParameters;

    EntityManager entityManager = null;

}
