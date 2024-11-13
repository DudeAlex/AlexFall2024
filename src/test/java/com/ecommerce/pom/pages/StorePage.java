package com.ecommerce.pom.pages;

import com.ecommerce.data.Product;
import com.ecommerce.pom.Loadable;
import com.ecommerce.utils.UserUtils;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.ecommerce.pom.EndPoints.STORE_URL;

public class StorePage extends SalesPage<StorePage> implements Loadable {

    String productFirstPartXpathTypeName = "//a[contains(text(),'";
    String productLastPartXpathTypeName = "')]";
    By headerTitle = By.xpath("//h1[@class='woocommerce-products-header__title page-title']");
    By loopProducts = By.xpath("//h2[@class='woocommerce-loop-product__title']");
    By productList = By.xpath("//ul//h2");
    By nextPageArrow = By.xpath("//a[@class='next page-numbers']");
    By previousPageArrow = By.xpath("//a[@class='prev page-numbers']");
    By listOfProducts = By.cssSelector("div ul.products li");
    By paginatorBtnArrowToRight = By.cssSelector("a.next");
    By iconNumberSecondPage = By.xpath("//a[@class='page-numbers' and text()='2']");
    By iconNumberFirstPage = By.xpath("//a[@class='page-numbers' and text()='1']");
    By DropdownListSortBy = By.xpath("//select[@name='orderby']");


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
                WebElement nextPageArrow = driver.findElement(this.nextPageArrow);
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

    public boolean areItemsInAlphabeticalOrder() {
        List<String> allItemList = getAllItemsFromAllPages(productList, getDriver()); // Получаем все элементы
        List<String> alphabeticalAllItemList = new ArrayList<>(allItemList); // Создаем копию списка
        Collections.sort(alphabeticalAllItemList);

        return allItemList.equals(alphabeticalAllItemList);
    }

    public String checkProductNameOnCartPage(String item) {
        String checkProductNameOnCartPageString = productFirstPartXpathTypeName + item + productLastPartXpathTypeName;
        By checkItemName = By.xpath(checkProductNameOnCartPageString);
        return WaitUtils.elementToBeClickable(getDriver(), checkItemName).getText();
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



    public List<Product> getAllProductsInStore() {
        List<Product> allProductsInStoreList = getProductsGrid().getAllProductsOnPage();
        clickNextPageButton();
        allProductsInStoreList.addAll(getProductsGrid().getAllProductsOnPage());

        return allProductsInStoreList;
    }

    public List<Product> getAllProductsInStoreAfterSorting() {
        List<Product> allProductsInStoreList = getProductsGrid().getAllProductsOnPage();
        clickNextPageButton();
        allProductsInStoreList.addAll(getProductsGrid().getAllProductsOnPage());

        return allProductsInStoreList;
    }

    public StorePage clickNextPageButton() {
        WaitUtils.elementToBeClickable(getDriver(), nextPageArrow).click();
        return this;
    }

    public StorePage clickPreviousPageButton() {
//        WaitUtils.elementToBeClickable(getDriver(), previousPageArrow).click();
        WaitUtils.elementToBeClickable(getDriver(), iconNumberFirstPage).click();
        return this;
    }

    public StorePage clickFirstPageIcon() {
        WaitUtils.elementToBeClickable(getDriver(), iconNumberFirstPage).click();
        return this;
    }

    public StorePage clickSecondPageIcon() {
        WaitUtils.elementToBeClickable(getDriver(), iconNumberSecondPage).click();
        return this;
    }

    public StorePage selectSortingOrder(String sortingOrder) {
        WebElement dropDown = WaitUtils.presenceOfElementLocated(getDriver(), DropdownListSortBy);
        Select select = new Select(dropDown);
        select.selectByVisibleText(sortingOrder);

        return this;
    }
}
