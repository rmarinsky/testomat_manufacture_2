package io.testomat.api

import com.github.javafaker.Faker
import org.junit.jupiter.api.Test

class APiTest {


    @Test
    fun `demo test api for new reified controller`() {
        val targetSuite = io.testomat.SuiteDto(id = Long.MAX_VALUE, name = Faker().name().firstName())

        println(SuiteController().postSuite(targetSuite))
    }
}
