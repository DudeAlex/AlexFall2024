package com.ecommerce.tests.cart;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pojo.User;
import com.ecommerce.pom.pages.AccountPage;
import com.ecommerce.pom.pages.CartPage;
import com.ecommerce.pom.pages.StorePage;
import com.ecommerce.utils.UserUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ItemsStayInTheCartTest extends BaseTest {
    @Test(description = "9.1-11-1 Added items stay in the cart when the Customer logged out")
    public void testItemsStayInTheCartWhenLogOut() throws IOException {
        User user = UserUtils.readUserFromJson("user.json");
        AccountPage accountPage =  new AccountPage(driver);
        accountPage.load().logIn(user.getLogin(), user.getPassword());

        CartPage cartPage = accountPage.getHeader().navigateToCartPage();
        cartPage.clearTheCartFromOneItem();

        StorePage storePage = accountPage.getHeader()
                .navigateToStorePage()
                .addFirstProductOnPageToCart();

        cartPage = storePage.getHeader().navigateToCartPage();

        Assert.assertEquals(cartPage.getCartItemsNumber(), "1");

        cartPage.getHeader().navigateToAccountPage().logOutUser();
        accountPage.logIn(user.getLogin(), user.getPassword());

        Assert.assertEquals(cartPage.getCartItemsNumber(), "1");

    }
    @Test(description = "9.1-12-1 Added items stay in the cart when the Customer logged out")
    public void testItemsStayInTheCartWhenNavigateAvay() throws IOException {
        User user = UserUtils.readUserFromJson("user.json");
        AccountPage accountPage =  new AccountPage(driver);
        accountPage.load().logIn(user.getLogin(), user.getPassword());

        CartPage cartPage = accountPage.getHeader().navigateToCartPage();
        cartPage.clearTheCartFromOneItem();

        StorePage storePage = accountPage.getHeader().navigateToStorePage();
        storePage.addFirstProductOnPageToCart();
        cartPage = storePage.getHeader().navigateToCartPage();

        Assert.assertEquals(cartPage.getCartItemsNumber(), "1");

        cartPage.getHeader().navigateToMenPage();

        Assert.assertEquals(cartPage.getCartItemsNumber(), "1");

    }

}
