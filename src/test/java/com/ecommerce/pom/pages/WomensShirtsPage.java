package com.ecommerce.pom.pages;

import org.openqa.selenium.WebDriver;

import static com.ecommerce.pom.pages.EndPoints.WOMENS_SHIRTS_URL;

public class WomensShirtsPage extends SalesPage{

    public WomensShirtsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public WomensShirtsPage load() {
        getDriver().get(WOMENS_SHIRTS_URL);

        return this;
    }
}
