package com.ecommerce.pom.pages;

import com.ecommerce.pom.BasePage;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    By shopButton = By.xpath("//a[@class='wp-block-button__link']");
    By manCategory = By.id("menu-item-1228");
    By accountHeaderLink = By.id("menu-item-1237");


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public StorePage navigateToStorePage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), shopButton).click();

        return new StorePage(getDriver());
    }

    public MenPage navigateToMenPage() {
        WaitUtils.elementToBeClickable(getDriver(), manCategory, 2).click();

        return new MenPage(getDriver());
    }

    public AccountPage navigateToAccountPage(){
        WaitUtils.elementToBeClickable(getDriver(), accountHeaderLink, 2).click();

        return new AccountPage(getDriver());
    }
}
