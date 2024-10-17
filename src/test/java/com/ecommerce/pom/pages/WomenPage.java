package com.ecommerce.pom.pages;

import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WomenPage extends SalesPage{

    By addToCartButton  = By.xpath("//div[@class='astra-shop-summary-wrap']//a[text()='Add to cart']");
    By cartButton = By.linkText("View cart");

    public WomenPage(WebDriver driver) {
        super(driver);
    }

    public WomenPage addToCartFromWomenPage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), addToCartButton).click();
        return this;
    }
    public CartPage clickCartPage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), cartButton).click();
        return new CartPage(getDriver());
    }

    @Override
    public WomenPage load() {
        getDriver().get("https://askomdch.com/product-category/women/");

        return this;
    }
}
