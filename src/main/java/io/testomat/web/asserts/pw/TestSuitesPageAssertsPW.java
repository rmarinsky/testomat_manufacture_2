package io.testomat.web.asserts.pw;

import io.testomat.common.pw.conditions.Condition;
import io.testomat.web.pages.pw.TestSuitesPagePW;

import static io.testomat.common.pw.PlaywrightWrapper.$;

public class TestSuitesPageAssertsPW extends TestSuitesPagePW {


    public TestSuitesPageAssertsPW listShouldHaveSize(int expectedSize) {
//        $(suitesListItem).shouldHave();
        return this;
    }

    public TestSuitesPageAssertsPW firstTestSuiteInListShouldHaveText(String expectedTestSuiteTitle) {
        $(suitesListItem).shouldHave(Condition.text(expectedTestSuiteTitle));
        return this;
    }

}
