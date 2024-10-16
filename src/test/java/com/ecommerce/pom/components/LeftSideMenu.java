package com.ecommerce.pom.components;

import com.ecommerce.pom.pages.StorePage;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LeftSideMenu {

    By searchInputField = By.xpath("//input[@type='search']");
    By searchButton = By.xpath("//button[@value='Search']");

    private final WebDriver driver;

    public LeftSideMenu(WebDriver driver) {
        this.driver = driver;
    }
    public  WebDriver getDriver() {
        return driver;
    }

    public StorePage searchProduct(String item) {
        WaitUtils.visibilityOfElementLocated(getDriver(), searchInputField).sendKeys(item);
        WaitUtils.presenceOfElementLocated(getDriver(), searchButton).click();
        return new StorePage(getDriver());
    }
}
