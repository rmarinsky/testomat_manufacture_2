package io.testomat.web.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import io.testomat.web.asserts.TestSuitesPageAsserts;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TestSuitesPage extends BasePage {

    String firstTestSuiteSelector = "[placeholder='%s']";
    private SelenideElement firstTestSuite = $("[placeholder='First Suite']");
    protected String suitesListItem = ".list-group-wrapper .dragSortItem";


    public TestSuitesPage isLoaded() {
        firstTestSuite.shouldBe(Condition.visible, Duration.ofSeconds(30));
        return this;
    }

    public TestSuitesPage closeReadmeModal() {
        $(".back").click();
        return this;
    }

    public TestSuitesPage fillFirstTestSuite(String targetTestSuite) {
        firstTestSuite.setValue(targetTestSuite).pressEnter();
        return this;
    }

    public TestSuitesPageAsserts asserts() {
        return new TestSuitesPageAsserts();
    }


    //just for example
    public TestSuitesPage fillFirstTestSuiteName(String targetTestSuite) {
        $(String.format(firstTestSuiteSelector, "First Suite")).setValue(targetTestSuite);
        return null;
    }

    //just for example
    public TestSuitesPage fillSecondTestSuiteName(String targetTestSuite) {
        $(Selectors.by("placeholder", "First Suite")).setValue(targetTestSuite);
        return null;
    }




}
