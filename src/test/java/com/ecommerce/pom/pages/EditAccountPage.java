package com.ecommerce.pom.pages;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.Loadable;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static com.ecommerce.pom.EndPoints.CART_URL;
import static com.ecommerce.pom.EndPoints.EDIT_ACCOUNT_URL;
import static java.sql.DriverManager.getDriver;

public class EditAccountPage extends AccountPage {


    public EditAccountPage(WebDriver driver) {
        super(driver);

    }

    By accountDetailLink = By.xpath("//a[normalize-space()='Account details']");
    By nameField = By.xpath("//input[@id='account_first_name']");
    By lastnameField = By.xpath("//input[@id='account_last_name']");
    By currentPasswordField = By.xpath("//input[@id='password_current']");
    By newPasswordField = By.xpath("//input[@id='password_1']");
    By confirmNewPasswordField = By.xpath("//input[@id='password_2']");
    By saveChangesBtn = By.xpath("//button[@name='save_account_details']");

    By changesConfirmationMassage = By.xpath("//div[@role='alert']");

    public EditAccountPage navigateToEditAccountPage() {
        WaitUtils.visibilityOfElementLocated(getDriver(), accountDetailLink).click();
        return new EditAccountPage(getDriver());
    }

    public void eddNameAndLastName() {
        WaitUtils.visibilityOfElementLocated(getDriver(),nameField).clear();
        WaitUtils.visibilityOfElementLocated(getDriver(), nameField).sendKeys("FirstName");
        WaitUtils.visibilityOfElementLocated(getDriver(),lastnameField).clear();
        WaitUtils.visibilityOfElementLocated(getDriver(), lastnameField).sendKeys("LastName");
    }

    public void eddCurrentPassword(String currentPass) {
        WaitUtils.visibilityOfElementLocated(getDriver(), currentPasswordField).sendKeys(currentPass);
    }

    public void eddNewPassword(String newPassword) {
        WaitUtils.visibilityOfElementLocated(getDriver(), newPasswordField).sendKeys(newPassword);
    }

    public void confirmNewPassword(String confirmNewPass) {
        WaitUtils.visibilityOfElementLocated(getDriver(), confirmNewPasswordField).sendKeys(confirmNewPass);
    }

    public EditAccountPage saveChanges() {
        WaitUtils.visibilityOfElementLocated(getDriver(), saveChangesBtn).click();
        return this;
    }

    public String getTextActualConfirmationMsg() {
        return WaitUtils.visibilityOfElementLocated(getDriver(), changesConfirmationMassage, 10).getText();
    }

    public EditAccountPage returnToOldPassword() {
        eddCurrentPassword("456");
        eddNewPassword("123");
        confirmNewPassword("123");
        saveChanges();

        return this;

    }
}
