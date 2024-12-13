package com.ecommerce.tests.components.header;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.HomePage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.time.Duration;

public class HeaderNavigationClickTest extends BaseTest {
    static String currentURL = "https://askomdch.com/store";

    /**
     * The method accepts a static string variable currentURL
     */
    @BeforeClass
    @Override
    public void setUp(String browser){
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
    public void tearDown(Method method, ITestResult testResult){
        driver.quit();
    }

    /**
     * By using @overridden methods, the tests check menu navigation from different positions each time
     */
    @Test(description = "0-4-1 | TC Verify Header menu navigation # https://app.clickup.com/t/8689q8844")
    public void testHomeMenuClick() {
        HomePage homePage = new HomePage(driver);
        homePage.getHeader().navigateToHomePage();// Home

        Assert.assertEquals(driver.getCurrentUrl(), "https://askomdch.com/");
    }

    @Test(description = "0-4-1 | TC Verify Header menu navigation # https://app.clickup.com/t/8689q8844")
    public void testStoreMenuClick() {
        HomePage homePage = new HomePage(driver);
        homePage.getHeader().navigateToStorePage(); // Store

        Assert.assertEquals(driver.getCurrentUrl(), "https://askomdch.com/store/");
    }

    @Test(description = "0-4-1 | TC Verify Header menu navigation # https://app.clickup.com/t/8689q8844")
    public void testMenMenuClick() {
        HomePage homePage = new HomePage(driver);
        homePage.getHeader().navigateToMenPage(); // Men

        Assert.assertEquals(driver.getCurrentUrl(), "https://askomdch.com/product-category/men/");
    }

    @Test(description = "0-4-1 | TC Verify Header menu navigation # https://app.clickup.com/t/8689q8844")
    public void testWomenMenuClick() {
        HomePage homePage = new HomePage(driver);
        homePage.getHeader().navigateToWomenPage(); // Women

        Assert.assertEquals(driver.getCurrentUrl(), "https://askomdch.com/product-category/women/");
    }

    @Test(description = "0-4-1 | TC Verify Header menu navigation # https://app.clickup.com/t/8689q8844")
    public void testAccessoriesMenuClick() {
        HomePage homePage = new HomePage(driver);
        homePage.getHeader().navigateToAccessoriesPage(); // Accessories

        Assert.assertEquals(driver.getCurrentUrl(), "https://askomdch.com/product-category/accessories/");
    }

    @Test(description = "0-4-1 | TC Verify Header menu navigation # https://app.clickup.com/t/8689q8844")
    public void testAccountMenuClick() {
        HomePage homePage = new HomePage(driver);
        homePage.getHeader().navigateToAccountPage(); // Account

        Assert.assertEquals(driver.getCurrentUrl(), "https://askomdch.com/account/");
    }

    @Test(description = "0-4-1 | TC Verify Header menu navigation # https://app.clickup.com/t/8689q8844")
    public void testAboutClick(){
        HomePage homePage = new HomePage(driver);
        homePage.getHeader().navigateToAboutPage(); // About

        Assert.assertEquals(driver.getCurrentUrl(), "https://askomdch.com/about/");
    }

    @Test(description = "0-4-1 | TC Verify Header menu navigation # https://app.clickup.com/t/8689q8844")
    public void testContactUsClick(){
        HomePage homePage = new HomePage(driver);
        homePage.getHeader().navigateToContactUsPage(); // Contact Us

        Assert.assertEquals(driver.getCurrentUrl(), "https://askomdch.com/contact-us/");
    }

    @Test(description = "0-4-1 | TC Verify Header menu navigation # https://app.clickup.com/t/8689q8844")
    public void testCartClick() {
        HomePage homePage = new HomePage(driver);
        homePage.getHeader().navigateToCartPage(); // Cart

        Assert.assertEquals(driver.getCurrentUrl(), "https://askomdch.com/cart/");
    }

}
