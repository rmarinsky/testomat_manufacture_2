package io.testomat.web.raw

import com.github.javafaker.Faker
import com.microsoft.playwright.*
import com.microsoft.playwright.BrowserType.LaunchOptions
import com.microsoft.playwright.assertions.LocatorAssertions
import com.microsoft.playwright.assertions.PlaywrightAssertions
import org.junit.jupiter.api.*

class PWRawTests {
    private var faker = Faker()

    // New instance for each test method.
    private var context: BrowserContext? = null
    private var page: Page? = null

    @BeforeEach
    fun createContextAndPage() {
        context = browser!!.newContext()
        page = context?.newPage()
        page?.navigate("https://uat.testomat.io/users/sign_in")
        PlaywrightAssertions.assertThat(page?.locator("#content-desktop #new_user")).isVisible(
            LocatorAssertions.IsVisibleOptions().setTimeout(20000.0)
        )
        page?.locator("#content-desktop #user_email")?.first()?.fill("newromka@gmail.com")
        val passwordField = page!!.locator("#content-desktop #user_password")
        passwordField.fill("3y77b7HzrL2ebwQ!")
        passwordField.press("Enter")
        isHidden(passwordField)
        page?.navigate("https://uat.testomat.io/projects/new")
    }

    @Test
    @DisplayName("first test on playwright")
    fun firstTestOnPlaywrigh() {
        page!!.locator("#project_title").fill(faker.book().title())
        page!!.locator("[name='commit']").click()
        val preloader = page!!.locator("#app-loader")
        page!!.locator(".back").click()
        val targetTestSuiteName = faker.commerce().department()
        val firstTestSuiteFiled = page!!.locator("[placeholder='First Suite']")
        firstTestSuiteFiled.fill(targetTestSuiteName)
        firstTestSuiteFiled.press("Enter")
        PlaywrightAssertions.assertThat(page!!.locator(".list-group-wrapper .dragSortItem a span"))
            .hasText(targetTestSuiteName)
    }

    fun isHidden(locator: Locator?) {
        PlaywrightAssertions.assertThat(locator).isHidden(LocatorAssertions.IsHiddenOptions().setTimeout(10000.0))
    }

    @AfterEach
    fun closeContext() {
        context!!.close()
    }

    companion object {
        var playwright: Playwright? = null
        var browser: Browser? = null

        @BeforeAll
        fun launchBrowser() {
            playwright = Playwright.create()
            browser = playwright?.chromium()?.launch(
                LaunchOptions()
                    .setHeadless(false)
                    .setSlowMo(0.0)
            )
        }

        @AfterAll
        fun closeBrowser() {
            playwright!!.close()
        }
    }
}
