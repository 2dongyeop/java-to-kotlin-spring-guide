package com.group.libraryapp.calculator

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2025. 04. 29
 * @description
 */
class JUnitCalculatorTest {

    @Test
    fun addTest() {
        // given
        val calculator = Calculator(5)

        // when
        calculator.add(3)

        // then
        assertThat(calculator.number).isEqualTo(8)
    }

    @Test
    fun devideTest() {
        // given
        val calculator = Calculator(5)

        // when
        calculator.divide(2)

        // then
        assertThat(calculator.number).isEqualTo(2)
    }

    @Test
    fun devideExceptionTest() {
        // given
        val calculator = Calculator(5)

        // when
        // example1
        val message1 = assertThrows(IllegalArgumentException::class.java) {
            calculator.divide(0)
        }.message
        assertThat(message1).isEqualTo("0으로 나눌 수 없습니다")

        // example2
        val message2 = assertThrows<IllegalArgumentException> {
            calculator.divide(0)
        }.message
        assertThat(message2).isEqualTo("0으로 나눌 수 없습니다")

        // example3
        assertThrows<IllegalArgumentException> {
            calculator.divide(0)
        }.apply {
            assertThat(message).isEqualTo("0으로 나눌 수 없습니다")
        }
    }
}