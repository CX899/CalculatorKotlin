package com.kotlinExercises.calculatorKotlin

import com.kotlinExercises.calculatorKotlin.service.arithmetic.ArithmeticDivision
import com.kotlinExercises.calculatorKotlin.service.scientific.ScientificPEMDAS
import com.kotlinExercises.calculatorKotlin.utility.cxLogger
import com.kotlinExercises.calculatorKotlin.validation.DivisionByZeroValidation
import com.kotlinExercises.calculatorKotlin.validation.NumberValidation
import com.kotlinExercises.calculatorKotlin.validation.Validator
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CalculatorKotlinApplication

fun main(args: Array<String>) {

	val ok = ArithmeticDivision(DivisionByZeroValidation(), NumberValidation()).calculate(listOf(1.0,2.0), cxLogger)
	println(ok)
	runApplication<CalculatorKotlinApplication>(*args)
}
