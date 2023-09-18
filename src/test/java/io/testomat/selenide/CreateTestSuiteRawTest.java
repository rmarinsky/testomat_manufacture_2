package io.testomat.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreateTestSuiteRawTest {

    Faker faker = new Faker();
    
    @Test
    @DisplayName("Should be possible to create test suite for new project")
    void shouldBePossibleToCreateTestSuiteForNewProject() {
        Selenide.open("https://app.testomat.io/users/sign_in");
        loginUser("newromka@gmail.com", "p8qfCZ7Jv7pT!hh");

        preloaderIsHidden();
        $("#content-desktop h2").shouldBe(Condition.visible);
        $("#content-desktop [href='/projects/new']").click();

        preloaderIsHidden();
        $("#project-form #project_title").setValue(faker.commerce().department());
        $("[name='commit']").click();

        preloaderIsHidden();
        $("[placeholder='First Suite']").shouldBe(Condition.visible);
        $(".back").click();

        String targetTestSuite = faker.commerce().productName();

        $("[placeholder='First Suite']")
                .setValue(targetTestSuite)
                .pressEnter();

        $$(".list-group-wrapper .dragSortItem").shouldHave(size(1));
        $(".list-group-wrapper .dragSortList").shouldHave(text(targetTestSuite));
    }

    private static void loginUser(String mail, String password) {
        $("#content-desktop #user_email").setValue(mail);
        $("#content-desktop #user_password").setValue(password);
        $("#content-desktop [name='commit']").click();
    }

    private void preloaderIsHidden() {
        $("#app-loader").shouldBe(Condition.disappear, Duration.ofSeconds(30));
    }

}
