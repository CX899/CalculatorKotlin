package com.kotlinExercises.calculatorKotlin.service.scientific

import com.kotlinExercises.calculatorKotlin.service.arithmetic.*
import com.kotlinExercises.calculatorKotlin.utility.cxLogger
import com.kotlinExercises.calculatorKotlin.validation.DivisionByZeroValidation
import com.kotlinExercises.calculatorKotlin.validation.NumberValidation
import com.kotlinExercises.calculatorKotlin.validation.SizeValidator

class ScientificPEMDAS : ScientificService {
    override fun calculate(expression: String): Double {
        val logger = cxLogger
        return evaluateExpression(expression, logger)
    }

    private val operators = mapOf(
        '+' to ArithmeticAddition(SizeValidator(), NumberValidation()),
        '-' to ArithmeticSubtraction(SizeValidator(), NumberValidation()),
        '*' to ArithmeticMultiplication(SizeValidator(), NumberValidation()),
        '/' to ArithmeticDivision(DivisionByZeroValidation(), NumberValidation())
    )

    fun evaluateExpression(expression: String, logger: cxLogger): Double {
        val postfixExpression = convertToPostfix(expression)
        val operandStack = mutableListOf<Double>()

        for (token in postfixExpression) {
            if (token.isDigit()) {
                operandStack.add(token.toString().toDouble())
            } else if (isOperator(token)) {
                val operator = operators.getValue(token)
                val operand2 = operandStack.removeLast()
                val operand1 = operandStack.removeLast()
                val listOp = listOf(operand1, operand2)
                val result = operator.calculate(listOp, logger)
                operandStack.add(result)
            }
        }

        return operandStack.last()
    }

    private fun convertToPostfix(expression: String): List<Char> {
        val postfix = mutableListOf<Char>()
        val operatorStack = mutableListOf<Char>()

        for (ch in expression.toCharArray()) {
            if (ch.isDigit()) {
                postfix.add(ch)
            } else if (isOperator(ch)) {
                while (operatorStack.isNotEmpty() && hasHigherPrecedence(operatorStack.last(), ch)) {
                    postfix.add(operatorStack.removeLast())
                }
                operatorStack.add(ch)
            } else if (ch == '(') {
                operatorStack.add(ch)
            } else if (ch == ')') {
                while (operatorStack.isNotEmpty() && operatorStack.last() != '(') {
                    postfix.add(operatorStack.removeLast())
                }
                operatorStack.removeLast() // Discard the opening parenthesis
            }
        }

        while (operatorStack.isNotEmpty()) {
            postfix.add(operatorStack.removeLast())
        }

        return postfix
    }

    private fun isOperator(ch: Char): Boolean {
        return operators.containsKey(ch)
    }

    private fun hasHigherPrecedence(operator1: Char, operator2: Char): Boolean {
        val precedence1 = getOperatorPrecedence(operator1)
        val precedence2 = getOperatorPrecedence(operator2)

        return precedence1 >= precedence2
    }

    private fun getOperatorPrecedence(operator: Char): Int {
        return when (operator) {
            '*', '/' -> 2
            '+', '-' -> 1
            else -> 0
        }
    }

}