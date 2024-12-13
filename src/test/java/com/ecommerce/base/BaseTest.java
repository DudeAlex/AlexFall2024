package com.ecommerce.base;
import com.ecommerce.pom.pages.AccountPage;
import com.ecommerce.pom.pages.CartPage;

import com.ecommerce.utils.ConfigUtil;
import com.ecommerce.utils.DriverManagerUtil;

import com.ecommerce.utils.ProjectUtils;
import com.ecommerce.utils.ThreadLocalWebDriver;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;


public class BaseTest {
    protected WebDriver driver;


    @Parameters("browser")
    @BeforeMethod(groups = "before")
    public void setUp(@Optional("chrome") String browser){
        driver = DriverManagerUtil.getWebdriver(browser);
        driver.get(ConfigUtil.getProperty("baseUrl"));
        driver.manage().window().maximize();
    }

    @AfterMethod(groups = "after")
    public void tearDown(Method method, ITestResult testResult) {
        if (!testResult.isSuccess()) {
            ProjectUtils.takeScreenshot(driver, testResult.getTestClass().getRealClass().getSimpleName(), testResult.getName());
        }
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
