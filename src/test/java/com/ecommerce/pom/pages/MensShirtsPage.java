package com.ecommerce.pom.pages;

import org.openqa.selenium.WebDriver;

import static com.ecommerce.pom.pages.EndPoints.MENS_SHIRTS_URL;

public class MensShirtsPage extends SalesPage{

    public MensShirtsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MensShirtsPage load() {
        getDriver().get(MENS_SHIRTS_URL);

        return this;
    }
}
