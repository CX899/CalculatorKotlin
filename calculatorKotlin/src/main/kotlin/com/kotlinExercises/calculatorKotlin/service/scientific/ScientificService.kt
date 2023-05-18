package com.kotlinExercises.calculatorKotlin.service.scientific

import com.kotlinExercises.calculatorKotlin.utility.cxLogger

interface ScientificService {
    fun calculate(expression: String): Double
}