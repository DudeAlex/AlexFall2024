package com.ecommerce.pom.pages;

import com.ecommerce.pom.BasePage;
import com.ecommerce.pom.Loadable;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class HomePage extends PurchasePage implements Loadable {

    By shopButton = By.xpath("//a[@class='wp-block-button__link']");
    By manCategory = By.id("menu-item-1228");
    By shopNowButton = By.xpath("//a[@class= 'wp-block-button__link']");
    By accountButton = By.xpath("//li[@id=\"menu-item-1237\"]");
    By cartIcon = By.xpath("//div[@id=\"ast-desktop-header\"]//a[@title=\"View your shopping cart\"]//span");
    By accessoriesButton = By.id("menu-item-1230");
    By firstProductAddToCartElementButton = By.xpath("//a[@href=\"?add-to-cart=1215\"]");
    By accountHeaderLink = By.id("menu-item-1237");
    By resetCartButton = By.cssSelector("a.remove_from_cart_button");
    By womenCategory = By.cssSelector("#menu-item-1229");


    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void load() {
        getDriver().get("https://askomdch.com/");
    }

    public StorePage navigateToStorePage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), shopButton).click();

        return new StorePage(getDriver());
    }

    public MenPage navigateToMenPage() {
        WaitUtils.elementToBeClickable(getDriver(), manCategory, 2).click();

        return new MenPage(getDriver());
    }

    public AccessoriesPage navigateToAccessoriesPage() {
        WaitUtils.elementToBeClickable(getDriver(), accessoriesButton, 2).click();

        return new AccessoriesPage(getDriver());
    }

    public WomenPage navigateToWomenPage() {
        WaitUtils.elementToBeClickable(getDriver(), womenCategory).click();
        return new WomenPage(getDriver());
    }

    public StorePage shopNowButton() {
        WaitUtils.elementToBeClickable(getDriver(), shopNowButton).click();
        return new StorePage(getDriver());
    }
        public AccountPage navigateToAccountPage(){
            WaitUtils.elementToBeClickable(getDriver(), accountButton, 2).click();
            return new AccountPage(getDriver());
        }

        public CartPage navigateToCartPage () {
            WaitUtils.elementToBeClickable(getDriver(), cartIcon, 1).click();
            return new CartPage(getDriver());
        }

        public int getAmountOfProductsFromCartIcon ()
        {
            int amount = Integer.parseInt(WaitUtils.visibilityOfElementLocated(getDriver(), cartIcon, 1).getText());
            return amount;
        }

        public int getAmountOfProductsFromCartIconAfterIncrease ( int quantity)
        {
            WaitUtils.waitForIncreasedAmountOfProductsInCart(getDriver(), 2, cartIcon, getAmountOfProductsFromCartIcon(), quantity);
            return Integer.parseInt(getDriver().findElement(cartIcon).getText());
        }

        public void addFirstProductToCart () {
            WaitUtils.elementToBeClickable(getDriver(), firstProductAddToCartElementButton, 2).click();

        }

        public void goToProduct(){
            new Actions(getDriver())
                    .moveToElement(getDriver().findElement(firstProductAddToCartElementButton))
                    .perform();
        }

        public void goToCartIcon(){
            new Actions(getDriver())
                    .moveToElement(getDriver().findElement(cartIcon))
                    .perform();
        }

        public void resetCart(){
            WaitUtils.elementToBeClickable(getDriver(), resetCartButton, 2).click();
        }
}
