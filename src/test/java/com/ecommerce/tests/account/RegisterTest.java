package com.ecommerce.tests.account;

import com.ecommerce.base.BaseTest;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {
    private static final String USER_NAME = "Andy";
    private static final String EMAIL = "andy@andy.com";
    private static final String PASSWORD = "12345";

    By accountOption = By.id("menu-item-1237");
    By registerUsernameInput = By.xpath("(//input[@type='text'])[2]");
    By registerEmailAddressInput = By.cssSelector("input[type='email']");
    By registerPasswordInput = By.xpath("(//input[@type='password'])[2]");
    By registerButton = By.xpath("(//button[@type='submit'])[2]");
    By welcomeNewUserText = By.xpath("//p[contains(text(),'Hello')]");
    By errorMessage = By.xpath("//ul[@role='alert']");

    @Test(description = "6-1.2 | TC > Account Page > Validate email error # https://app.clickup.com/t/868a34t20")
    public void testValidateEmailErrorMessage() {
        WaitUtils.elementToBeClickable(driver, accountOption).click();

        WebElement registerUsernameInputField = WaitUtils.visibilityOf(driver, registerUsernameInput, 2);
        registerUsernameInputField.sendKeys(USER_NAME);
        registerUsernameInputField.sendKeys(Keys.TAB);
        WebElement activeElement = driver.switchTo().activeElement();

        WebElement emailInputField = driver.findElement(registerEmailAddressInput);
        activeElement.sendKeys("email.com");
        emailInputField.sendKeys(Keys.TAB);

        WebElement passwordInputField = driver.findElement(registerPasswordInput);
        passwordInputField.sendKeys(PASSWORD);
        passwordInputField.sendKeys(Keys.TAB);

        driver.findElement(registerButton).click();

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String validationMessage = (String) jsExecutor.executeScript(
                "return arguments[0].validationMessage;", emailInputField);

        boolean isHelloTextInvisible = WaitUtils.invisibilityOfElementLocated(driver, welcomeNewUserText, 5);

        Assert.assertFalse(validationMessage.isEmpty());
        Assert.assertTrue(isHelloTextInvisible);
    }

    @Test(description = "6-1.3 | TC > Account Page > Verify error message for empty password # https://app.clickup.com/t/868a34t9v")
    public void testVerifyErrorMassageForEmptyPassword() {
        WaitUtils.elementToBeClickable(driver, accountOption).click();

        WebElement registerUsernameInputField = WaitUtils.visibilityOf(driver, registerUsernameInput, 2);
        registerUsernameInputField.sendKeys(USER_NAME);
        registerUsernameInputField.sendKeys(Keys.TAB);

        WebElement emailInputField = driver.switchTo().activeElement();
        emailInputField.sendKeys(EMAIL);
        emailInputField.sendKeys(Keys.TAB);

        WebElement passwordInputField = driver.switchTo().activeElement();
        passwordInputField.sendKeys(Keys.ENTER);

        String errorMessageText = WaitUtils.visibilityOf(driver, errorMessage).getText();

        Assert.assertEquals(errorMessageText, "Error: Please enter an account password.");
    }

    //Test <work in progress> for question
    @Test(description = "6-1.1 | TC > Accout Page > Verify registry # https://app.clickup.com/t/868a34rph")
    public void testVerifyRegistry() {
        WaitUtils.elementToBeClickable(driver, accountOption).click();

        WebElement registerUsernameInputField = WaitUtils.visibilityOf(driver, registerUsernameInput, 2);
        registerUsernameInputField.sendKeys(USER_NAME);
        registerUsernameInputField.sendKeys(Keys.TAB);

        WebElement emailInputField = driver.switchTo().activeElement();
        emailInputField.sendKeys("nahmee@email.com");
        emailInputField.sendKeys(Keys.TAB);

        WebElement passwordInputField = driver.switchTo().activeElement();
        passwordInputField.sendKeys(PASSWORD);

//        passwordInputField.sendKeys(Keys.ENTER);
//
//
//        String welcomeUserText = WaitUtils.visibilityOf(driver, welcomeNewUserText).getText();
//        System.out.println(welcomeUserText);
//        Assert.assertTrue(welcomeUserText.contains("Hello " + USER_NAME));

    }
}
