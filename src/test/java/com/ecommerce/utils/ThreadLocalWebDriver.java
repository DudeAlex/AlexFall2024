package com.ecommerce.utils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class ThreadLocalWebDriver {

    // ThreadLocal to hold WebDriver instance for each thread
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Method to initialize WebDriver for each thread based on a browser type
    public static WebDriver initializeDriver(String browser, Capabilities capabilities) {
        if (browser.equalsIgnoreCase("chrome")) {
            driver.set(new ChromeDriver());
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver.set(new FirefoxDriver());
        }
        return driver.get();
    }

    // Get the current thread's WebDriver
    public static WebDriver getDriver() {
        return driver.get();
    }

    // Remove the WebDriver instance for the current thread
    public static void removeDriver() {
        WebDriver webDriver = driver.get();
        if (webDriver != null) {
            webDriver.quit();
            driver.remove();
        }
    }

 }


