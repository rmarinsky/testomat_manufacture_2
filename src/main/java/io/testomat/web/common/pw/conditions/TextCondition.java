package io.testomat.web.common.pw.conditions;

import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import io.testomat.web.common.pw.Configuration;
import io.testomat.web.common.pw.LocatorActions;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TextCondition implements Condition {

    private final String expectedText;

    @Override
    public void verify(LocatorActions locatorActions) {
        PlaywrightAssertions.assertThat(locatorActions.getLocator()).containsText(
                expectedText,
                new LocatorAssertions.ContainsTextOptions()
                        .setUseInnerText(true)
                        .setIgnoreCase(true)
                        .setTimeout(Configuration.defaultTimeout)
        );
    }

    @Override
    public String toString() {
        return "contains text \"" + expectedText + "\"";
    }

}
