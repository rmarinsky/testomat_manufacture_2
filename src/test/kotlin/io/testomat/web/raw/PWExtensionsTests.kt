package io.testomat.web.raw

import com.github.javafaker.Faker
import com.microsoft.playwright.Locator
import com.microsoft.playwright.assertions.LocatorAssertions
import com.microsoft.playwright.assertions.PlaywrightAssertions
import io.testomat.web.common.Drama.find
import io.testomat.web.common.Drama.open
import io.testomat.web.common.KeysPW.ENTER
import io.testomat.web.common.containsText
import io.testomat.web.common.pressKey
import io.testomat.web.common.setVal
import io.testomat.web.common.shouldBeVisible
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PWExtensionsTests {

    private var faker = Faker()

    // New instance for each test method.


    @BeforeEach
    fun createContextAndPage() {

        open("https://uat.testomat.io/users/sign_in")
        find("#content-desktop #new_user").shouldBeVisible()
        find("#content-desktop #user_email").first()
            .fill("newromka@gmail.com")
        find("#content-desktop #user_password")
            .setVal(faker.book().title())
            .press("Enter")

        open("https://uat.testomat.io/projects/new")
    }


    @Test
    fun `olo demo with extensions`() {
        find("#project_title").fill(faker.book().title())
        find("[name='commit']").click()
        val preloader = find("#app-loader")
        find(".back").click()
        val targetTestSuiteName = faker.commerce().department()
        /*find("[placeholder='First Suite']").apply {
            fill(targetTestSuiteName)
            press("Enter")
        }*/

        find("[placeholder='First Suite']")
            .setVal(targetTestSuiteName)
            .pressKey(ENTER)

        find(".list-group-wrapper .dragSortItem a span").containsText(targetTestSuiteName)
    }


    fun isHidden(locator: Locator?) {
        PlaywrightAssertions.assertThat(locator).isHidden(LocatorAssertions.IsHiddenOptions().setTimeout(10000.0))
    }
}