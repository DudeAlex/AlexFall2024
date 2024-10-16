package com.ecommerce.tests.account;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.AccountPage;
import com.ecommerce.pom.pages.HomePage;
import com.ecommerce.utils.UserUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.ecommerce.data.RegisterUsernameData;

public class RegisterTest extends BaseTest {

    @Test(description = "6.2-1-7 | TC > Account Page > Validate email error # https://app.clickup.com/t/868a34t20")
    public void testValidateBrowserEmailErrorMessage() {
        AccountPage accountPage = new HomePage(driver)
                .navigateToAccountPage()
                .typeUserNameAndPressTabKey(UserUtils.generateUniqueUsername())
                .typeEmailBeingOnEmailInputAndPressTabKey(UserUtils.generateUniqueEmail().replace("@", ""))
                .typePasswordBeingOnPasswordInputAndPressEnterKey(UserUtils.generateUniquePassword());

        Assert.assertFalse(accountPage.getValidationMessage().isEmpty());
        Assert.assertTrue(accountPage.isWelcomeTextInvisible());

    }

    @Test(description = "6.2-1-4 | TC > Account Page > Verify error message for empty password # https://app.clickup.com/t/868a34t9v")
    public void testVerifyErrorMassageForEmptyPassword() {
        String errorMessageText = new HomePage(driver)
                .navigateToAccountPage()
                .typeUsernameToUsernameInputField(UserUtils.generateUniqueUsername())
                .typeEmailToEmailInputField(UserUtils.generateUniqueEmail())
                .clickRegisterButton()
                .getErrorMessage();

        Assert.assertEquals(errorMessageText, "Error: Please enter an account password.");
    }

    @Test(description = "6.2-1-1 | TC > Account Page > Verify registry # https://app.clickup.com/t/868a34rph")
    public void testVerifyRegistry() {
        final String newUser = UserUtils.generateUniqueUsername();

        String welcomeUserText = new HomePage(driver)
                .navigateToAccountPage()
                .typeUserNameAndPressTabKey(newUser)
                .typeEmailBeingOnEmailInputAndPressTabKey(UserUtils.generateUniqueEmail())
                .typePasswordBeingOnPasswordInputAndPressEnterKey(UserUtils.generateUniquePassword())
                .getWelcomeNewUserText();

        Assert.assertTrue(welcomeUserText.contains("Hello " + newUser));
    }

    @Test(dataProvider = "provideInvalidCharacters", dataProviderClass = RegisterUsernameData.class,
            description = "6.2-1-5 | TC > Verify error for invalid characters #https://app.clickup.com/t/868a80kv3")
    public void testVerifyErrorMessageForInvalidCharacters(String character) {
        String errorMessageText = new HomePage(driver)
                .navigateToAccountPage()
                .typeUserNameAndPressTabKey(UserUtils.generateUniqueUsername() + character)
                .typeEmailBeingOnEmailInputAndPressTabKey(UserUtils.generateUniqueEmail())
                .typePasswordBeingOnPasswordInputAndPressEnterKey(UserUtils.generateUniquePassword())
                .getErrorMessage();

        Assert.assertEquals(errorMessageText, "Error: Please enter a valid account username.");
    }

    @Test(description = "6.2-1-6 | TC > Verify error for username longer than 60 characters # https://app.clickup.com/t/868a80mw5")
    public void testVerifyErrorMessageForUsernameMoreSixtyCharacters() {
        String errorMessageText = new HomePage(driver)
                .navigateToAccountPage()
                .typeUsernameToUsernameInputField("qwertyuiopasdfghjklzxcvbnmqwertyuioasdfghjklzxcvbnmqwertyuioq")
                .typeEmailToEmailInputField(UserUtils.generateUniqueEmail())
                .typePasswordToPasswordInputField(UserUtils.generateUniquePassword())
                .clickRegisterButton()
                .getErrorMessage();

        Assert.assertEquals(errorMessageText, "Error: Username may not be longer than 60 characters.");
    }

    @Test(description = "6.2-1-3 | TC > Verify Error for empty email # https://app.clickup.com/t/868a80p4a")
    public void testVerifyErrorMessageForEmptyEmail() {
        String errorMessageText = new HomePage(driver)
                .navigateToAccountPage()
                .typeUsernameToUsernameInputField(UserUtils.generateUniqueUsername())
                .typePasswordToPasswordInputField(UserUtils.generateUniquePassword())
                .clickRegisterButton()
                .getErrorMessage();

        Assert.assertEquals(errorMessageText, "Error: Please provide a valid email address.");
    }

    @Test(description = "6.2-1-2 | TC > Verify Error for empty Username  #https://app.clickup.com/t/868a80u7t")
    public void testVerifyErrorMessageForEmptyUserName() {
        String errorMessageText = new HomePage(driver)
                .navigateToAccountPage()
                .typeEmailToEmailInputField(UserUtils.generateUniqueEmail())
                .typePasswordToPasswordInputField(UserUtils.generateUniquePassword())
                .clickRegisterButton()
                .getErrorMessage();

        Assert.assertEquals(errorMessageText, "Error: Please enter a valid account username.");
    }
}