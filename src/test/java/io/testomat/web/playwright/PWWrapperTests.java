package io.testomat.web.playwright;

import com.github.javafaker.Faker;
import io.testomat.web.common.PWContextExtension;
import io.testomat.web.common.pw.Configuration;
import io.testomat.web.common.pw.conditions.Condition;
import io.testomat.web.pages.pw.LoginPagePW;
import io.testomat.web.pages.pw.ProjectsPagePW;
import io.testomat.web.pages.pw.TestSuitesPagePW;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static io.testomat.web.common.pw.PlaywrightWrapper.$;
import static io.testomat.web.common.pw.PlaywrightWrapper.open;


@ExtendWith(PWContextExtension.class)
@Tag("pwweb")
public class PWWrapperTests {

    Faker faker = new Faker();

    private final LoginPagePW loginPage = new LoginPagePW();

    static {
        Configuration.baseUrl = "https://uat.testomat.io";
        Configuration.headless = false;
        Configuration.saveTraces = true;
        Configuration.poolingInterval = 0;
    }

    @Test
    @DisplayName("Should be possible to create test suite for new project")
    void shouldBePossibleToCreateTestSuiteForNewProject() {
        open("/users/sign_in");
        loginPage
                .isLoaded()
                .loginUser("newromka@gmail.com", "3y77b7HzrL2ebwQ!"); //or loginUser(CredsWithRoles.MANAGER);

        preloaderIsHidden();

        final var targetProjectTitle = faker.commerce().department();
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

    @ParameterizedTest
    @MethodSource("olodata")
    @DisplayName("asdlfkjasd [{index}] {argumentsWithNames}")
    void asdlfkjasd(String targetString) {

        Assertions.fail();
    }

    private static List<String> olodata() {
        return List.of("");
    }

    private void preloaderIsHidden() {
        $("#app-loader").shouldBe(Condition.disappear);
    }

}
