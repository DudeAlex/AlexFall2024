package com.ecommerce.tests.cart;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.CartPage;
import com.ecommerce.pom.pages.MenPage;
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
        storePage.addToCartSpinnerDisappears();

        storePage.chooseAnItemByClickingAddToCart("Basic Blue Jeans");
        storePage.addToCartSpinnerDisappears();

        storePage.chooseAnItemByClickingAddToCart("Black Over-the-shoulder Handbag");
        storePage.addToCartSpinnerDisappears();

        storePage.chooseAnItemByClickingAddToCart("Blue Denim Shorts");
        storePage.addToCartSpinnerDisappears();

        storePage.chooseAnItemByClickingAddToCart("Blue Shoes");
        storePage.addToCartSpinnerDisappears();

        storePage.chooseAnItemByClickingAddToCart("Blue Tshirt");
        storePage.addToCartSpinnerDisappears();

        storePage.chooseAnItemByClickingAddToCart("Boho Bangle Bracelet");
        storePage.addToCartSpinnerDisappears();

        storePage.chooseAnItemByClickingAddToCart("Dark Brown Jeans");
        storePage.addToCartSpinnerDisappears();

        WaitUtils.elementToBeClickable(driver, By.xpath("//li/a[@class = 'next page-numbers']")).click();

        storePage.chooseAnItemByClickingAddToCart("Dark Grey Jeans");
        storePage.addToCartSpinnerDisappears();

        storePage.chooseAnItemByClickingAddToCart("Denim Blue Jeans");
        storePage.addToCartSpinnerDisappears();

        storePage.chooseAnItemByClickingAddToCart("Faint Blue Jeans");
        storePage.addToCartSpinnerDisappears();

        storePage.chooseAnItemByClickingAddToCart("Green Tshirt");
        storePage.addToCartSpinnerDisappears();

        storePage.chooseAnItemByClickingAddToCart("Red Shoes");
        storePage.addToCartSpinnerDisappears();

        CartPage cartPage = new CartPage(driver);

        cartPage.clearCartFromAllItems();

        Assert.assertEquals(cartPage.getEmptyCartMessage(), "Your cart is currently empty.", "Cart is not empty");
    }
}
