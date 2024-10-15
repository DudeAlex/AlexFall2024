package com.ecommerce.pom.pages;

import org.openqa.selenium.WebDriver;

public class MensShirtsPage extends SalesPage{

    public MensShirtsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void load() {
        getDriver().get("https://askomdch.com/product-category/mens-shirts/");
    }
}
