package com.ecommerce.tests.account;

import com.ecommerce.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisteredUserLoginTest extends BaseTest {

    @Test
    public void RegisteredUserLoginWithEmailTest() {

        String email = "test_test@test.test";
        String username = "test_test";
        String password = "12345";
        String logOut = "Logout";
        String log_out = "log out";
        String logIn = "LOG IN";

        driver.findElement(By.xpath("//a[contains(text(), 'Account')]")).click();
        driver.findElement(By.xpath("//input [ @ id = 'username']")).sendKeys(email, Keys.ENTER);
        driver.findElement(By.xpath("//input [ @ id = 'password']")).sendKeys(password, Keys.ENTER);
        String linkLogout = driver.findElement(By.xpath(
                "//li[contains(@class, 'woocommerce-MyAccount-navigation-link--customer-logout')]/a[text()='Logout']\n")).getText();

        Assert.assertEquals(logOut, linkLogout);

        driver.findElement(By.xpath(
                "//li[contains(@class, 'woocommerce-MyAccount-navigation-link--customer-logout')]/a[text()='Logout']\n")).click();
        WebElement loginButton = driver.findElement(By.xpath(
                "//button[@class='woocommerce-button button woocommerce-form-login__submit' and text()='Log in']\n"));

        Assert.assertEquals(logIn, loginButton.getText());

    }
    @Test
    public void RegisteredUserLoginWithUserNameTest() {

        String email = "test_test@test.test";
        String username = "test_test";
        String password = "12345";
        String logOut = "Logout";
        String log_out = "log out";
        String logIn = "LOG IN";

        driver.findElement(By.xpath("//a[contains(text(), 'Account')]")).click();
        driver.findElement(By.xpath("//input [ @ id = 'username']")).sendKeys(username, Keys.ENTER);
        driver.findElement(By.xpath("//input [ @ id = 'password']")).sendKeys(password, Keys.ENTER);
        String linkLogout = driver.findElement(By.xpath(
                "//li[contains(@class, 'woocommerce-MyAccount-navigation-link--customer-logout')]/a[text()='Logout']\n")).getText();

        Assert.assertEquals(logOut, linkLogout);

        driver.findElement(By.xpath(
                "//li[contains(@class, 'woocommerce-MyAccount-navigation-link--customer-logout')]/a[text()='Logout']\n")).click();
        WebElement loginButton = driver.findElement(By.xpath(
                "//button[@class='woocommerce-button button woocommerce-form-login__submit' and text()='Log in']\n"));

        Assert.assertEquals(logIn, loginButton.getText());

    }

}
