package com.kotlinExercises.calculatorKotlin.validation

interface Validator {
    fun is_authorized(numList: List<Double>): Boolean
}