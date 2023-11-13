package io.testomat.web.playwright;

import com.github.javafaker.Faker;
import com.microsoft.playwright.BrowserContext;
import io.testomat.web.common.PWContextExtension;
import io.testomat.web.common.pw.Configuration;
import io.testomat.web.common.pw.PlaywrightWrapper;
import io.testomat.web.common.pw.conditions.Condition;
import io.testomat.web.pages.pw.LoginPagePW;
import io.testomat.web.pages.pw.ProjectsPagePW;
import io.testomat.web.pages.pw.TestSuitesPagePW;
import net.bytebuddy.build.Plugin;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static io.testomat.web.common.pw.PlaywrightWrapper.$;
import static io.testomat.web.common.pw.PlaywrightWrapper.open;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(PWContextExtension.class)
@Tag("pwweb")
public class PWWrapperTests {
    private static final String STORAGE_STATE_FILE = "playwright/.auth/state.json";

    Faker faker = new Faker();

    private final LoginPagePW loginPage = new LoginPagePW();

    static {
        Configuration.baseUrl = "https://uat.testomat.io";
        Configuration.headless = false;
        Configuration.saveTraces = true;
        Configuration.poolingInterval = 0;
    }

    @Test
    @Order(1)
    @DisplayName("Should be possible to create test suite for new project")
    void shouldBePossibleToCreateTestSuiteForNewProject() {
        open("/users/sign_in");
        loginPage
                .isLoaded()
                .loginUser("newromka@gmail.com", "3y77b7HzrL2ebwQ!"); //or loginUser(CredsWithRoles.MANAGER);

        PlaywrightWrapper.saveStorageState(STORAGE_STATE_FILE);

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

    @Test
    @Order(2)
    @DisplayName("B Should be possible to create test suite for new project with SignIn State")
    void shouldBePossibleToCreateTestSuiteForNewProject2() {
        // Load the saved storage state before running the test
        BrowserContext context = PlaywrightWrapper.createNewContextWithStorageState(STORAGE_STATE_FILE);

        // Set the new context with the loaded storage state in the PWContainer
        PlaywrightWrapper.pw().setContext(context);

        open("");

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

    @Order(3)
    @ParameterizedTest
    @MethodSource("olodata")
    @DisplayName("wsdlfkjasd [{index}] {argumentsWithNames}")
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
