package com.ecommerce.pom.pages;

import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.ecommerce.pom.EndPoints.ACCOUNT_DETAILS_URL;

public class AccountDetailsPage extends AccountPage {


    public AccountDetailsPage(WebDriver driver) {super(driver); }

    public AccountDetailsPage load(){
        getDriver().get(ACCOUNT_DETAILS_URL);
        return this;
    }

    By accountDetailLink = By.xpath("//a[normalize-space()='Account details']");
    By nameField = By.xpath("//input[@id='account_first_name']");
    By lastnameField = By.xpath("//input[@id='account_last_name']");
    By currentPasswordField = By.xpath("//input[@id='password_current']");
    By newPasswordField = By.xpath("//input[@id='password_1']");
    By confirmNewPasswordField = By.xpath("//input[@id='password_2']");
    By saveChangesBtn = By.xpath("//button[@name='save_account_details']");

    By changesConfirmationMassage = By.xpath("//div[@role='alert']");

    public AccountDetailsPage navigateToEditAccountPage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), accountDetailLink).click();
        return new AccountDetailsPage(getDriver());
    }

    public void addNameAndLastName() {
        WaitUtils.visibilityOfElementLocated(getDriver(),nameField).clear();
        WaitUtils.visibilityOfElementLocated(getDriver(), nameField).sendKeys("FirstName");
        WaitUtils.visibilityOfElementLocated(getDriver(),lastnameField).clear();
        WaitUtils.visibilityOfElementLocated(getDriver(), lastnameField).sendKeys("LastName");
    }

    public void addCurrentPassword(String currentPass) {
        WaitUtils.visibilityOfElementLocated(getDriver(), currentPasswordField).sendKeys(currentPass);
    }

    public void addNewPassword(String newPassword) {
        WaitUtils.visibilityOfElementLocated(getDriver(), newPasswordField).sendKeys(newPassword);
    }

    public void confirmNewPassword(String confirmNewPass) {
        WaitUtils.visibilityOfElementLocated(getDriver(), confirmNewPasswordField).sendKeys(confirmNewPass);
    }

    public AccountDetailsPage saveChanges() {
        WaitUtils.visibilityOfElementLocated(getDriver(), saveChangesBtn).click();
        return this;
    }

    public String getTextActualConfirmationMsg() {
        return WaitUtils.visibilityOfElementLocated(getDriver(), changesConfirmationMassage, 10).getText();
    }

    public AccountDetailsPage returnToOldPassword() {
        addCurrentPassword("456");
        addNewPassword("123");
        confirmNewPassword("123");
        saveChanges();

        return this;

    }
}
