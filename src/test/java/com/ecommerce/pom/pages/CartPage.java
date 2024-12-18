package com.ecommerce.pom.pages;

import com.ecommerce.pom.BasePage;
import com.ecommerce.pom.Loadable;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;

import static com.ecommerce.pom.EndPoints.CART_URL;

public class CartPage extends BasePage implements Loadable {

    By checkoutButton = By.xpath("//a[@href='https://askomdch.com/checkout/']");
    By viewCart = (By.xpath("//a[@title='View cart']"));
    By quantityOfProducts = By.xpath(".//div[id='editing-view-port']");
    By updateCartButton = By.xpath("//button[@name=\"update_cart\"]");
    By cartIcon = By.xpath("//div/header/div[1]/div[1]/div/div/div/div[2]/div[2]/div/div[1]/a/div/span");
    By removeButton = By.xpath("//a[@class='remove']");
    By removePopUpButton = By.xpath("//div/header/div[1]/div[1]/div/div/div/div[2]/div[2]/div/div[2]/div/div/ul/li/a[1]");
    By returnToShopButton = By.xpath("//a[@class = 'button wc-backward']");
    By emptyCartMessage = By.xpath("//p[@class='cart-empty woocommerce-info']");
    By storePageLink = By.id("menu-item-1227");
    By spinnerElement = By.cssSelector(".blockUI.blockOverlay");
    By cartFirstProductPrice = By.xpath("(//td[@data-title='Price'])[1]//bdi");
    By cartFirstProductSubtotal = By.xpath("(//td[@data-title='Subtotal'])[1]//bdi");
    By cartFirstProductQuantityField = By.xpath("(//input[@title='Qty'])[1]");
    By itemRemovedMessage = By.xpath("//div[@role='alert']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CartPage load() {
        getDriver().get(CART_URL);

        return this;
    }

    public CheckoutPage clickCheckoutButton() {
        WaitUtils.visibilityOfElementLocated(getDriver(), checkoutButton).click();
        return new CheckoutPage(getDriver());
    }

    public CartPage clickViewCartButton() {
        WaitUtils.elementToBeClickable(getDriver(), viewCart).click();
        return new CartPage(getDriver());
    }

    //        the method is written for the first product in the cart
    public int getProductQuantityInt() {
        String productQuantity = WaitUtils.visibilityOfElementLocated(getDriver(),
                cartFirstProductQuantityField, 3).getAttribute("value");

        return Integer.parseInt(productQuantity);
    }

    //        the method is written for the first product in the cart
    public int getProductPriceInt() {
        String productPrice = WaitUtils.visibilityOfElementLocated(getDriver(),cartFirstProductPrice,2).getText();
        return Integer.parseInt(productPrice.replace("$", "").split("\\.")[0]);
    }

    //        the method written for the first product in the cart
    public int getProductSubtotalInt() {
        String productSubtotal = WaitUtils.visibilityOfElementLocated(getDriver(), cartFirstProductSubtotal, 2).getText();
        return Integer.parseInt(productSubtotal.replace("$", "").split("\\.")[0]);
    }

    public void resetValueOfProductQuantity() {
        getDriver().findElement(quantityOfProducts).clear();
        WaitUtils.elementToBeClickable(getDriver(), updateCartButton, 5).click();
        WaitUtils.invisibilityOfElementLocated(getDriver(), updateCartButton, 3);
    }

    public String getCartItemsNumber() {
        return WaitUtils.visibilityOfElementLocated(getDriver(), cartIcon).getText();
    }

    public CartPage removeItemsFromCart() {
        WaitUtils.elementToBeClickable(getDriver(), removeButton).click();

        return this;
    }

    public String getEmptyCartMessage() {
        return WaitUtils.visibilityOfElementLocated(getDriver(), emptyCartMessage).getText();
    }

    public HomePage clickReturnToShopButton() {
        WaitUtils.visibilityOfElementLocated(getDriver(), returnToShopButton).click();
        return new HomePage(getDriver());
    }

    public StorePage navigateToStorePage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), storePageLink).click();

        return new StorePage(getDriver());
    }

    public void setZeroValueOfProductQuantity() {
        getDriver().findElement(quantityOfProducts).clear();
        getDriver().findElement(quantityOfProducts).sendKeys("0");

        WaitUtils.elementToBeClickable(getDriver(), updateCartButton, 5).click();
        WaitUtils.invisibilityOfElementLocated(getDriver(), updateCartButton, 3);
    }

    public void removeItemsFromCartPreview() {

        Actions actions = new Actions(getDriver());
        actions.moveToElement(getDriver().findElement(cartIcon)).perform();
        getDriver().findElement(removePopUpButton).click();

    }

    public void clearTheCartFromOneItem() {
        CartPage cartPage = new CartPage(getDriver());
        if (Integer.parseInt(cartPage.getCartItemsNumber()) > 0) {
            cartPage.removeItemsFromCart();
        }
    }

    public CartPage clearCart() {

        CartPage cartPage = new CartPage(getDriver());
        getDriver().navigate().refresh();

        if (Integer.parseInt(cartPage.getCartItemsNumber()) > 0) {
            List<WebElement> itemList = WaitUtils.visibilityOfAllElementsLocatedBy(getDriver(), removeButton);

            while (!itemList.isEmpty()) {
                if (itemList.size() == 1) {
                    cartPage.removeItemsFromCart();
                    WaitUtils.invisibilityOfElementLocated(getDriver(), spinnerElement);
                    break;
                } else {
                    cartPage.removeItemsFromCart();
                    WaitUtils.invisibilityOfElementLocated(getDriver(), spinnerElement);
                    itemList = WaitUtils.visibilityOfAllElementsLocatedBy(getDriver(), removeButton);
                    }
                }
            }
            Assert.assertEquals(cartPage.getEmptyCartMessage(), "Your cart is currently empty.", "Cart is not empty");
        return this;
        }

//        the method is written for the first product in the cart
        public CartPage clearCartQuantityField() {
            WaitUtils.presenceOfElementLocated(getDriver(), cartFirstProductQuantityField,2).clear();

            return this;
        }

        public CartPage setProductQuantity(String productQuantity) {
        WaitUtils.visibilityOfElementLocated(getDriver(), cartFirstProductQuantityField)
                .sendKeys(productQuantity + Keys.ENTER);

        return this;
        }

        public String getItemRemovedMessage() {

        return WaitUtils.visibilityOfElementLocated(getDriver(),itemRemovedMessage,2).getText();
        }

}
