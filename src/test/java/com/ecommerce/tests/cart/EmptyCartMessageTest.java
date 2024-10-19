package com.ecommerce.tests.cart;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pojo.User;
import com.ecommerce.pom.pages.AccountPage;
import com.ecommerce.pom.pages.CartPage;
import com.ecommerce.pom.pages.HomePage;
import com.ecommerce.pom.pages.StorePage;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


public class EmptyCartMessageTest extends BaseTest {

    @Test(description = "9.1-9-1 | TC The cart page displays a message 'Your cart is currently empty' # https://app.clickup.com/t/868abdbhv")
    public void testRemoveOneProductFromTheCartByX() {
        User user = new User("test_test@test.test", "12345");
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = homePage.getHeader().navigateToAccountPage();
        accountPage.logIn(user.getEmail(), user.getPassword());

        CartPage cartPage = accountPage.getHeader().navigateToCartPage();
        cartPage.clearTheCart();

        WaitUtils.waitForQuantityToBe(cartPage.getDriver(), By.xpath("//span[@class='count']"), "0");

        Assert.assertEquals(cartPage.getCartItemsNumber(), "0");

        Assert.assertEquals(cartPage.getEmptyCartMessage(), "Your cart is currently empty.");

    }

}

