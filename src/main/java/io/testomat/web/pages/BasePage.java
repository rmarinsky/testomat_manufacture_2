package io.testomat.web.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public abstract class BasePage {

    private final String baseContent = System.getProperty("isMobile") == null ? "#content-desktop " : "#content-mobile ";

    protected SelenideElement f(String selector) {
        return $(baseContent + selector);
    }

}
