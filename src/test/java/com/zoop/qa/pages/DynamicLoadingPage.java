package com.zoop.qa.pages;

import com.zoop.qa.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DynamicLoadingPage {
    private final WebDriver driver;

    private final By startButton = By.xpath("//button[normalize-space()='Start']");
    private final By helloWorld = By.xpath("//*[normalize-space()='Hello World!']");

    public DynamicLoadingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openExample(String baseUrl, int example) {
        if (example != 1 && example != 2) {
            throw new IllegalArgumentException("Dynamic loading example must be 1 or 2");
        }
        driver.get(baseUrl + "/dynamic_loading/" + example);
    }

    public void start() {
        WaitUtils.clickable(driver, startButton).click();
    }

    public String waitForHelloWorld() {
        return WaitUtils.visible(driver, helloWorld).getText().trim();
    }
}
