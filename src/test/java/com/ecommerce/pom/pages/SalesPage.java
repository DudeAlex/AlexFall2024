package com.ecommerce.pom.pages;

import com.ecommerce.pom.BasePage;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class SalesPage extends PurchasePage {

    By searchField = By.id("woocommerce-product-search-field-0");
    By searchButton = By.xpath("//button[@value='Search']");
    By singleItemContainer = By.xpath("//ul[@class='products columns-4']//li");
    By saleTag = By.xpath("//span[@class='onsale']");
    By crossedOutPrice = By.xpath("//del");
    By sortByPrice = By.xpath("//span[@class='byPrice']");
    By sortBy = By.xpath("//select[@name='orderby']");

    public SalesPage(WebDriver driver) {
        super(driver);
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

    public SalesPage sortByDropDownButton(){
        WaitUtils.elementToBeClickable(getDriver(), sortBy).click();
        return this;
    }

    public SalesPage sortByPrice(String priceType){
        WaitUtils.visibilityOfElementLocated(getDriver(), sortByPrice).click();
        return this;
    }

}
