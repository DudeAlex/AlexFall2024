package com.ecommerce.pom.pages;

import com.ecommerce.pom.BasePage;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    By checkoutButton = By.xpath("//a[@href='https://askomdch.com/checkout/']");
    By cartIcon = By.xpath("//span[@class='count']");
    By removeButton = By.xpath("//a[@class='remove']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage clickCheckoutButton() {
        WaitUtils.visibilityOfElementLocated(getDriver(), checkoutButton).click();
        return new CheckoutPage(getDriver());
    }

    public String getCartItemsNumber(){
       return WaitUtils.visibilityOfElementLocated(getDriver(),cartIcon).getText();
    }

    public void removeItemsFromCart(){

        WaitUtils.elementToBeClickable(getDriver(),removeButton).click();

    }
}
