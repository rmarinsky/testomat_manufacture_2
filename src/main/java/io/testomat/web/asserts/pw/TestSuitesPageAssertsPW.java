package io.testomat.web.asserts.pw;

import io.qameta.allure.Step;
import io.testomat.web.common.pw.conditions.Condition;
import io.testomat.web.pages.pw.TestSuitesPagePW;

import static io.testomat.web.common.pw.PlaywrightWrapper.$;

public class TestSuitesPageAssertsPW extends TestSuitesPagePW {


    @Step
    public TestSuitesPageAssertsPW listShouldHaveSize(int expectedSize) {
//        $(suitesListItem).shouldHave();
        return this;
    }

    @Step
    public TestSuitesPageAssertsPW firstTestSuiteInListShouldHaveText(String expectedTestSuiteTitle) {
        $(suitesListItem).shouldHave(Condition.text(expectedTestSuiteTitle));
        return this;
    }

}
