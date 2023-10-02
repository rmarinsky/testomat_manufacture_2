package io.testomat.web.common.pw;

import com.microsoft.playwright.Locator;
import io.testomat.web.common.pw.conditions.Condition;
import lombok.Data;

@Data
public class LocatorActions {

    private final Locator locator;

    public LocatorActions fill(String text) {
        locator.fill(text);
        return this;
    }

    public LocatorActions setValue(String text) {
        return fill(text);
    }

    public LocatorActions press(String key) {
        locator.press(key);
        return this;
    }

    public LocatorActions click() {
        locator.click();
        return this;
    }

    public LocatorActions shouldBe(Condition condition) {
        condition.verify(this);
        return this;
    }

    public LocatorActions shouldHave(Condition condition) {
        condition.verify(this);
        return this;
    }

    public LocatorActions shouldHas(Condition condition) {
        condition.verify(this);
        return this;
    }

    //hWQ1Ac17nRwVB96FLpMpDQ2n99m3bjdhJd0089c/QwmMIChTVSsiO9JqbO8hDt/tFsStEd1qak0hH3kPRKosaA==
    //hWQ1Ac17nRwVB96FLpMpDQ2n99m3bjdhJd0089c/QwmMIChTVSsiO9JqbO8hDt/tFsStEd1qak0hH3kPRKosaA==
}
