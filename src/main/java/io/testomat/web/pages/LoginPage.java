package io.testomat.web.pages;

import com.codeborne.selenide.Condition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class LoginPage extends BasePage {

    public LoginPage isLoaded() {
        f("h2").shouldHave(Condition.text("Sign in"));
        return this;
    }

    public void loginUser(String mail, String password) {
        fillEmail(mail);
        fillPassword(password);
        submitLogin();
    }

    public void loginUser(CredsWithRoles targetUser) {
        fillEmail(targetUser.mail);
        fillPassword(targetUser.password);
        submitLogin();
    }

    public LoginPage submitLogin() {
        f("[name='commit']").as("Submit button").click();
        return this;
    }

    public LoginPage fillPassword(String password) {
        f("#user_password").setValue(password);
        return this;
    }

    public LoginPage fillEmail(String mail) {
        f("#user_email").setValue(mail);
        return this;
    }

    public void preloaderIsHidden() {
        $("#app-loader").shouldBe(Condition.disappear, Duration.ofSeconds(30));
    }

    @Data
    @AllArgsConstructor
    public static class Creds {

        public String mail;
        public String password;

    }

    @AllArgsConstructor
    public enum CredsWithRoles {

        MANAGER("newromka@gmail.com", "p8qfCZ7Jv7pT!hh");


        private final String mail;
        private final String password;

    }

    //just for example
    @AllArgsConstructor
    @Getter
    public enum CredsWithRolesAnother {

        MANAGER(new Creds("newromka@gmail.com", "p8qfCZ7Jv7pT!hh"));

        private final Creds creds;

    }


}