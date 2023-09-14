package io.testomat;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import io.testomat.web.asserts.TestSuitesPageAsserts;
import io.testomat.web.pages.LoginPage;
import io.testomat.web.pages.ProjectsPage;
import io.testomat.web.pages.TestSuitesPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class CreateTestSuitePOTest {

    Faker faker = new Faker();

    private final LoginPage loginPage = new LoginPage();

    static {
        Configuration.baseUrl = "https://app.testomat.io";
    }

    @Test
    @DisplayName("Should be possible to create test suite for new project")
    void shouldBePossibleToCreateTestSuiteForNewProject() {
        Selenide.open("/users/sign_in");
        loginPage
                .isLoaded()
                .loginUser("newromka@gmail.com", "p8qfCZ7Jv7pT!hh"); //or loginUser(CredsWithRoles.MANAGER);

        preloaderIsHidden();

        var targetProjectTitle = faker.commerce().department();
        new ProjectsPage()
                .isLoaded()
                .clickOnNewProjectButton()
                .fillProjectTitle(targetProjectTitle)
                .submitProjectCreation();

        preloaderIsHidden();

        String targetTestSuite = faker.commerce().productName();

        new TestSuitesPage()
                .isLoaded()
                .closeReadmeModal()
                .fillFirstTestSuite(targetTestSuite)

                .asserts()
                .listShouldHaveSize(1)
                .firstTestSuiteInListShouldHaveText(targetTestSuite);

        //just for example
        new TestSuitesPageAsserts()
                .listShouldHaveSize(1)
                .firstTestSuiteInListShouldHaveText(targetTestSuite);
    }

    private void preloaderIsHidden() {
        $("#app-loader").shouldBe(Condition.disappear, Duration.ofSeconds(30));
    }

}
