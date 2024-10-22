package com.ecommerce.tests.cart;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pojo.User;
import com.ecommerce.pom.pages.AccountPage;
import com.ecommerce.pom.pages.HomePage;
import com.ecommerce.utils.UserUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.ecommerce.pom.EndPoints.CART_URL;

public class NavigateToCartUsingHeaderMenuTest extends BaseTest {

    @Test(description = "9.1-5-1 | TC Customer can view the cart by clicking the cart logo in the site header # https://app.clickup.com/t/868abenum")
    public void testNavigateToCartUsingHeaderMenu() throws IOException {
        User user = UserUtils.readUserFromJson("user.json");
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = homePage.getHeader().navigateToAccountPage();
        accountPage.logIn(user.getLogin(), user.getPassword());

        accountPage.getHeader().navigateToCartPage();

        Assert.assertEquals(driver.getCurrentUrl(), CART_URL);
    }

}
