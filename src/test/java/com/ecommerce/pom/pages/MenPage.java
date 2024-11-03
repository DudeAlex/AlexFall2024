package com.ecommerce.pom.pages;

import com.ecommerce.pom.Loadable;
import com.ecommerce.utils.CollectToListUtils;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static com.ecommerce.pom.EndPoints.MEN_URL;

public class MenPage extends SalesPage<MenPage> {
    By cartButton = By.linkText("View cart");
    By cartIcon = By.xpath("//div[@id=\"ast-desktop-header\"]//a[@title=\"View your shopping cart\"]//span");
    By pageHeader = By.tagName("h1");
    By menQtyInFilter = By.xpath("//select[@id='product_cat']/option[@selected='selected']");
    By menQtyOnPage = By.cssSelector(".ast-woocommerce-container ul li");
    By priceFilter = By.cssSelector("#woocommerce_price_filter-3 .price_label");
    By menItems = By.cssSelector(".ast-woo-product-category");

    public MenPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MenPage load() {
        getDriver().get(MEN_URL);

        return this;
    }

    public List<String> collectCategories() {
        return CollectToListUtils.productsCategories(getDriver());
    }

    public CartPage clickCartPage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), cartButton).click();
        return new CartPage(getDriver());
    }

    public String verifyHeaderText(){
        return WaitUtils.visibilityOfElementLocated(getDriver(), pageHeader, 2).getText();
    }

    public boolean getMenQtyOnPage(){
        List<WebElement> items = getDriver().findElements(menItems);
        for( WebElement item : items){
            if(item.getText().equals("Men")) return true;
        }
        return false;
    }

    public int getMenQtyInFilter(){
        String filterText = getDriver().findElement(menQtyInFilter).getText();
        String number = filterText.replaceAll("\\D+", "");
        return Integer.parseInt(number);
    }

    public String getPriceRangeText(){
        String text = getDriver().findElement(priceFilter).getText();
        return text;
    }

    public boolean verifyMenItemsCount(int expectedCount) {
        List<WebElement> items = getDriver().findElements(menItems);
        int displayedItemCount = items.size();
        return displayedItemCount == expectedCount;
    }
}

