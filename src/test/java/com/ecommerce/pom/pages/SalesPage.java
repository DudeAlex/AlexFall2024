package com.ecommerce.pom.pages;

import com.ecommerce.pom.BasePage;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class SalesPage extends BasePage {

    By searchField = By.id("woocommerce-product-search-field-0");
    By searchButton = By.xpath("//button[@value='Search']");

    public SalesPage(WebDriver driver) {
        super(driver);
    }

    public StorePage searchProduct(String item){
        WaitUtils.visibilityOfElementLocated(getDriver(),searchField).sendKeys(item);
        WaitUtils.presenceOfElementLocated(getDriver(), searchButton).click();
        return new StorePage(getDriver());
    }
}
