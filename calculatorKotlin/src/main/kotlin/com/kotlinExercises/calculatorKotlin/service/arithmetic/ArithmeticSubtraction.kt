package com.kotlinExercises.calculatorKotlin.service.arithmetic

import com.kotlinExercises.calculatorKotlin.utility.cxLogger
import com.kotlinExercises.calculatorKotlin.validation.NumberValidation
import com.kotlinExercises.calculatorKotlin.validation.SizeValidator

class ArithmeticSubtraction(private val sizeValidator: SizeValidator, private val numberValidator: NumberValidation): ArithmeticService {
    override fun calculate(numList: List<Double>, logger: cxLogger): Double{
        val badReturn = 0.0

        if (!numberValidator.is_authorized(numList)) {
            logger.warn("Invalid inputs received for subtraction service")
            return badReturn
        }

        if (!sizeValidator.is_authorized(numList)){
            logger.warn("Inputs list size for subtraction service must be from 2 to 10")
            return badReturn
        }

        val subtrahend = numList.drop(1).sum()
        val minuend = numList[0]
        val result = minuend - subtrahend

        logger.info("Subtraction completed. Result: $result.")
        return result
    }
}