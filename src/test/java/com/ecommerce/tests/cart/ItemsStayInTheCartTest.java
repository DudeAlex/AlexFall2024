package com.ecommerce.tests.cart;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pojo.User;
import com.ecommerce.pom.pages.AccountPage;
import com.ecommerce.pom.pages.CartPage;
import com.ecommerce.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ItemsStayInTheCartTest extends BaseTest {
    @Test(description = "9.1-11-1 Added items stay in the cart when the Customer logged out")
    public void testItemsStayInTheCartWhenLogOut(){

        User user = new User("test_test@test.test", "12345");
        AccountPage accountPage =  new AccountPage(driver);
        accountPage.load().logIn(user.getEmail(), user.getPassword());

        CartPage cartPage = accountPage.getHeader().navigateToCartPage();
        if (Integer.parseInt(cartPage.getCartItemsNumber()) > 0) {
            cartPage.removeItemsFromCart();
        }

        StorePage storePage = accountPage.getHeader().navigateToStorePage();
        storePage.addToCartFromStorePage();
        cartPage = storePage.getHeader().navigateToCartPage();

        Assert.assertEquals(cartPage.getCartItemsNumber(), "1");

        cartPage.getHeader().navigateToAccountPage().logout();
        accountPage.logIn(user.getEmail(), user.getPassword());

        Assert.assertEquals(cartPage.getCartItemsNumber(), "1");

    }
    @Test(description = "9.1-12-1 Added items stay in the cart when the Customer logged out")
    public void testItemsStayInTheCartWhenNavigateAvay(){

        User user = new User("test_test@test.test", "12345");
        AccountPage accountPage =  new AccountPage(driver);
        accountPage.load().logIn(user.getEmail(), user.getPassword());

        CartPage cartPage = accountPage.getHeader().navigateToCartPage();
        if (Integer.parseInt(cartPage.getCartItemsNumber()) > 0) {
            cartPage.removeItemsFromCart();
        }

        StorePage storePage = accountPage.getHeader().navigateToStorePage();
        storePage.addToCartFromStorePage();
        cartPage = storePage.getHeader().navigateToCartPage();

        Assert.assertEquals(cartPage.getCartItemsNumber(), "1");

        cartPage.getHeader().navigateToMenPage();

        Assert.assertEquals(cartPage.getCartItemsNumber(), "1");

    }

}
