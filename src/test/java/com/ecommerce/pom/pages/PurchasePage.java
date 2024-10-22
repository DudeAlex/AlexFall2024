package com.ecommerce.pom.pages;

import com.ecommerce.pom.BasePage;
import com.ecommerce.pom.components.ProductsGrid;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class PurchasePage<T extends PurchasePage> extends BasePage {


    private final ProductsGrid productsGrid;

    public PurchasePage(WebDriver driver) {
        super(driver);
        productsGrid = new ProductsGrid(driver);
    }

    public ProductsGrid getProductsGrid() {
        return productsGrid;
    }

    public T clickAddToCartButton(String targetProductName) {
        List<WebElement> productList = getProductsGrid().getProductsList();
        for (WebElement product: productList) {
            String productName = product.findElement(getProductsGrid().productTitle).getText();
            if (productName.equals(targetProductName)) {
                product.findElement(getProductsGrid().productAddToCartButton).click();
            }
        }

        return (T) this;
    }

}
