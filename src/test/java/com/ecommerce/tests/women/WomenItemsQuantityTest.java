package com.ecommerce.tests.women;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.WomenPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WomenItemsQuantityTest extends BaseTest {

    @Test
    public void testQuantityOfItems() {

        WomenPage womenPage = new WomenPage(driver);
        womenPage.load();

        int itemsQuantityNumber = womenPage.getPageResultCount();
        int quantityOfDisplayedItemsOnPage = womenPage.getNumberOfProductsOnPage();

        Assert.assertEquals(itemsQuantityNumber, quantityOfDisplayedItemsOnPage,
                "The quantity of displayed items on page doesn't match result number");
    }
}