package com.ecommerce.tests.cart;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RemoveAllItemsFromCartTest extends BaseTest {
    @Test (description = "9.1-2-2.4 | TC All products removed by clicking 'x' icon in the Cart # https://app.clickup.com/t/868afjgv3")
    public void testRemoveAllItems() {

        AccountPage accountPage = new AccountPage(driver);
        accountPage.load();
        accountPage.logIn("aaa", "111");

        StorePage storePage = new StorePage(driver);
        storePage.load();

        storePage.chooseAnItemByClickingAddToCart("Anchor Bracelet");
        storePage.chooseAnItemByClickingAddToCart("Basic Blue Jeans");
        storePage.chooseAnItemByClickingAddToCart("Black Over-the-shoulder Handbag");
        storePage.chooseAnItemByClickingAddToCart("Blue Denim Shorts");
        storePage.chooseAnItemByClickingAddToCart("Blue Shoes");
        storePage.chooseAnItemByClickingAddToCart("Blue Tshirt");
        storePage.chooseAnItemByClickingAddToCart("Boho Bangle Bracelet");
        storePage.chooseAnItemByClickingAddToCart("Dark Brown Jeans");

        storePage.clickNextPageButton();

        storePage.chooseAnItemByClickingAddToCart("Dark Grey Jeans");
        storePage.chooseAnItemByClickingAddToCart("Denim Blue Jeans");
        storePage.chooseAnItemByClickingAddToCart("Faint Blue Jeans");
        storePage.chooseAnItemByClickingAddToCart("Green Tshirt");
        storePage.chooseAnItemByClickingAddToCart("Red Shoes");

        CartPage cartPage = new CartPage(driver);
        cartPage.load();

        cartPage.clearCart();

        Assert.assertEquals(cartPage.getEmptyCartMessage(), "Your cart is currently empty.", "Cart is not empty");
    }
}
