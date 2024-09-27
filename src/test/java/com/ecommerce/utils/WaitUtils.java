package com.ecommerce.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static final long TIMEOUTS = 10;

    public static WebElement visibilityOfElementLocated(WebDriver driver, By by, long timeout){

        return new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    public static WebElement visibilityOfElementLocated(WebDriver driver, By by){
        return visibilityOfElementLocated(driver, by, TIMEOUTS);
    }
}
