package com.ecommerce.pom.pages;

import org.openqa.selenium.WebDriver;

public class MensJeansPage extends SalesPage {

    public MensJeansPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MensJeansPage load() {
        getDriver().get("https://askomdch.com/product-category/mens-jeans/");

        return this;
    }
}
