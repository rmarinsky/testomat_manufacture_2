package io.testomat.web

import com.github.javafaker.Faker
import io.testomat.web.common.PWContextExtension
import io.testomat.web.common.pw.Configuration
import io.testomat.web.common.pw.PlaywrightWrapper
import io.testomat.web.common.pw.conditions.Condition
import io.testomat.web.pages.pw.LoginPagePW
import io.testomat.web.pages.pw.ProjectsPagePW
import io.testomat.web.pages.pw.TestSuitesPagePW
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(PWContextExtension::class)
@Tag("pwweb")
class PWWrapperTests {
    var faker = Faker()
    private val loginPage = LoginPagePW()

    @Test
    @DisplayName("Should be possible to create test suite for new project")
    fun shouldBePossibleToCreateTestSuiteForNewProject() {
        PlaywrightWrapper.open("/users/sign_in")
        loginPage
            .isLoaded()
            .loginUser("newromka@gmail.com", "3y77b7HzrL2ebwQ!") //or loginUser(CredsWithRoles.MANAGER);

        preloaderIsHidden()

        val targetProjectTitle = faker.commerce().department()
        ProjectsPagePW()
            .isLoaded()
            .clickOnNewProjectButton()
            .fillProjectTitle(targetProjectTitle)
            .submitProjectCreation()
        preloaderIsHidden()

        val targetTestSuite = "${faker.commerce().productName()} by_autotest"

        TestSuitesPagePW()
            .isLoaded()
            .closeReadmeModal()
            .fillFirstTestSuite(targetTestSuite)
            .asserts()
            .listShouldHaveSize(1)
            .firstTestSuiteInListShouldHaveText(targetTestSuite)
    }

    private fun preloaderIsHidden() {
        PlaywrightWrapper.`$`("#app-loader").shouldBe(Condition.disappear)
    }

    companion object {
        init {
            Configuration.baseUrl = "https://uat.testomat.io"
            Configuration.headless = false
            Configuration.saveTraces = true
            Configuration.poolingInterval = 0.0
        }

        private fun olodata(): List<String> {
            return listOf("")
        }
    }
}
