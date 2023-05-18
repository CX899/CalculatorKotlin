package com.kotlinExercises.calculatorKotlin.controller

import com.kotlinExercises.calculatorKotlin.model.AccountsModel
import com.kotlinExercises.calculatorKotlin.model.ArithmeticModel
import com.kotlinExercises.calculatorKotlin.model.ScientificModel
import com.kotlinExercises.calculatorKotlin.service.accounts.AccountsInterest
import com.kotlinExercises.calculatorKotlin.service.arithmetic.ArithmeticAddition
import com.kotlinExercises.calculatorKotlin.service.arithmetic.ArithmeticDivision
import com.kotlinExercises.calculatorKotlin.service.arithmetic.ArithmeticMultiplication
import com.kotlinExercises.calculatorKotlin.service.arithmetic.ArithmeticSubtraction
import com.kotlinExercises.calculatorKotlin.service.scientific.ScientificPEMDAS
import com.kotlinExercises.calculatorKotlin.utility.cxLogger
import com.kotlinExercises.calculatorKotlin.validation.AccountInputValidation
import com.kotlinExercises.calculatorKotlin.validation.DivisionByZeroValidation
import com.kotlinExercises.calculatorKotlin.validation.NumberValidation
import com.kotlinExercises.calculatorKotlin.validation.SizeValidator
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/calculator")
class CalculatorController (private val logger: cxLogger = cxLogger ){
    val sizeValidator = SizeValidator()
    val divisionByZeroValidator = DivisionByZeroValidation()
    val numberValidator = NumberValidation()

    @RequestMapping("/Arithmetic/Addition")
    fun getAdditionConfiguration(@RequestBody arithmeticModel: ArithmeticModel): Double{
        val addition =ArithmeticAddition(sizeValidator = sizeValidator, numberValidator = numberValidator)
        val result = addition.calculate(arithmeticModel.numbers, logger)
        return result
    }

    @PostMapping("/Arithmetic/Subtraction")
    fun getSubtractionConfiguration(@RequestBody arithmeticModel: ArithmeticModel): Double {
        val subtraction =ArithmeticSubtraction(sizeValidator = sizeValidator,numberValidator = numberValidator)
        val result = subtraction.calculate(arithmeticModel.numbers, logger)
        return result
    }

    @PostMapping("/Arithmetic/Multiplication")
    private fun getMultiplicationConfiguration(@RequestBody arithmeticModel: ArithmeticModel): Double {
        val multiplication =ArithmeticMultiplication(sizeValidator = sizeValidator, numberValidator = numberValidator)
        val result = multiplication.calculate(arithmeticModel.numbers, logger)
        return result
    }
    @PostMapping("/Arithmetic/Division")
    private fun getDivisionConfiguration(@RequestBody arithmeticModel: ArithmeticModel): Double {
        val division =ArithmeticDivision(zeroDivisionValidator = divisionByZeroValidator, numberValidator = numberValidator)
        val result = division.calculate(arithmeticModel.numbers, logger)
        return result
    }

    @PostMapping("/Scientific/PEMDAS")
    @ResponseBody
    private fun getScientificConfiguration(@RequestBody scientificModel: ScientificModel): Double {
        val pemdas =ScientificPEMDAS()
        val result = pemdas.calculate(scientificModel.expression)
        return result
    }

    @PostMapping("/Account/Interest")
    @ResponseBody
    private fun getAccountsConfiguration(@RequestBody accountsService: AccountsModel): Double {
        val interest = AccountsInterest(AccountInputValidation(NumberValidation(), logger))
        val result = interest.performAction(accountsService.numbers, logger)
        return result
    }

}