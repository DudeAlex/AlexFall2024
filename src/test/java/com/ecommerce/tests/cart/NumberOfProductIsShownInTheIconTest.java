package com.ecommerce.tests.cart;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pojo.User;
import com.ecommerce.pom.pages.AccountPage;
import com.ecommerce.pom.pages.CartPage;
import com.ecommerce.pom.pages.HomePage;
import com.ecommerce.pom.pages.StorePage;
import com.ecommerce.utils.UserUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;


public class NumberOfProductIsShownInTheIconTest extends BaseTest {

    @Test(description = "9.1-8-1| TC The number of products in the cart is shown on the cart icon in the header # https://app.clickup.com/t/868abcvg5")
    public void testNumberOfProductIsShownInTheIconStorePage() throws IOException {
        User user = UserUtils.readUserFromJson("user.json");
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = homePage.getHeader().navigateToAccountPage();
        accountPage.logIn(user.getLogin(), user.getPassword());

        CartPage cartPage = accountPage.getHeader().navigateToCartPage();
        cartPage.clearTheCartFromOneItem();

        StorePage storePage = cartPage.getHeader().navigateToStorePage();
        storePage.addFirstProductOnPageToCart();
        cartPage = storePage.getHeader().navigateToCartPage();

        Assert.assertEquals(cartPage.getCartItemsNumber(), "1");

    }

}

