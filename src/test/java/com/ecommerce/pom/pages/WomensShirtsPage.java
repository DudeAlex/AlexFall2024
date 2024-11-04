package com.ecommerce.pom.pages;

import org.openqa.selenium.WebDriver;

import static com.ecommerce.pom.EndPoints.WOMENS_SHIRTS_URL;

public class WomensShirtsPage extends SalesPage<WomensShirtsPage> {

    public WomensShirtsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public WomensShirtsPage load() {
        getDriver().get(WOMENS_SHIRTS_URL);

        return this;
    }
}
