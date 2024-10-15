package com.ecommerce.tests.women;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.CartPage;
import com.ecommerce.pom.pages.HomePage;
import com.ecommerce.pom.pages.WomenPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WomenAddToCartTest extends BaseTest {

    @Test (description = "4_14 TC | Women > Verify user can add item to shopping cart # https://app.clickup.com/t/8689p8yak")
    public void testAddItemsToCart() {
        HomePage homePage = new HomePage(driver);
        WomenPage womenPage = homePage.getHeader().navigateToWomenPage();
        womenPage.addToCartFromWomenPage();

        CartPage cartPage = womenPage.clickCartPage();
        int product = cartPage.getProductsQuantity();

        Assert.assertEquals(product, 1, "Quantity of items in cart is not 1");
    }
}
