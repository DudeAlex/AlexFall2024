package com.ecommerce.pom.pages;

import com.ecommerce.pom.Loadable;
import com.ecommerce.pom.components.LeftSidebar;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
public abstract class SalesPage<Page extends SalesPage> extends PurchasePage<SalesPage> implements Loadable {

    By singleItemContainer = By.xpath("//ul[@class='products columns-4']//li");
    By saleTag = By.xpath("//span[@class='onsale']");
    By crossedOutPrice = By.xpath("//del");
    By sortByPrice = By.xpath("//span[@class='byPrice']");
    By sortBy = By.xpath("//select[@name='orderby']");
    By price = By.xpath("//span[@class='price']/*[not(@aria-hidden='true')]");
    By allProductList = By.xpath("//ul//h2");
    // By onSaleIcon = By.xpath("//span[contains(text(),'Sale!')]");

    private final LeftSidebar leftSidebar;

    public SalesPage(WebDriver driver) {
        super(driver);
        leftSidebar = new LeftSidebar(driver);
    }

    public LeftSidebar getLeftSidebar() {
        return leftSidebar;
    }

    public List<WebElement> getAllProductsPriceElements(){
        return getDriver().findElements(price);
    }

    public List<Double> getAllProductsPriceList() {
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

    public String onSaleIcon() {
        return WaitUtils.presenceOfElementLocated(getDriver(), saleTag).getText();
    }

    public List<String> getAllItemsNameList() {
        List<WebElement> productList = WaitUtils.visibilityOfAllElementsLocatedBy(getDriver(), allProductList);
        List<String> productNameList = new ArrayList<>();
        for (WebElement item : productList) {
            productNameList.add(item.getText());
        }

        return productNameList;
    }

    public Page clickFilterButton() {
        WaitUtils.presenceOfElementLocated(getDriver(), getLeftSidebar().filterButton).click();

        return (Page) this;
    }

    public Page moveLeftNodOfPriceFilter(int targetMinPrice) {
        WebElement leftSliderNodElement = getDriver().findElement(getLeftSidebar().leftSliderNod);

        double xOffset = ((double) (targetMinPrice - getLeftSidebar().getMinAvailableFilterPrice()) /
                getLeftSidebar().getPriceFilterStep()) * getLeftSidebar().calculateOneStepXOffsetForPriceFilterSlider();
        int xOffsetInt = (int) Math.round(xOffset);

        Actions actions = new Actions(getDriver());
        actions.clickAndHold(leftSliderNodElement)
                .moveByOffset(xOffsetInt, 0)
                .perform();

        return (Page) this;
    }

    public Page moveRightNodOfPriceFilter(int targetMaxPrice) {
        WebElement rightSliderNodElement = getDriver().findElement(getLeftSidebar().rightSliderNod);

        double xOffset = ((double) (targetMaxPrice - getLeftSidebar().getMaxAvailableFilterPrice()) /
                getLeftSidebar().getPriceFilterStep()) * getLeftSidebar().calculateOneStepXOffsetForPriceFilterSlider();
        int xOffsetInt = (int) Math.round(xOffset);

        Actions actions = new Actions(getDriver());
        actions.clickAndHold(rightSliderNodElement)
                .moveByOffset(xOffsetInt, 0)
                .perform();

        return (Page) this;
    }

}
