package com.ecommerce.pom.components;

import com.ecommerce.data.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductsGrid extends BaseComponent {
    public ProductsGrid(WebDriver driver) {
        super(driver);
    }

    private final By listedItems = By.xpath("//li[contains(@class, 'ast')]");

    public By productAddToCartButton = By.xpath(".//a[contains(text(), 'Add to cart')]");
    public By productTitle = By.xpath(".//h2");

    private final By priceForProductsWithoutDiscount = By.
            xpath(".//span[contains(@class,'price')]/span/bdi");
    private final By priceAfterDiscount = By.xpath(".//ins ");
    private final By priceBeforeDiscount = By.xpath(".//del");

    private final By productCategoryLabel = By.cssSelector(".astra-shop-summary-wrap .ast-woo-product-category");

    public List<WebElement> getProductsList() {

        return getDriver().findElements(listedItems);
    }

    public List<Product> getAllProductsOnPage() {
        List<Product> allProductsOnPage = new ArrayList<>();

        for (WebElement productElement : getProductsList()) {
            String productName = productElement.findElement(productTitle).getText();
            double actualPrice;
            List<WebElement> discountPriceElements = productElement.findElements(priceAfterDiscount);

            if (!discountPriceElements.isEmpty()) {
                actualPrice = Double.parseDouble(productElement
                        .findElement(priceAfterDiscount)
                        .getText()
                        .replaceAll("[^\\d.]", ""));
            } else {
                actualPrice = Double.parseDouble(productElement
                        .findElement(priceForProductsWithoutDiscount)
                        .getText()
                        .replaceAll("[^\\d.]", ""));
            }
            allProductsOnPage.add(new Product(productName, actualPrice));
        }

        return allProductsOnPage;
    }

    public List <String> getProductCategoryLabel(){
        List <WebElement> productlist = getProductsList();
        List<String> categoryLabels = new ArrayList<>();
        productlist.forEach(label ->{
            String categoryLabelText = label.findElement(productCategoryLabel).getText();
            categoryLabels.add(categoryLabelText);
        });
       return categoryLabels;
    }
}
