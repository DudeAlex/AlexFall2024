package com.ecommerce.tests.cart;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.CartPage;
import com.ecommerce.pom.pages.StorePage;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RemoveAllItemsFromCartTest extends BaseTest {
    @Test
    public void testRemoveAllItems() {

        StorePage storePage = new StorePage(driver);
        storePage.load();

        storePage.chooseAnItemByClickingAddToCart("Anchor Bracelet");
        WaitUtils.waitForAddToCartSpinnerToDisappear(driver);

        storePage.chooseAnItemByClickingAddToCart("Basic Blue Jeans");
        WaitUtils.waitForAddToCartSpinnerToDisappear(driver);

        storePage.chooseAnItemByClickingAddToCart("Black Over-the-shoulder Handbag");
        WaitUtils.waitForAddToCartSpinnerToDisappear(driver);

        storePage.chooseAnItemByClickingAddToCart("Blue Denim Shorts");
        WaitUtils.waitForAddToCartSpinnerToDisappear(driver);

        storePage.chooseAnItemByClickingAddToCart("Blue Shoes");
        WaitUtils.waitForAddToCartSpinnerToDisappear(driver);

        storePage.chooseAnItemByClickingAddToCart("Blue Tshirt");
        WaitUtils.waitForAddToCartSpinnerToDisappear(driver);

        storePage.chooseAnItemByClickingAddToCart("Boho Bangle Bracelet");
        WaitUtils.waitForAddToCartSpinnerToDisappear(driver);

        storePage.chooseAnItemByClickingAddToCart("Dark Brown Jeans");
        WaitUtils.waitForAddToCartSpinnerToDisappear(driver);

        WaitUtils.elementToBeClickable(driver, By.xpath("//li/a[@class = 'next page-numbers']")).click();

        storePage.chooseAnItemByClickingAddToCart("Dark Grey Jeans");
        WaitUtils.waitForAddToCartSpinnerToDisappear(driver);

        storePage.chooseAnItemByClickingAddToCart("Denim Blue Jeans");
        WaitUtils.waitForAddToCartSpinnerToDisappear(driver);

        storePage.chooseAnItemByClickingAddToCart("Faint Blue Jeans");
        WaitUtils.waitForAddToCartSpinnerToDisappear(driver);

        storePage.chooseAnItemByClickingAddToCart("Green Tshirt");
        WaitUtils.waitForAddToCartSpinnerToDisappear(driver);

        storePage.chooseAnItemByClickingAddToCart("Red Shoes");
        WaitUtils.waitForAddToCartSpinnerToDisappear(driver);

        CartPage cartPage = new CartPage(driver);

        cartPage.clearCartFromAllItems();

        Assert.assertEquals(cartPage.getEmptyCartMessage(), "Your cart is currently empty.", "Cart is not empty");
    }
}
