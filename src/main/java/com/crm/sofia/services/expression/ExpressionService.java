package com.crm.sofia.services.expression;

import com.crm.sofia.model.expression.ExprResponce;
import com.crm.sofia.model.expression.ExprUnit;
import com.crm.sofia.model.expression.expressionUnits.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpressionService {


    public ExprResponce create(String expression) {

       if(expression.length() == 0) return null;

        Boolean brakcetCheck = this.checkBruckets(expression);
        if (!brakcetCheck) {
            return new ExprResponce("Error on Bracker openings & closings",false,expression,null);
        }

        Boolean quoteCheck = this.checkQuotes(expression);
        if (!quoteCheck) {
            return new ExprResponce("Error on Quotes openings & closings",false,expression,null);
        }

        expression = this.removeSpaces(expression);
        if(expression.length() == 0) return null;

        List<ExprUnit> exprUnits = this.createExprUnitList(expression);

        if (exprUnits == null) {
            return new ExprResponce("Could not read parameter",false,expression,null);
        }

        this.createBracketPriorities(exprUnits);
        exprUnits = this.removeBracketsFromList(exprUnits);
        Boolean treeCreated = this.createTree(exprUnits, exprUnits.get(0).getPriority());
        if (!treeCreated) {
            return new ExprResponce("Error on tree creation",false,expression,null);
        }


        ExprResponce exprResponce = new ExprResponce();
        exprResponce.setExpression(expression);
        exprResponce.setExprUnit(exprUnits.get(0).getTopParrent());
        return exprResponce;

    }


    /*
    * Removes useless spaces from the expression
    * This functionality checks Quotes, because spaces inside quote expression must not be removed
    * */
    private String removeSpaces(String expression) {

        Boolean outOfQuote = true;
        String prevExpressionPositionString = "";
        String expressionPositionString = "";
        String newExpression= "";
        for(int i=0;i<expression.length();i++){

            expressionPositionString = expression.substring(i, i + 1);

            if(expressionPositionString.equals("'")  && outOfQuote )
            {
                outOfQuote = false;
            } else if(expressionPositionString.equals("'") && !prevExpressionPositionString.equals("/") && !outOfQuote )
            {
                outOfQuote = true;
            }

            if(!outOfQuote || (outOfQuote && !expressionPositionString.equals(" ")) ) {
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

        for(int i=0;i<expression.length();i++){

            expressionPositionString = expression.substring(i, i + 1);

            if(expressionPositionString.equals("'")  && outOfQuote )
            {
                outOfQuote = false;
            } else if(expressionPositionString.equals("'") && !prevExpressionPositionString.equals("/") && !outOfQuote )
            {
                outOfQuote = true;
            }

            prevExpressionPositionString = expressionPositionString;
        }
        return outOfQuote;
    }


    private List<ExprUnit> removeBracketsFromList(List<ExprUnit> exprUnits) {
        return exprUnits.stream()
                .filter(
                        exprUnit -> !(exprUnit instanceof ExprOpenBracket || exprUnit instanceof ExprCloseBracket )
                ).collect(Collectors.toList());
    }


    private boolean createTree(List<ExprUnit> exprUnits, int currentPriority) {

       // int currentPriority = this.currentPriority ;
        int listPosition = 0;

        for (ExprUnit exprUnit : exprUnits) {


            /*
             * On those Types of ExprUnits at least 3 expr units must follow
             * 1. The first parameter, 2. Comma, 3.The second parameter
             * So they get on the same code section
             */
            if (    (exprUnit instanceof ExprStringConcat ||
                    exprUnit instanceof ExprDatePlus   ||
                    exprUnit instanceof ExprDoubleNumberAddition ||
                    exprUnit instanceof ExprDoubleNumberDivision ||
                    exprUnit instanceof ExprDoubleNumberMultiplication ||
                    exprUnit instanceof ExprDoubleNumberSubtraction ||
                    exprUnit instanceof ExprNumberTrim ||
                    exprUnit instanceof ExprStringConcat ||
                    exprUnit instanceof ExprStringReplace ||
                    exprUnit instanceof ExprStringReplace)
                    && !exprUnit.getIsOnTree()
                    &&  exprUnit.getPriority().equals(currentPriority)
            ) {


                /*
                 * On those Types of ExprUnits at least 3 expr units must follow
                 * 1. The first parameter, 2. Comma, 3.The second parameter
                 * If not return error on tree creation
                 */
                if( (listPosition+3) > exprUnits.size()) return false;


                /*
                 * Run createTree for the next Priority level
                 * (inside the brackets of the current expression there is a higher priority)
                 * It must be executed first
                 */
                boolean treeCreationResult = this.createTree(exprUnits,exprUnits.get(listPosition+1).getPriority());
                if(!treeCreationResult) return false;

                /*
                 * Set LeftChildExprUnit on the current exprUnit
                 */
                exprUnit.setLeftChildExprUnit( exprUnits.get(listPosition+1).getTopParrent() );
                exprUnits.get(listPosition+1).getTopParrent().setParentExprUnit(exprUnit);

                /*
                 * Set RightChildExprUnit on the current exprUnit
                 */
                int rightChildPosition = this.traceRightChildPosition(exprUnits, listPosition);
                if(rightChildPosition== 0) return false;
                exprUnit.setRightChildExprUnit( exprUnits.get(rightChildPosition).getTopParrent() );
                exprUnits.get(rightChildPosition).getTopParrent().setParentExprUnit(exprUnit);


                exprUnit.setIsOnTree(true);

            }

            /*
             * On those Types of ExprUnits at least 1 expr unit must follow
             * So they get on the same code section
             */
            if (    (exprUnit instanceof ExprDateNowPlus ||
                    exprUnit instanceof ExprYyyyMmDdHhMmStringToDate   ||
                    exprUnit instanceof ExprYyyyMmDdStringToDate)
                    && !exprUnit.getIsOnTree()
                    &&  exprUnit.getPriority().equals(currentPriority)
            ) {


                /*
                 * On those Types of ExprUnits at least 1 expr unit must follow
                 * If not return error on tree creation
                 */
                if( (listPosition+1) > exprUnits.size()) return false;


                /*
                 * Run createTree for the next Priority level
                 * (inside the brackets of the current expression there is a higher priority)
                 * It must be executed first
                 */
                boolean treeCreationResult = this.createTree(exprUnits,exprUnits.get(listPosition+1).getPriority());
                if(!treeCreationResult) return false;

                /*
                 * Set ChildExprUnit on the current exprUnit
                 */
                exprUnit.setChildExprUnit( exprUnits.get(listPosition+1).getTopParrent() );
                exprUnits.get(listPosition+1).getTopParrent().setParentExprUnit(exprUnit);


                exprUnit.setIsOnTree(true);

            }


            listPosition++;
        }

        return true;
    }

    private int traceRightChildPosition(List<ExprUnit> exprUnits, int listPosition) {

        int currentPriority = exprUnits.get(listPosition).getPriority();
        /*
         * We search for the commaExprUnit on the same priority, after that we have the second parameter
         * We search  from listPosition+2 because
         * +1 is be the firt parameter of the current exprUnit , only after that can be the comma
         */
        for (int i=listPosition+2; i < exprUnits.size();i++) {
            if(exprUnits.get(i) instanceof ExprComma && exprUnits.get(i).getPriority().equals(currentPriority)){
                return i+1;
            }
        }
        return 0;
    }

//    private void createTree(List<ExprUnit> exprUnits, int priority) {
//        if (priority < 0) return;
//        int listPosition = 0;
//        for (ExprUnit exprUnit : exprUnits) {
//            exprUnit.joinTree(exprUnits, listPosition, priority );
//            listPosition++;
//        }
//
//        this.createTree(exprUnits, priority - 1);
//
//    }

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
            if (exprUnit == null) exprUnit = ExprOpenBracket.exrtactExprUnit(expression, i);
            if (exprUnit == null) exprUnit = ExprCloseBracket.exrtactExprUnit(expression, i);
            if (exprUnit == null) exprUnit = ExprDateNowPlus.exrtactExprUnit(expression, i);
            if (exprUnit == null) exprUnit = ExprDatePlus.exrtactExprUnit(expression, i);
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
    Boolean checkBruckets(String expression) {
        Integer opened = 0;
        Integer closed = 0;
        Integer length = expression.length();
        String stringAtPosition = "";

        for (int i = 0; i < length; i++) {
            stringAtPosition = expression.substring(i, i + 1);
            if (stringAtPosition.equals("(")) opened++;
            if (stringAtPosition.equals(")")) closed++;
        }

        if (opened == closed) return true;
        else return false;
    }


}
