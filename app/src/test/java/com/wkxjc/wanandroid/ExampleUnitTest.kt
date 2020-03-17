package com.wkxjc.wanandroid

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
        val a = listOf(1, 2, 3, 4, 5)
        run outside@{
            a.forEach {
                println(it)
                if (it > 3) return@outside
                println("end$it")
            }
        }
        return
    }
}
