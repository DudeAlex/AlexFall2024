package com.ecommerce.tests.account;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.AccountPage;
import com.ecommerce.pom.pages.EditAccountPage;
import com.ecommerce.pom.pages.HomePage;
import org.testng.annotations.Test;

public class ChangePasswordTest extends BaseTest {

    @Test(description = "")
    public void testChangePassword() {
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = homePage.getHeader().navigateToAccountPage();
        accountPage.logInWithUserName("registeredUser", "123");
        EditAccountPage editAccountPage = new EditAccountPage(driver);
        editAccountPage.
    }
}
