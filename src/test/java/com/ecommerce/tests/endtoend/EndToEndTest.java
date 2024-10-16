package com.ecommerce.tests.endtoend;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pojo.UserData;
import com.ecommerce.pojo.UserDataPool;
import com.ecommerce.pom.pages.*;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EndToEndTest  extends BaseTest {

    @Test
    public void testProductToShoppingCart (){

       UserData userData = UserDataPool.getFakerUserDataList(10).get(4);
       String product = "Blue";
       String productNameAndQuantity = "Blue Shoes  × 1";
       String yourOrderIsReceived = "Thank you. Your order has been received.";

        HomePage homePage = new HomePage(driver);
        StorePage storePage = homePage.getHeader().navigateToStorePage();
        String searchResult = storePage.searchProduct(product).getSearchHeaderTitle();
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

}
