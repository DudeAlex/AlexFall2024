package com.ecommerce.pom.pages;

import com.ecommerce.pom.Loadable;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.ecommerce.pom.EndPoints.WOMEN_URL;

public class WomenPage extends SalesPage<WomenPage> {

    By itemsResultNumber = By.cssSelector(".woocommerce-result-count");

        public WomenPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public WomenPage load() {
        getDriver().get(WOMEN_URL);

        return this;
    }

    public int getPageResultCount() {
        String result = WaitUtils.presenceOfElementLocated(getDriver(), itemsResultNumber).getText();
        result = result.replaceAll("[A-Za-z\\s]", "");
        return Integer.parseInt(result);
    }
}
