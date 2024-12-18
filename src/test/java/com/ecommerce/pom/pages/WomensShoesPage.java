package com.ecommerce.pom.pages;

import org.openqa.selenium.WebDriver;

import static com.ecommerce.pom.EndPoints.WOMENS_SHOES_URL;

public class WomensShoesPage extends SalesPage<WomensShoesPage> {

    public WomensShoesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public WomensShoesPage load() {
        getDriver().get(WOMENS_SHOES_URL);

        return this;
    }
}
