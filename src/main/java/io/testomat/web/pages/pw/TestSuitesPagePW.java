package io.testomat.web.pages.pw;

import io.testomat.common.pw.LocatorActions;
import io.testomat.common.pw.conditions.Condition;
import io.testomat.web.asserts.pw.TestSuitesPageAssertsPW;

import static io.testomat.common.pw.PlaywrightWrapper.$;

public class TestSuitesPagePW extends BasePage {

    String firstTestSuiteSelector = "[placeholder='%s']";
    private LocatorActions firstTestSuite = $("[placeholder='First Suite']");
    protected String suitesListItem = ".list-group-wrapper .dragSortItem";


    public TestSuitesPagePW isLoaded() {
        firstTestSuite.shouldBe(Condition.visible);
        return this;
    }

    public TestSuitesPagePW closeReadmeModal() {
        $(".back").click();
        return this;
    }

    public TestSuitesPagePW fillFirstTestSuite(String targetTestSuite) {
        firstTestSuite.setValue(targetTestSuite).press("Enter");
        return this;
    }

    public TestSuitesPageAssertsPW asserts() {
        return new TestSuitesPageAssertsPW();
    }


    //just for example
    public TestSuitesPagePW fillFirstTestSuiteName(String targetTestSuite) {
        $(String.format(firstTestSuiteSelector, "First Suite")).setValue(targetTestSuite);
        return null;
    }

    //just for example
    public TestSuitesPagePW fillSecondTestSuiteName(String targetTestSuite) {
        $("[placeholder='First Suite']").setValue(targetTestSuite);
        return null;
    }

}
