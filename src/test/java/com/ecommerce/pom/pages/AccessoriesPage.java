package com.ecommerce.pom.pages;

import com.ecommerce.utils.CollectToListUtils;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static com.ecommerce.pom.EndPoints.ACCESSORIES_URL;

public class AccessoriesPage extends SalesPage{
    By addToCartButton  = By.xpath("//div[@class='astra-shop-summary-wrap']//a[text()='Add to cart']");
    public AccessoriesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public AccessoriesPage load() {
        getDriver().get(ACCESSORIES_URL);

        return this;
    }

    public AccessoriesPage addToCartFromAccessoriesPage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), addToCartButton).click();
        return this;
    }



    public List<String> collectCategories() {
        return CollectToListUtils.productsCategories(getDriver());
    }

}
