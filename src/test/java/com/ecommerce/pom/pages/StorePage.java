package com.ecommerce.pom.pages;

import com.ecommerce.pom.BasePage;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class StorePage extends SalesPage {

    By headerTitle = By.xpath("//h1[@class='woocommerce-products-header__title page-title']");
    By loopProducts = By.xpath("//h2[@class='woocommerce-loop-product__title']");
    By addToCartButton  = By.xpath("//div[@class='astra-shop-summary-wrap']//a[text()='Add to cart']");

    public StorePage(WebDriver driver) {
        super(driver);
    }

    public String getSearchHeaderTitle() {
        return WaitUtils.visibilityOf(getDriver(), headerTitle).getText();
    }

    public String getTextFromListProducts(int number) {
        List<WebElement> items = WaitUtils.numberOfElementsToBeMoreThan(getDriver(), loopProducts, 0);
        return items.get(number).getText();
    }

    public void addToCartFromStorePage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), addToCartButton).click();
    }

}

