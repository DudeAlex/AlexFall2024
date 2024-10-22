package com.ecommerce.pom.pages;

import com.ecommerce.pom.BasePage;
import com.ecommerce.pom.Loadable;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.ecommerce.pom.EndPoints.ACCOUNT_URL;
import static java.sql.DriverManager.getDriver;


public class AccountPage extends BasePage implements Loadable {
    By emailField = By.id("//input [ @ id = 'username']");
    By passwordField = By.id("//input [ @ id = 'password']");
    By loginButton = By.xpath("//button[@class='woocommerce-button button woocommerce-form-login__submit' and text()='Log in']");
    By loginUsername = By.xpath("//input[@id=\"username\"]");
    By loginPassword = By.xpath("//input[@id=\"password\"]");
    By registerUsernameInput = By.xpath("(//input[@type='text'])[2]");
    By registerButton = By.xpath("(//button[@type='submit'])[2]");
    By lostPasswordLink = By.xpath("//a[normalize-space()='Lost your password?']");
    By registerEmailAddressInput = By.cssSelector("input[type='email']");
    By registerPasswordInput = By.xpath("(//input[@type='password'])[2]");
    By welcomeNewUserText = By.xpath("//p[contains(text(),'Hello')]");
    By errorMessage = By.xpath("//ul[@role='alert']");
    By logoutLink = By.xpath("//div[@class = 'woocommerce-MyAccount-content']//a[text() = 'Log out']");
    By accountDetailLink = By.xpath("//a[normalize-space()='Account details']");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public AccountPage load() {
        getDriver().get(ACCOUNT_URL);

        return this;
    }

    public void logIn() {
        getDriver().findElement(loginUsername).sendKeys("aaaaa@aa.aa");
        getDriver().findElement(loginPassword).sendKeys("11111");
        getDriver().findElement(loginButton).click();
    }

    public void logIn(String email, String password) {
        getDriver().findElement(loginUsername).sendKeys(email);
        getDriver().findElement(loginPassword).sendKeys(password);
        getDriver().findElement(loginButton).click();
    }

    public AccountPage logout() {
        WebElement logout = WaitUtils.presenceOfElementLocated(getDriver(), logoutLink, 5);
        if (logout.isDisplayed()) {
            logout.click();
        }
        return this;
    }

    public LostPasswordPage navigateToLostPasswordPage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), lostPasswordLink).click();
        return new LostPasswordPage(getDriver());
    }

    public AccountPage typeUserNameAndPressTabKey(String username) {
        WebElement registerUsernameInputField = WaitUtils.visibilityOf(getDriver(), registerUsernameInput, 2);
        registerUsernameInputField.sendKeys(username);
        registerUsernameInputField.sendKeys(Keys.TAB);

        return this;
    }

    public AccountPage typeEmailBeingOnEmailInputAndPressTabKey(String email) {
        WebElement activeElement = getDriver().switchTo().activeElement();
        activeElement.sendKeys(email);
        activeElement.sendKeys(Keys.TAB);

        return this;
    }

    public AccountPage typePasswordBeingOnPasswordInputAndPressEnterKey(String password) {
        WebElement passwordInputField = getDriver().switchTo().activeElement();
        passwordInputField.sendKeys(password);
        passwordInputField.sendKeys(Keys.ENTER);

        return this;
    }

    public AccountPage typeUsernameToUsernameInputField(String username) {
        WebElement registerUsernameInputField = WaitUtils.visibilityOf(getDriver(), registerUsernameInput, 2);
        registerUsernameInputField.sendKeys(username);

        return this;
    }

    public AccountPage typeEmailToEmailInputField(String email) {
        WebElement registerUsernameInputField = WaitUtils.visibilityOf(getDriver(), registerEmailAddressInput, 2);
        registerUsernameInputField.sendKeys(email);

        return this;
    }

    public AccountPage typePasswordToPasswordInputField(String password) {
        WebElement passwordInputField = getDriver().findElement(registerPasswordInput);
        passwordInputField.sendKeys(password);

        return this;
    }

    public AccountPage clickRegisterButton() {
        getDriver().findElement(registerButton).click();

        return this;
    }

    public String getValidationMessage() {
        WebElement emailInputField = getDriver().findElement(registerEmailAddressInput);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();

        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", emailInputField);
    }

    public boolean isWelcomeTextInvisible() {
        return WaitUtils.invisibilityOfElementLocated(getDriver(), welcomeNewUserText, 5);
    }

    public String getErrorMessage() {

        return WaitUtils.visibilityOf(getDriver(), errorMessage).getText();
    }

    public String getWelcomeNewUserText() {
        return WaitUtils.visibilityOf(getDriver(), welcomeNewUserText).getText();
    }

}
