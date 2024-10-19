package com.ecommerce.pom.pages;

import org.openqa.selenium.WebDriver;

import static com.ecommerce.pom.EndPoints.MENS_SHOES_URL;

public class MensShoesPage extends SalesPage {

    public MensShoesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MensShoesPage load() {
        getDriver().get(MENS_SHOES_URL);

        return this;
    }
}
