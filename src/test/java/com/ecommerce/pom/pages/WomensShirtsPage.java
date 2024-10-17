package com.ecommerce.pom.pages;

import org.openqa.selenium.WebDriver;

public class WomensShirtsPage extends SalesPage{

    public WomensShirtsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public WomensShirtsPage load() {
        getDriver().get("https://askomdch.com/product-category/womens-shirts/");

        return this;
    }
}
