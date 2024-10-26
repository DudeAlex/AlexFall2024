package com.ecommerce.tests.account;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.AccountPage;
import com.ecommerce.pom.pages.AccountDetailsPage;
import com.ecommerce.pom.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ChangePasswordTest extends BaseTest {

    @Test(description = "6.4.5-3 | TC >Account Page > User Dashboard Page> Account Details> Change Password # https://app.clickup.com/t/868abmm8w")
    public void testChangePassword() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = homePage.getHeader().navigateToAccountPage();
        accountPage.logIn("bbb@bb.com","123");
        AccountDetailsPage accountDetailsPage = new AccountDetailsPage(driver);
        accountDetailsPage.navigateToEditAccountPage().addNameAndLastName();
        accountDetailsPage.addCurrentPassword("123");
        accountDetailsPage.addNewPassword("456");
        accountDetailsPage.confirmNewPassword("456");
        String actualConfirmationMassage = accountDetailsPage.saveChanges().getTextActualConfirmationMsg();
        String expectedConfirmationMassage = "Account details changed successfully.";

        Assert.assertEquals(actualConfirmationMassage,expectedConfirmationMassage,"Password change was unsuccessful!");

        accountDetailsPage.returnToOldPassword();
        Assert.assertEquals(actualConfirmationMassage,expectedConfirmationMassage);



    }
}
