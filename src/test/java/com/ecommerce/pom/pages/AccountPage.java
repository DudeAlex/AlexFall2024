package com.ecommerce.pom.pages;

import com.ecommerce.pom.BasePage;
import com.ecommerce.pom.Loadable;
import com.ecommerce.utils.ConfigUtil;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.*;

import java.util.List;

import static com.ecommerce.pom.EndPoints.ACCOUNT_URL;

public class AccountPage extends BasePage implements Loadable {
    By emailField = By.id("//input[@id='username']");
    By passwordField = By.id("//input[@id='password']");
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
    By logoutLinkFromMainContent = By.xpath("//div[@class='woocommerce-MyAccount-content']//a[text()='Log out']");
    By logoutLinkFromSideMenu = By.xpath("//li[contains(@class, 'woocommerce-MyAccount-navigation-link--customer-logout')]/a[text()='Logout']");
    By accountDetailLink = By.xpath("//a[normalize-space()='Account details']");
    By addressesLink = By.xpath("//a[text()='Addresses']");
    By logInTable = By.cssSelector(".woocommerce-form.woocommerce-form-login.login");


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

    public AccountPage logInUsingConfigUtils() {
        String userLogin = ConfigUtil.getProperty("userLogin");
        String userPassword = ConfigUtil.getProperty("userPassword");

        getDriver().findElement(loginUsername).sendKeys(userLogin);
        getDriver().findElement(loginPassword).sendKeys(userPassword);
        getDriver().findElement(loginButton).click();

        return this;
    }

    public AccountPage logInUsingConfigUtilsWithParameters(String userLogin, String userPassword) {
        String userLoginType = ConfigUtil.getProperty(userLogin);
        String userPasswordType = ConfigUtil.getProperty(userPassword);

        getDriver().findElement(loginUsername).sendKeys(userLoginType);
        getDriver().findElement(loginPassword).sendKeys(userPasswordType);
        getDriver().findElement(loginButton).click();

        return this;
    }

    public AccountPage logInUsingConfigUtilsWithUserLoginOnly(String userLogin) {
        String userLoginType = ConfigUtil.getProperty(userLogin);

        getDriver().findElement(loginUsername).sendKeys(userLoginType);
        getDriver().findElement(loginButton).click();

        return this;
    }

    public AccountPage logInUsingConfigUtilsWithPasswordOnly(String userPassword) {
        String userPasswordType = ConfigUtil.getProperty(userPassword);

        getDriver().findElement(loginPassword).sendKeys(userPasswordType);
        getDriver().findElement(loginButton).click();

        return this;
    }

    public AccountPage logOutUser() {

        List<WebElement> listLogOut;
        List<WebElement> listLogInTable;

        for (int i = 0; i < 8; i++) {
            listLogOut = getDriver().findElements(logoutLinkFromMainContent);
            listLogInTable = getDriver().findElements(logInTable);

            if (listLogOut.size() > 0) {
                listLogOut.get(0).click();
                return this;
            }
            if (listLogInTable.size() > 0) {
                return this;
            }
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        throw new TimeoutException("Unable to logout");
    }

    public AccountPage logOutFromSideMenu() {
        WaitUtils.elementToBeClickable(getDriver(), logoutLinkFromSideMenu).click();//*******in AccountLoginUserPage
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

        return this;    //******************************************* return new AccountLoginUserPage
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
    }//***************************************************               in AccountLoginUserPage

    public AccountAddressesPage clickAddressesLink (){
        WaitUtils.elementToBeClickable(getDriver(), addressesLink).click();
        return new AccountAddressesPage(getDriver());//*************    *********************** already in Dashboard
    }

    public AccountPage assertLogin() {
        List<WebElement> logoutLink = WaitUtils.visibilityOfAllElementsLocatedBy(getDriver(), logoutLinkFromMainContent, 2);
        if (logoutLink.isEmpty()) {
            throw new AssertionError("User was not logged-in");
        } else {
            System.out.println("Login was successful");
        }

        return this;//********************************************************** In AccountLoginUserPage
    }

    public By getEmailField() {
        return emailField;
    }

    public By getPasswordField() {
        return passwordField;
    }

    public By getLoginButton() {
        return loginButton;
    }

    public By getLoginUsername() {
        return loginUsername;
    }

    public By getLoginPassword() {
        return loginPassword;
    }

    public By getRegisterUsernameInput() {
        return registerUsernameInput;
    }

    public By getRegisterButton() {
        return registerButton;
    }

    public By getLostPasswordLink() {
        return lostPasswordLink;
    }

    public By getRegisterEmailAddressInput() {
        return registerEmailAddressInput;
    }

    public By getRegisterPasswordInput() {
        return registerPasswordInput;
    }

    public By getLogoutLinkFromMainContent() {
        return logoutLinkFromMainContent;
    }

    public By getAccountDetailLink() {
        return accountDetailLink;
    }

    public By getLogoutLinkFromSideMenu() {
        return logoutLinkFromSideMenu;
    }
}
