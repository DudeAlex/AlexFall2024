package com.ecommerce.pom.pages;

import com.ecommerce.pom.BasePage;
import com.ecommerce.pom.Loadable;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import static com.ecommerce.pom.EndPoints.CHECKOUT_URL;
import static org.openqa.selenium.Keys.ENTER;

public class CheckoutPage extends BasePage implements Loadable {
    By firstNameField = By.xpath("//input[@id='billing_first_name']");
    By lastNameField = By.xpath("//input[@id='billing_last_name']");
    By companyNameField = By.xpath("//input[@id='billing_company']");
    By streetAddressField = By.xpath("//input[@id='billing_address_1']");
    By townField = By.xpath("//input[@id='billing_city']");
    By zipField = By.xpath("//input[@id='billing_postcode']");
    By emailField = By.xpath("//input[@id='billing_email']");
    By placeOrderButton = By.xpath("//button[@id='place_order']");
    By billingCountryDropDown = By.xpath("//span[@id='select2-billing_country-container']");
    By inputBillingCountryDropDownField = By.xpath("//input[@class='select2-search__field']");
    By billingStateDropDownButton = By.xpath("//span[@id='select2-billing_state-container']");
    By inputBillingStateDropDownField = By.xpath("//input[@class='select2-search__field']");
    By productNameAndQuantity = By.xpath("//td[@class='product-name']");
    By checkYourOrderHasBeenReceivedMessage = By.xpath("//p[@class='woocommerce-notice woocommerce-notice--success woocommerce-thankyou-order-received']");

    public CheckoutPage(WebDriver driver) {

        super(driver);
    }

    @Override
    public CheckoutPage load() {
        getDriver().get(CHECKOUT_URL);

        return this;
    }

    public CheckoutPage inputFirstName(String firstName) {
        WaitUtils.visibilityOfElementLocated(getDriver(), firstNameField).clear();
        WaitUtils.visibilityOfElementLocated(getDriver(), firstNameField).sendKeys(firstName);
        return this;
    }
    public CheckoutPage inputLastName(String lastName) {
        WaitUtils.visibilityOfElementLocated(getDriver(), lastNameField).clear();
        WaitUtils.visibilityOfElementLocated(getDriver(), lastNameField).sendKeys(lastName);
        return this;
    }
    public CheckoutPage inputCompanyName(String companyName) {
        WaitUtils.visibilityOfElementLocated(getDriver(), companyNameField).clear();
        WaitUtils.visibilityOfElementLocated(getDriver(), companyNameField).sendKeys(companyName);
        return this;
    }
    public CheckoutPage inputStreetAddress(String streetAddress) {
        WaitUtils.visibilityOfElementLocated(getDriver(), streetAddressField).clear();
        WaitUtils.visibilityOfElementLocated(getDriver(), streetAddressField).sendKeys(streetAddress);
        return this;
    }
    public CheckoutPage inputTown(String town) {
        WaitUtils.visibilityOfElementLocated(getDriver(), townField).clear();
        WaitUtils.visibilityOfElementLocated(getDriver(), townField).sendKeys(town);
        return this;
    }
    public CheckoutPage inputZip(String zip) {
        WaitUtils.visibilityOfElementLocated(getDriver(), zipField).clear();
        WaitUtils.visibilityOfElementLocated(getDriver(), zipField).sendKeys(zip);
        return this;
    }
    public CheckoutPage inputEmail(String email) {
        WaitUtils.visibilityOfElementLocated(getDriver(), emailField).clear();
        WaitUtils.visibilityOfElementLocated(getDriver(), emailField).sendKeys(email);
        return this;
    }
    public OrderReceivedPage clickPlaceOrderButton() {
        WaitUtils.elementToBeClickable(getDriver(), placeOrderButton).click();
        return new OrderReceivedPage(getDriver());
    }


    public CheckoutPage inputCountry(String country){
        WaitUtils.elementToBeClickable(getDriver(),townField ).sendKeys(country);
        return this;
    }
  
    public CheckoutPage clickBillingCountryDropDown(String country){
        WaitUtils.visibilityOf(getDriver(), billingCountryDropDown).click();
        WaitUtils.visibilityOf(getDriver(), inputBillingCountryDropDownField).sendKeys(country);
        return this;
    }

    public CheckoutPage clickBillingStateDropDown(String country){
        WaitUtils.visibilityOf(getDriver(), billingStateDropDownButton).click();
        WaitUtils.visibilityOf(getDriver(), inputBillingStateDropDownField).sendKeys(country);
        WaitUtils.visibilityOf(getDriver(), inputBillingStateDropDownField).sendKeys(ENTER);
        return this;
    }

    public String productNameAndQuantity(){
        return WaitUtils.visibilityOfElementLocated(getDriver(), productNameAndQuantity).getText();
    }

    public String checkYourOrderHasBeenReceivedMessage(){
        return WaitUtils.visibilityOfElementLocated(getDriver(), checkYourOrderHasBeenReceivedMessage).getText();
    }

}
