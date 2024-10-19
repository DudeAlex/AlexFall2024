package com.ecommerce.pom.pages;

import com.ecommerce.pom.BasePage;
import com.ecommerce.pom.components.ProductsGrid;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class PurchasePage extends BasePage {


    private final ProductsGrid productsGrid;

    public PurchasePage(WebDriver driver) {
        super(driver);
        productsGrid = new ProductsGrid(driver);
    }

    public ProductsGrid getProductsGrid() {

        return productsGrid;
    }

    public PurchasePage clickAddToCartButton(String targetProductName) {
        List<WebElement> productList = getProductsList();
        for (WebElement product: productList) {
            String productName = product.findElement(productTitle).getText();
            if (productName.equals(targetProductName)) {
                product.findElement(productAddToCartButton).click();
            }
        }

        return this;
    }

}
