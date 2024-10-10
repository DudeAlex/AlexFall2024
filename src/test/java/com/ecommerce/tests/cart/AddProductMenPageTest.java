package com.ecommerce.tests.cart;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddProductMenPageTest extends BaseTest {
    @Test(description = "9.1-1-1.2 | TC Add a product from the 'Men' page # https://app.clickup.com/t/868a40bq7")
    public void testAddProductMenPage()  {
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = homePage.navigateToAccountPage();
        accountPage.logIn();

        CartPage cartPage = accountPage.navigateToCartPage();
        if(Integer.parseInt(cartPage.getCartItemsNumber()) > 0) {
            cartPage.removeItemsFromCart();
        }

        MenPage menPage = accountPage.navigateToMenPage();
        menPage.addToCartFromManPage();
        cartPage = menPage.clickCartPage();
        Assert.assertEquals(cartPage.getCartItemsNumber(),"1");

    }
}
