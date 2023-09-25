package io.testomat.playwright;

import com.github.javafaker.Faker;
import io.testomat.common.PWContextExtension;
import io.testomat.common.pw.Configuration;
import io.testomat.common.pw.PlaywrightWrapper;
import io.testomat.common.pw.conditions.Condition;
import io.testomat.web.pages.pw.LoginPagePW;
import io.testomat.web.pages.pw.ProjectsPagePW;
import io.testomat.web.pages.pw.TestSuitesPagePW;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith(PWContextExtension.class)
public class PWWrapperTests {

    Faker faker = new Faker();

    private final LoginPagePW loginPage = new LoginPagePW();

    static {
        Configuration.baseUrl = "https://app.testomat.io";
        Configuration.headless = false;
        Configuration.saveTraces = true;
        Configuration.poolingInterval = 100;
    }

    @Test
    @DisplayName("Should be possible to create test suite for new project")
    void shouldBePossibleToCreateTestSuiteForNewProject() {
        PlaywrightWrapper.open("/users/sign_in");
        loginPage
                .isLoaded()
                .loginUser("newromka@gmail.com", "p8qfCZ7Jv7pT!hh"); //or loginUser(CredsWithRoles.MANAGER);

        preloaderIsHidden();

        var targetProjectTitle = faker.commerce().department();
        new ProjectsPagePW()
                .isLoaded()
                .clickOnNewProjectButton()
                .fillProjectTitle(targetProjectTitle)
                .submitProjectCreation();

        preloaderIsHidden();

        String targetTestSuite = faker.commerce().productName();

        new TestSuitesPagePW()
                .isLoaded()
                .closeReadmeModal()
                .fillFirstTestSuite(targetTestSuite)

                .asserts()
                .listShouldHaveSize(1)
                .firstTestSuiteInListShouldHaveText(targetTestSuite);
    }

    private void preloaderIsHidden() {
        PlaywrightWrapper.$("#app-loader").shouldBe(Condition.disappear);
    }

}
