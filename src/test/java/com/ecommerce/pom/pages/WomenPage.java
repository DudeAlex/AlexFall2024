package com.ecommerce.pom.pages;

import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.ecommerce.pom.EndPoints.WOMEN_URL;

public class WomenPage extends SalesPage{

    By addToCartButton  = By.xpath("//div[@class='astra-shop-summary-wrap']//a[text()='Add to cart']");
    By cartButton = By.linkText("View cart");
    By itemsResultNumber = By.cssSelector(".woocommerce-result-count");

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
        getDriver().get(WOMEN_URL);

        return this;
    }

    @Override
    public WomenPage clickAddToCartButton(String targetProductName) {
        super.clickAddToCartButton(targetProductName);

        return this;
    }
    public int getPageResultCount() {
        String result = WaitUtils.presenceOfElementLocated(getDriver(), itemsResultNumber).getText();
        result = result.replaceAll("[A-Za-z\\s]", "");
        return Integer.parseInt(result);
    }
}
