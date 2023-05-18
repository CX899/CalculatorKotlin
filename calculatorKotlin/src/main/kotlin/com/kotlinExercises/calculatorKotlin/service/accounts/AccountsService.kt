package com.kotlinExercises.calculatorKotlin.service.accounts

import com.kotlinExercises.calculatorKotlin.utility.cxLogger

interface AccountsService {
    fun performAction(numList: List<Double>, logger: cxLogger): Double
}