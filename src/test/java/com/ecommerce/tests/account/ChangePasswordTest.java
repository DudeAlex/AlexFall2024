package com.ecommerce.tests.account;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.AccountPage;
import com.ecommerce.pom.pages.EditAccountPage;
import com.ecommerce.pom.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ChangePasswordTest extends BaseTest {

    @Test(description = "6.4.5-3 | TC >Account Page > User Dashboard Page> Account Details> Change Password # https://app.clickup.com/t/868abmm8w")
    public void testChangePassword() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = homePage.getHeader().navigateToAccountPage();
        accountPage.logIn("bbb@bb.com","123");
        EditAccountPage editAccountPage = new EditAccountPage(driver);
        editAccountPage.navigateToEditAccountPage().addNameAndLastName();
        editAccountPage.addCurrentPassword("123");
        editAccountPage.addNewPassword("456");
        editAccountPage.confirmNewPassword("456");
        String actualConfirmationMassage = editAccountPage.saveChanges().getTextActualConfirmationMsg();
        String expectedConfirmationMassage = "Account details changed successfully.";

        Assert.assertEquals(actualConfirmationMassage,expectedConfirmationMassage,"Password change was unsuccessful!");

        editAccountPage.returnToOldPassword();
        Assert.assertEquals(actualConfirmationMassage,expectedConfirmationMassage);



    }
}
