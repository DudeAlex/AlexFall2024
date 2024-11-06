package com.ecommerce.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JSExecutorUtils {
    public static void scrollIntoView(WebDriver driver, By by){
        WebElement element = WaitUtils.visibilityOfElementLocated(driver, by);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element );
    }

    public static void scrollToOneThirdOfPage(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight * 0.3);");
    }
}
