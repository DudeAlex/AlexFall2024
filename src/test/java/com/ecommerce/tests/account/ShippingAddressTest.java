package com.ecommerce.tests.account;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.AccountAddressesPage;
import com.ecommerce.pom.pages.AccountPage;
import com.ecommerce.pom.pages.ShippingAddressPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ShippingAddressTest extends BaseTest {

    @Test(description = "6.4.4.2-1 | TC > Change shipping address # https://app.clickup.com/t/868ake8je")
    public void testAddShippingAddress() {
        AccountPage accountPage = new AccountPage(driver);
        accountPage.load();
        accountPage.logIn("aaa", "111");
        AccountAddressesPage accountAddressesPage = accountPage.clickAddressesLink();
        ShippingAddressPage shippingAddressPage = accountAddressesPage.clickAddShippingAddress();
        accountAddressesPage = shippingAddressPage.fillOutShippingAddressForm();
        String addressBoxMessage = accountAddressesPage.getAddressChangedMessage();

        Assert.assertEquals(addressBoxMessage,
                "Address changed successfully.", "User does not set up address yet");

    }
}
