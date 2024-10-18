package com.ecommerce.tests.men;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.HomePage;
import com.ecommerce.pom.pages.MenPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class MenTest extends BaseTest {

    @Test(description = "3_16 - 1 | TC > Men > Verify categories # https://app.clickup.com/t/8689zw695")
    public void testProductsBelongToCategories() {
        List<String> productCategories = new HomePage(driver)
                .getHeader().navigateToMenPage()
                .collectCategories();
        for (String categories : productCategories) {
            Assert.assertEquals(categories, "Men");
        }
    }

    @Test(description = "3.9-1.1 | TC> Man> Verify Sale items price # https://app.clickup.com/t/8689v3293")
    public void testVerifyReducedPriceForSaleItems() {
        List<Boolean> productList = new HomePage(driver)
                .getHeader().navigateToMenPage()
                .areProductsOnSaleHaveCrossedPrice();

        for (Boolean isOnSalAndHasCrossedPrice : productList) {

            Assert.assertTrue(isOnSalAndHasCrossedPrice, "Product does not have sale tag or crossed-out price");
        }
    }

    @Test(description = "3.1 -1-1 | TC > Men >Sort low to high price # https://app.clickup.com/t/8689vk1yn")
    public void testSortByPriceLowToHigh() {
        boolean isSorted = new HomePage(driver)
                .getHeader().navigateToMenPage()
                .selectSortByPriceLowToHigh()
                .areProductsPricesInAscendingOrder();

        Assert.assertTrue(isSorted, "Prices are not sorted from low to high as expected");
    }

    @Test(description = "3.1 - 1-2 | TC> Men> Sort high to low price # https://app.clickup.com/t/8689vjzgq")
    public void testSortByPriceHighToLow() {
        boolean isSorted = new HomePage(driver)
                .getHeader().navigateToMenPage()
                .selectSortByPriceHighToLow()
                .areProductsPricesInDescendingOrder();

        Assert.assertTrue(isSorted, "Prices are not sorted from high to low as expected");
    }

    @Test(description = "3.7-1-4 |TC Page header \"Men\" is displayed # https://app.clickup.com/t/868a9eewt")
    public void testVerifyMenHeader(){
        String text = new MenPage(driver)
                .load()
                .verifyHeaderText();

        Assert.assertEquals("Men",text);
    }

    @Test(description = "3.7-1-2 |TC Total number of displayed \"Men's\" products should be 7 # https://app.clickup.com/t/868a7n4yt")
    public void testMenQtyMatches(){
        MenPage menPage = new MenPage(driver);
        menPage.load();

        int expectedCount = 7;
        Assert.assertTrue(menPage.verifyMenItemsCount(7));
    }

    @Test(description = "3.7-1-3 |TC Filter by price is displaying the range between $20-$150 # https://app.clickup.com/t/868a2tq56")
    public void testPriceRange(){
        MenPage menPage = new MenPage(driver);
        menPage.load();

        Assert.assertEquals("Price: $20 — $150",menPage.getPriceRangeText());
    }

    @Test(description = "3.7-1-1 |TC Only products categorized as \"Men\" should be displayed # https://app.clickup.com/t/868a2tm55")
    public void testVerifyOnlyMenItemsDisplayed(){
        MenPage menPage = new MenPage(driver);
        menPage.load();

        Assert.assertTrue(menPage.getMenQtyOnPage());
    }
}