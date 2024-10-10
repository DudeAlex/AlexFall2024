package com.ecommerce.pom.pages;

import com.ecommerce.pom.BasePage;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    By checkoutButton = By.xpath("//a[@href='https://askomdch.com/checkout/']");
   // By productName = (By.xpath("//a[@aria-label='Add “" +  + "” to your cart']")
    By viewCart = (By.xpath("//a[@title='View cart']"));
    By quantityOfProducts = By.xpath("//input[@type=\"number\"]");
    By updateCartButton = By.xpath("//button[@name=\"update_cart\"]");
    By cartIcon = By.xpath("//span[@class='count']");
    By removeButton = By.xpath("//a[@class='remove']");
    By returnToShopButton = By.xpath("//a[@class = 'button wc-backward']");
    By emptyCartMessage = By.xpath("//p[@class='cart-empty woocommerce-info']");
    By storePageLink = By.id("menu-item-1227");



    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage clickCheckoutButton() {
        WaitUtils.visibilityOfElementLocated(getDriver(), checkoutButton).click();
        return new CheckoutPage(getDriver());
    }


    public CartPage clickViewCartButton(){
        WaitUtils.elementToBeClickable(getDriver(), viewCart).click();
        return new CartPage(getDriver());
    }


    public int getProductsQuantity()
    {
        return Integer.parseInt(WaitUtils.visibilityOfElementLocated(getDriver(), quantityOfProducts, 3).getAttribute("value"));
    }

    public void resetValueOfProductQuantity()
    {
        getDriver().findElement(quantityOfProducts).clear();
        WaitUtils.elementToBeClickable(getDriver(), updateCartButton, 5).click();
        WaitUtils.invisibilityOfElementLocated(getDriver(), updateCartButton, 3);
    }
    public String getCartItemsNumber(){
       return WaitUtils.visibilityOfElementLocated(getDriver(),cartIcon).getText();
    }

    public void removeItemsFromCart(){

        WaitUtils.elementToBeClickable(getDriver(),removeButton).click();
    }

    public String getEmptyCartMessage() {
        return WaitUtils.visibilityOfElementLocated(getDriver(),emptyCartMessage).getText();
    }
    public void clickReturnToShopButton() {
        WaitUtils.visibilityOfElementLocated(getDriver(), returnToShopButton).click();
        new HomePage(getDriver());
    }

    public StorePage navigateToStorePage() {
        WaitUtils.visibilityOfElementLocated(getDriver(),storePageLink).click();

        return new StorePage(getDriver());
    }

    public void setZeroValueOfProductQuantity()
    {
        getDriver().findElement(quantityOfProducts).clear();
        getDriver().findElement(quantityOfProducts).sendKeys("0");

        WaitUtils.elementToBeClickable(getDriver(), updateCartButton, 5).click();
        WaitUtils.invisibilityOfElementLocated(getDriver(), updateCartButton, 3);
    }
}
