package com.crm.sofia.model.expression;

import com.crm.sofia.services.auth.JWTService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExprInitParameters {

    Map<String, Object> systemParameters;

    JWTService jwtService = null;

}
