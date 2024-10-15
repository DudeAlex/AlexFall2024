package com.ecommerce.pom.components;

import com.ecommerce.pom.pages.AccessoriesPage;
import com.ecommerce.pom.pages.CartPage;
import com.ecommerce.pom.pages.ContactUsPage;
import com.ecommerce.pom.pages.MenPage;
import com.ecommerce.pom.pages.StorePage;
import com.ecommerce.pom.pages.WomenPage;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header {
    private WebDriver driver;

    public Header(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    /**
     * header elements like links, buttons, etc.
     */
    By headerHome = By.id("menu-item-1226"); // Header Home
    By headerStore = By.id("menu-item-1227"); // Header Store
    By headerMen = By.id("menu-item-1228"); // Header Men
    By headerWomen = By.id("menu-item-1229"); // Header Women
    By headerAccessories = By.id("menu-item-1230"); // Header Accessories
    By headerAccount = By.id("menu-item-1237"); // Header Account
    By headerAbout = By.id("menu-item-1232"); // Header About
    By headerContactUs = By.id("menu-item-1233"); // Header Contact Us
    By headerCartButton = By.xpath("(//span[@class='count'])[1]"); // Header Cart

    public AccessoriesPage navigateToAccessoriesPage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), headerAccessories).click();
        return new AccessoriesPage(getDriver());
    }

    public CartPage clickCartPage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), headerCartButton).click();
        return new CartPage(getDriver());
    }

    public ContactUsPage clickContactUs() {
        WaitUtils.visibilityOfElementLocated(getDriver(), headerContactUs).click();
        return new ContactUsPage(getDriver());
    }

    public MenPage navigateToMenPage() {
        WaitUtils.elementToBeClickable(getDriver(), headerMen, 2).click();

        return new MenPage(getDriver());
    }

    public WomenPage navigateToWomenPage() {
        WaitUtils.elementToBeClickable(getDriver(), headerWomen).click();
        return new WomenPage(getDriver());
    }

    public StorePage navigateToStorePage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), headerStore).click();

        return new StorePage(getDriver());
    }

}
