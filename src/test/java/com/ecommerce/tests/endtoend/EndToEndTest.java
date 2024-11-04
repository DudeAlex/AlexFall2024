package com.ecommerce.tests.endtoend;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pojo.User;
import com.ecommerce.pojo.UserData;
import com.ecommerce.pojo.UserDataPool;
import com.ecommerce.pom.pages.*;
import com.ecommerce.utils.UserUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class EndToEndTest extends BaseTest {

    @Test
    public void testProductToShoppingCart() throws IOException, InterruptedException {

        String product = "Blue";
        String productNameAndQuantity = "Blue Shoes  × 1";
        String yourOrderIsReceived = "Thank you. Your order has been received.";

        HomePage homePage = new HomePage(driver);
        StorePage storePage = homePage.getHeader().navigateToStorePage();
        String searchResult = storePage.getLeftSidebar().searchProduct(product).getSearchHeaderTitle();
        String item = storePage.getTextFromListProducts(0);
        Assert.assertEquals(searchResult, "Search results: “%s”".formatted(product));

        storePage.addProductToCart(item);

        CartPage cartPage = new CartPage(driver);
        cartPage.clickViewCartButton();

        String sameItem = storePage.checkProductNameOnCartPage(item);
        Assert.assertEquals(sameItem, item);

        cartPage.clickCheckoutButton();


        User user = UserUtils.readUserFromJson("user1.json");

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.inputFirstName(user.getFirstName())
                .inputLastName(user.getLastName())
                .inputCountry(user.getCountry())
                .inputStreetAddress(user.getAddress())
                .inputTown(user.getTown())
                .clickBillingStateDropDown(user.getState())
                .inputZip(user.getZipCode())
                .inputEmail(user.getLogin());

        Thread.sleep(4000);

        String productOrder = checkoutPage.productNameAndQuantity();
        Assert.assertEquals(productOrder, productNameAndQuantity);

        checkoutPage.clickPlaceOrderButton();

        String checkOrder = checkoutPage.checkYourOrderHasBeenReceivedMessage();
        Assert.assertEquals(checkOrder, yourOrderIsReceived);
    }

    @Test
    public void mockTest() {

        try {
            User user = UserUtils.readUserFromJson("user.json"); // json file can not be found -> throwing an error message
            Assert.assertNotNull(user);
            Assert.assertEquals(user.getLogin(), "test_test@test.test");
            Assert.assertEquals(user.getPassword(), "12345");
        } catch (IOException e) {
            Assert.fail("Failed to read user from JSON", e);
        }
    }
}
