package com.zoop.qa.pages;

import com.zoop.qa.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StatusCodesPage {
    private final WebDriver driver;
    private final By pageHeading = By.cssSelector("h3");
    private final By pageBody = By.cssSelector("div.example");

    public StatusCodesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String baseUrl) {
        driver.get(baseUrl + "/status_codes");
    }

    public void openStatusCode(int code) {
        By link = By.xpath("//a[normalize-space()='" + code + "']");
        WaitUtils.clickable(driver, link).click();
    }

    public String getHeading() {
        return WaitUtils.visible(driver, pageHeading).getText().trim();
    }

    public String getBody() {
        return WaitUtils.visible(driver, pageBody).getText().trim();
    }
}
