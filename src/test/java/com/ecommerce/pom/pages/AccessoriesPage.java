package com.ecommerce.pom.pages;

import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccessoriesPage extends SalesPage {
    By addToCartButton  = By.xpath("//div[@class='astra-shop-summary-wrap']//a[text()='Add to cart']");
    public AccessoriesPage(WebDriver driver) {
        super(driver);
    }

    public AccessoriesPage addToCartFromAccessoriesPage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), addToCartButton).click();
        return this;
    }

    public CartPage clickCartPage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), getHeaderCartButton()).click();
        return new CartPage(getDriver());
    }
}
