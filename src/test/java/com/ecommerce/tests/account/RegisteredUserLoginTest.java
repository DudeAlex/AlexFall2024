package com.ecommerce.tests.account;

import com.ecommerce.base.BaseTest;
import com.ecommerce.data.RegisteredUserLoginData;
import com.ecommerce.data.RegisteredUserLoginWithEmail;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegisteredUserLoginTest extends BaseTest {

    @Test(description = "*-* | TC > Login registered user with email or username # * ",
            dataProvider = "registeredUserLogin", dataProviderClass = RegisteredUserLoginData.class)
    public void testRegisteredUserLogin(String accountHeaderLink,
                                        String emailField,
                                        String emailOrUserName,
                                        String passwordField,
                                        String password,
                                        String logoutButtonXpath,
                                        String logOutButtonText,
                                        String loginButtonXpath,
                                        String logInButtonText) {

        driver.findElement(By.xpath(accountHeaderLink)).click(); // find the account link in the header
        driver.findElement(By.xpath(emailField)).sendKeys(emailOrUserName, Keys.ENTER); // fill in the email field
        driver.findElement(By.xpath(passwordField)).sendKeys(password, Keys.ENTER); // fill in the password field and log in
        String linkLogoutGetText = driver.findElement(By.xpath(logoutButtonXpath)).getText(); // find the logout button

        Assert.assertEquals(logOutButtonText, linkLogoutGetText); // check if there is a logout button

        driver.findElement(By.xpath(logoutButtonXpath)).click(); // log out of the account using the logout button
        String loginButtonGetText = driver.findElement(By.xpath(loginButtonXpath)).getText(); // find the login button

        Assert.assertEquals(logInButtonText, loginButtonGetText); // check if there is a login button

    }

    @Test(description = " ", dataProvider = "registeredUserEmail", dataProviderClass = RegisteredUserLoginWithEmail.class)

    public void oderHistoryCheck(
            String accountHeaderLink,
            String emailField,
            String emailOrUserName,
            String passwordField,
            String password) throws InterruptedException {

        By placeOderBtn = By.xpath("//button[@id='place_order']");

        driver.findElement(By.xpath(accountHeaderLink)).click(); // find the account link in the header
        driver.findElement(By.xpath(emailField)).sendKeys(emailOrUserName, Keys.ENTER);
        driver.findElement(By.xpath(passwordField)).sendKeys(password, Keys.ENTER);
        Thread.sleep(3000);


        driver.findElement(By.id("menu-item-1227")).click();//shop link
        driver.findElement(By.xpath("//a[@href='?add-to-cart=1205']")).click();//add item to the cart
        WebElement viewCart = driver.findElement(By.linkText("View cart"));//click cart
        viewCart.click();
        driver.findElement(By.xpath("//a[@class='checkout-button button alt wc-forward']")).click();//checkOut btn
        Thread.sleep(3000);

        driver.findElement(By.xpath("//input[@name='billing_first_name']")).clear();
        driver.findElement(By.xpath("//input[@name='billing_first_name']")).sendKeys("Mihai");

        driver.findElement(By.xpath("//input[@name='billing_last_name']")).clear();
        driver.findElement(By.xpath("//input[@name='billing_last_name']")).sendKeys("B");

        driver.findElement(By.xpath("//span[@id='select2-billing_country-container']")).click();
        driver.findElement(By.xpath("//li[contains(text(),'United States (US)')]")).click();

        driver.findElement(By.xpath("//input[@name='billing_address_1']")).clear();
        driver.findElement(By.xpath("//input[@name='billing_address_1']")).sendKeys("32 Main st");

        driver.findElement(By.xpath("//input[@name='billing_city']")).clear();
        driver.findElement(By.xpath("//input[@name='billing_city']")).sendKeys("Redmond");

        driver.findElement(By.xpath("//span[@id='select2-billing_state-container']")).click();
        driver.findElement(By.xpath("//li[contains(text(),'California')]")).click();

        driver.findElement(By.xpath("//input[@id='billing_postcode']")).clear();
        driver.findElement(By.xpath("//input[@id='billing_postcode']")).sendKeys("98052");

        WaitUtils.visibilityOfElementLocated(driver, placeOderBtn).click();
        driver.findElement(By.xpath("//li[@id='menu-item-1237']")).click();//Account link

        driver.findElement(By.xpath("//a[normalize-space()='Orders']")).click();//Orders link
        driver.findElement(By.xpath("//a[@class=\"woocommerce-button button view\"]")).click();//view order btn

        WebElement orderDetails = driver.findElement(By.xpath("//h2[@class='woocommerce-order-details__title']"));

        Assert.assertEquals(orderDetails.getText(), "Order details");

    }

}
