package com.zoop.qa.pages;

import com.zoop.qa.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NotificationMessagePage {
    private final WebDriver driver;
    private final By trigger = By.xpath("//a[contains(normalize-space(), 'Click here')]");
    private final By notification = By.id("flash");

    public NotificationMessagePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String baseUrl) {
        driver.get(baseUrl + "/notification_message_rendered");
    }

    public String loadMessage() {
        WaitUtils.clickable(driver, trigger).click();
        return WaitUtils.visible(driver, notification).getText().trim();
    }
}
