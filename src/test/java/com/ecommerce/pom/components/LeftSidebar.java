package com.ecommerce.pom.components;

import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LeftSidebar extends BaseComponent {

    By searchInputField = By.xpath("//input[@type='search']");
    By searchButton = By.xpath("//button[@value='Search']");

    public LeftSidebar(WebDriver driver) {
        super(driver);
    }

    public <T> T searchProduct(String item, T page) {
        WaitUtils.visibilityOfElementLocated(getDriver(), searchInputField).sendKeys(item);
        WaitUtils.presenceOfElementLocated(getDriver(), searchButton).click();

        return page;
    }
}
