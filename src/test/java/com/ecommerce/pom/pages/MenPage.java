package com.ecommerce.pom.pages;

import com.ecommerce.utils.CollectToListUtils;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class MenPage extends SalesPage {

    public MenPage(WebDriver driver) {
        super(driver);
    }

    public List<String> collectCategories() {
        return CollectToListUtils.productsCategories(getDriver());
    }
}

