package com.zoop.qa.tests;

import com.zoop.qa.base.BaseTest;
import com.zoop.qa.pages.StatusCodesPage;
import com.zoop.qa.utils.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class StatusCodesTest extends BaseTest {

    @Test(description = "Validate a successful status-code page through the UI")
    public void shouldValidate200ThroughUi() {
        StatusCodesPage page = new StatusCodesPage(driver());
        page.open(baseUrl());
        page.openStatusCode(200);

        Assert.assertTrue(page.getHeading().contains("Status Codes"));
        Assert.assertTrue(page.getBody().contains("200"), "UI should identify the returned 200 status");
    }
}
