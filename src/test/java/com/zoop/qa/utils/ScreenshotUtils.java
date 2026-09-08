package com.zoop.qa.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;

public final class ScreenshotUtils {
    private ScreenshotUtils() {}

    public static void capture(WebDriver driver, String fileName) {
        try {
            Path directory = Path.of("target", "screenshots");
            Files.createDirectories(directory);
            byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            String safeFileName = fileName.replaceAll("[^a-zA-Z0-9._-]", "_");
            String timestamp = Instant.now().toString().replaceAll("[:.]", "-");
            Files.write(directory.resolve(safeFileName + "_" + timestamp + ".png"), bytes);
        } catch (IOException ignored) {
            // Screenshot is diagnostic only; it must not hide the original test failure.
        }
    }
}
