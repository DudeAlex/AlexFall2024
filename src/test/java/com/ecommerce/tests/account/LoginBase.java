package com.ecommerce.tests.account;

import com.ecommerce.base.BaseTest;
import com.ecommerce.data.RegisteredUserLoginData;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginBase extends BaseTest {

    //@Test(description = "*-* | TC > Login registered user with email or username # * ",
    //      dataProvider = "registeredUserLogin", dataProviderClass = RegisteredUserLoginData.class)
    public void UserLogin(String accountHeaderLink,
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
    }

}

