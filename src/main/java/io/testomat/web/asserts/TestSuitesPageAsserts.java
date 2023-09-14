package io.testomat.web.asserts;

import com.codeborne.selenide.Condition;
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
        $(suitesListItem).shouldHave(Condition.text(expectedTestSuiteTitle)
                .because("First test suite in list should have text: " + expectedTestSuiteTitle));
        return this;
    }

}
