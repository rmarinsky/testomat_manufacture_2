package io.testomat.common;

import io.testomat.common.pw.PlaywrightWrapper;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class PWContextExtension implements BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        PlaywrightWrapper.initTestContext(getTestName(context));
    }

    @Override
    public void afterEach(ExtensionContext context) {
        PlaywrightWrapper.closeContext(getTestName(context));
    }

    private String getTestName(ExtensionContext context) {
        return context.getRequiredTestClass().getName() + " " + context.getDisplayName();
    }


}
