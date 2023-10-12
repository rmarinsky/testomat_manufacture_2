package io.testomat.web

import io.testomat.web.pages.pw.LoginPagePW
import org.junit.jupiter.api.Test

class DemoTest {

    @Test
    fun `my test name`() {
        LoginPagePW().apply {
            fillEmail("")
            fillPassword("")
            submitLogin()
        }
    }


}