package com.ecommerce.tests.account;

import com.ecommerce.base.BaseTest;
import com.ecommerce.data.RegisteredUserLoginData;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class RedirectionDashboardAfterLogin extends LoginBase {

    @Test(description = "6.4-1-1 | TC > Redirection Dashboard after login # https://app.clickup.com/t/868a60tyx",
            dataProvider = "registeredUserLogin", dataProviderClass = RegisteredUserLoginData.class)
    public void testRegisteredUserLogin(String accountHeaderLink,
                                        String emailField,
                                        String emailOrUserName,
                                        String passwordField,
                                        String password,
                                        String logoutButtonXpath,
                                        String logOutButtonText,
                                        String loginButtonXpath,
                                        String logInButtonText) throws InterruptedException{
        super.UserLogin( accountHeaderLink,
                 emailField,
                 emailOrUserName,
                 passwordField,
                 password,
                 logoutButtonXpath,
                 logOutButtonText,
                 loginButtonXpath,
                 logInButtonText);
        driver.findElement(By.xpath("//*[@id=\"post-1235\"]/div/div[2]/div/div[2]/nav/ul/li[1]/a"));//Dashboard
        driver.findElement(By.xpath("//*[@id=\"post-1235\"]/div/div[2]/div/div[2]/nav/ul/li[2]/a"));//Orders
        driver.findElement(By.xpath("//*[@id=\"post-1235\"]/div/div[2]/div/div[2]/nav/ul/li[3]/a"));//Downloads
        driver.findElement(By.xpath("//*[@id=\"post-1235\"]/div/div[2]/div/div[2]/nav/ul/li[4]/a"));//Addresses
        driver.findElement(By.xpath("//*[@id=\"post-1235\"]/div/div[2]/div/div[2]/nav/ul/li[5]/a"));//Account details
        driver.findElement(By.xpath("//*[@id='post-1235']/div/div[2]/div/div[2]/nav/ul/li[6]/a"));//Logout
    }
}