package com.ecommerce.tests.account;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.AccountPage;
import com.ecommerce.pom.pages.HomePage;
import com.ecommerce.pom.pages.LostPasswordPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LostPasswordTest extends BaseTest {

    @Test (description = "6.3-1.1 | TC >Account Page > Lost Password >Resset Password # https://app.clickup.com/t/868a7v2a5")
    public void testPasswordReset() {
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = homePage.navigateToAccountPage();
        LostPasswordPage lostPasswordPage = accountPage.navigateToLostPasswordPage();
        String actualResetPasswordNotification = lostPasswordPage.inputValidEmail().clickResetPasswordBtn().getTextResetPasswordNotification();
        String expectedResetPasswordNotification = "Password reset email has been sent.";

        Assert.assertEquals(actualResetPasswordNotification,expectedResetPasswordNotification,"Password reset notification text does not match!");


    }
    @Test (description = "6.3-2.1 | TC >Account Page > Lost Password >Invalid Email # https://app.clickup.com/t/868a7vgfq")
    public void testPasswordResetWrongEmail() {
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = homePage.navigateToAccountPage();
        LostPasswordPage lostPasswordPage = accountPage.navigateToLostPasswordPage();
        String actualErrorMassageInvalidEmail = lostPasswordPage.inputInvalidEmail().clickResetPasswordBtn().getTextErrorMassageInvalidEmail();
        String expectedErrorMassageInvalidEmail = "Invalid username or email.";

        Assert.assertEquals(actualErrorMassageInvalidEmail,expectedErrorMassageInvalidEmail,"Error massage doesn't match!");

    }
}
