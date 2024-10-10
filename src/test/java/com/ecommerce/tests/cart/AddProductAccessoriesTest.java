package com.ecommerce.tests.cart;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddProductAccessoriesTest extends BaseTest {

    String email = "test_test@test.test";
    String password = "12345";

    @Test(description = "9.1-1-1.4 | TC Add a product from the 'Accessories' page # https://app.clickup.com/t/868a40c1g")
    public void testAddProductStorePage(){
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = homePage.navigateToAccountPage();
        accountPage.logIn(email, password);


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
