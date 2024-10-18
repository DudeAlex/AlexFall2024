package com.ecommerce.pom.pages;

import com.ecommerce.pom.BasePage;
import com.ecommerce.pom.Loadable;
import com.ecommerce.pom.components.ProductsGrid;
import org.openqa.selenium.WebDriver;

public abstract class PurchasePage extends BasePage{

    private ProductsGrid productsGrid;

    public PurchasePage(WebDriver driver) {
        super(driver);
        productsGrid = new ProductsGrid(driver);
    }
}
