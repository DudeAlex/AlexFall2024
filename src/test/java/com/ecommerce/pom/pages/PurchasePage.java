package com.ecommerce.pom.pages;

import com.ecommerce.pom.BasePage;
import com.ecommerce.pom.components.ProductsGrid;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

public abstract class PurchasePage<Page extends PurchasePage> extends BasePage {

    By allProductList = By.xpath("//ul//h2");
    By headerTitle = By.xpath("//h1[@class='woocommerce-products-header__title page-title']");

    private final ProductsGrid productsGrid;

    public PurchasePage(WebDriver driver) {
        super(driver);
        productsGrid = new ProductsGrid(driver);
    }

    public ProductsGrid getProductsGrid() {
        return productsGrid;
    }

    public Page clickAddToCartButton(String targetProductName) {
        List<WebElement> productList = getProductsGrid().getProductsList();
        for (WebElement product : productList) {
            String productName = product.findElement(getProductsGrid().productTitle).getText();
            if (productName.equals(targetProductName)) {
                product.findElement(getProductsGrid().productAddToCartButton).click();
            }
        }

        return (Page) this;
    }

    public String getSearchHeaderTitle() {
        return WaitUtils.visibilityOf(getDriver(), headerTitle).getText();
    }

}
