package com.ecommerce.tests.account;

import com.ecommerce.base.BaseTest;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.ecommerce.data.RegisterUsernameData;

import java.util.UUID;

public class RegisterTest extends BaseTest {

    By accountOption = By.id("menu-item-1237");
    By registerUsernameInput = By.xpath("(//input[@type='text'])[2]");
    By registerEmailAddressInput = By.cssSelector("input[type='email']");
    By registerPasswordInput = By.xpath("(//input[@type='password'])[2]");
    By registerButton = By.xpath("(//button[@type='submit'])[2]");
    By welcomeNewUserText = By.xpath("//p[contains(text(),'Hello')]");
    By errorMessage = By.xpath("//ul[@role='alert']");

    public String generateUniqueUsername() {
        String baseUsername = "user";

        return baseUsername + UUID.randomUUID();
    }

    public String generateUniqueEmail() {
        String baseEmail = "user";
        String domain = "@example.com";

        return baseEmail + UUID.randomUUID() + domain;
    }

    public static String generateUniquePassword() {

        return UUID.randomUUID().toString().replace("-", "").substring(0, 10);
    }

    @Test(description = "6.2-1-7 | TC > Account Page > Validate email error # https://app.clickup.com/t/868a34t20")
    public void testValidateBrowserEmailErrorMessage() {
        WaitUtils.elementToBeClickable(driver, accountOption).click();

        WebElement registerUsernameInputField = WaitUtils.visibilityOf(driver, registerUsernameInput, 2);
        registerUsernameInputField.sendKeys(generateUniqueUsername());
        registerUsernameInputField.sendKeys(Keys.TAB);
        WebElement activeElement = driver.switchTo().activeElement();

        WebElement emailInputField = driver.findElement(registerEmailAddressInput);
        activeElement.sendKeys(generateUniqueEmail().replace("@", ""));
        emailInputField.sendKeys(Keys.TAB);

        WebElement passwordInputField = driver.findElement(registerPasswordInput);
        passwordInputField.sendKeys(generateUniquePassword());
        passwordInputField.sendKeys(Keys.TAB);

        driver.findElement(registerButton).click();

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String validationMessage = (String) jsExecutor.executeScript(
                "return arguments[0].validationMessage;", emailInputField);

        boolean isHelloTextInvisible = WaitUtils.invisibilityOfElementLocated(driver, welcomeNewUserText, 5);

        Assert.assertFalse(validationMessage.isEmpty());
        Assert.assertTrue(isHelloTextInvisible);
    }

    @Test(description = "6.2-1-4 | TC > Account Page > Verify error message for empty password # https://app.clickup.com/t/868a34t9v")
    public void testVerifyErrorMassageForEmptyPassword() {
        WaitUtils.elementToBeClickable(driver, accountOption).click();

        WebElement registerUsernameInputField = WaitUtils.visibilityOf(driver, registerUsernameInput, 2);
        registerUsernameInputField.sendKeys(generateUniqueUsername());
        registerUsernameInputField.sendKeys(Keys.TAB);

        WebElement emailInputField = driver.switchTo().activeElement();
        emailInputField.sendKeys(generateUniqueEmail());
        emailInputField.sendKeys(Keys.TAB);

        WebElement passwordInputField = driver.switchTo().activeElement();
        passwordInputField.sendKeys(Keys.ENTER);

        String errorMessageText = WaitUtils.visibilityOf(driver, errorMessage).getText();

        Assert.assertEquals(errorMessageText, "Error: Please enter an account password.");
    }

    @Test(description = "6.2-1-1 | TC > Account Page > Verify registry # https://app.clickup.com/t/868a34rph")
    public void testVerifyRegistry() {
        WaitUtils.elementToBeClickable(driver, accountOption).click();

        WebElement registerUsernameInputField = WaitUtils.visibilityOf(driver, registerUsernameInput, 2);
        String userName = generateUniqueUsername();
        registerUsernameInputField.sendKeys(userName);
        registerUsernameInputField.sendKeys(Keys.TAB);

        WebElement emailInputField = driver.switchTo().activeElement();
        emailInputField.sendKeys(generateUniqueEmail());
        emailInputField.sendKeys(Keys.TAB);

        WebElement passwordInputField = driver.switchTo().activeElement();
        passwordInputField.sendKeys(generateUniquePassword());

        passwordInputField.sendKeys(Keys.ENTER);

        String welcomeUserText = WaitUtils.visibilityOf(driver, welcomeNewUserText).getText();

        Assert.assertTrue(welcomeUserText.contains("Hello " + userName));
    }

    @Test(dataProvider = "provideInvalidCharacters", dataProviderClass = RegisterUsernameData.class,
            description = "6.2-1-5 | TC > Verify error for invalid characters #https://app.clickup.com/t/868a80kv3")
    public void testVerifyErrorMessageForInvalidCharacters(String character) {
        WaitUtils.elementToBeClickable(driver, accountOption).click();

        WebElement registerUsernameInputField = WaitUtils.visibilityOf(driver, registerUsernameInput, 2);
        registerUsernameInputField.sendKeys(generateUniqueUsername() + character);
        registerUsernameInputField.sendKeys(Keys.TAB);

        WebElement emailInputField = driver.switchTo().activeElement();
        emailInputField.sendKeys(generateUniqueEmail());
        emailInputField.sendKeys(Keys.TAB);

        WebElement passwordInputField = driver.switchTo().activeElement();
        passwordInputField.sendKeys(generateUniquePassword());

        passwordInputField.sendKeys(Keys.ENTER);

        String errorMessageText = WaitUtils.visibilityOf(driver, errorMessage).getText();
        Assert.assertEquals(errorMessageText, "Error: Please enter a valid account username.");
    }

    @Test(description = "6.2-1-6 | TC > Verify error for username longer than 60 characters # https://app.clickup.com/t/868a80mw5")
    public void testVerifyErrorMessageForUsernameMoreSixtyCharacters() {
        WaitUtils.elementToBeClickable(driver, accountOption).click();

        WebElement registerUsernameInputField = WaitUtils.visibilityOf(driver, registerUsernameInput, 2);
        registerUsernameInputField.sendKeys("qwertyuiopasdfghjklzxcvbnmqwertyuioasdfghjklzxcvbnmqwertyuioq");
        registerUsernameInputField.sendKeys(Keys.TAB);

        WebElement emailInputField = driver.switchTo().activeElement();
        emailInputField.sendKeys(generateUniqueEmail());
        emailInputField.sendKeys(Keys.TAB);

        WebElement passwordInputField = driver.switchTo().activeElement();
        passwordInputField.sendKeys(generateUniquePassword());

        passwordInputField.sendKeys(Keys.ENTER);

        String errorMessageText = WaitUtils.visibilityOf(driver, errorMessage).getText();
        Assert.assertEquals(errorMessageText, "Error: Username may not be longer than 60 characters.");
    }

    @Test(description = "6.2-1-3 | TC > Verify Error for empty email # https://app.clickup.com/t/868a80p4a")
    public void testVerifyErrorMessageForEmptyEmail() {
        WaitUtils.elementToBeClickable(driver, accountOption).click();

        WebElement registerUsernameInputField = WaitUtils.visibilityOf(driver, registerUsernameInput, 2);
        registerUsernameInputField.sendKeys(generateUniqueUsername());
        registerUsernameInputField.sendKeys(Keys.TAB);

        WebElement emailInputField = driver.switchTo().activeElement();
        emailInputField.sendKeys(Keys.TAB);

        WebElement passwordInputField = driver.switchTo().activeElement();
        passwordInputField.sendKeys(generateUniquePassword());
        passwordInputField.sendKeys(Keys.ENTER);

        String errorMessageText = WaitUtils.visibilityOf(driver, errorMessage).getText();
        Assert.assertEquals(errorMessageText, "Error: Please provide a valid email address.");
    }

    @Test(description = "6.2-1-2 | TC > Verify Error for empty Username  #https://app.clickup.com/t/868a80u7t")
    public void testVerifyErrorMessageForEmptyUserName() {
        WaitUtils.elementToBeClickable(driver, accountOption).click();

        WebElement registerUsernameInputField = WaitUtils.visibilityOf(driver, registerUsernameInput, 2);
        registerUsernameInputField.sendKeys(Keys.TAB);

        WebElement emailInputField = driver.switchTo().activeElement();
        emailInputField.sendKeys(generateUniqueEmail());
        emailInputField.sendKeys(Keys.TAB);

        WebElement passwordInputField = driver.switchTo().activeElement();
        passwordInputField.sendKeys(generateUniquePassword());
        passwordInputField.sendKeys(Keys.ENTER);

        String errorMessageText = WaitUtils.visibilityOf(driver, errorMessage).getText();
        Assert.assertEquals(errorMessageText, "Error: Please enter a valid account username.");
    }

}