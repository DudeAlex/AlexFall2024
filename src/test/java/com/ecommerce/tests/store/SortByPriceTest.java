package com.ecommerce.tests.store;

import com.ecommerce.base.BaseTest;
import com.ecommerce.data.Product;
import com.ecommerce.pom.pages.HomePage;
import com.ecommerce.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class SortByPriceTest extends BaseTest {
    @Test(description = "2 - 1.1 TC> Store Page > Sort products by price from High to Low # https://app.clickup.com/t/8689ug934")
/*    The test verifies that items in the shop are ordered in the descending order (from high to low) according to
      their prices, when "Sort by price: high to low" option is chosen in the drop-down menu on the "Store" page.
 */
    public void testSortingItemsByPrice() {

        HomePage homePage = new HomePage(driver);
        List<Product> allProductsInStore = homePage.getHeader()
                .navigateToStorePage()
                .selectSortingOrder("Sort by price: high to low")
                .getAllProductsInStore();

        List<Double> allPrices = new ArrayList<>();
        for (Product product : allProductsInStore) {
                allPrices.add(product.getPrice());
        }

//      Verifying that the order of the prices in the pricesNumeric list is descending
        boolean isDescending = true;
        for (int i = 0; i < allPrices.size() - 1; i++) {
            if (allPrices.get(i) < allPrices.get(i + 1)) {
                isDescending = false;
                break;
            }
            Assert.assertTrue(isDescending, "The prices are NOT in descending order!");
        }
    }

    @Test(description = "2 - 1.0 TC> Store Page > Sort products by price from Low to High # https://app.clickup.com/t/8689ug903")

    public void testSortPriceLowToHigh() {
        HomePage homePage = new HomePage(driver);
        List<Product> allProductsInStore = homePage.getHeader()
                .navigateToStorePage()
                .selectSortingOrder("Sort by price: low to high")
                .getAllProductsInStore();

        List<Double> allPrices = new ArrayList<>();
        for (Product product : allProductsInStore) {
            allPrices.add(product.getPrice());
        }

        boolean isAscending = true;
        for (int i = 0; i < allPrices.size() - 1; i++) {
            if (allPrices.get(i) < allPrices.get(i + 1)) {
                isAscending = false;
                break;
            }
            Assert.assertTrue(isAscending, "The prices are NOT in ascending order!");
        }
    }

    @Test(description = "TC 2.1 - 5.1| Sort all products in the shop by price from high to low using 'Product' object." +
            "# https://app.clickup.com/t/868ar73c2")

    public void testSortByPriceHighToLowUsingProductObject() {

     StorePage storePage = new HomePage(driver).getHeader().navigateToStorePage();
     List<Product> allProductsList_defaultSorting = storePage.getAllProductsInStore();

     storePage.clickFirstPageIcon().selectSortingOrder("Sort by price: high to low");

     List<Product> allProductsList_highToLowPriceSorting = storePage.getAllProductsInStoreAfterSorting();

 // sorting products in the 'default sorting' list by prices from high to low using java methods
     allProductsList_defaultSorting.sort(Comparator.reverseOrder());

/*  compare products in the list sorted by java with the list created by sorting at the website.
    if all the products are the same and have the same sorting order assertion will pass.
 */

     for(int i = 0; i < allProductsList_defaultSorting.size(); i++) {
         Product product_1 = allProductsList_highToLowPriceSorting.get(i);
         Product product_2 = allProductsList_defaultSorting.get(i);

         Assert.assertEquals(product_1, product_2, "Products at index " + i + " are not the same.");
     }

    }
}
