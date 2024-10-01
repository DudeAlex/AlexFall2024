package com.ecommerce.pom.pages;

import com.ecommerce.pom.BasePage;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    By shopButton = By.xpath("//a[@class='wp-block-button__link']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public StorePage navigateToStorePage(){
        WaitUtils.visibilityOfElementLocated(getDriver(), shopButton).click();
        return new StorePage(getDriver());
    }


}
