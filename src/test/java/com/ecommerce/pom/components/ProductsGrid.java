package com.ecommerce.pom.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsGrid extends BaseComponent {
    public ProductsGrid(WebDriver driver) {
        super(driver);
    }

    private By listedItems = By.xpath("//li[contains(@class, 'ast')]");

    private By productAddToCartButton = By.xpath(".//a[contains(text(), 'Add to cart')]");
    private By productTitle = By.xpath(".//h2");

    private List<WebElement> getProductsList() {
        return getDriver().findElements(listedItems);
    }

    public ProductsGrid clickAddToCartButton(String targetProductName) {
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
