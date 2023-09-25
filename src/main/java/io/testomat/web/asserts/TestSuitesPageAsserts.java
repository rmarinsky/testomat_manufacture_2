package io.testomat.web.asserts;

import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.testomat.web.pages.TestSuitesPage;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TestSuitesPageAsserts extends TestSuitesPage {

    public TestSuitesPageAsserts listShouldHaveSize(int expectedSize) {
        $$(suitesListItem).shouldHave(size(expectedSize).because("List should have size: " + expectedSize));
        return this;
    }

    public TestSuitesPageAsserts firstTestSuiteInListShouldHaveText(String expectedTestSuiteTitle) {
        Configuration.assertionMode = AssertionMode.SOFT;

        $(suitesListItem).shouldHave(Condition.text(expectedTestSuiteTitle)
                .because("First test suite in list should have text: " + expectedTestSuiteTitle));

        Configuration.assertionMode = AssertionMode.STRICT;
        return this;
    }

    //implement via singletoin wrapper for playwright to store objects: Playuwright, Browser, BrowserContext, Page
    // and store them in concurfenthashmap with thread id as key

    //store all objects in the single concurrenthashmap

    //add mehtod find("css Selector") and LocatorActions object where will be implkementation as decorator pattern
    // method click, fill("value"), press("key"), shouldHave(Condition), shouldHas(Condition), shouldBe(Condition)

}
