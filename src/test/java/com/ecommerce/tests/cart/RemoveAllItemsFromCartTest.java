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

        storePage.addProductToCart("Anchor Bracelet");
        storePage.addProductToCart("Basic Blue Jeans");
        storePage.addProductToCart("Black Over-the-shoulder Handbag");
        storePage.addProductToCart("Blue Denim Shorts");
        storePage.addProductToCart("Blue Shoes");
        storePage.addProductToCart("Blue Tshirt");
        storePage.addProductToCart("Boho Bangle Bracelet");
        storePage.addProductToCart("Dark Brown Jeans");

        storePage.clickNextPageButton();

        storePage.addProductToCart("Dark Grey Jeans");
        storePage.addProductToCart("Denim Blue Jeans");
        storePage.addProductToCart("Faint Blue Jeans");
        storePage.addProductToCart("Green Tshirt");
        storePage.addProductToCart("Red Shoes");

        CartPage cartPage = new CartPage(driver);
        cartPage.load();

        cartPage.clearCart();

        Assert.assertEquals(cartPage.getEmptyCartMessage(), "Your cart is currently empty.", "Cart is not empty");
    }
}
