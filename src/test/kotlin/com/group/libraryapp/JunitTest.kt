package com.group.libraryapp

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * @author 이동엽(Lee Dongyeop)
 * @date 2025. 04. 29
 * @description
 */
class JunitTest {

    companion object {

        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            println("모든 테스트 시작 전")
        }

        @JvmStatic
        @AfterAll
        fun afterAll() {
            println("모든 테스트 종료 후")
        }
    }

    @BeforeEach
    fun setUp() {
        println("각 테스트 시작 전")
    }

    @AfterEach
    fun tearDown() {
        println("각 테스트 종료 후")
    }

    @Test
    fun test1() {
        println("테스트 1")
    }

    @Test
    fun test2() {
        println("테스트 2")
    }
}