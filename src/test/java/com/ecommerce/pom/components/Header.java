package com.ecommerce.pom.components;

import com.ecommerce.pom.AboutPage;
import com.ecommerce.pom.pages.*;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Header extends BaseComponent {

    public Header(WebDriver driver) {
        super(driver);
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
    By activeMenuItem = By.cssSelector("li.current-menu-item a"); // Active element of heade menu


    public HomePage navigateToHomePage(){
        WaitUtils.visibilityOfElementLocated(getDriver(), headerHome).click();
        return new HomePage(getDriver());
    }

    public StorePage navigateToStorePage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), headerStore).click();

        return new StorePage(getDriver());
    }

    public MenPage navigateToMenPage() {
        WaitUtils.elementToBeClickable(getDriver(), headerMen, 2).click();

        return new MenPage(getDriver());
    }

    public WomenPage navigateToWomenPage() {
        WaitUtils.elementToBeClickable(getDriver(), headerWomen).click();
        return new WomenPage(getDriver());
    }

    public AccessoriesPage navigateToAccessoriesPage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), headerAccessories).click();
        return new AccessoriesPage(getDriver());
    }

    public AccountPage navigateToAccountPage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), headerAccount).click();
        return new AccountPage(getDriver());
    }

    public AboutPage navigateToAboutPage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), headerAbout).click();
        return new AboutPage(getDriver());
    }

    public ContactUsPage navigateToContactUsPage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), headerContactUs).click();
        return new ContactUsPage(getDriver());
    }

    public CartPage navigateToCartPage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), headerCartButton).click();
        return new CartPage(getDriver());
    }

    public int getAmountOfProductsOnCartIcon() {
        String numberProductsInCart = getDriver().findElement(headerCartButton).getText();

        return Integer.parseInt(numberProductsInCart);
    }

    public String getActiveMenuColor(By menuItemLocator) {
        // Wait until element is visible
        WebElement menuItem = WaitUtils.visibilityOfElementLocated(getDriver(), menuItemLocator);
        menuItem.click();

        // Wait until element become active
        WebElement activeItem = WaitUtils.visibilityOfElementLocated(getDriver(), activeMenuItem);

        // return the color of active element
        return activeItem.getCssValue("color");
    }

    public By getHeaderHome() {
        return headerHome;
    }

    public By getHeaderStore() {
        return headerStore;
    }

    public By getHeaderMen() {
        return headerMen;
    }

    public By getHeaderWomen() {
        return headerWomen;
    }

    public By getHeaderAccessories() {
        return headerAccessories;
    }

    public By getHeaderAccount() {
        return headerAccount;
    }

    public By getHeaderAbout() {
        return headerAbout;
    }

    public By getHeaderContactUs() {
        return headerContactUs;
    }

    public By getHeaderCartButton() {
        return headerCartButton;
    }

    public By getActiveMenuItem() {return activeMenuItem;}
}
