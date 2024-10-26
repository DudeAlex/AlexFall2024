package com.ecommerce.pom.pages;

import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.ecommerce.pom.EndPoints.ACCOUNT_ORDERS_URL;

public class AccountOrdersPage extends AccountPage{
    public AccountOrdersPage(WebDriver driver) {
        super(driver);
    }

    public AccountOrdersPage load(){
        getDriver().get(ACCOUNT_ORDERS_URL);
        return this;
    }


    By ordersPageButton = By.xpath("//a[@href='https://askomdch.com/account/orders/']");
    By browseProductsButton = By.xpath("//a[contains(text(),'Browse products')]");
    By noOrdersMadeText = By.xpath("//*[@id=\"post-1235\"]/div/div[2]/div/div[2]/div/div[2]");
    By ordersTable = By.xpath("//table[@class='woocommerce-orders-table woocommerce-MyAccount-orders shop_table shop_table_responsive my_account_orders account-orders-table']");

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

    public String ordersTabInfo(){
        return WaitUtils.visibilityOfElementLocated(getDriver(), ordersTable).getText();
    }
}
