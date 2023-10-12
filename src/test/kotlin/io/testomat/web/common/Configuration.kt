package io.testomat.web.common

object Configuration {

    lateinit var baseUrl: String
    var headless = true
    var devTools = false
    var defaultTimeout: Double = 4000.0
    var poolingInterval: Double = 100.0

}
