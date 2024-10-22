package com.ecommerce.tests.cart;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pojo.User;
import com.ecommerce.pom.pages.AccountPage;
import com.ecommerce.pom.pages.CartPage;
import com.ecommerce.pom.pages.HomePage;
import com.ecommerce.pom.pages.StorePage;
import com.ecommerce.utils.UserUtils;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;


public class RemoveOneProductFromThePopUpByXTest extends BaseTest {

    @Test(description = "9.1-2-2.1 | TC Product removed by clicking 'x' icon on the 'cart preview' window # https://app.clickup.com/t/868a5jk11")
    public void testRemoveOneProductFromThePopUpByX() throws IOException {
        User user = UserUtils.readUserFromJson("user.json");
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = homePage.getHeader().navigateToAccountPage();
        accountPage.logIn(user.getLogin(), user.getPassword());

        CartPage cartPage = accountPage.getHeader().navigateToCartPage();
        cartPage.clearTheCart();

        StorePage storePage = cartPage.getHeader().navigateToStorePage();
        storePage.addToCartFromStorePage();
        WaitUtils.waitForQuantityToBe(cartPage.getDriver(), By.xpath("//div/header/div[1]/div[1]/div/div/div/div[2]/div[2]/div/div[1]/a/div/span"),"1");

        Assert.assertEquals(cartPage.getCartItemsNumber(), "1");

        cartPage.removeItemsFromCartPreview();
        WaitUtils.waitForQuantityToBe(cartPage.getDriver(), By.xpath("//div/header/div[1]/div[1]/div/div/div/div[2]/div[2]/div/div[1]/a/div/span"),"0");

        Assert.assertEquals(cartPage.getCartItemsNumber(), "0");

    }

}

