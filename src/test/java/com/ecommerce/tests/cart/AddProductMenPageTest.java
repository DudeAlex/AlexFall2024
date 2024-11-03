package com.ecommerce.tests.cart;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pojo.User;
import com.ecommerce.pom.pages.*;
import com.ecommerce.utils.UserUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddProductMenPageTest extends BaseTest {
    @Test(description = "9.1-1-1.2 | TC Add a product from the 'Men' page # https://app.clickup.com/t/868a40bq7")
    public void testAddProductMenPage() {
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage =
                homePage.getHeader()
                        .navigateToAccountPage()
                        .logInUsingConfigUtils()
                        .assertLogin();

        accountPage.getHeader()
                        .navigateToCartPage()
                        .clearCart();

        CartPage cartPage =
                accountPage.getHeader()
                        .navigateToMenPage()
                        .addFirstProductOnPageToCart()
                        .getHeader()
                        .navigateToCartPage();

        Assert.assertEquals(cartPage.getCartItemsNumber(),"1");
    }
}
