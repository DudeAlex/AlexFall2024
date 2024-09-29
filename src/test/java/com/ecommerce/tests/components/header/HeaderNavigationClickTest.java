package com.ecommerce.tests.components.header;

import com.ecommerce.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class HeaderNavigationClickTest extends BaseTest {
    static String currentURL = "https://askomdch.com/";

    /**
     * The method accepts a static string variable currentURL
     */
    @BeforeClass
    @Override
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");
        driver = new ChromeDriver(options);

        driver.get(currentURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
    }

    /**
     * The method changes the static string variable to the one opened by the previous test.
     */
    @AfterMethod
    public void getURL(){
        currentURL = driver.getCurrentUrl();
    }
    @AfterClass
    @Override
    public void tearDown(){
        driver.quit();
    }

    /**
     * By using @overridden methods, the tests check menu navigation from different positions each time
     */
    @Test(description = "0-4-1 | Verify Menu Links # https://app.clickup.com/t/8689q8844")
    public void testHomeMenuClick() {

        driver.findElement(By.id("menu-item-1226")).click(); // Home

        Assert.assertEquals(driver.getCurrentUrl(), "https://askomdch.com/");
    }

    @Test(description = "0-4-1 | Verify Menu Links # https://app.clickup.com/t/8689q8844")
    public void testStoreMenuClick() {

        driver.findElement(By.id("menu-item-1227")).click(); // Store

        Assert.assertEquals(driver.getCurrentUrl(), "https://askomdch.com/store/");
    }

    @Test(description = "0-4-1 | Verify Menu Links # https://app.clickup.com/t/8689q8844")
    public void testMenMenuClick() {

        driver.findElement(By.id("menu-item-1228")).click(); // Men

        Assert.assertEquals(driver.getCurrentUrl(), "https://askomdch.com/product-category/men/");
    }

    @Test(description = "0-4-1 | Verify Menu Links # https://app.clickup.com/t/8689q8844")
    public void testWomenMenuClick() {

        driver.findElement(By.id("menu-item-1229")).click(); // Women

        Assert.assertEquals(driver.getCurrentUrl(), "https://askomdch.com/product-category/women/");
    }

    @Test(description = "0-4-1 | Verify Menu Links # https://app.clickup.com/t/8689q8844")
    public void testAccessoriesMenuClick() {

        driver.findElement(By.id("menu-item-1230")).click(); // Accessories

        Assert.assertEquals(driver.getCurrentUrl(), "https://askomdch.com/product-category/accessories/");
    }

    @Test(description = "0-4-1 | Verify Menu Links # https://app.clickup.com/t/8689q8844")
    public void testAccountMenuClick() {

        driver.findElement(By.id("menu-item-1237")).click(); // Account

        Assert.assertEquals(driver.getCurrentUrl(), "https://askomdch.com/account/");
    }

    @Test(description = "0-4-1 | Verify Menu Links # https://app.clickup.com/t/8689q8844")
    public void testAboutClick(){

        driver.findElement(By.id("menu-item-1232")).click(); // About

        Assert.assertEquals(driver.getCurrentUrl(), "https://askomdch.com/about/");
    }

    @Test(description = "0-4-1 | Verify Menu Links # https://app.clickup.com/t/8689q8844")
    public void testContactUsClick(){

        driver.findElement(By.id("menu-item-1233")).click(); // Contact Us

        Assert.assertEquals(driver.getCurrentUrl(), "https://askomdch.com/contact-us/");
    }

    @Test(description = "0-4-1 | Verify Menu Links # https://app.clickup.com/t/8689q8844")
    public void testCartClick() {

        driver.findElement(By.cssSelector("a[href='https://askomdch.com/cart/']")).click(); // Cart

        Assert.assertEquals(driver.getCurrentUrl(), "https://askomdch.com/cart/");
    }

}
