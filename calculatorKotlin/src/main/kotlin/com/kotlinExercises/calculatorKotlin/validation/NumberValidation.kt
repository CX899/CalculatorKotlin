package com.kotlinExercises.calculatorKotlin.validation
class NumberValidation: Validator {
    private val maxDecimalPlaces = 5 // Maximum allowed decimal places
    private val maxSignificantDigits = 10 // Maximum allowed significant digits

    override fun is_authorized(numList: List<Double>): Boolean {
        for (i in numList){
            if (!isValidNumber(i)){
                return false
            }
        }
        return true
    }
    private fun isValidNumber(value: Double): Boolean {
        // Data type validation
        if (!isNumeric(value)) {
            return false
        }

        // Input range validation (optional)
        val maxAllowedValue = 100.0
        val minAllowedValue = -100.0
        if (value > maxAllowedValue || value < minAllowedValue) {
            return false
        }

        // Precision validation (optional)
        return !(getDecimalPlaces(value) > maxDecimalPlaces || getSignificantDigits(value) > maxSignificantDigits)
    }

    private fun isNumeric(value: Double): Boolean {
        val numericRegex = """-?\d{1,7}(\.\d{1,7})?""".toRegex()
        return value.toString().matches(numericRegex)
    }

    private fun getDecimalPlaces(value: Double): Int {
        val decimalRegex = """\.(\d{1,7})""".toRegex()
        val matchResult = decimalRegex.find(value.toString())
        return matchResult?.groupValues?.get(1)?.length ?: 0
    }

    private fun getSignificantDigits(value: Double): Int {
        val digitsRegex = """\d""".toRegex()
        val digitCount = digitsRegex.findAll(value.toString()).count()
        return if (value < 0) digitCount - 1 else digitCount
    }


}