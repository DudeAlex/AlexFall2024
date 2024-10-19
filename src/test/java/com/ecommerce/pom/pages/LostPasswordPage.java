package com.ecommerce.pom.pages;

import com.ecommerce.pom.BasePage;
import com.ecommerce.pom.Loadable;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.ecommerce.pom.EndPoints.LOST_PASSWORD_URL;


public class LostPasswordPage extends BasePage implements Loadable {

    By emailInputToResetPassword = By.xpath("//input[@id='user_login']");
    By resetPasswordBtn = By.xpath("//button[@value='Reset password']");
    By passwordResetNotification = By.xpath("//div[@role='alert']");
    By errorMessageInvalidEmail = By.xpath("//li[normalize-space()='Invalid username or email.']");
    public LostPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public LostPasswordPage load() {
        getDriver().get(LOST_PASSWORD_URL);

        return this;
    }


    public LostPasswordPage inputValidEmail() {
        WaitUtils.visibilityOfElementLocated(getDriver(), emailInputToResetPassword).clear();
        WaitUtils.visibilityOfElementLocated(getDriver(), emailInputToResetPassword).sendKeys("aaaaa@aa.aa");
        return this;
    }

    public LostPasswordPage inputInvalidEmail() {
        WaitUtils.visibilityOfElementLocated(getDriver(), emailInputToResetPassword).clear();
        WaitUtils.visibilityOfElementLocated(getDriver(), emailInputToResetPassword).sendKeys("bbbbb@bb.bb");
        return this;
    }

    public LostPasswordPage clickResetPasswordBtn() {
        WaitUtils.visibilityOfElementLocated(getDriver(), resetPasswordBtn).click();
        return new LostPasswordPage(getDriver());
    }

    public String getTextResetPasswordNotification() {
        return WaitUtils.visibilityOfElementLocated(getDriver(), passwordResetNotification).getText();
    }

    public String getTextErrorMassageInvalidEmail() {
        return WaitUtils.visibilityOfElementLocated(getDriver(), errorMessageInvalidEmail).getText();
    }
}


