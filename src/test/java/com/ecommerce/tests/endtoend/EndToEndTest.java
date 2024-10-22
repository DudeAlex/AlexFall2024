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

public class EndToEndTest  extends BaseTest {

    @Test
    public void testProductToShoppingCart (){

       UserData userData = UserDataPool.getFakerUserDataList(10).get(4);
       String product = "Blue";
       String productNameAndQuantity = "Blue Shoes  × 1";
       String yourOrderIsReceived = "Thank you. Your order has been received.";

        HomePage homePage = new HomePage(driver);
        StorePage storePage = homePage.getHeader().navigateToStorePage();
        String searchResult = storePage.getLeftSidebar().searchProduct(product).getSearchHeaderTitle();
        String item = storePage.getTextFromListProducts(0);
        Assert.assertEquals(searchResult, "Search results: “%s”".formatted(product));

        storePage.chooseAnItemByClickingAddToCart(item);

        CartPage cartPage = new CartPage(driver);
        cartPage.clickViewCartButton();

        String sameItem = storePage.checkProductNameOnCartPage(item);
        Assert.assertEquals(sameItem, item);

        cartPage.clickCheckoutButton();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.inputFirstName(userData.getFirstName())
                    .inputLastName(userData.getLastName())
                    .inputCountry(userData.getCountry())
                    .inputStreetAddress(userData.getAddress())
                    .inputTown(userData.getTown())
                    .clickBillingStateDropDown(userData.getState())
                    .inputZip(userData.getZipCode())
                    .inputEmail(userData.getEmailAddress());

        String productOrder = checkoutPage.productNameAndQuantity();
        Assert.assertEquals(productOrder, productNameAndQuantity);

        checkoutPage.clickPlaceOrderButton();

        String checkOrder = checkoutPage.checkYourOrderHasBeenReceivedMessage();
        Assert.assertEquals(checkOrder, yourOrderIsReceived);



    }

    @Test
    public void mockTest(){

        try {
            User user = UserUtils.readUserFromJson("user.json"); // json file can not be found -> throwing an error message
            Assert.assertNotNull(user);
            Assert.assertEquals(user.getLogin(), "user@example.com");
            Assert.assertEquals(user.getPassword(), "securePassword123");
        } catch (IOException e) {
            Assert.fail("Failed to read user from JSON", e);
        }
    }



    }


