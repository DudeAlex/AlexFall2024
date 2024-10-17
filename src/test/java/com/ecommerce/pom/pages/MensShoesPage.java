package com.ecommerce.pom.pages;

import org.openqa.selenium.WebDriver;

public class MensShoesPage extends SalesPage {

    public MensShoesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MensShoesPage load() {
        getDriver().get("https://askomdch.com/product-category/mens-shoes/");

        return this;
    }
}
