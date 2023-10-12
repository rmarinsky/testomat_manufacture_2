package io.testomat

import com.github.javafaker.Faker

data class SuiteDto(
    var name: String = Faker().name().title(),
    var id: Long,
    var type: String = Faker().book().title()
)


fun main() {
    SuiteDto(id = 1).apply {
        println(this)
    }

}
