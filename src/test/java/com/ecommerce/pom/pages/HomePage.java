package com.ecommerce.pom.pages;

import com.ecommerce.pom.BasePage;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends PurchasePage {

    By shopButton = By.xpath("//a[@class='wp-block-button__link']");
    By manCategory = By.id("menu-item-1228");
    By shopNowButton = By.xpath("//a[@class= 'wp-block-button__link']");
    By accountButton = By.xpath("//li[@id=\"menu-item-1237\"]");
    By cartIcon = By.xpath("//div[@id=\"ast-desktop-header\"]//a[@title=\"View your shopping cart\"]//span");
    By firstProductAddToCartButton = By.xpath("//ul[@class=\"products columns-4\"]//a[2]");
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

    public StorePage shopNowButton() {
        WaitUtils.elementToBeClickable(getDriver(), shopNowButton).click();
        return new StorePage(getDriver());
    }
        public AccountPage navigateToAccountPage(){
            WaitUtils.elementToBeClickable(getDriver(), accountButton, 2).click();
            return new AccountPage(getDriver());
        }

        public CartPage naigateToCartPage () {
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
            WaitUtils.waitForIncreasedAmountOfProductsInCart(getDriver(), 3, cartIcon, getAmountOfProductsFromCartIcon(), quantity);
            return Integer.parseInt(getDriver().findElement(cartIcon).getText());
        }

        public void addFirstProductToCart () {
            WaitUtils.elementToBeClickable(getDriver(), firstProductAddToCartButton, 1).click();

        }
    }
