package com.zoop.qa.base;

import com.zoop.qa.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverFactory.startDriver();
    }

    protected WebDriver driver() {
        return DriverFactory.getDriver();
    }

    protected String baseUrl() {
        return ConfigReader.get("baseUrl");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
