package com.ecommerce.tests.cart;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.AccountPage;
import com.ecommerce.pom.pages.CartPage;
import com.ecommerce.pom.pages.HomePage;
import com.ecommerce.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddProductAccessoriesTest extends BaseTest {

    @Test(description = "9.1-1-1.4 | TC Add a product from the 'Accessories' page # https://app.clickup.com/t/868a40c1g")
    public void testAddProductStorePage(){
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = homePage.navigateToAccountPage();
        accountPage.logIn();

        CartPage cartPage = accountPage.navigateToCartPage();
        if (Integer.parseInt(cartPage.getCartItemsNumber()) > 0) {
            cartPage.removeItemsFromCart();
        }

        AccessoriesPage accessoriesPage = accountPage.navigateToAccessoriesPage();
        accessoriesPage.addToCartFromAccessoriesPage();
        cartPage = accessoriesPage.clickCartPage();
        Assert.assertEquals(cartPage.getCartItemsNumber(), "1");

    }
}
