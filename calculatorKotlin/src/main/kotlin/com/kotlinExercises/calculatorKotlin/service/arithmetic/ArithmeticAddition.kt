package com.kotlinExercises.calculatorKotlin.service.arithmetic

import com.kotlinExercises.calculatorKotlin.utility.cxLogger
import com.kotlinExercises.calculatorKotlin.validation.NumberValidation
import com.kotlinExercises.calculatorKotlin.validation.SizeValidator

class ArithmeticAddition(private val sizeValidator: SizeValidator, private val numberValidator: NumberValidation): ArithmeticService {
    override fun calculate(numList: List<Double>, logger: cxLogger): Double{
        val badReturn = 0.0

        if (!numberValidator.is_authorized(numList)) {
            logger.warn("Invalid inputs received for addition service")
            return badReturn
        }

        if (!sizeValidator.is_authorized(numList)){
            logger.warn("Inputs list size for addition service must be from 2 to 10")
            return badReturn
        }

        val result = numList.sum()
        logger.info("Addition completed. Result: $result.")
        return result
    }
}