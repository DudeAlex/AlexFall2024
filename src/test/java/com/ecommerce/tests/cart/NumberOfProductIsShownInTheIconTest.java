package com.ecommerce.tests.cart;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pojo.User;
import com.ecommerce.pom.pages.AccountPage;
import com.ecommerce.pom.pages.CartPage;
import com.ecommerce.pom.pages.HomePage;
import com.ecommerce.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class NumberOfProductIsShownInTheIconTest extends BaseTest {

    @Test(description = "9.1-8-1| TC The number of products in the cart is shown on the cart icon in the header # https://app.clickup.com/t/868abcvg5")
    public void testNumberOfProductIsShownInTheIconStorePage(){
        User user = new User("test_test@test.test", "12345");
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = homePage.getHeader().navigateToAccountPage();
        accountPage.logIn(user.getLogin(), user.getPassword());

        CartPage cartPage = accountPage.getHeader().navigateToCartPage();
        cartPage.clearTheCart();

        StorePage storePage = cartPage.getHeader().navigateToStorePage();
        storePage.addToCartFromStorePage();
        cartPage = storePage.getHeader().navigateToCartPage();

        Assert.assertEquals(cartPage.getCartItemsNumber(), "1");

    }

}

