package com.zoop.qa.utils;

import com.zoop.qa.config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public final class WaitUtils {
    private WaitUtils() {}

    public static WebDriverWait waitFor(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getInt("timeoutSeconds")));
    }

    public static WebElement visible(WebDriver driver, By locator) {
        return waitFor(driver).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement clickable(WebDriver driver, By locator) {
        return waitFor(driver).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static boolean invisible(WebDriver driver, By locator) {
        return waitFor(driver).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static boolean present(WebDriver driver, By locator) {
        return waitFor(driver).until(ExpectedConditions.presenceOfElementLocated(locator)) != null;
    }

    public static <T> T until(WebDriver driver, Function<WebDriver, T> condition) {
        return waitFor(driver).until(condition);
    }
}
