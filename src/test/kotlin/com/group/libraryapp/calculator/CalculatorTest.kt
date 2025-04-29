package com.group.libraryapp.calculator

import org.junit.jupiter.api.Assertions.*

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2025. 04. 29
 * @description 순수 Kotlin Test
 */
fun main() {
    val calculatorTest = CalculatorTest()
    calculatorTest.addTest()
    calculatorTest.devideTest()
    calculatorTest.devideExceptionTest()
}

class CalculatorTest {

    fun addTest() {
        // given
        val calculator = Calculator(5)

        // when
        calculator.add(3)

        // then
        if (calculator.number != 8) {
            throw IllegalArgumentException()
        }
    }

    fun devideTest() {
        // given
        val calculator = Calculator(5)

        // when
        calculator.divide(2)

        // then
        if (calculator.number != 2) {
            throw IllegalArgumentException()
        }
    }

    fun devideExceptionTest() {
        // given
        val calculator = Calculator(5)

        // when
        try {
            calculator.divide(0)
        } catch (e: IllegalArgumentException) {
            // Test Success
            return
        } catch (e: Exception) {
            throw IllegalStateException()
        }

        throw IllegalStateException("기대하는 예외가 발생하지 않았습니다.")
    }
}