package com.ecommerce.tests.cart;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.AccountPage;
import com.ecommerce.pom.pages.CartPage;
import com.ecommerce.pom.pages.HomePage;
import com.ecommerce.pom.pages.StorePage;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


public class RemoveOneProductFromTheCartByXTest extends BaseTest {

    @Test(description = "9.1-2-2.3 | TC Product removed by clicking the 'x' icon near the product in the cart # https://app.clickup.com/t/868a52u2t")
    public void testAddProductStorePage() {
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = homePage.navigateToAccountPage();
        accountPage.logIn();

        CartPage cartPage = accountPage.navigateToCartPage();
        if (Integer.parseInt(cartPage.getCartItemsNumber()) > 0) {
            cartPage.removeItemsFromCart();
        }

        StorePage storePage = cartPage.navigateToStorePage();
        storePage.addToCartFromStorePage();
        cartPage = storePage.clickCartPage();

        WaitUtils.waitForQuantityToBe(cartPage.getDriver(), By.xpath("//span[@class='count']"),"1");

        Assert.assertEquals(cartPage.getCartItemsNumber(), "1");

        cartPage.removeItemsFromCart();

        WaitUtils.waitForQuantityToBe(cartPage.getDriver(), By.xpath("//span[@class='count']"),"0");

        Assert.assertEquals(cartPage.getCartItemsNumber(), "0");

    }

}
