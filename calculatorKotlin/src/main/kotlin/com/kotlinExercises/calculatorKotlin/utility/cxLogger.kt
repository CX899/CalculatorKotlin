package com.kotlinExercises.calculatorKotlin.utility

import org.slf4j.LoggerFactory

object cxLogger {

    private val instance = LoggerFactory.getLogger(this::class.java)

    fun info(message: String){
        instance.info(message)
    }

    fun warn(message: String){
        instance.warn(message)
    }

}