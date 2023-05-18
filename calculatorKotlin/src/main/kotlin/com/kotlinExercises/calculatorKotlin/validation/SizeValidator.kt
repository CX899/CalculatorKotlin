package com.kotlinExercises.calculatorKotlin.validation

class SizeValidator: Validator {
    override fun is_authorized(numList: List<Double>): Boolean {
        return (numList.size in 2..10)
    }
}