package com.ecommerce.pom.pages;

import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends PurchasePage implements SearchResultPage {

    By mainNameProductTitle = By.xpath("//h1");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getProductNameText() {
        return WaitUtils.visibilityOf(getDriver(), mainNameProductTitle).getText();
    }

    public boolean containsSubstringInProductNames(String substring) {
        return getProductNameText().toLowerCase().contains(substring.toLowerCase());
    }

    @Override
    public int countItemsOnPage(String category) {
        return 1;
    }
}
