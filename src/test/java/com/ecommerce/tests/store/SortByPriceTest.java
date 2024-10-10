package com.ecommerce.tests.store;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.HomePage;
import com.ecommerce.pom.pages.StorePage;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class SortByPriceTest extends BaseTest {
    @Test(description = "2 - 1.1 TC> Store Page > Sort products by price from High to Low # https://app.clickup.com/t/8689ug934")
/*    The test verifies that items in the shop are ordered in the descending order (from high to low) according to
      their prices, when "Sort by price: high to low" option is chosen in the drop-down menu on the "Store" page.
 */
    public void testSortingItemsByPrice() {
//      Going from the Home page to the "Store" page and finding the dropdown menu

        By orderBy = By.xpath("//select[@name='orderby']");
        By byPrice = By.xpath("//span[@class='byPrice']");

        HomePage homePage = new HomePage(driver);
        StorePage storePage = homePage.shopNowButton();
        storePage.sortByDropDownButton().sortByPrice();

       // driver.findElement(By.xpath("//a[@class= 'wp-block-button__link']")).click();

        WebElement dropDown = WaitUtils.presenceOfElementLocated(driver, orderBy);
        //WebElement dropdown = driver.findElement(By.xpath("//select[@name='orderby']"));

//      Selecting the menu option that we need for this test
        Select select = new Select(dropDown);
        select.selectByVisibleText("Sort by byPrice: high to low");

//      Creating a list of all items shown on the page 1 of the Store after the sorting; Then, initializing
//      the array where all the prices will be added
        List<WebElement> allProductsPage1 = WaitUtils.numberOfElementsToBeMoreThan(driver, byPrice, 0);
        //List<WebElement> allProductsPage1 = driver.findElements(By.xpath("//span[@class='byPrice']"));
        List<String> allPrices = new ArrayList<>();

//      Collecting the prices into the array. If there is a discount, the discounted byPrice is taken (try block)
//      If there is no discount, regular byPrice is taken
        for (WebElement product : allProductsPage1) {
            try {
                String discountedPrice = product.findElement(By.xpath(".//ins")).getText();
                allPrices.add(discountedPrice);
            } catch (NoSuchElementException e) {
                String regularPrice = product.getText();
                allPrices.add(regularPrice);
            }
        }

//      Going to the 2nd page of the webstore
        driver.findElement(By.xpath("//a[@class='next page-numbers']")).click();

//      Creating a list with all the products on the 2nd page
        List<WebElement> allProductsPage2 = driver.findElements(By.xpath("//span[@class='byPrice']"));

//      Collecting all the prices from the 2nd page to the 'allPrices' array
        for (WebElement product : allProductsPage2) {
            try {
                String discountedPrice = product.findElement(By.xpath(".//ins")).getText();
                allPrices.add(discountedPrice);
            } catch (NoSuchElementException e) {
                String regularPrice = product.getText();
                allPrices.add(regularPrice);
            }
        }

//      Initializing the list of Double values to put prices in the numeric type to it
        List<Double> pricesNumeric = new ArrayList<>();

//      Parsing String prices to Double format, removing the '$' sign, populating pricesNumeric list
        for (String price : allPrices) {
            pricesNumeric.add(Double.parseDouble(price.replace("$", "")));
        }

//      Verifying that the order of the prices in the pricesNumeric list is descending
        boolean isDescending = true;
        for (int i = 0; i < pricesNumeric.size() - 1; i++) {
            if (pricesNumeric.get(i) < pricesNumeric.get(i + 1)) {
                isDescending = false;
                break;
            }
            Assert.assertTrue(isDescending, "The prices are NOT in descending order!");
        }
    }

    @Test(description = "2 - 1.0 TC> Store Page > Sort products by price from Low to High # https://app.clickup.com/t/8689ug903")

    public void testSortPriceLowToHigh() {
        driver.findElement(By.xpath("//a[@class= 'wp-block-button__link']")).click();
        WebElement dropdown = driver.findElement(By.xpath("//select[@name='orderby']"));

        Select select = new Select(dropdown);
        select.selectByVisibleText("Sort by price: low to high");

        List<WebElement> allProductsPage1 = driver.findElements(By.xpath("//span[@class='price']"));
        List<String> allPrices = new ArrayList<>();

        List<Double> pricesNumeric = new ArrayList<>();

//      Parsing String prices to Double format, removing the '$' sign, populating pricesNumeric list
        for (String price : allPrices) {
            pricesNumeric.add(Double.parseDouble(price.replace("$", "")));
        }

        boolean isAscending = true;
        for (int i = 0; i < pricesNumeric.size() - 1; i++) {
            if (pricesNumeric.get(i) < pricesNumeric.get(i + 1)) {
                isAscending = false;
                break;
            }
            Assert.assertTrue(isAscending, "The prices are NOT in ascending order!");
        }
    }
}
