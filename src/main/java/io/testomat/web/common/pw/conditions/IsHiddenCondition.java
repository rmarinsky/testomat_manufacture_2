package io.testomat.web.common.pw.conditions;

import com.microsoft.playwright.assertions.LocatorAssertions;
import io.qameta.allure.Allure;
import io.testomat.web.common.pw.Configuration;
import io.testomat.web.common.pw.LocatorActions;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class IsHiddenCondition implements Condition {

    @Override
    public void verify(LocatorActions locatorActions) {
        Allure.step(locatorActions.getLocator().toString());
        assertThat(locatorActions.getLocator())
                .isHidden(
                        new LocatorAssertions.IsHiddenOptions().setTimeout(Configuration.defaultTimeout)
                );
    }

    @Override
    public String toString() {
        return "hidden";
    }

}
