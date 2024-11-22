package com.ecommerce.base;


import com.ecommerce.pom.pages.AccountPage;
import com.ecommerce.pom.pages.CartPage;

import com.ecommerce.utils.ConfigUtil;
import com.ecommerce.utils.DriverManagerUtil;

import com.ecommerce.utils.ThreadLocalWebDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest {
    protected WebDriver driver;


    @BeforeMethod(groups = "before")
    public void setUp(){
        driver = DriverManagerUtil.getWebdriver();
        driver.get(ConfigUtil.getProperty("baseUrl"));
        driver.manage().window().maximize();
    }

    @AfterMethod(groups = "after")
    public void tearDown() {
        CartPage cartPage = new CartPage(driver);
        cartPage.load();
        cartPage.clearCart();

        AccountPage accountPage = new AccountPage(driver);
        accountPage.load();
        accountPage.logOutUser();

        ThreadLocalWebDriver.removeDriver();

        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.out.println("Error while closing the browser: " + e.getMessage());
            }
        }
    }
}
