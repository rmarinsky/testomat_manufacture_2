package io.testomat.web.pages.pw;

import com.codeborne.selenide.SelenideElement;
import io.testomat.common.pw.LocatorActions;
import io.testomat.common.pw.PlaywrightWrapper;

import static io.testomat.common.pw.PlaywrightWrapper.*;


public class BasePage {

private final String baseContent = System.getProperty("isMobile") == null ? "#content-desktop " : "#content-mobile ";

    protected LocatorActions f(String selector) {
        return $(baseContent + selector);
    }

}
