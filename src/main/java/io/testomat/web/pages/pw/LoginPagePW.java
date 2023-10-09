package io.testomat.web.pages.pw;

import io.testomat.web.common.pw.conditions.Condition;
import io.testomat.web.pages.LoginPage;

import static io.testomat.web.common.pw.PlaywrightWrapper.$;

public class LoginPagePW extends BasePage {

      public LoginPagePW isLoaded() {
        f("h2").shouldHave(Condition.text("Sign in"));
        return this;
    }

    public void loginUser(String mail, String password) {
        fillEmail(mail);
        fillPassword(password);
        submitLogin();
    }

    public void loginUser(LoginPage.CredsWithRoles targetUser) {
        fillEmail(targetUser.mail);
        fillPassword(targetUser.password);
        submitLogin();
    }

    public LoginPagePW submitLogin() {
        f("[name='commit']").click();
        return this;
    }


    public LoginPagePW fillPassword(String password) {
        f("#user_password").setValue(password);
        return this;
    }

    public LoginPagePW fillEmail(String mail) {
        f("#user_email").setValue(mail);
        return this;
    }

    public void preloaderIsHidden() {
        $("#app-loader").shouldBe(Condition.disappear);
    }

}
