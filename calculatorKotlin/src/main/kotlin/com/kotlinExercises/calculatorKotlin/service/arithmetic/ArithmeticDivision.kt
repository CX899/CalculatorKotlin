package com.kotlinExercises.calculatorKotlin.service.arithmetic

import com.kotlinExercises.calculatorKotlin.utility.cxLogger
import com.kotlinExercises.calculatorKotlin.validation.DivisionByZeroValidation
import com.kotlinExercises.calculatorKotlin.validation.NumberValidation
import com.kotlinExercises.calculatorKotlin.validation.Validator

class ArithmeticDivision(private val zeroDivisionValidator: DivisionByZeroValidation, private val numberValidator: NumberValidation): ArithmeticService {
    override fun calculate(numList: List<Double>, logger: cxLogger): Double {
        val badReturn = 0.0

        if (numList.size != 2) {
            logger.warn("Invalid number of inputs received for division service. Input only 2 numbers")
            return badReturn
        }

        if (!numberValidator.is_authorized(numList)) {
            logger.warn("Invalid inputs received for division service")
            return badReturn
        }

        if (!zeroDivisionValidator.is_authorized(numList)) {
            logger.warn("Invalid denominator input: Cannot divide a number by 0")
            return badReturn
        }

        val numerator = numList[0]
        val denominator = numList[1]
        val result = numerator / denominator

        logger.info("Division completed. Result: $result.")
        return result
    }
}
