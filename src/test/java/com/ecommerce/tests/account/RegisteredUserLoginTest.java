package com.ecommerce.tests.account;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pojo.User;
import com.ecommerce.pom.pages.AccountPage;
import com.ecommerce.utils.UserUtils;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegisteredUserLoginTest extends BaseTest {

    @Test
    public void testRegisteredUserLogin() throws IOException {
        String logoutButtonText = "Logout";
        String logInButtonText = "LOG IN";

        User user = UserUtils.readUserFromJson("user.json");
        AccountPage accountPage = new AccountPage(driver).load();
        accountPage.logIn(user.getLogin(), user.getPassword());

        String linkLogoutGetText = driver.findElement(accountPage.getLogoutLinkFromSideMenu()).getText();
        Assert.assertEquals(linkLogoutGetText, logoutButtonText);

        accountPage.logOutUser();

        String loginButtonGetText = driver.findElement(accountPage.getLoginButton()).getText();
        Assert.assertEquals(loginButtonGetText,logInButtonText);

    }

}
