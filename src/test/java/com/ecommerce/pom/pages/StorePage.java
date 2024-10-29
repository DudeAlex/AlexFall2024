package com.ecommerce.pom.pages;

import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.ecommerce.pom.EndPoints.STORE_URL;

public class StorePage extends SalesPage implements SearchResultPage {

    String add = "//a[@aria-label='Add “";
    String toCart = "” to your cart']";
    String productFirstPartXpathTypeName = "//a[contains(text(),'";
    String productLastPartXpathTypeName = "')]";

    By headerTitle = By.xpath("//h1[@class='woocommerce-products-header__title page-title']");
    By loopProducts = By.xpath("//h2[@class='woocommerce-loop-product__title']");
    By addToCartButton = By.xpath("//div[@class='astra-shop-summary-wrap']//a[text()='Add to cart']");
    By cartButton = By.xpath("//div/header/div[1]/div[1]/div/div/div/div[2]/div[2]/div/div[1]/a/div/span");
    By productList = By.xpath("//ul//h2");
    By nextPageNumber = By.xpath("//a[@class='next page-numbers']");
    By firstProductAddToCartButton = By.xpath("//ul[@class=\"products columns-4\"]//a[2]");


    public StorePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public StorePage load() {
        getDriver().get(STORE_URL);

        return this;
    }

    private List<String> getAllItemsFromAllPages(By locator, WebDriver driver) {
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

    public StorePage chooseAnItemByClickingAddToCart(String item) {
        String chooseAnItemString = add + item + toCart;
        By chooseAnItem = By.xpath(chooseAnItemString);
        WaitUtils.elementToBeClickable(getDriver(), chooseAnItem).click();
        return this;
    }

    public String checkProductNameOnCartPage(String item) {
        String checkProductNameOnCartPageString = productFirstPartXpathTypeName + item + productLastPartXpathTypeName;
        By checkItemName = By.xpath(checkProductNameOnCartPageString);
        return WaitUtils.elementToBeClickable(getDriver(), checkItemName).getText();
    }

    public void addFirstProductToCart () {
        WaitUtils.elementToBeClickable(getDriver(), firstProductAddToCartButton, 2).click();

    }

//    public List<String> getAllItemsNameList() {
//        List<WebElement> productList = WaitUtils.visibilityOfAllElementsLocatedBy(getDriver(), allProductList);
//        List<String> productNameList = new ArrayList<>();
//        for (WebElement item : productList) {
//            productNameList.add(item.getText());
//        }
//        return productNameList;
//    }
//
//    @Override
//    public boolean containsSubstringInProductNames(String substring) {
//        for (String name : getAllItemsNameList()) {
//            if (!name.toLowerCase().contains(substring.toLowerCase())) {
//                return false;
//            }
//        }
//        return true;
//    }



}
