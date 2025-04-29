package com.group.libraryapp.calculator

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2025. 04. 29
 * @description
 */
class Calculator(
    private var _number: Int
) {
    val number: Int
        get() = this._number

    fun add(operand: Int) {
        this._number += operand
    }

    fun minus(operand: Int) {
        this._number -= operand
    }

    fun multiply(operand: Int) {
        this._number *= operand
    }

    fun divide(operand: Int) {
        if (operand == 0) {
            throw IllegalArgumentException("0으로 나눌 수 없습니다")
        }
        this._number /= operand
    }
}