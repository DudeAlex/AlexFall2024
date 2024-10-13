package com.ecommerce.pom.pages;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.BasePage;
import com.ecommerce.pom.Loadable;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LostPasswordPage extends BasePage implements Loadable {


    public LostPasswordPage(WebDriver driver) {super (driver);
    }

    @Override
    public void load() {getDriver().get("https://askomdch.com/account/lost-password/");
    }

    By emailInputToResetPassword = By.xpath("//input[@id='user_login']");
    By resetPasswordBtn = By.xpath("//button[@value='Reset password']");

    By passwordResetNotification = By.xpath("//div[@role='alert']");
    public LostPasswordPage inputValidEmail () {
        WaitUtils.visibilityOfElementLocated(getDriver(),emailInputToResetPassword).clear();
        WaitUtils.visibilityOfElementLocated(getDriver(),emailInputToResetPassword).sendKeys("aaaaa@aa.aa");
        return this;
    }
    public LostPasswordPage clickResetPasswordBtn () {
        WaitUtils.visibilityOfElementLocated(getDriver(),resetPasswordBtn).click();
        return new LostPasswordPage(getDriver());
    }

    public String getTextResetPasswordNotification () {
        return WaitUtils.visibilityOfElementLocated(getDriver(),passwordResetNotification).getText();
    }
    }



