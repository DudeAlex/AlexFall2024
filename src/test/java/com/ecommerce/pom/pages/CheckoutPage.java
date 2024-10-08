package com.ecommerce.pom.pages;

import com.ecommerce.pojo.UserData;
import com.ecommerce.pojo.UserDataPool;
import com.ecommerce.pom.BasePage;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CheckoutPage extends BasePage {
    By firstNameField = By.xpath("//input[@id='billing_first_name']");
    By lastNameField = By.xpath("//input[@id='billing_last_name']");
    By companyNameField = By.xpath("//input[@id='billing_company']");
    By streetAddressField = By.xpath("//input[@id='billing_address_1']");
    By townField = By.xpath("//input[@id='billing_city']");
    By zipField = By.xpath("//input[@id='billing_postcode']");
    By emailField = By.xpath("//input[@id='billing_email']");
    By placeOrderButton = By.xpath("//button[@id='place_order']");

    By countryRegion = By.xpath("//span[@id='select2-billing_country-container']");
    By billingCountryDropDown = By.xpath("//span[@id='select2-billing_country-container']");
    By unitedStatesOption = By.xpath("//li[contains(text(),'United States (US)')]");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }
    public CheckoutPage inputFirstName(String firstName) {
        WaitUtils.visibilityOfElementLocated(getDriver(), firstNameField).sendKeys(firstName);
        return this;
    }
    public CheckoutPage inputLastName(String lastName) {
        WaitUtils.visibilityOfElementLocated(getDriver(), lastNameField).sendKeys(lastName);
        return this;
    }
    public CheckoutPage inputCompanyName(String companyName) {
        WaitUtils.visibilityOfElementLocated(getDriver(), companyNameField).sendKeys(companyName);
        return this;
    }
    public CheckoutPage inputStreetAddress(String streetAddress) {
        WaitUtils.visibilityOfElementLocated(getDriver(), streetAddressField).sendKeys(streetAddress);
        return this;
    }
    public CheckoutPage inputTown(String town) {
        WaitUtils.visibilityOfElementLocated(getDriver(), townField).sendKeys(town);
        return this;
    }
    public CheckoutPage inputZip(String zip) {
        WaitUtils.visibilityOfElementLocated(getDriver(), zipField).sendKeys(zip);
        return this;
    }
    public CheckoutPage inputEmail(String email) {
        WaitUtils.visibilityOfElementLocated(getDriver(), emailField).sendKeys(email);
        return this;
    }
    public OrderReceivedPage clickPlaceOrderButton() {
        WaitUtils.visibilityOfElementLocated(getDriver(), placeOrderButton).click();
        return new OrderReceivedPage(getDriver());
    }


    public CheckoutPage inputCountry(String country){
        WaitUtils.elementToBeClickable(getDriver(),townField ).sendKeys(country);
        return this;
    }

//    public List<UserData> inputCountryOrRegion(){
//       return WaitUtils.visibilityOfElementLocated(getDriver(), countryRegion).getFakeUserDataList(1);
//    }

 // chatGPT option:

    public CheckoutPage clickBillingCountryDropDown(){
        WaitUtils.visibilityOf(getDriver(), billingCountryDropDown).click();
        return this;
    }

    public CheckoutPage selectUnitedStates(){
        WaitUtils.elementToBeClickable(getDriver(), unitedStatesOption);
        return this;
    }

}
