package com.kotlinExercises.calculatorKotlin.service.accounts

import com.kotlinExercises.calculatorKotlin.utility.cxLogger
import com.kotlinExercises.calculatorKotlin.validation.AccountInputValidation
import com.kotlinExercises.calculatorKotlin.validation.NumberValidation

class AccountsInterest(private val validator: AccountInputValidation): AccountsService {
    override fun performAction(numList: List<Double>, logger: cxLogger): Double {
        val badReturn = 0.0

        if (!validator.is_authorized(numList)){
            logger.warn("Invalid inputs received for account interest service.")
            return badReturn
        }

        val balance = numList[0]
        val interestRate = numList[1] / 100
        val numYears = numList[2]
        val result = balance * interestRate * numYears

        logger.info("Interest calculation completed. Result: $result.")
        return result
    }
}