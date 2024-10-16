package com.ecommerce.pom.pages;

import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountOrdersPage extends AccountPage{
    public AccountOrdersPage(WebDriver driver) {
        super(driver);
    }

    By ordersPageButton = By.xpath("//a[@href='https://askomdch.com/account/orders/']");
    By browseProductsButton = By.xpath("//a[contains(text(),'Browse products')]");
    By noOrdersMadeText = By.xpath("//*[@id=\"post-1235\"]/div/div[2]/div/div[2]/div/div[2]");

    public AccountOrdersPage navigateToAccountOrdersPage(){
         WaitUtils.elementToBeClickable(getDriver(), ordersPageButton).click();
         return this;
    }

    public  String checkBrowseProductsButton(){
       return WaitUtils.visibilityOfElementLocated(getDriver(), browseProductsButton).getText();
    }

    public String noOrdersHaveBeenMadeText(){
        return WaitUtils.visibilityOfElementLocated(getDriver(), noOrdersMadeText).getText();
    }

}
