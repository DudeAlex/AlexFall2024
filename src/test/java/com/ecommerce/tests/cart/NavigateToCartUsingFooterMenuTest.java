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

public class NavigateToCartUsingFooterMenuTest extends BaseTest {

    @Test(description = "9.1-6-1 | TC Customer can view the cart using the quick access link in the footer # https://app.clickup.com/t/868abenum")
    public void testNavigateToCartUsingFooterMenu() throws IOException {
        User user = UserUtils.readUserFromJson("user.json");
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = homePage.getHeader().navigateToAccountPage();
        accountPage.logIn(user.getLogin(), user.getPassword());

        accountPage.getFooter().navigateToCartPageFromFooter();

        Assert.assertEquals(driver.getCurrentUrl(), CART_URL);
    }

}
