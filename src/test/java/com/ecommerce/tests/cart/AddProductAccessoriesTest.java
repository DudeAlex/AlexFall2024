package com.ecommerce.tests.cart;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pojo.User;
import com.ecommerce.pom.pages.*;
import com.ecommerce.utils.UserUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddProductAccessoriesTest extends BaseTest {

    @Test(description = "9.1-1-1.4 | TC Add a product from the 'Accessories' page # https://app.clickup.com/t/868a40c1g")
    public void testAddProductAccessoriesPage() throws IOException {
        User user = UserUtils.readUserFromJson("user.json");
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = homePage.getHeader().navigateToAccountPage();
        accountPage.logIn(user.getLogin(), user.getPassword());

        CartPage cartPage = accountPage.getHeader().navigateToCartPage();
        cartPage.clearTheCart();

        AccessoriesPage accessoriesPage = accountPage.getHeader().navigateToAccessoriesPage();
        accessoriesPage.addToCartFromAccessoriesPage();
        cartPage = accessoriesPage.getHeader().navigateToCartPage();

        Assert.assertEquals(cartPage.getCartItemsNumber(), "1");

    }

}
