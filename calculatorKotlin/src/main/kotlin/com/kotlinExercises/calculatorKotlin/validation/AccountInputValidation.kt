package com.kotlinExercises.calculatorKotlin.validation

import com.kotlinExercises.calculatorKotlin.utility.cxLogger

class AccountInputValidation(private val validation: NumberValidation, private val logger: cxLogger): Validator {
    override fun is_authorized(numList: List<Double>): Boolean {
        if (numList.size != 3) {
            logger.warn("Invalid number of inputs received for account interest service. Input only 3 numbers: Balance, Interest rate, Number of years")
            return false
        }

        if (!validation.is_authorized(numList)) {
            logger.warn("Invalid inputs received for account interest calculation service")
            return false
        }

        if (!positiveBalanceValidator(numList)) {
            logger.warn("Invalid balance input: Balance must be positive")
            return false
        }

        if (!interestValidation(numList)) {
            logger.warn("Invalid interest rate input : Interest rate must be positive")
            return false
        }

        if (!positiveYearValidation(numList)) {
            logger.warn("Invalid number of year input : Number of year must be positive")
            return false
        }

        return true
    }
    private fun interestValidation(numList: List<Double>): Boolean {
        val interestRate = numList[1]
        return interestRate > 0
    }

    private fun positiveBalanceValidator(numList: List<Double>): Boolean {
        val balance = numList[0]
        return balance > 0
    }

    private fun positiveYearValidation(numList: List<Double>): Boolean {
        val years = numList[2]
        return years > 0
    }

}