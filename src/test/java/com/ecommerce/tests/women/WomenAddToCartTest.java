package com.ecommerce.tests.women;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.CartPage;
import com.ecommerce.pom.pages.HomePage;
import com.ecommerce.pom.pages.WomenPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WomenAddToCartTest extends BaseTest {

    @Test
    public void testAddItemsToCart() {
        HomePage homePage = new HomePage(driver);
        WomenPage womenPage = homePage.navigateToWomenPage();
        womenPage.addToCartFromWomenPage();

        CartPage cartPage = womenPage.clickCartPage();
        int product = cartPage.getProductsQuantity();

        Assert.assertEquals(product, 1);
    }

}
