package com.kotlinExercises.calculatorKotlin.validation

class DivisionByZeroValidation: Validator {
    override fun is_authorized(numList: List<Double>): Boolean {
        val denominator = numList[1]
        return denominator != 0.0
    }
}
