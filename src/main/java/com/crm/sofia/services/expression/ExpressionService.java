package com.crm.sofia.services.expression;

import com.crm.sofia.dto.component.designer.ComponentDTO;
import com.crm.sofia.dto.component.designer.ComponentPersistEntityDTO;
import com.crm.sofia.dto.expression.ExprUnitDTO;
import com.crm.sofia.model.expression.ExprInitParameters;
import com.crm.sofia.model.expression.ExprResponse;
import com.crm.sofia.model.expression.ExprUnit;
import com.crm.sofia.model.expression.expressionUnits.*;
import com.crm.sofia.services.auth.JWTService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExpressionService {

    private final JWTService jwtService;
    private final EntityManager entityManager;

    public ExpressionService(JWTService jwtService,
                             EntityManager entityManager) {
        this.jwtService = jwtService;
        this.entityManager = entityManager;
    }

    @Cacheable(value="expression", key = "#id")
    public ExprResponse createCacheable(String expression, String id) {
        return this.create(expression);
    }

    public ExprResponse create(String expression) {

        if (expression.length() == 0) {
            this.createEmptyExprResponce();
        }

        Boolean brakcetCheck = this.checkBruckets(expression);
        if (!brakcetCheck) {
            return new ExprResponse("Error on Bracker openings & closings", false, expression, null);
        }

        Boolean quoteCheck = this.checkQuotes(expression);
        if (!quoteCheck) {
            return new ExprResponse("Error on Quotes openings & closings", false, expression, null);
        }

        expression = this.removeSpaces(expression);
        if (expression.length() == 0) {
            this.createEmptyExprResponce();
        }

        List<ExprUnit> exprUnits = this.createExprUnitList(expression);

        if (exprUnits == null) {
            return new ExprResponse("Could not read parameter", false, expression, null);
        }

        if (exprUnits.size() == 0) {
            return new ExprResponse("Could not read parameter", false, expression, null);
        }

        this.createBracketPriorities(exprUnits);
        exprUnits = this.removeBracketsFromList(exprUnits);
        boolean treeCreated = this.createTree(exprUnits, exprUnits.get(0).getPriority());
        if (!treeCreated) {
            return new ExprResponse("Error on tree creation", false, expression, null);
        }

        ExprResponse exprResponse = new ExprResponse();
        exprResponse.setExpression(expression);
        exprResponse.setExprUnit(exprUnits.get(0).getTopParrent());
        return exprResponse;
    }

    /*
     * Removes useless spaces from the expression
     * This functionality checks Quotes, because spaces inside quote expression must not be removed
     * */
    private String removeSpaces(String expression) {

        boolean outOfQuote = true;
        String prevExpressionPositionString = "";
        String expressionPositionString;
        String newExpression = "";
        for (int i = 0; i < expression.length(); i++) {

            expressionPositionString = expression.substring(i, i + 1);

            if (expressionPositionString.equals("'") && outOfQuote) {
                outOfQuote = false;
            } else if (expressionPositionString.equals("'") && !prevExpressionPositionString.equals("/") && !outOfQuote) {
                outOfQuote = true;
            }

            if (!outOfQuote || (outOfQuote && !expressionPositionString.equals(" ") && !expressionPositionString.equals("\n"))) {
                newExpression += expressionPositionString;
            }

            prevExpressionPositionString = expressionPositionString;
        }

        return newExpression;
    }

    private Boolean checkQuotes(String expression) {
        Boolean outOfQuote = true;
        String prevExpressionPositionString = "";
        String expressionPositionString = "";

        for (int i = 0; i < expression.length(); i++) {

            expressionPositionString = expression.substring(i, i + 1);

            if (expressionPositionString.equals("'") && outOfQuote) {
                outOfQuote = false;
            } else if (expressionPositionString.equals("'") && !prevExpressionPositionString.equals("/") && !outOfQuote) {
                outOfQuote = true;
            }

            prevExpressionPositionString = expressionPositionString;
        }
        return outOfQuote;
    }

    private List<ExprUnit> removeBracketsFromList(List<ExprUnit> exprUnits) {
        return exprUnits.stream()
                .filter(
                        exprUnit -> !(exprUnit instanceof ExprOpenBracket || exprUnit instanceof ExprCloseBracket)
                ).collect(Collectors.toList());
    }

    private boolean createTree(List<ExprUnit> exprUnits, int currentPriority) {

        int listPosition = 0;

        for (ExprUnit exprUnit : exprUnits) {

            /*
             * On those Types of ExprUnits at least 1 expr units must be before
             * And at least 1 expr units must be after
             */
            if ((exprUnit instanceof ExprPlus)
                    && !exprUnit.getIsOnTree() && exprUnit.getPriority().equals(currentPriority)) {

                /*
                 * On those Types of ExprUnits at least 1 expr units must be before
                 * If not return error on tree creation
                 */
                if (listPosition < 1) return false;

                /*
                 * On those Types of ExprUnits at least 1 expr units must be after
                 * If not return error on tree creation
                 */
                if ((listPosition + 1) > exprUnits.size()) return false;

                /*
                 * Run createTree for the next Priority level
                 * If inside the brackets of the Right expression there is a higher priority
                 * It must be executed first
                 */
                if  ( !exprUnits.get(listPosition + 1).getPriority().equals(currentPriority) ){
                    boolean treeCreationResult = this.createTree(exprUnits, exprUnits.get(listPosition + 1).getPriority());
                    if (!treeCreationResult) return false;
                }

                /*
                 * Set RightChildExprUnit on the current exprUnit
                 */
                exprUnit.setRightChildExprUnit(exprUnits.get(listPosition + 1).getTopParrent());
                exprUnits.get(listPosition + 1).getTopParrent().setParentExprUnit(exprUnit);

                /*
                 * Run createTree for the next Priority level
                 * If inside the brackets of the Left expression there is a higher priority
                 * It must be executed first
                 */
                if  ( exprUnits.get(listPosition - 1).getPriority() > currentPriority ){

                    int targetPriority = exprUnits.get(listPosition - 1).getPriority();
                    for(int stepPriority = (currentPriority + 1) ; stepPriority <= targetPriority; stepPriority++){
                        boolean treeCreationResult = this.createTree(exprUnits, stepPriority);
                        if (!treeCreationResult) return false;
                    }

                }

                /*
                 * Set LeftChildExprUnit on the current exprUnit
                 */
                exprUnit.setLeftChildExprUnit(exprUnits.get(listPosition - 1).getTopParrent());
                exprUnits.get(listPosition - 1).getTopParrent().setParentExprUnit(exprUnit);

                exprUnit.setIsOnTree(true);
            }

            /*
             * On those Types of ExprUnits at least 5 expr units must follow
             * 1. The first parameter, 2. Comma, 3.The second parameter, 4, 5
             * So they get on the same code section
             */
            if ((exprUnit instanceof ExprIf)
                    && !exprUnit.getIsOnTree()
                    && exprUnit.getPriority().equals(currentPriority)) {

                /*
                 * On those Types of ExprUnits at least 5 expr units must follow
                 * 1. The first parameter, 2. Comma, 3.The second parameter, 4. Comma, 5.The third parameter
                 * If not return error on tree creation
                 */
                if ((listPosition + 5) > exprUnits.size()) return false;

                /*
                 * Run createTree for the next Priority level
                 * (inside the brackets of the current expression there is a higher priority)
                 * It must be executed first
                 */
                boolean treeCreationResult = this.createTree(exprUnits, exprUnits.get(listPosition + 1).getPriority());
                if (!treeCreationResult) return false;

                /*
                 * Set ChildExprUnit on the current exprUnit
                 */
                exprUnit.setChildExprUnit(exprUnits.get(listPosition + 1).getTopParrent());
                exprUnits.get(listPosition + 1).getTopParrent().setParentExprUnit(exprUnit);

                /*
                 * Set LeftChildExprUnit on the current exprUnit
                 */
                int leftChildPosition = this.traceNextChildPosition(exprUnits, listPosition + 2, currentPriority);
                if (leftChildPosition == 0) return false;
                exprUnit.setLeftChildExprUnit(exprUnits.get(leftChildPosition).getTopParrent());
                exprUnits.get(leftChildPosition).getTopParrent().setParentExprUnit(exprUnit);

                /*
                 * Set RightChildExprUnit on the current exprUnit
                 */
                int rightChildPosition = this.traceNextChildPosition(exprUnits, leftChildPosition + 1, currentPriority);
                if (rightChildPosition == 0) return false;
                exprUnit.setRightChildExprUnit(exprUnits.get(rightChildPosition).getTopParrent());
                exprUnits.get(rightChildPosition).getTopParrent().setParentExprUnit(exprUnit);

                exprUnit.setIsOnTree(true);
            }

            /*
             * On those Types of ExprUnits at least 3 expr units must follow
             * 1. The first parameter, 2. Comma, 3.The second parameter
             * So they get on the same section
             */
            if ((exprUnit instanceof ExprStringConcat ||
                    exprUnit instanceof ExprDatePlus ||
                    exprUnit instanceof ExprDoubleNumberAddition ||
                    exprUnit instanceof ExprDoubleNumberDivision ||
                    exprUnit instanceof ExprDoubleNumberMultiplication ||
                    exprUnit instanceof ExprDoubleNumberSubtraction ||
                    exprUnit instanceof ExprNumberTrim ||
                    exprUnit instanceof ExprStringConcat ||
                    exprUnit instanceof ExprStringReplace ||
                    exprUnit instanceof ExprStringReplace ||
                    exprUnit instanceof ExprGreaterThan ||
                    exprUnit instanceof ExprMinorThan ||
                    exprUnit instanceof ExprEqualsTo ||
                    exprUnit instanceof ExprStrEqualsTo ||
                    exprUnit instanceof ExprIfNull ||
                    exprUnit instanceof ExprAtListPosParameter ||
                    exprUnit instanceof ExprSetFieldValue) && !exprUnit.getIsOnTree() && exprUnit.getPriority().equals(currentPriority)) {

                /*
                 * On those Types of ExprUnits at least 3 expr units must follow
                 * 1. The first parameter, 2. Comma, 3.The second parameter
                 * If not return error on tree creation
                 */
                if ((listPosition + 3) > exprUnits.size()) return false;

                /*
                 * Run createTree for the next Priority level
                 * (inside the brackets of the current expression there is a higher priority)
                 * It must be executed first
                 */
                boolean treeCreationResult = this.createTree(exprUnits, exprUnits.get(listPosition + 1).getPriority());
                if (!treeCreationResult) return false;

                /*
                 * Set LeftChildExprUnit on the current exprUnit
                 */
                exprUnit.setLeftChildExprUnit(exprUnits.get(listPosition + 1).getTopParrent());
                exprUnits.get(listPosition + 1).getTopParrent().setParentExprUnit(exprUnit);

                /*
                 * Set RightChildExprUnit on the current exprUnit
                 */
                int rightChildPosition = this.traceNextChildPosition(exprUnits, listPosition + 2, currentPriority);
                if (rightChildPosition == 0) return false;
                exprUnit.setRightChildExprUnit(exprUnits.get(rightChildPosition).getTopParrent());
                exprUnits.get(rightChildPosition).getTopParrent().setParentExprUnit(exprUnit);

                exprUnit.setIsOnTree(true);
            }

            /*
             * On those Types of ExprUnits at least 1 expr unit must follow
             * So they get on the same code section
             */
            if ((exprUnit instanceof ExprDateNowPlus ||
                    exprUnit instanceof ExprYyyyMmDdHhMmStringToDate ||
                    exprUnit instanceof ExprYyyyMmDdStringToDate ||
                    exprUnit instanceof ExprSystemParameter ||
                    exprUnit instanceof ExprImportColumnParameter ||
                    exprUnit instanceof ExprGetSqlParameter ||
                    exprUnit instanceof ExprThrowExceptionParameter ||
                    exprUnit instanceof ExprException ||
                    exprUnit instanceof ExprGetSqlValParameter ||
                    exprUnit instanceof ExprGetFieldValue)
                    && !exprUnit.getIsOnTree()
                    && exprUnit.getPriority().equals(currentPriority)) {

                /*
                 * On those Types of ExprUnits at least 1 expr unit must follow
                 * If not return error on tree creation
                 */
                if ((listPosition + 1) > exprUnits.size()) return false;

                /*
                 * Run createTree for the next Priority level
                 * (inside the brackets of the current expression there is a higher priority)
                 * It must be executed first
                 */
                boolean treeCreationResult = this.createTree(exprUnits, exprUnits.get(listPosition + 1).getPriority());
                if (!treeCreationResult) return false;

                /*
                 * Set ChildExprUnit on the current exprUnit
                 */
                exprUnit.setChildExprUnit(exprUnits.get(listPosition + 1).getTopParrent());
                exprUnits.get(listPosition + 1).getTopParrent().setParentExprUnit(exprUnit);

                exprUnit.setIsOnTree(true);
            }

            /*
             * On those Types of ExprUnits 0 expr unit must follow
             * So they get on the same code section
             */
            if ((exprUnit instanceof ExprUuid
                    || exprUnit instanceof ExprStringValue
                    || exprUnit instanceof ExprDoubleValue
                    || exprUnit instanceof ExprIntegerValue)
                    && !exprUnit.getIsOnTree()
                    && exprUnit.getPriority().equals(currentPriority)) {

                exprUnit.setIsOnTree(true);
            }

            listPosition++;
        }

        return true;
    }

    private int traceNextChildPosition(List<ExprUnit> exprUnits, int listPosition, int currentPriority) {
        /*
         * We search for the commaExprUnit on the same priority, after that we have the second parameter
         * We search  from listPosition+2 because
         * +1 is be the first parameter of the current exprUnit , only after that can be the comma
         */
        for (int i = listPosition; i < exprUnits.size(); i++) {
            if (exprUnits.get(i) instanceof ExprComma && exprUnits.get(i).getPriority().equals(currentPriority)) {
                return i + 1;
            }
        }
        return 0;
    }

    private void createBracketPriorities(List<ExprUnit> exprUnits) {
        int priority = 0;
        for (ExprUnit exprUnit : exprUnits) {

            if (exprUnit.getClass() == ExprCloseBracket.class) {
                priority -= 1;
            }

            if (exprUnit.getClass() == ExprComma.class) {
                exprUnit.setPriority(priority - 1);
            } else {
                exprUnit.setPriority(priority);
            }

            if (exprUnit.getClass() == ExprOpenBracket.class) {
                priority += 1;
            }
        }
    }

    private List<ExprUnit> createExprUnitList(String expression) {
        List<ExprUnit> exprUnits = new ArrayList<>();

        int i = 0;
        while (i < expression.length()) {

            ExprUnit exprUnit = null;

            if (exprUnit == null) exprUnit = ExprOpenBracket.exrtactExprUnit(expression, i);
            if (exprUnit == null) exprUnit = ExprCloseBracket.exrtactExprUnit(expression, i);
            if (exprUnit == null) exprUnit = ExprComma.exrtactExprUnit(expression, i);
            if (exprUnit == null) exprUnit = ExprPlus.exrtactExprUnit(expression, i);
            if (exprUnit == null) exprUnit = ExprDateNowPlus.exrtactExprUnit(expression, i);
            if (exprUnit == null) exprUnit = ExprDatePlus.exrtactExprUnit(expression, i);
            if (exprUnit == null) exprUnit = ExprDoubleNumberAddition.exrtactExprUnit(expression, i);
            if (exprUnit == null) exprUnit = ExprDoubleNumberDivision.exrtactExprUnit(expression, i);
            if (exprUnit == null) exprUnit = ExprDoubleNumberMultiplication.exrtactExprUnit(expression, i);
            if (exprUnit == null) exprUnit = ExprDoubleNumberSubtraction.exrtactExprUnit(expression, i);
            if (exprUnit == null) exprUnit = ExprNumberTrim.exrtactExprUnit(expression, i);
            if (exprUnit == null) exprUnit = ExprStringConcat.exrtactExprUnit(expression, i);
            if (exprUnit == null) exprUnit = ExprStringReplace.exrtactExprUnit(expression, i);
            if (exprUnit == null) exprUnit = ExprYyyyMmDdHhMmStringToDate.exrtactExprUnit(expression, i);
            if (exprUnit == null) exprUnit = ExprYyyyMmDdStringToDate.exrtactExprUnit(expression, i);
            if (exprUnit == null) exprUnit = ExprParameter.exrtactExprUnit(expression, i);
            if (exprUnit == null) exprUnit = ExprStringValue.exrtactExprUnit(expression, i);
            if (exprUnit == null) exprUnit = ExprDoubleValue.exrtactExprUnit(expression, i);
            if (exprUnit == null) exprUnit = ExprIntegerValue.exrtactExprUnit(expression, i);
            if (exprUnit == null) exprUnit = ExprSystemParameter.exrtactExprUnit(expression, i); // parameters
            if (exprUnit == null) exprUnit = ExprImportColumnParameter.exrtactExprUnit(expression, i); // parameters
            if (exprUnit == null) exprUnit = ExprGetSqlValParameter.exrtactExprUnit(expression, i); // entityManager
            if (exprUnit == null) exprUnit = ExprGetSqlParameter.exrtactExprUnit(expression, i); // entityManager
            if (exprUnit == null) exprUnit = ExprThrowExceptionParameter.exrtactExprUnit(expression, i); // entityManager
            if (exprUnit == null) exprUnit = ExprSetFieldValue.exrtactExprUnit(expression, i);
            if (exprUnit == null) exprUnit = ExprGetFieldValue.exrtactExprUnit(expression, i);

            if (exprUnit == null) exprUnit = ExprAtListPosParameter.exrtactExprUnit(expression, i);

            if (exprUnit == null) exprUnit = ExprGreaterThan.exrtactExprUnit(expression, i);
            if (exprUnit == null) exprUnit = ExprMinorThan.exrtactExprUnit(expression, i);
            if (exprUnit == null) exprUnit = ExprEqualsTo.exrtactExprUnit(expression, i);

            if (exprUnit == null) exprUnit = ExprIfNull.exrtactExprUnit(expression, i);
            if (exprUnit == null) exprUnit = ExprIf.exrtactExprUnit(expression, i);
            if (exprUnit == null) exprUnit = ExprStrEqualsTo.exrtactExprUnit(expression, i);
            if (exprUnit == null) exprUnit = ExprUuid.exrtactExprUnit(expression, i);
            if (exprUnit == null) exprUnit = ExprException.exrtactExprUnit(expression, i);

            if (exprUnit == null) {
                return null;
            }

            i += exprUnit.getExprUnitLength();
            exprUnits.add(exprUnit);
        }

        return exprUnits;
    }

    /*
     * Checks if the brackets openings & closings numbers are equal
     * */
    private Boolean checkBruckets(String expression) {
        Integer opened = 0;
        Integer closed = 0;
        Integer length = expression.length();
        String stringAtPosition = "";

        for (int i = 0; i < length; i++) {
            stringAtPosition = expression.substring(i, i + 1);
            if (stringAtPosition.equals("(")) opened++;
            if (stringAtPosition.equals(")")) closed++;
        }

        return opened == closed;
    }

    private Map<String, Object> defineSystemParameters() {
        Map<String, Object> systemParameters = new HashMap<>();
        systemParameters.put("userId", this.jwtService.getUserId().toString());

        return systemParameters;
    }

    private ExprResponse createEmptyExprResponce() {
        ExprResponse exprResponse = new ExprResponse();
        exprResponse.setExpression("");
        exprResponse.setExprUnit(new ExprEmptyValue());
        return exprResponse;
    }

    private void map(ExprUnit exprUnit, ExprUnitDTO exprUnitDTO) {
        exprUnitDTO.setExpressionPart(exprUnit.getExpressionPart());
        exprUnitDTO.setType(exprUnit.getClass().getSimpleName());

        if(exprUnit.getChildExprUnit() != null){
            ExprUnitDTO childExprUnit = new ExprUnitDTO();
            exprUnitDTO.setChildExprUnit(childExprUnit);
            this.map(exprUnit.getChildExprUnit(),childExprUnit);
        }

        if(exprUnit.getLeftChildExprUnit() != null){
            ExprUnitDTO leftChildExprUnit = new ExprUnitDTO();
            exprUnitDTO.setLeftChildExprUnit(leftChildExprUnit);
            this.map(exprUnit.getLeftChildExprUnit(), leftChildExprUnit);
        }

        if(exprUnit.getRightChildExprUnit() != null){
            ExprUnitDTO rightChildExprUnit = new ExprUnitDTO();
            exprUnitDTO.setRightChildExprUnit(rightChildExprUnit);
            this.map(exprUnit.getRightChildExprUnit(), rightChildExprUnit);
        }
    }

    public Object getResult(ExprResponse exprResponse, Object fieldValue) {
        Map<String, Object> systemParameters = this.defineSystemParameters();

        ExprInitParameters exprInitParameters = new ExprInitParameters();
        exprInitParameters.setSystemParameters(systemParameters);
        exprInitParameters.getSystemParameters().put("fieldValue", fieldValue);
        exprInitParameters.setEntityManager(this.entityManager);

        return exprResponse.getResult(exprInitParameters);
    }

    public Object getResult(ExprResponse exprResponse, Map<String, Object> parameters) {
        Map<String, Object> systemParameters = this.defineSystemParameters();

        ExprInitParameters exprInitParameters = new ExprInitParameters();
        exprInitParameters.setSystemParameters(systemParameters);
        exprInitParameters.getSystemParameters().putAll(parameters);
        exprInitParameters.setEntityManager(this.entityManager);

        return exprResponse.getResult(exprInitParameters);
    }

    public Object getResult(ExprResponse exprResponse, Map<String, Object> parameters, ComponentDTO componentDTO) {
        Map<String, Object> systemParameters = this.defineSystemParameters();

        ExprInitParameters exprInitParameters = new ExprInitParameters();
        exprInitParameters.setSystemParameters(systemParameters);
        exprInitParameters.getSystemParameters().putAll(parameters);
        exprInitParameters.setEntityManager(this.entityManager);
        exprInitParameters.setComponentDTO(componentDTO);

        return exprResponse.getResult(exprInitParameters);
    }

    public Object getResult(ExprResponse exprResponse) {
        Map<String, Object> systemParameters = this.defineSystemParameters();

        ExprInitParameters exprInitParameters = new ExprInitParameters();
        exprInitParameters.setSystemParameters(systemParameters);
        exprInitParameters.setEntityManager(this.entityManager);

        return exprResponse.getResult(exprInitParameters);
    }

}
