package com.ecommerce.tests.account;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.AccountPage;
import com.ecommerce.utils.ConfigUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginFormTest extends BaseTest {

    @Test(description = "6.5-1-1| TC Empty password field. # https://app.clickup.com/t/868anr41q")
    public void testEmptyPasswordField() {

    AccountPage accountPage = new AccountPage(driver).load()
            .logInUsingConfigUtilsWithUserLoginOnly("userLogin");

    String actualErrorMsg = accountPage.getErrorMessage();
    String emptyPasswordErrorMsg = "Error: The password field is empty.";

    Assert.assertEquals(actualErrorMsg, emptyPasswordErrorMsg);
    Assert.assertTrue(accountPage.isWelcomeTextInvisible());
}

    @Test(description = "6.5-2-1| TC Empty username field. # https://app.clickup.com/t/868anr4fk")
    public void testEmptyUsernameField() {

        AccountPage accountPage = new AccountPage(driver).load()
                .logInUsingConfigUtilsWithPasswordOnly("userPassword");

        String actualErrorMsg = accountPage.getErrorMessage();
        String emptyUsernameErrorMsg = "Error: Username is required.";

        Assert.assertEquals(actualErrorMsg, emptyUsernameErrorMsg);
        Assert.assertTrue(accountPage.isWelcomeTextInvisible());
    }

    @Test(description = "6.5-3-1| TC Invalid username. # https://app.clickup.com/t/868anr54w")
    public void testInvalidUsername() {

        AccountPage accountPage = new AccountPage(driver).load()
                .logInUsingConfigUtilsWithParameters("invalidUsername", "userPassword");

        String actualErrorMsg = accountPage.getErrorMessage();
        String invalidUsernameErrorMsgTemplate = "Error: The username %s is not registered on this site. " +
                "If you are unsure of your username, try your email address instead.";
        String invalidUsernameErrorMsg = String.format(invalidUsernameErrorMsgTemplate, ConfigUtil.getProperty("invalidUsername"));

        Assert.assertEquals(actualErrorMsg, invalidUsernameErrorMsg);
        Assert.assertTrue(accountPage.isWelcomeTextInvisible());
    }

    @Test(description = "6.5-4-1| TC Invalid email. # https://app.clickup.com/t/868anr5dr")
    public void testInvalidEmail() {

        AccountPage accountPage = new AccountPage(driver).load()
                .logInUsingConfigUtilsWithParameters("userInvalidLogin", "userPassword");

        String actualErrorMsg = accountPage.getErrorMessage();
        String invalidEmailErrorMsg = "Unknown email address. Check again or try your username.";

        Assert.assertEquals(actualErrorMsg, invalidEmailErrorMsg);
        Assert.assertTrue(accountPage.isWelcomeTextInvisible());
    }
}
