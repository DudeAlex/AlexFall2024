package com.ecommerce.pom.pages;

import com.ecommerce.pom.BasePage;
import com.ecommerce.pom.Loadable;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;


public class AccountPage extends BasePage implements Loadable {
    By emailField = By.id("//input [ @ id = 'username']");
    By passwordField = By.id("//input [ @ id = 'password']");
    By loginButton = By.xpath("//button[@class='woocommerce-button button woocommerce-form-login__submit' and text()='Log in']");
    By storePageLink = By.id("menu-item-1227");
    By menPageLink = By.id("menu-item-1228");
    By womenPageLink = By.id("menu-item-1229");
    By cartIcon = By.xpath("//span[@class='count']");
    By loginUsername = By.xpath("//input[@id=\"username\"]");
    By loginPassword = By.xpath("//input[@id=\"password\"]");
    By storeButton = By.xpath("//li[@id=\"menu-item-1227\"]/a");
    By homePageLink = By.xpath("//li[@id=\"menu-item-1226\"]/a");
    By logoutLink = By.xpath("//div[@class = 'woocommerce-MyAccount-content']//a[text() = 'Log out']");

    By lostPasswordLink = By.xpath("//a[normalize-space()='Lost your password?']");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void load() {
        getDriver().get("https://askomdch.com/account/");
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

    public StorePage navigateToStorePage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), storePageLink).click();

        return new StorePage(getDriver());
    }

    public HomePage navigateToHomePage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), homePageLink).click();
        return new HomePage(getDriver());
    }

    public CartPage navigateToCartPage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), cartIcon).click();

        return new CartPage(getDriver());
    }

    public MenPage navigateToMenPage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), menPageLink).click();

        return new MenPage(getDriver());
    }

    public WomenPage navigateToWomenPage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), womenPageLink).click();

        return new WomenPage(getDriver());
    }

    public AccessoriesPage navigateToAccessoriesPage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), getHeaderAccessories()).click();
        return new AccessoriesPage(getDriver());
    }

    public LostPasswordPage navigateToLostPasswordPage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), lostPasswordLink).click();
        return new LostPasswordPage(getDriver());
    }


}
