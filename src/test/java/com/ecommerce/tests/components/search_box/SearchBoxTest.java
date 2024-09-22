package com.ecommerce.tests.components.search_box;

import com.ecommerce.base.BaseTest;
import com.ecommerce.data.ProductsData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SearchBoxTest extends BaseTest {

    private static final String ITEM_CATEGORY = "jeans";

    @Test(description = "12-1.1 | TC>Store> Verify Search Returns All Items Across Categories "
            + "# https://app.clickup.com/t/8689vk47d")
    public void testVerifySearchReturnsAllItemsInAllCategories() {
        driver.findElement(By.xpath("//a[@href='/store']")).click();
        driver.findElement(By.xpath("//input[@type='search']")).sendKeys(ITEM_CATEGORY);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        List<WebElement> searchResultList = driver.findElements(By.xpath("//ul//h2"));
        Assert.assertFalse(searchResultList.isEmpty(), "Search results are empty.");
        int countItemBySearch = ProductsData.countItemsContainingItemText(searchResultList, ITEM_CATEGORY);

        driver.findElement(By.xpath("//li[@id='menu-item-1228']//a[text()='Men']")).click();
        List<WebElement> menItemsList = driver.findElements(By.xpath("//ul//h2"));
        int countItemInMenResult = ProductsData.countItemsContainingItemText(menItemsList, ITEM_CATEGORY);

        driver.findElement(By.xpath("//li[@id='menu-item-1229']//a[text()='Women']")).click();
        List<WebElement> womenItemsList = driver.findElements(By.xpath("//ul//h2"));
        int countItemInWomenResult = ProductsData.countItemsContainingItemText(womenItemsList, ITEM_CATEGORY);

        Assert.assertEquals(countItemBySearch, countItemInMenResult + countItemInWomenResult,
                "Search box did not find all the items with item name '"
                        + ITEM_CATEGORY + "' or find extra items");

    }
}
