package com.ecommerce.pom.pages;

import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddressesPage extends AccountPage{
    By addBillingAddress = By.xpath("//h3[text() ='Billing address']/../a");
    By addressChangedSuccessfullyMessage = By.cssSelector(".woocommerce-notices-wrapper .woocommerce-message");
     public AddressesPage (WebDriver driver){
         super(driver);
     }
     public BillingAddressPage clickAddBillingAddress(){
         WaitUtils.elementToBeClickable(getDriver(),addBillingAddress).click();
         return new BillingAddressPage(getDriver());
     }
     public String getAddressChangedMessage(){
         WebElement addressBox = WaitUtils.visibilityOfElementLocated(getDriver(), addressChangedSuccessfullyMessage);
         return addressBox.getText();
     }
}
