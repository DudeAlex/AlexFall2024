package com.ecommerce.tests.account;

import com.beust.ah.A;
import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.AccountOrdersPage;
import com.ecommerce.pom.pages.AccountPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountPageOrderHistoryTest extends BaseTest{

        @Test(description = "6.4.1 - 1.1 | TC LogIn with new account to check empty order history ")
        public void loggedInUserCheckingEmptyOrderHistoryOnAccountPage() {

            String noOrders = "BROWSE PRODUCTS\n" +
                    "No order has been made yet.";
            String browseProducts = "BROWSE PRODUCTS";

            AccountPage accountPage = new AccountPage(driver);
            accountPage.load();
            accountPage.logIn("anyemail@gmail.com", "Testing1");

            AccountOrdersPage accountOrdersPage = new AccountOrdersPage(driver);
            accountOrdersPage.navigateToAccountOrdersPage();

            String checkBrowseButton = accountOrdersPage.checkBrowseProductsButton();
            Assert.assertEquals(checkBrowseButton, browseProducts);

            String noOrdersText = accountOrdersPage.noOrdersHaveBeenMadeText();
            Assert.assertEquals(noOrdersText, noOrders);
        }

    @Test(description = "6.4.1 - 1.2 | TC LogIn with older account to check order history data")
    public void loggedInUserCheckingExistingOrderHistoryOnAccountPage() throws InterruptedException {

            AccountPage accountPage = new AccountPage(driver);
            accountPage.load();
            accountPage.logIn("practice@gmail.com", "Testing1");

            AccountOrdersPage accountOrdersPage = new AccountOrdersPage(driver);
            accountOrdersPage.navigateToAccountOrdersPage().ordersTabInfo();

            String viewOrdersButton = accountOrdersPage.navigateToAccountOrdersPage().ordersTabInfo();

            Assert.assertEquals(accountOrdersPage.ordersTabInfo(), viewOrdersButton);
    }
}
