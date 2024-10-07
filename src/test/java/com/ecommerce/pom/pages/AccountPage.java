package com.ecommerce.pom.pages;

import com.ecommerce.pom.BasePage;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends BasePage {

    public AccountPage(WebDriver driver) {super(driver);}
    By loginUsername = By.xpath("//input[@id=\"username\"]");
    By loginPassword = By.xpath("//input[@id=\"password\"]");
    By loginButton = By.xpath("//button[@name=\"login\"]");
    By storeButton = By.xpath("//li[@id=\"menu-item-1227\"]/a");

    public void logIn()
    {
        getDriver().findElement(loginUsername).sendKeys("aaaaa@aa.aa");
        getDriver().findElement(loginPassword).sendKeys("11111");
        getDriver().findElement(loginButton).click();
    }

    public StorePage navigateToStorePage() {
        WaitUtils.elementToBeClickable(getDriver(), storeButton, 2).click();

        return  new StorePage(getDriver());
    }
}
