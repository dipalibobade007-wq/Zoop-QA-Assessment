package com.zoop.qa.tests;

import com.zoop.qa.base.BaseTest;
import com.zoop.qa.pages.DynamicLoadingPage;
import com.zoop.qa.utils.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class DynamicLoadingTest extends BaseTest {

    @Test(description = "Wait for an initially hidden element to become visible")
    public void shouldHandleHiddenElementAfterDynamicLoading() {
        DynamicLoadingPage page = new DynamicLoadingPage(driver());
        page.openExample(baseUrl(), 1);
        page.start();
        Assert.assertEquals(page.waitForHelloWorld(), "Hello World!");
    }

    @Test(description = "Wait for an element rendered after the request completes")
    public void shouldHandleElementRenderedAfterDynamicLoading() {
        DynamicLoadingPage page = new DynamicLoadingPage(driver());
        page.openExample(baseUrl(), 2);
        page.start();
        Assert.assertEquals(page.waitForHelloWorld(), "Hello World!");
    }
}
