package com.ecommerce.pom.pages;

import org.openqa.selenium.WebDriver;

public class WomensJeansPage extends SalesPage{

    public WomensJeansPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public WomensJeansPage load() {
        getDriver().get("https://askomdch.com/product-category/womens-jeans/");

        return this;
    }
}
