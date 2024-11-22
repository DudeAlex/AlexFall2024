package com.ecommerce.tests.components.header;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.HomePage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class HeaderNavigationMenuVisibleTest extends BaseTest {

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
    public void setUp(String browser){
    }

    @AfterMethod
    @Override
    public void tearDown(){
    }

    @Test(description = "0-3-1| Verify header menu content # https://app.clickup.com/t/8689q87dt")
    public void testHomeMenuIsDisplayed() {
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(driver.findElement(homePage.getHeader().getHeaderHome()).isDisplayed()); // Home
    }

    @Test(description = "0-3-1| Verify header menu content # https://app.clickup.com/t/8689q87dt")
    public void testStoreMenuIsDisplayed() {
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(driver.findElement(homePage.getHeader().getHeaderStore()).isDisplayed()); // Store
    }

    @Test(description = "0-3-1| Verify header menu content # https://app.clickup.com/t/8689q87dt")
    public void testMenMenuIsDisplayed() {
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(driver.findElement(homePage.getHeader().getHeaderMen()).isDisplayed()); // Men
    }

    @Test(description = "0-3-1| Verify header menu content # https://app.clickup.com/t/8689q87dt")
    public void testWomenMenuIsDisplayed() {
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(driver.findElement(homePage.getHeader().getHeaderWomen()).isDisplayed()); // Women
    }

    @Test(description = "0-3-1| Verify header menu content # https://app.clickup.com/t/8689q87dt")
    public void testAccessoriesMenuIsDisplayed() {
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(driver.findElement(homePage.getHeader().getHeaderAccessories()).isDisplayed()); // Accessories
    }

    @Test(description = "0-3-1| Verify header menu content # https://app.clickup.com/t/8689q87dt")
    public void testAccountMenuIsDisplayed() {
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(driver.findElement(homePage.getHeader().getHeaderAccount()).isDisplayed()); // Account
    }

    @Test(description = "0-3-1| Verify header menu content # https://app.clickup.com/t/8689q87dt")
    public void testAboutIsDisplayed(){
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(driver.findElement(homePage.getHeader().getHeaderAbout()).isDisplayed()); // About
    }

    @Test(description = "0-3-1| Verify header menu content # https://app.clickup.com/t/8689q87dt")
    public void testContactUsIsDisplayed(){
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(driver.findElement(homePage.getHeader().getHeaderContactUs()).isDisplayed()); // Contact Us
    }

    @Test(description = "0-3-1| Verify header menu content # https://app.clickup.com/t/8689q87dt")
    public void testCartIsDisplayed() {
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(driver.findElement(homePage.getHeader().getHeaderCartIcon()).isDisplayed()); // Cart
    }
}
