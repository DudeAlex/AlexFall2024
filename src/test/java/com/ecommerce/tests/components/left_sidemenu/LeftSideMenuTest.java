package com.ecommerce.tests.components.left_sidemenu;

import com.ecommerce.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeftSideMenuTest extends BaseTest {

    @Test(description = "10.4-10.4-1  | TC >Search box Test> Search by key word # https://app.clickup.com/t/8689x8h18")
    public void testSearchBox() {
        driver.findElement(By.xpath("//a[@class='wp-block-button__link']")).click();
        WebElement searchBar = driver.findElement(By.id("woocommerce-product-search-field-0"));
        searchBar.sendKeys("blue");
        driver.findElement(By.xpath("//button[@value='Search']")).click();

        List<String> expectedSearchResultList = new ArrayList<>();
        List<WebElement> searchResult = driver.findElements(By.xpath("//h2[@class='woocommerce-loop-product__title']"));
        for (WebElement search : searchResult) {
            String text = search.getText();
            if (text.contains("blue")) {
                expectedSearchResultList.add(text);
            }
            for (String item : expectedSearchResultList) {
                Assert.assertTrue(item.toLowerCase().contains("blue"), "The search result does not contain the word 'blue': " + item);

                List<String> actualSearchResultList = List.of("Blue Shoes", "Denim Blue Jeans", "Faint Blue Jeans", "Blue Denim Shorts", "Basic Blue Jeans", "Blue Tshirt");

                Assert.assertEquals(actualSearchResultList, expectedSearchResultList);
            }
        }
    }

    @Test(description = "10.4-10.4-2  | TC >Search box Test> Search by key word> No product were found # https://app.clickup.com/t/8689xwcb5")
    public void testSearchBoxNoResult()  {
        driver.findElement(By.xpath("//a[@class='wp-block-button__link']")).click();
        WebElement searchBar = driver.findElement(By.id("woocommerce-product-search-field-0"));
        searchBar.sendKeys("moon");
        driver.findElement(By.xpath("//button[@value='Search']")).click();
        WebElement notFoundMsg = driver.findElement(By.xpath("//p[@class='woocommerce-info woocommerce-no-products-found']"));

        Assert.assertEquals(notFoundMsg.getText(),"No products were found matching your selection.");

    }
    @Test(description = "10.2-10.2-2 | TC > Leftside_Bar > Browser by categories # https://app.clickup.com/t/8689x8my5")
    public void testBrowseByCategoriesLeftMenu() {
        driver.findElement(By.xpath("//a[@class='wp-block-button__link']")).click();
        WebElement dropdown = driver.findElement(By.id("product_cat"));
        Select select = new Select(dropdown);
        select.selectByIndex(2);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='woocommerce-products-header__title page-title']")));

        List<String> actualSortedList = new ArrayList<>();
        List<WebElement> sortedList = driver.findElements(By.xpath("//span[@class='ast-woo-product-category']"));
        for (WebElement category : sortedList) {
            actualSortedList.add(category.getText());

            List<String> expectedMenSorting = new ArrayList<>(actualSortedList);
            Collections.sort(expectedMenSorting);

            Assert.assertEquals(actualSortedList, expectedMenSorting, "Sorting by Category Dropdown Did Not Apply to 'Men' Category");
        }
    }

    @Test(description = "10.3-10.3-1  | TC > Leftside_Bar > filter price  > price range # https://app.clickup.com/t/8689x9pxq")
    public void testFilterPriceCheck() {
        driver.findElement(By.xpath("//a[@class='wp-block-button__link']")).click();

        WebElement priceFilter = driver.findElement(By.xpath("//span[@tabindex='0' and contains(@class, 'ui-slider-handle') and @style='left: 100%;']"));
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(priceFilter, -150, 0).perform();
        WebElement filterBtn = driver.findElement(By.xpath("//button[normalize-space()='Filter']"));
        filterBtn.click();

        List<WebElement> filterPrice = driver.findElements(By.xpath("//span[@class='price']/*[not(@aria-hidden='true')]"));
        List<Double> expectingFilteringPricePage = new ArrayList<>();
        for (WebElement price : filterPrice) {
            String priceText = price.getText().replace("$", "");
            Double filteringPriceValue = Double.parseDouble(priceText);
            expectingFilteringPricePage.add(filteringPriceValue);

            boolean allPricesUnderSixty = true;
            for (Double expectPrice : expectingFilteringPricePage) {
                if (expectPrice > 60) {
                    allPricesUnderSixty = false;
                }
                Assert.assertTrue(allPricesUnderSixty, "Filter by price doesn't filter the price accordingly");
            }
        }
    }

}


