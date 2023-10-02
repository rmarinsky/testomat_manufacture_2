package io.testomat.web.common;

import io.testomat.web.common.pw.PlaywrightWrapper;
import org.testng.*;

public class TracesListener implements ITestListener, IInvokedMethodListener {

    @Override
    public void onTestStart(ITestResult result) {
        PlaywrightWrapper.initTestContext(getTestName(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        PlaywrightWrapper.closeContext(getTestName(result));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        PlaywrightWrapper.closeContext(getTestName(result));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        PlaywrightWrapper.closeContext(getTestName(result));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        PlaywrightWrapper.closeContext(getTestName(result));
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        // not implementing beforeInvocation as onTestStart should suffice
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        // not implementing afterInvocation as onTestSuccess/onTestFailure should suffice
    }

    private String getTestName(ITestResult result) {
        return result.getMethod().getMethodName();
    }

    //Other methods from these interfaces are not required, you can leave them as empty
    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
    }

}
