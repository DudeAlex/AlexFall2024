package com.ecommerce.pom.pages;

import com.ecommerce.pom.Loadable;
import com.ecommerce.utils.UserUtils;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.ecommerce.pom.EndPoints.STORE_URL;

public class StorePage extends SalesPage<StorePage> implements Loadable {

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
    By products = By.cssSelector("div.ast-woocommerce-container>ul.products");
    By listOfProducts = By.cssSelector("div ul.products li");
    By paginatorBtnArrowToRight = By.cssSelector("a.next");
    By spinnerElement = By.cssSelector(".button.product_type_simple.add_to_cart_button.ajax_add_to_cart.loading");


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
        WaitUtils.invisibilityOfElementLocated(getDriver(), spinnerElement);
        return this;
    }

    public String checkProductNameOnCartPage(String item) {
        String checkProductNameOnCartPageString = productFirstPartXpathTypeName + item + productLastPartXpathTypeName;
        By checkItemName = By.xpath(checkProductNameOnCartPageString);
        return WaitUtils.elementToBeClickable(getDriver(), checkItemName).getText();
    }

    public void addFirstProductToCart() {
        WaitUtils.elementToBeClickable(getDriver(), firstProductAddToCartButton, 2).click();

    }

    public void checkLabelSaleOnEveryDiscountProduct() {

        boolean hasNextPage = true;

        //check pagination
        while (hasNextPage) {
            WaitUtils.visibilityOf(getDriver(), listOfProducts, 5000);
            List<WebElement> products = getDriver().findElements(listOfProducts);
            int counter = 1;

            //Get every product and check
            for (int i = 0; i < products.size(); i++) {

                //Check if the product has a tag named "del" then it should have a "Sale" label
                if (products.get(i).findElements(By.tagName("del")).size() > 0) {

                    WebElement firstProductPict = getDriver().findElement(By.cssSelector("ul.products li.product:nth-child(" + counter + ") a:nth-child(2)"));
                    WebElement saleLabel = getDriver().findElement(By.cssSelector("ul.products li.product:nth-child(" + counter + ") span.onsale"));

                    org.openqa.selenium.Rectangle rect1 = firstProductPict.getRect();
                    org.openqa.selenium.Rectangle rect2 = saleLabel.getRect();
                    boolean isOverlap = UserUtils.isOverlapping(rect1, rect2);
                    Assert.assertTrue(isOverlap, "Sale icon doesn't overlap product picture");
                }
                counter += 1;
            }
            try {
                WebElement nextButton = WaitUtils.presenceOfElementLocated(getDriver(), paginatorBtnArrowToRight);
                if (nextButton.isDisplayed()) {
                    nextButton.click();
                } else {
                    hasNextPage = false;
                }

            } catch (Exception e) {
                hasNextPage = false;
            }
        }
    }

    public StorePage clickNextPageButton() {
        WaitUtils.elementToBeClickable(getDriver(), nextPageNumber).click();
        return this;
    }
}
