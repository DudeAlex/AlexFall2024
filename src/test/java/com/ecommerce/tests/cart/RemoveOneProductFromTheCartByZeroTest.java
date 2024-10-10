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


public class RemoveOneProductFromTheCartByZeroTest extends BaseTest {

    @Test(description = "9.1-2-2.2 | TC Product quantity changed to '0' and the cart is updated # https://app.clickup.com/t/868a52u2t")
    public void testRemoveOneProductFromTheCartByZero() {
        User user = new User("test_test@test.test", "12345");
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = homePage.navigateToAccountPage();
        accountPage.logIn(user.getEmail(), user.getPassword());
        StorePage storePage = accountPage.navigateToStorePage();
        storePage.addToCartFromStorePage();
        CartPage cartPage = storePage.clickCartPage();
        cartPage.setZeroValueOfProductQuantity();
        WaitUtils.waitForQuantityToBe(cartPage.getDriver(), By.xpath("//span[@class='count']"),"0");

        Assert.assertEquals(cartPage.getCartItemsNumber(), "0");

    }

}

