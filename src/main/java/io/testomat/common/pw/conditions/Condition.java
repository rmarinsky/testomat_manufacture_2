package io.testomat.common.pw.conditions;

import io.testomat.common.pw.LocatorActions;

public interface Condition {
    Condition visible = new VisibleCondition();
    Condition hidden = new IsHiddenCondition();
    Condition disappear = new IsHiddenCondition();

    static Condition text(String expectedText) {
        return new TextCondition(expectedText);
    }

    void verify(LocatorActions locatorActions);


}
