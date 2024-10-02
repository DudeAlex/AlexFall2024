package com.ecommerce.pom.pages;

import com.ecommerce.pom.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ManPage extends BasePage {

    By singleItemContainer = By.xpath("//ul[@class='products columns-4']//li");
    By saleTag = By.xpath("//span[@class='onsale']");
    By crossedOutPrice = By.xpath("//del");

    public ManPage(WebDriver driver) {
        super(driver);
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
}

