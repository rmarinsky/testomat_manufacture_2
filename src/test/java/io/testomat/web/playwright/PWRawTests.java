package io.testomat.web.playwright;

import com.github.javafaker.Faker;
import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PWRawTests {


    static Playwright playwright;
    static Browser browser;

    Faker faker = new Faker();

    // New instance for each test method.
    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                        .setChannel("chrome")
                .setHeadless(false)
                .setSlowMo(0)
        );
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();

        page.navigate("https://uat.testomat.io/users/sign_in");
        PlaywrightAssertions.assertThat(page.locator("#content-desktop #new_user")).isVisible(
                new LocatorAssertions.IsVisibleOptions().setTimeout(20000));

        page.locator("#content-desktop #user_email").first().fill("newromka@gmail.com");
        Locator passwordField = page.locator("#content-desktop #user_password");
        passwordField.fill("3y77b7HzrL2ebwQ!");
        passwordField.press("Enter");

        isHidden(passwordField);

        page.navigate("https://uat.testomat.io/projects/new");
    }

    @Test
    @DisplayName("first test on playwright")
    void firstTestOnPlaywrigh() {
        page.locator("#project_title").fill(faker.book().title());

        page.locator("[name='commit']").click();

        Locator preloader = page.locator("#app-loader");

        page.locator(".back").click();

        String targetTestSuiteName = faker.commerce().department();
        Locator firstTestSuiteFiled = page.locator("[placeholder='First Suite']");
        firstTestSuiteFiled.fill(targetTestSuiteName);
        firstTestSuiteFiled.press("Enter");

        assertThat(page.locator((".list-group-wrapper .dragSortItem a span"))).hasText(targetTestSuiteName);
    }

    public void isHidden(Locator locator) {
        PlaywrightAssertions.assertThat(locator).isHidden(new LocatorAssertions.IsHiddenOptions().setTimeout(10000));
    }


    @AfterEach
    void closeContext() {
        context.close();
    }

}
