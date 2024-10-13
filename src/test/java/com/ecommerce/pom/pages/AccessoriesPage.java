package com.ecommerce.pom.pages;

import com.ecommerce.pom.Loadable;
import com.ecommerce.utils.CollectToListUtils;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class AccessoriesPage extends SalesPage{
    By addToCartButton  = By.xpath("//div[@class='astra-shop-summary-wrap']//a[text()='Add to cart']");
    public AccessoriesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void load() {

        getDriver().get("https://askomdch.com/product-category/accessories/");
    }

    public AccessoriesPage addToCartFromAccessoriesPage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), addToCartButton).click();
        return this;
    }

    public CartPage clickCartPage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), getHeaderCartButton()).click();
        return new CartPage(getDriver());
    }

    public List<String> collectCategories() {
        return CollectToListUtils.productsCategories(getDriver());
    }

}
