package com.ecommerce.pom.pages;

import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StorePage extends SalesPage {

    String Add = "//a[@aria-label='Add “";
    String toCart = "” to your cart']";

    By headerTitle = By.xpath("//h1[@class='woocommerce-products-header__title page-title']");
    By loopProducts = By.xpath("//h2[@class='woocommerce-loop-product__title']");
    By addToCartButton = By.xpath("//div[@class='astra-shop-summary-wrap']//a[text()='Add to cart']");
    By cartButton = By.linkText("View cart");
    By productList = By.xpath("//ul//h2");
    By nextPageNumber = By.xpath("//a[@class='next page-numbers']");
    By sortBy = By.xpath("//select[@name='orderby']");
    By sortByPrice = By.xpath("//span[@class='byPrice']");

    public StorePage(WebDriver driver) {
        super(driver);
    }

    public List<String> getAllItemsFromAllPages(By locator, WebDriver driver) {
        List<String> allItemList = new ArrayList<>();
        boolean hasNextPage = true;

        while (hasNextPage) {
            List<WebElement> itemList = WaitUtils.visibilityOfAllElementsLocatedBy(driver, locator);
            for (WebElement item : itemList) {
                allItemList.add(item.getText());
            }

            try {
                WebElement nextPageArrow = driver.findElement(nextPageNumber);
                nextPageArrow.click();
            } catch (NoSuchElementException e) {
                hasNextPage = false;
            }
        }
        return allItemList;
    }

    public String getSearchHeaderTitle() {
        return WaitUtils.visibilityOf(getDriver(), headerTitle).getText();
    }

    public String getTextFromListProducts(int number) {
        List<WebElement> items = WaitUtils.numberOfElementsToBeMoreThan(getDriver(), loopProducts, 0);
        return items.get(number).getText();
    }

    public StorePage addToCartFromStorePage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), addToCartButton).click();
        return this;
    }

    public CartPage clickCartPage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), cartButton).click();
        return new CartPage(getDriver());
    }

    public boolean areItemsInAlphabeticalOrder() {
        List<String> allItemList = getAllItemsFromAllPages(productList, getDriver()); // Получаем все элементы
        List<String> alphabeticalAllItemList = new ArrayList<>(allItemList); // Создаем копию списка
        Collections.sort(alphabeticalAllItemList);

        return allItemList.equals(alphabeticalAllItemList);
    }

    public StorePage sortByDropDownButton(){
        WaitUtils.visibilityOfElementLocated(getDriver(), sortBy).click();
        return this;
    }

    public StorePage sortByPrice(){
        WaitUtils.visibilityOfElementLocated(getDriver(), sortByPrice).click();
        return this;
    }

    public StorePage chooseAnItemByClickingAddToCart(String item){
        String chooseAnItemString = Add + item  + toCart;
        By chooseAnItem = By.xpath(chooseAnItemString);
        WaitUtils.elementToBeClickable(getDriver(), chooseAnItem).click();
        return this;
    }

}
