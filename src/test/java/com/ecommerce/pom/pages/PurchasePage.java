package com.ecommerce.pom.pages;

import com.ecommerce.pom.BaseModel;
import com.ecommerce.pom.BasePage;
import com.ecommerce.pom.components.ProductsGrid;
import org.openqa.selenium.WebDriver;

public abstract class PurchasePage extends BaseModel {

    private ProductsGrid productsGrid;

    public PurchasePage(WebDriver driver) {
        super(driver);
        productsGrid = new ProductsGrid(driver);
    }

    public ProductsGrid getProductsGrid() {
        return productsGrid;
    }
}
