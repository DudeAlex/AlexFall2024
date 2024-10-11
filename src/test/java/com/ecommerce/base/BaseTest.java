package com.ecommerce.base;

import com.ecommerce.pom.pages.AccountPage;
import com.ecommerce.pom.pages.CartPage;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;


public class BaseTest {
    protected WebDriver driver;


    @BeforeMethod
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");
        driver = new ChromeDriver(options);  // Используем ChromeOptions напрямую

        driver.get("https://askomdch.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
    }

    @AfterMethod
    public void tearDown(){
        if(driver!=null){
            clearCart();
            logout();
            driver.quit();
        }
    }
    private void clearCart(){
//        CartPage cartPage = new CartPage(driver);
//        cartPage.load();
//        cartPage.removeItemsFromCart();
//

//        driver.get("https://askomdch.com/cart/");

    }
    private void logout(){
//        AccountPage accountPage = new AccountPage(driver);
//        accountPage.load();
//        accountPage.logout();
    }
}

