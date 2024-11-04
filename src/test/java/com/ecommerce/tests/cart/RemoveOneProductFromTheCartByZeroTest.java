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


public class RemoveOneProductFromTheCartByZeroTest extends BaseTest {

    @Test(description = "9.1-2-2.2 | TC Product quantity changed to '0' and the cart is updated # https://app.clickup.com/t/868a52u2t")
    public void testRemoveOneProductFromTheCartByZero() throws IOException {
        User user = UserUtils.readUserFromJson("user.json");
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = homePage.getHeader().navigateToAccountPage();
        accountPage.logIn(user.getLogin(), user.getPassword());
        StorePage storePage = accountPage.getHeader().navigateToStorePage();
        storePage.addFirstProductOnPageToCart();
        CartPage cartPage = storePage.getHeader().navigateToCartPage();
        cartPage.setZeroValueOfProductQuantity();
        WaitUtils.waitForQuantityToBe(cartPage.getDriver(), By.xpath("//span[@class='count']"),"0");

        Assert.assertEquals(cartPage.getCartItemsNumber(), "0");

    }

}

