package io.testomat.web.common.pw.conditions;

import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import io.testomat.web.common.pw.Configuration;
import io.testomat.web.common.pw.LocatorActions;

public class IsHiddenCondition implements Condition {

    @Override
    public void verify(LocatorActions locatorActions) {
        PlaywrightAssertions.assertThat(locatorActions.getLocator())
                .isHidden(
                        new LocatorAssertions.IsHiddenOptions().setTimeout(Configuration.defaultTimeout)
                );
    }

}
