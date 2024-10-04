package com.ecommerce.tests.components.header;

import com.ecommerce.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class NavigationMenuVisibleTest extends BaseTest {

    @BeforeClass
    public void setUpClass(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");
        driver = new ChromeDriver(options);

        driver.get("https://askomdch.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
    }

    @AfterClass
    public void driverDown(){
        driver.quit();
    }

    @BeforeMethod
    @Override
    public void setUp(){
    }

    @AfterMethod
    @Override
    public void tearDown(){
    }

    @Test(description = "0-3-1| Verify Navigation Menu Placement # https://app.clickup.com/t/8689q87dt")
    public void testHomeMenuIsDisplayed() {

        Assert.assertTrue(driver.findElement(By.id("menu-item-1226")).isDisplayed()); // Home
    }

    @Test(description = "0-3-1| Verify Navigation Menu Placement # https://app.clickup.com/t/8689q87dt")
    public void testStoreMenuIsDisplayed() {

        Assert.assertTrue(driver.findElement(By.id("menu-item-1227")).isDisplayed()); // Store
    }

    @Test(description = "0-3-1| Verify Navigation Menu Placement # https://app.clickup.com/t/8689q87dt")
    public void testMenMenuIsDisplayed() {

        Assert.assertTrue(driver.findElement(By.id("menu-item-1228")).isDisplayed()); // Men
    }

    @Test(description = "0-3-1| Verify Navigation Menu Placement # https://app.clickup.com/t/8689q87dt")
    public void testWomenMenuIsDisplayed() {

        Assert.assertTrue(driver.findElement(By.id("menu-item-1229")).isDisplayed()); // Women
    }

    @Test(description = "0-3-1| Verify Navigation Menu Placement # https://app.clickup.com/t/8689q87dt")
    public void testAccessoriesMenuIsDisplayed() {

        Assert.assertTrue(driver.findElement(By.id("menu-item-1230")).isDisplayed()); // Accessories
    }

    @Test(description = "0-3-1| Verify Navigation Menu Placement # https://app.clickup.com/t/8689q87dt")
    public void testAccountMenuIsDisplayed() {

        Assert.assertTrue(driver.findElement(By.id("menu-item-1237")).isDisplayed()); // Account
    }

    @Test(description = "0-3-1| Verify Navigation Menu Placement # https://app.clickup.com/t/8689q87dt")
    public void testAboutIsDisplayed(){

        Assert.assertTrue(driver.findElement(By.id("menu-item-1232")).isDisplayed()); // About
    }

    @Test(description = "0-3-1| Verify Navigation Menu Placement # https://app.clickup.com/t/8689q87dt")
    public void testContactUsIsDisplayed(){

        Assert.assertTrue(driver.findElement(By.id("menu-item-1233")).isDisplayed()); // Contact Us
    }

    @Test(description = "0-3-1| Verify Navigation Menu Placement # https://app.clickup.com/t/8689q87dt")
    public void testCartIsDisplayed() {

        Assert.assertTrue(driver.findElement(By.cssSelector("a[href='https://askomdch.com/cart/']")).isDisplayed()); // Cart
    }
}
