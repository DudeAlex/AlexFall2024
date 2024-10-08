package com.ecommerce.pom.pages;

import com.ecommerce.pom.BasePage;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    By checkoutButton = By.xpath("//a[@href='https://askomdch.com/checkout/']");
   // By productName = (By.xpath("//a[@aria-label='Add “" +  + "” to your cart']")
    By viewCart = (By.xpath("//a[@title='View cart']"));


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

}
