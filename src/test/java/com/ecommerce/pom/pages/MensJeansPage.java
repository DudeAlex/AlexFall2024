package com.ecommerce.pom.pages;

import org.openqa.selenium.WebDriver;

import static com.ecommerce.pom.pages.EndPoints.MENS_JEANS_URL;

public class MensJeansPage extends SalesPage {

    public MensJeansPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MensJeansPage load() {
        getDriver().get(MENS_JEANS_URL);

        return this;
    }
}
