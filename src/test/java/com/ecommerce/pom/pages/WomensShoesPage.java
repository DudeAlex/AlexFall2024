package com.ecommerce.pom.pages;

import org.openqa.selenium.WebDriver;

public class WomensShoesPage extends SalesPage{

    public WomensShoesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void load() {
        getDriver().get("https://askomdch.com/product-category/womens-shoes/");
    }
}
