package com.zoop.qa.tests;

import com.zoop.qa.base.BaseTest;
import com.zoop.qa.pages.NotificationMessagePage;
import com.zoop.qa.utils.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class NotificationMessageTest extends BaseTest {

    @Test(description = "Handle a notification whose result may change after each request")
    public void shouldHandleChangingNotificationMessage() {
        NotificationMessagePage page = new NotificationMessagePage(driver());
        page.open(baseUrl());

        String message = page.loadMessage();
        boolean isExpectedNotification = message.contains("Action successful")
                || message.contains("Action unsuccesful")
                || message.contains("Action unsuccessful");
        Assert.assertTrue(
                isExpectedNotification,
                "Unexpected notification message: " + message);
    }
}
