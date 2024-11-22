package com.ecommerce.utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitUtils {

    private static final long TIMEOUTS = Long.parseLong(ConfigUtil.getProperty("timeout"));

    public static WebElement visibilityOfElementLocated(WebDriver driver, By by, long timeout) {

        return new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static WebElement visibilityOfElementLocated(WebDriver driver, By by) {
        return visibilityOfElementLocated(driver, by, TIMEOUTS);
    }

    public static WebElement presenceOfElementLocated(WebDriver driver, By by, long timeout) {

        return new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static WebElement presenceOfElementLocated(WebDriver driver, By by) {
        return presenceOfElementLocated(driver, by, TIMEOUTS);

    }

    public static WebElement visibilityOf(WebDriver driver, By by, long timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOf(driver.findElement(by)));
    }

    public static WebElement visibilityOf(WebDriver driver, By by) {
        return visibilityOf(driver, by, TIMEOUTS);
    }

    public static List<WebElement> numberOfElementsToBeMoreThan(WebDriver driver, By by, long timeout, Integer multipleElements) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.numberOfElementsToBeMoreThan(by, multipleElements));
    }


    public static List<WebElement> numberOfElementsToBeMoreThan(WebDriver driver, By by, Integer multipleElements) {
        return numberOfElementsToBeMoreThan(driver, by, TIMEOUTS, multipleElements);
    }

    // numberOfWindowsWait with default timeout
    public static Boolean numberOfWindowsToBe(WebDriver driver, int numberOfWindow) {
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUTS)).until(ExpectedConditions.numberOfWindowsToBe(numberOfWindow));
    }

    // numberOfWindowsWait with custom timeout
    public static Boolean numberOfWindowsToBe(WebDriver driver, int timeout, int numberOfWindow) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.numberOfWindowsToBe(numberOfWindow));
    }

    public static WebElement elementToBeClickable(WebDriver driver, By by, long timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.elementToBeClickable(by));
    }

    public static WebElement elementToBeClickable(WebDriver driver, By by) {
        return elementToBeClickable(driver, by, TIMEOUTS);
    }

    public static List<WebElement> visibilityOfAllElementsLocatedBy(WebDriver driver, By by, long timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public static List<WebElement> visibilityOfAllElementsLocatedBy(WebDriver driver, By by) {
        return visibilityOfAllElementsLocatedBy(driver, by, TIMEOUTS);
    }

    public static Boolean invisibilityOfElementLocated(WebDriver driver, By by, long timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public static Boolean invisibilityOfElementLocated(WebDriver driver, By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUTS)).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public static Boolean waitForIncreasedAmountOfProductsInCart(WebDriver driver, long timeout, By by, Integer initialValue, int quantity) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.textToBe(by, String.valueOf(initialValue + quantity)));
    }

    public static boolean waitForQuantityToBe(WebDriver driver, By by, String quantity) {
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUTS)).until(ExpectedConditions.textToBe(by, quantity));
    }

    public static boolean waitForValueAttributeChanged(WebDriver driver, By selector, String attributeName,
                                                       String expectedValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUTS));

//        return wait.until(ExpectedConditions.attributeToBe(selector, attributeName, expectedValue));
        return wait.until(driver1 -> driver.findElement(selector).getAttribute(attributeName).equals(expectedValue));
    }
}
