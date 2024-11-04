package com.ecommerce.pom.pages;

import com.ecommerce.pom.Loadable;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static com.ecommerce.pom.EndPoints.BASE_URL;

public class HomePage extends PurchasePage<HomePage> implements Loadable {

    By shopNowButton = By.xpath("//a[@class= 'wp-block-button__link']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public HomePage load() {
        getDriver().get(BASE_URL);

        return this;
    }

    public StorePage clickShopNowButton() {
        WaitUtils.elementToBeClickable(getDriver(), shopNowButton).click();
        return new StorePage(getDriver());
    }

    public HomePage scrollToElement(WebElement webElement) {
        new Actions(getDriver())
                .scrollToElement(webElement)
                .perform();

        return this;
    }

}
