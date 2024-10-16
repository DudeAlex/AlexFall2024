package com.ecommerce.pom.pages;

import com.ecommerce.pom.Loadable;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class SalesPage extends PurchasePage implements Loadable {

    By searchField = By.id("woocommerce-product-search-field-0");
    By searchButton = By.xpath("//button[@value='Search']");
    By singleItemContainer = By.xpath("//ul[@class='products columns-4']//li");
    By saleTag = By.xpath("//span[@class='onsale']");
    By crossedOutPrice = By.xpath("//del");
    By sortByPrice = By.xpath("//span[@class='byPrice']");
    By sortBy = By.xpath("//select[@name='orderby']");
    By price = By.xpath("//span[@class='price']/*[not(@aria-hidden='true')]");
   // By onSaleIcon = By.xpath("//span[contains(text(),'Sale!')]");

    public SalesPage(WebDriver driver) {
        super(driver);
    }

    private List<Double> getAllProductsPriceList() {
        List<WebElement> priceTextList = getDriver().findElements(price);
        List<String> priceStrings = new ArrayList<>();
        for (WebElement priceElement : priceTextList) {
            priceStrings.add(priceElement.getText());
        }

        return getConvertedToDoublePriceList(priceStrings);
    }

    private static List<Double> getConvertedToDoublePriceList(List<String> priceTextList) {
        List<Double> actualPriceList = new ArrayList<>();
        for (String priceText : priceTextList) {
            String priceClearedFromSigns = priceText.replace("$", "").trim();
            double price = Double.parseDouble(priceClearedFromSigns);
            actualPriceList.add(price);
        }

        return actualPriceList;
    }

    public StorePage searchProduct(String item) {
        WaitUtils.visibilityOfElementLocated(getDriver(), searchField).sendKeys(item);
        WaitUtils.presenceOfElementLocated(getDriver(), searchButton).click();
        return new StorePage(getDriver());
    }

    public List<Boolean> areProductsOnSaleHaveCrossedPrice() {
        List<WebElement> productsList = getDriver().findElements(singleItemContainer);
        List<Boolean> saleStatusList = new ArrayList<>();

        for (WebElement product : productsList) {
            try {
                WebElement saleTagElement = product.findElement(saleTag);
                WebElement delTagElement = product.findElement(crossedOutPrice);

                saleStatusList.add(saleTagElement.getText().equals("Sale!") && delTagElement.getText() != null);

            } catch (NoSuchElementException e) {
                saleStatusList.add(false);
            }
        }

        return saleStatusList;
    }

    public SalesPage sortByDropDownButton() {
        WaitUtils.elementToBeClickable(getDriver(), sortBy).click();
        return this;
    }

    public SalesPage sortByPrice(String priceType) {
        WaitUtils.visibilityOfElementLocated(getDriver(), sortByPrice).click();
        return this;
    }

    public SalesPage selectSortByPriceLowToHigh() {
        WebElement dropdown = WaitUtils.visibilityOf(getDriver(), sortBy);
        Select select = new Select(dropdown);
        select.selectByVisibleText("Sort by price: low to high");

        return this;
    }

    public SalesPage selectSortByPriceHighToLow() {
        WebElement dropdown = WaitUtils.visibilityOf(getDriver(), sortBy);
        Select select = new Select(dropdown);
        select.selectByVisibleText("Sort by price: high to low");

        return this;
    }

    public Boolean areProductsPricesInAscendingOrder() {
        List<Double> actualPriceList = getAllProductsPriceList();

        List<Double> expectedLowToHighPriceList = new ArrayList<>(actualPriceList);
        Collections.sort(expectedLowToHighPriceList);

        return actualPriceList.equals(expectedLowToHighPriceList);
    }

    public Boolean areProductsPricesInDescendingOrder() {
        List<Double> actualPriceList = getAllProductsPriceList();

        List<Double> expectedLowToHighPriceList = new ArrayList<>(actualPriceList);
        expectedLowToHighPriceList.sort(Comparator.reverseOrder());

        return actualPriceList.equals(expectedLowToHighPriceList);
    }

    public String onSaleIcon(){
        return  WaitUtils.presenceOfElementLocated(getDriver(),saleTag).getText();
    }

}
