package com.ecommerce.pom.pages;

import com.ecommerce.pom.BasePage;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class AccountPage extends BasePage {
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

    public AccountPage(WebDriver driver) {
        super(driver);
    }
    
        public void logIn()
    {
        getDriver().findElement(loginUsername).sendKeys("aaaaa@aa.aa");
        getDriver().findElement(loginPassword).sendKeys("11111");
        getDriver().findElement(loginButton).click();
    }

    public StorePage navigateToStorePage() {
        WaitUtils.visibilityOfElementLocated(getDriver(),storePageLink).click();

        return new StorePage(getDriver());
    }
    public CartPage navigateToCartPage() {
        WaitUtils.visibilityOfElementLocated(getDriver(),cartIcon).click();

        return new CartPage(getDriver());
    }

    public MenPage navigateToMenPage() {
        WaitUtils.visibilityOfElementLocated(getDriver(),menPageLink).click();

        return new MenPage(getDriver());
    }

    public WomenPage navigateToWomenPage() {
        WaitUtils.visibilityOfElementLocated(getDriver(),womenPageLink).click();

        return new WomenPage(getDriver());
    }

    public AccessoriesPage navigateToAccessoriesPage() {
        WaitUtils.visibilityOfElementLocated(getDriver(),getHeaderAccessories()).click();
        return new AccessoriesPage(getDriver());
    }
}
