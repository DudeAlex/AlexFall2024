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
    public void testAddProductMenPage() throws IOException {
        User user = UserUtils.readUserFromJson("user.json");
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = homePage.getHeader().navigateToAccountPage();
        accountPage.logIn(user.getLogin(), user.getPassword());

        CartPage cartPage = accountPage.getHeader().navigateToCartPage();
        cartPage.clearTheCart();

        MenPage menPage = accountPage.getHeader().navigateToMenPage();
        menPage.addToCartFromManPage();
        cartPage = menPage.getHeader().navigateToCartPage();

        Assert.assertEquals(cartPage.getCartItemsNumber(),"1");

    }
}
