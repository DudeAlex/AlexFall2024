package com.ecommerce.pom.pages;

import com.ecommerce.pom.BasePage;
import com.ecommerce.pom.components.ProductsGrid;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.NoSuchElementException;

public abstract class PurchasePage<Page extends PurchasePage> extends BasePage {

    By allProductList = By.xpath("//ul//h2");
    By headerTitle = By.xpath("//h1[@class='woocommerce-products-header__title page-title']");
    By addToCartFirstProductOnPageButton = By.xpath(
            "//ul[contains(@class, 'products columns')]//li[1]//a[contains(@class, 'button')]");

    private final ProductsGrid productsGrid;

    public PurchasePage(WebDriver driver) {
        super(driver);
        productsGrid = new ProductsGrid(driver);
    }

    public ProductsGrid getProductsGrid() {
        return productsGrid;
    }

    public Page addProductToCart(String targetProductName) {
        List<WebElement> productList = getProductsGrid().getProductsList();
        for (WebElement product: productList) {
            String productName = product.findElement(getProductsGrid().productTitle).getText();
            if (productName.equals(targetProductName)) {
                product.findElement(getProductsGrid().productAddToCartButton).click();
            }
        }

        return (Page) this;
    }

    public <Page extends PurchasePage> Page addFirstProductOnPageToCart() {
        WaitUtils.visibilityOfElementLocated(getDriver(), addToCartFirstProductOnPageButton).click();

        return (Page) this;
    }

    public Page addProductToCartNumberOfTimes(int numberOfTimes) {
        for(int i = 0; i < numberOfTimes; i++) {
            addFirstProductOnPageToCart();
        }

        return (Page) this;
    }

    private static int countItemsContainingItemText(List<WebElement> items, String product) {
        int count = 0;
        for (WebElement item : items) {
            String itemText = item.getText().toLowerCase();
            if (itemText.contains(product.toLowerCase())) {
                count++;
            }
        }
        return count;
    }

    public int countItemsOnPage(String itemName) {
        List<WebElement> searchResultList = WaitUtils.visibilityOfAllElementsLocatedBy(getDriver(), allProductList);
        if (searchResultList.isEmpty()) {
            throw new NoSuchElementException("Search results are empty.");
        }

        return countItemsContainingItemText(searchResultList, itemName);
    }

    public String getSearchHeaderTitle() {
        return WaitUtils.visibilityOf(getDriver(), headerTitle).getText();
    }

    public int getNumberOfProductsOnPage() {
        List<WebElement> itemsList = WaitUtils.visibilityOfAllElementsLocatedBy(getDriver(), allProductList);
        return itemsList.size();
    }
    public Page goToProduct(){
        new Actions(getDriver())
                .moveToElement(getDriver().findElement(addToCartFirstProductOnPageButton))
                .perform();

        return (Page) this;
    }
}
