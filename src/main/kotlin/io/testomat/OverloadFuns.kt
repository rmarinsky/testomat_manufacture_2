package io.testomat

class OverloadFuns {

    fun ololo(id: Long, name: String = "default", type: String = "test default") {
        println("id = $id, name = $name, type = $type")
    }

}

fun main() {
    OverloadFuns().ololo(id = 1)
    OverloadFuns().ololo(id = 1, name = "test")
    OverloadFuns().ololo(id = 1, name = "test popo", type = "test iuiu")
}