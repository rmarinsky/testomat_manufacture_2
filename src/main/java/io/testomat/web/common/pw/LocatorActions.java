package io.testomat.web.common.pw;

import com.microsoft.playwright.Locator;
import io.qameta.allure.Step;
import io.testomat.web.common.pw.conditions.Condition;
import lombok.Data;

@Data
public class LocatorActions {

    private final Locator locator;

    @Step("Fill {text}")
    public LocatorActions $(String selector) {
        locator.locator(selector);
        return this;
    }

    @Step("Fill {text}")
    public LocatorActions fill(String text) {
        locator.fill(text);
        return this;
    }

    @Step("Fill {text}")
    public LocatorActions setValue(String text) {
        return fill(text);
    }

    @Step("Press {key}")
    public LocatorActions press(String key) {
        locator.press(key);
        return this;
    }

    @Step("Click")
    public LocatorActions click() {
        locator.click();
        return this;
    }

    @Step("Should be {condition}")
    public LocatorActions shouldBe(Condition condition) {
        condition.verify(this);
        return this;
    }

    @Step("Should have {condition}")
    public LocatorActions shouldHave(Condition condition) {
        condition.verify(this);
        return this;
    }

    @Step
    public LocatorActions shouldHas(Condition condition) {
        condition.verify(this);
        return this;
    }

}
