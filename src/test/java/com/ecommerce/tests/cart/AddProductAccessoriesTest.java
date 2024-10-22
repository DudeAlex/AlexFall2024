package com.ecommerce.tests.cart;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pojo.User;
import com.ecommerce.pom.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddProductAccessoriesTest extends BaseTest {

    @Test(description = "9.1-1-1.4 | TC Add a product from the 'Accessories' page # https://app.clickup.com/t/868a40c1g")
    public void testAddProductAccessoriesPage(){
        User user = new User("test_test@test.test", "12345");
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = homePage.getHeader().navigateToAccountPage();
        accountPage.logIn(user.getEmail(), user.getPassword());

        CartPage cartPage = accountPage.getHeader().navigateToCartPage();
        cartPage.clearTheCart();

        AccessoriesPage accessoriesPage = accountPage.getHeader().navigateToAccessoriesPage();
        accessoriesPage.addToCartFromAccessoriesPage();
        cartPage = accessoriesPage.getHeader().navigateToCartPage();

        Assert.assertEquals(cartPage.getCartItemsNumber(), "1");

    }

}
