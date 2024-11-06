package com.ecommerce.pom.pages;

import com.ecommerce.pom.Loadable;
import com.ecommerce.utils.JSExecutorUtils;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.ecommerce.pom.EndPoints.WOMEN_URL;

public class WomenPage extends SalesPage<WomenPage> {

    By itemsResultNumber = By.cssSelector(".woocommerce-result-count");
    By denimBlueJeansImage = By.xpath("//a[contains(@href, 'blue-jeans/') and contains(@class, 'LoopProduct-link')]/img");

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
    public String getImageAttribute(String attribute) {
        WebElement image = WaitUtils.visibilityOfElementLocated(getDriver(), denimBlueJeansImage);
        String attributeOfImage = image.getAttribute(attribute);
        return attributeOfImage;
    }

    public void scrollToOneThirdOfWomenPage() {
        JSExecutorUtils.scrollToOneThirdOfPage(getDriver());
    }
}
