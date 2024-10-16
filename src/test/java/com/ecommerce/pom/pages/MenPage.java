package com.ecommerce.pom.pages;

import com.ecommerce.pom.Loadable;
import com.ecommerce.utils.CollectToListUtils;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class MenPage extends SalesPage{

    By addToCartButton  = By.xpath("//div[@class='astra-shop-summary-wrap']//a[text()='Add to cart']");
    By cartButton = By.linkText("View cart");
    By firstProductAddToCartButton = By.xpath("//ul[@class=\"products columns-4\"]//a[2]");
    By cartIcon = By.xpath("//div[@id=\"ast-desktop-header\"]//a[@title=\"View your shopping cart\"]//span");
    By pageHeader = By.tagName("h1");

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

    public void goToProduct(){
        new Actions(getDriver())
                .moveToElement(getDriver().findElement(firstProductAddToCartButton))
                .perform();
    }

    public void addFirstProductToCart () {
        WaitUtils.elementToBeClickable(getDriver(), firstProductAddToCartButton, 2).click();

    }

    public void goToCartIcon(){
        new Actions(getDriver())
                .moveToElement(getDriver().findElement(cartIcon))
                .perform();
    }

    public String verifyHeaderText(){
        return WaitUtils.visibilityOfElementLocated(getDriver(), pageHeader, 2).getText();
    }
}

