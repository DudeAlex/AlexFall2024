package com.ecommerce.pom.pages;

import com.ecommerce.pom.BaseModel;
import com.ecommerce.pom.BasePage;
import com.ecommerce.pom.components.Footer;
import com.ecommerce.pom.components.Header;
import com.ecommerce.pom.components.ProductsGrid;
import org.openqa.selenium.WebDriver;

public abstract class PurchasePage extends BaseModel {

    private Header header;
    private Footer footer;
    private ProductsGrid productsGrid;

    public PurchasePage(WebDriver driver) {
        super(driver);
        header = new Header(driver);
        footer = new Footer(driver);
        productsGrid = new ProductsGrid(driver);
    }

    public ProductsGrid getProductsGrid() {
        return productsGrid;
    }

    public Header getHeader() {
        return header;
    }

    public Footer getFooter() {
        return footer;
    }
}
