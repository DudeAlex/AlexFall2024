package com.ecommerce.pom.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsGrid extends BaseComponent {
    public ProductsGrid(WebDriver driver) {
        super(driver);
    }

    private final By listedItems = By.xpath("//li[contains(@class, 'ast')]");

    public By productAddToCartButton = By.xpath(".//a[contains(text(), 'Add to cart')]");
    public By productTitle = By.xpath(".//h2");

    public List<WebElement> getProductsList() {
        return getDriver().findElements(listedItems);
    }

}
