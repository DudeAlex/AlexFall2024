package com.ecommerce.tests.cart;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pojo.User;
import com.ecommerce.pom.pages.*;
import com.ecommerce.utils.UserUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddProductWomenPageTest extends BaseTest {

    @Test(description = "9.1-1-1.3 | TC Add a product from the 'Women' page # https://app.clickup.com/t/868a40bz6")
    public void testAddProductWomenPage() throws IOException {
        User user = UserUtils.readUserFromJson("user.json");
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = homePage.getHeader().navigateToAccountPage();
        accountPage.logIn(user.getLogin(), user.getPassword());

        CartPage cartPage = accountPage.getHeader().navigateToCartPage();
        cartPage.clearCart();

        WomenPage womenPage = accountPage.getHeader().navigateToWomenPage();
        womenPage.addToCartFromWomenPage();
        cartPage = womenPage.getHeader().navigateToCartPage();

        Assert.assertEquals(cartPage.getCartItemsNumber(),"1");

    }
}
