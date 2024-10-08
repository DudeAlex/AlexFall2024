package com.ecommerce.tests.cart;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddProductWomenPageTest extends BaseTest {

    @Test(description = "9.1-1-1.3 | TC Add a product from the 'Women' page # https://app.clickup.com/t/868a40bz6")
    public void testAddProductWomenPage() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = homePage.navigateToAccountPage();
        accountPage.logIn();

        CartPage cartPage = accountPage.navigateToCartPage();
        if(Integer.parseInt(cartPage.getCartItemsNumber()) > 0) {
            cartPage.removeItemsFromCart();
        }

        WomenPage womenPage = accountPage.navigateToWomenPage();
        womenPage.addToCartFromWomenPage();
        cartPage = womenPage.clickCartPage();
        Assert.assertEquals(cartPage.getCartItemsNumber(),"1");

    }
}
