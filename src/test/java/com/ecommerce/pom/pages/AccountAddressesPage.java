package com.ecommerce.pom.pages;

import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.ecommerce.pom.EndPoints.ACCOUNT_ADDRESSES_URL;

public class AccountAddressesPage extends AccountPage{
    By addBillingAddress = By.xpath("//h3[text() ='Billing address']/../a");
    By addressChangedSuccessfullyMessage = By.cssSelector(".woocommerce-notices-wrapper .woocommerce-message");
    By addShippingAddress = By.xpath("//a[@href='https://askomdch.com/account/edit-address/shipping/']");
     public AccountAddressesPage(WebDriver driver){
         super(driver);
     }

    public AccountAddressesPage load(){
        getDriver().get(ACCOUNT_ADDRESSES_URL);
        return this;
    }

     public BillingAddressPage clickAddBillingAddress(){
         WaitUtils.elementToBeClickable(getDriver(),addBillingAddress).click();
         return new BillingAddressPage(getDriver());
     }
     public String getAddressChangedMessage(){
         WebElement addressBox = WaitUtils.visibilityOfElementLocated(getDriver(), addressChangedSuccessfullyMessage);
         return addressBox.getText();
     }

     public ShippingAddressPage clickAddShippingAddress() {
         WaitUtils.elementToBeClickable(getDriver(),addShippingAddress).click();
         return new ShippingAddressPage(getDriver());
     }
}
