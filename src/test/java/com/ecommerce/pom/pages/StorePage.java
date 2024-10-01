package com.ecommerce.pom.pages;

import com.ecommerce.pom.BasePage;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StorePage extends SalesPage {

    By headerTitle = By.xpath("//h1[@class='woocommerce-products-header__title page-title']");
    By loopProducts = By.xpath("//h2[@class='woocommerce-loop-product__title']");

    public StorePage(WebDriver driver) {
        super(driver);
    }

    public String getSearchHeaderTitle() {
        return WaitUtils.visibilityOf(getDriver(), headerTitle).getText();
    }




}

