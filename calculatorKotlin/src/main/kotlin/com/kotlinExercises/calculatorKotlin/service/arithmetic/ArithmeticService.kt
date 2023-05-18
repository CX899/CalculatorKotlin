package com.kotlinExercises.calculatorKotlin.service.arithmetic

import com.kotlinExercises.calculatorKotlin.utility.cxLogger

interface ArithmeticService {
    fun calculate(numList: List<Double>, logger: cxLogger): Double

}