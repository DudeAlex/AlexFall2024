package com.ecommerce.pom.components;

import com.ecommerce.pom.pages.StorePage;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LeftSidebar extends BaseComponent {

    By searchInputField = By.xpath("//input[@type='search']");
    By searchButton = By.xpath("//button[@value='Search']");
    By bestSellerTitle = By.cssSelector(".widget.woocommerce.widget_top_rated_products >.widget-title");
    By bestSellersItems = By.cssSelector(".product_list_widget>li");


    public LeftSidebar(WebDriver driver) {
        super(driver);
    }

    public StorePage searchProduct(String item) {
        WaitUtils.visibilityOfElementLocated(getDriver(), searchInputField).sendKeys(item);
        WaitUtils.presenceOfElementLocated(getDriver(), searchButton).click();

        return new StorePage(getDriver());
    }
    public String getBestSellerTitle() {
        return  WaitUtils.visibilityOfElementLocated(getDriver(), bestSellerTitle).getText();
    }
    public List<WebElement> getBestSellersList(){
       return  WaitUtils.visibilityOfAllElementsLocatedBy(getDriver(),bestSellersItems);
    }
}
