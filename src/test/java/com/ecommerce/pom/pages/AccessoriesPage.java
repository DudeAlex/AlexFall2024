package com.ecommerce.pom.pages;

import com.ecommerce.utils.CollectToListUtils;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static com.ecommerce.pom.EndPoints.ACCESSORIES_URL;

public class AccessoriesPage extends SalesPage<AccessoriesPage> {
    public AccessoriesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public AccessoriesPage load() {
        getDriver().get(ACCESSORIES_URL);

        return this;
    }

    public List<String> collectCategories() {

        return CollectToListUtils.productsCategories(getDriver());
    }
}
