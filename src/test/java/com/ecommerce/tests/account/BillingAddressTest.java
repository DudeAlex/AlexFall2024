package com.ecommerce.tests.account;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.AccountPage;
import com.ecommerce.pom.pages.AddressesPage;
import com.ecommerce.pom.pages.BillingAddressPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BillingAddressTest extends BaseTest {
    @Test (description = "6.4.4-1|TC>Account Page>Add Billing Address # https://app.clickup.com/t/868adye7r")
    public void testEditBillingAddress() {
        AccountPage accountPage = new AccountPage(driver);
        accountPage.load();
        accountPage.logIn("aaa", "111");
        AddressesPage addressesPage = accountPage.clickAddressesLink();
        BillingAddressPage billingAddressPage = addressesPage.clickAddBillingAddress();
        addressesPage = billingAddressPage.fillOutBillingAddressForm();
        String addressBoxMessage = addressesPage.getAddressChangedMessage();

        Assert.assertEquals(addressBoxMessage,
                "Address changed successfully.", "User does not set up address yet");
    }
}
