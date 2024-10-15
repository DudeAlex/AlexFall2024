package com.ecommerce.pom.pages;

import com.ecommerce.pom.Loadable;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class HomePage extends PurchasePage implements Loadable {

    By shopNowButton = By.xpath("//a[@class= 'wp-block-button__link']");
    By accountButton = By.xpath("//li[@id=\"menu-item-1237\"]");
    By cartIcon = By.xpath("//div[@id=\"ast-desktop-header\"]//a[@title=\"View your shopping cart\"]//span");
    By firstProductAddToCartElementButton = By.xpath("//a[@href=\"?add-to-cart=1215\"]");
    By accountHeaderLink = By.id("menu-item-1237");
    By resetCartButton = By.cssSelector("a.remove_from_cart_button");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void load() {
        getDriver().get("https://askomdch.com/");
    }

    public StorePage shopNowButton() {
        WaitUtils.elementToBeClickable(getDriver(), shopNowButton).click();
        return new StorePage(getDriver());
    }

    public AccountPage navigateToAccountPage() {
        WaitUtils.elementToBeClickable(getDriver(), accountButton, 2).click();
        return new AccountPage(getDriver());
    }

    public CartPage navigateToCartPage() {
        WaitUtils.elementToBeClickable(getDriver(), cartIcon, 1).click();
        return new CartPage(getDriver());
    }

    public int getAmountOfProductsFromCartIcon() {
        int amount = Integer.parseInt(WaitUtils.visibilityOfElementLocated(getDriver(), cartIcon, 1).getText());
        return amount;
    }

    public int getAmountOfProductsFromCartIconAfterIncrease(int quantity) {
        WaitUtils.waitForIncreasedAmountOfProductsInCart(getDriver(), 2, cartIcon, getAmountOfProductsFromCartIcon(), quantity);
        return Integer.parseInt(getDriver().findElement(cartIcon).getText());
    }

    public void addFirstProductToCart() {
        WaitUtils.elementToBeClickable(getDriver(), firstProductAddToCartElementButton, 2).click();

    }

    public void goToProduct() {
        new Actions(getDriver())
                .moveToElement(getDriver().findElement(firstProductAddToCartElementButton))
                .perform();
    }

    public void goToCartIcon() {
        new Actions(getDriver())
                .moveToElement(getDriver().findElement(cartIcon))
                .perform();
    }

    public void resetCart() {
        WaitUtils.elementToBeClickable(getDriver(), resetCartButton, 2).click();
    }
}
