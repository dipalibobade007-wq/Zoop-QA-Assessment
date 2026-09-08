package com.zoop.qa.pages;

import com.zoop.qa.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DynamicControlsPage {
    private final WebDriver driver;

    private final By checkbox = By.cssSelector("input[type='checkbox']");
    private final By textInput = By.cssSelector("input[type='text']");
    private final By actionButton = By.cssSelector("form button");
    private final By message = By.id("message");

    public DynamicControlsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String baseUrl) {
        driver.get(baseUrl + "/dynamic_controls");
    }

    public boolean isCheckboxVisible() {
        return !driver.findElements(checkbox).isEmpty() && driver.findElement(checkbox).isDisplayed();
    }

    public void removeCheckbox() {
        WaitUtils.clickable(driver, By.xpath("//button[normalize-space()='Remove']")).click();
        WaitUtils.invisible(driver, checkbox);
    }

    public void addCheckbox() {
        WaitUtils.clickable(driver, By.xpath("//button[normalize-space()='Add']")).click();
        WaitUtils.visible(driver, checkbox);
    }

    public String getMessage() {
        return WaitUtils.visible(driver, message).getText().trim();
    }

    public void enableInput() {
        WaitUtils.clickable(driver, By.xpath("//button[normalize-space()='Enable']")).click();
        WaitUtils.until(driver, d -> d.findElement(textInput).isEnabled());
    }

    public void disableInput() {
        WaitUtils.clickable(driver, By.xpath("//button[normalize-space()='Disable']")).click();
        WaitUtils.until(driver, d -> !d.findElement(textInput).isEnabled());
    }

    public boolean isInputEnabled() {
        return WaitUtils.visible(driver, textInput).isEnabled();
    }
}
