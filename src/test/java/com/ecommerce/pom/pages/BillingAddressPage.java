package com.ecommerce.pom.pages;

import com.ecommerce.pojo.UserData;
import com.ecommerce.pojo.UserDataPool;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BillingAddressPage extends AccountAddressesPage {
    By firstName = By.id("billing_first_name");
    By lastname = By.id("billing_last_name");
    By country = By.id("billing_country");
    By streetAddress = By.id("billing_address_1");
    By town = By.id("billing_city");
    By state = By.id("billing_state");
    By zipCode = By.id("billing_postcode");

    By saveAddressButton = By.xpath("//button[@type ='submit']");

   public BillingAddressPage (WebDriver driver){
       super(driver);
   }


 public AccountAddressesPage fillOutBillingAddressForm(){
       UserData userData = UserDataPool.getFakerUserDataList(1).get(0);

     WebElement firstNameField = WaitUtils.elementToBeClickable(getDriver(),firstName);
     firstNameField.clear();
     firstNameField.sendKeys(userData.getFirstName());
     WebElement lastNameField = WaitUtils.elementToBeClickable(getDriver(), lastname);
     lastNameField.clear();
     lastNameField.sendKeys(userData.getLastName());

     WebElement countryField = WaitUtils.elementToBeClickable(getDriver(), country);
     countryField.sendKeys("Yemen");

     WebElement streetAddressField = WaitUtils.elementToBeClickable(getDriver(), streetAddress);
     streetAddressField.clear();
     streetAddressField.sendKeys(userData.getAddress());

     WebElement townField = WaitUtils.elementToBeClickable(getDriver(), town);
     townField.clear();
     townField.sendKeys(userData.getTown());

     WebElement stateField = WaitUtils.elementToBeClickable(getDriver(), state);
     stateField.clear();
     stateField.sendKeys(userData.getState());

     WebElement zipCodeField = WaitUtils.elementToBeClickable(getDriver(), zipCode);
     zipCodeField.clear();
     zipCodeField.sendKeys(userData.getZipCode());
     WaitUtils.elementToBeClickable(getDriver(),saveAddressButton).click();
     return new AccountAddressesPage(getDriver());
 }
}
