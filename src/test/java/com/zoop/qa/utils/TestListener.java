package com.zoop.qa.utils;

import com.zoop.qa.base.DriverFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        try {
            String testName = result.getTestClass().getRealClass().getSimpleName()
                    + "_" + result.getName();
            ScreenshotUtils.capture(DriverFactory.getDriver(), testName);
        } catch (Exception ignored) {
            // Do not mask the test failure.
        }
    }
}
