package com.ecommerce.tests.men;

import com.ecommerce.base.BaseTest;
import com.ecommerce.data.Product;
import com.ecommerce.pom.pages.MenPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FilterByPriceTest extends BaseTest {

    @Test (description = "10.4 - 3.1 | Filter by price on the 'Men' Page")
    public void testFilterNodMoves() {
        MenPage menPage = new MenPage(driver);
        menPage.load();

        List<Product> listProductsBeforeFiltering = menPage.getProductsGrid()
                .getAllProductsOnPage();

        menPage.moveLeftNodOfPriceFilter(30)
                .moveRightNodOfPriceFilter(140)
                .clickFilterButton();

        List<Product> listProductsAfterFiltering = menPage.getProductsGrid()
                .getAllProductsOnPage();

        for (Product product: listProductsAfterFiltering) {
            Assert.assertTrue(product.getPrice() >= 30 & product.getPrice()<= 140);
            Assert.assertTrue(listProductsBeforeFiltering.contains(product));
        }
    }
}
