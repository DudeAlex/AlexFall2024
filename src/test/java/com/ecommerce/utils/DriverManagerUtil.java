package com.ecommerce.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverManagerUtil {
    private static WebDriver driver;

    public static WebDriver getWebdriver(String browser) {
        if(browser.isEmpty()) browser = ConfigUtil.getProperty("browser");

        if (browser.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-search-engine-choice-screen");
            driver = ThreadLocalWebDriver.initializeDriver(browser, options);

        } else if (browser.equals("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--disable-search-engine-choice-screen");
            driver = ThreadLocalWebDriver.initializeDriver(browser, options);

        }

        return driver;
    }




}
