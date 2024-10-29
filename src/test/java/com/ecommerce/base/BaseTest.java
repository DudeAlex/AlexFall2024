package com.ecommerce.base;

import com.ecommerce.utils.DriverManagerUtil;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;


public class BaseTest {
    protected WebDriver driver;


    @BeforeMethod(
            groups = "before"
    )
    public void setUp(){
        driver = DriverManagerUtil.getWebdriver();
        driver.get("https://askomdch.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
    }

    @AfterMethod(
            groups = "after"
    )
    public void tearDown(){
        driver.quit();
    }
}
