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
}