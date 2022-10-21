package com.crm.sofia.controllers.expression;

import com.crm.sofia.dto.expression.ExprUnitDTO;
import com.crm.sofia.model.sofia.expression.ExprResponse;
import com.crm.sofia.model.sofia.expression.ExprUnit;
import com.crm.sofia.services.expression.ExpressionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@Validated
@RequestMapping("/expression")
public class ExpressionController {

    private final ExpressionService expressionService;

    public ExpressionController(ExpressionService expressionService) {
        this.expressionService = expressionService;
    }

    @GetMapping(path = "/result", produces = "text/plain")
    String getResult(@RequestParam("expression") String expression) {
        ExprResponse exprResponse = expressionService.create(expression);
        if (exprResponse.getError()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exprResponse.getErrorMessage());
        }
        Object fieldValue = exprResponse.getExprUnit().getResult();
        return fieldValue.toString();
    }

    @GetMapping(path = "/expression-units")
    ExprUnitDTO getExpressionUnits(@RequestParam("expression") String expression) {
        ExprResponse exprResponse = expressionService.create(expression);
        if (exprResponse.getError()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exprResponse.getErrorMessage());
        }
        ExprUnit exprUnit = exprResponse.getExprUnit();
        ExprUnitDTO exprUnitDTO = new ExprUnitDTO();
        this.expressionService.map(exprUnit,exprUnitDTO);
        return exprUnitDTO;
    }

}
