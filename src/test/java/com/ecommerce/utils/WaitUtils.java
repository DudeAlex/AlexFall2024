package com.ecommerce.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitUtils {

    private static final long TIMEOUTS = 10;

    public static WebElement visibilityOfElementLocated(WebDriver driver, By by, long timeout){

        return new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    public static WebElement visibilityOfElementLocated(WebDriver driver, By by){
        return visibilityOfElementLocated(driver, by, TIMEOUTS);
    }

    public static List<WebElement> visibilityOfAllElementsLocated(WebDriver driver, By by, long timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public static List<WebElement> visibilityOfAllElementsLocated(WebDriver driver, By by) {
        return visibilityOfAllElementsLocated(driver, by, TIMEOUTS);
    }

    public static WebElement presenceOfElementLocated(WebDriver driver, By by, long timeout) {

        return new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static WebElement presenceOfElementLocated(WebDriver driver, By by){
        return presenceOfElementLocated(driver, by, TIMEOUTS);

    }

    public static WebElement visibilityOf(WebDriver driver, By by, long timeout){
        return  new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOf(driver.findElement(by)));
    }

    public static WebElement visibilityOf(WebDriver driver, By by){
        return visibilityOf(driver, by, TIMEOUTS);
    }

     public static List<WebElement> numberOfElementsToBeMoreThan(WebDriver driver, By by, long timeout, Integer multipleElements){
        return new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.numberOfElementsToBeMoreThan(by,multipleElements));
     }


    public static List<WebElement> numberOfElementsToBeMoreThan(WebDriver driver, By by, Integer multipleElements){
        return numberOfElementsToBeMoreThan(driver, by, TIMEOUTS, multipleElements);
    }

    // numberOfWindowsWait with default timeout
    public static Boolean numberOfWindowsToBe(WebDriver driver, int numberOfWindow){
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUTS)).
                until(ExpectedConditions.numberOfWindowsToBe(numberOfWindow));
    }

    // numberOfWindowsWait with custom timeout
    public static Boolean numberOfWindowsToBe(WebDriver driver, int timeout, int numberOfWindow){
        return new WebDriverWait(driver, Duration.ofSeconds(timeout)).
                until(ExpectedConditions.numberOfWindowsToBe(numberOfWindow));
    }



}
