package com.ecommerce.pom.pages;

import com.ecommerce.pom.BasePage;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CartPage extends BasePage {
    By checkoutButton = By.xpath("//a[@href='https://askomdch.com/checkout/']");
    By quantityOfProducts = By.xpath("//input[@type=\"number\"]");
    By updateCartButton = By.xpath("//button[@name=\"update_cart\"]");
    public CartPage(WebDriver driver) {
        super(driver);
    }
    public CheckoutPage clickCheckoutButton() {
        WaitUtils.visibilityOfElementLocated(getDriver(), checkoutButton).click();
        return new CheckoutPage(getDriver());
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
}
