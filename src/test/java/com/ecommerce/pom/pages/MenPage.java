package com.ecommerce.pom.pages;

import com.ecommerce.utils.CollectToListUtils;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class MenPage extends SalesPage {

    By addToCartButton  = By.xpath("//div[@class='astra-shop-summary-wrap']//a[text()='Add to cart']");
    By cartButton = By.linkText("View cart");

    public MenPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void load() {
        getDriver().get("https://askomdch.com/product-category/men/");
    }

    public List<String> collectCategories() {
        return CollectToListUtils.productsCategories(getDriver());
    }

    public MenPage addToCartFromManPage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), addToCartButton).click();
        return this;
    }

    public CartPage clickCartPage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), cartButton).click();
        return new CartPage(getDriver());
    }
}

