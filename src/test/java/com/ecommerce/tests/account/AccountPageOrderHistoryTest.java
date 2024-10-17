package com.ecommerce.tests.account;

import com.beust.ah.A;
import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.AccountOrdersPage;
import com.ecommerce.pom.pages.AccountPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountPageOrderHistoryTest extends BaseTest{

        @Test(description = "6.4.1 - 1.1 | TC LogIn with new account to check empty order history ")
        public void loggedInUserCheckingOrderHistoryOnAccountPage() {

            String noOrders = "BROWSE PRODUCTS\n" +
                    "No order has been made yet.";
            String browseProducts = "BROWSE PRODUCTS";

            AccountPage accountPage = new AccountPage(driver).load();
            accountPage.logIn("practice@gmail.com", "Testing1");

            AccountOrdersPage accountOrdersPage = new AccountOrdersPage(driver);
            accountOrdersPage.navigateToAccountOrdersPage();

            String checkBrowseButton = accountOrdersPage.checkBrowseProductsButton();
            Assert.assertEquals(checkBrowseButton, browseProducts);

            String noOrdersText = accountOrdersPage.noOrdersHaveBeenMadeText();
            Assert.assertEquals(noOrdersText, noOrders);
        }

}
