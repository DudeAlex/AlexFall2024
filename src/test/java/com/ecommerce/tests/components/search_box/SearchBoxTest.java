package com.ecommerce.tests.components.search_box;

import com.ecommerce.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class SearchBoxTest extends BaseTest {

    private static final String ITEM_CATEGORY = "jeans";

    private static int countItemsContainingItemText(List<WebElement> items, String product) {
        int count = 0;
        for (WebElement item : items) {
            String itemText = item.getText().toLowerCase();
            if (itemText.contains(product.toLowerCase())) {
                count++;
            }
        }
        return count;
    }

    private WebElement waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(3));

        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private WebElement waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    @Test(description = "12-1.1 | TC>Store> Verify Search Returns All Items Across Categories "
            + "# https://app.clickup.com/t/8689vk47d")
    public void testVerifySearchReturnsAllItemsInAllCategories() {
        driver.findElement(By.xpath("//a[@href='/store']")).click();
        WebElement searchBox = waitForElementToBeVisible(By.xpath("//input[@type='search']"));
        searchBox.sendKeys(ITEM_CATEGORY);

        waitForElementToBeClickable(By.xpath("//button[@type='submit']")).click();

        List<WebElement> searchResultList = driver.findElements(By.xpath("//ul//h2"));
        Assert.assertFalse(searchResultList.isEmpty(), "Search results are empty.");

        int countItemBySearch = countItemsContainingItemText(searchResultList, ITEM_CATEGORY);
        waitForElementToBeClickable(By.xpath("//li[@id='menu-item-1228']//a[text()='Men']")).click();

        List<WebElement> menItemsList = driver.findElements(By.xpath("//ul//h2"));
        int countItemInMenResult = countItemsContainingItemText(menItemsList, ITEM_CATEGORY);

        waitForElementToBeClickable(By.xpath("//li[@id='menu-item-1229']//a[text()='Women']")).click();
        List<WebElement> womenItemsList = driver.findElements(By.xpath("//ul//h2"));
        int countItemInWomenResult = countItemsContainingItemText(womenItemsList, ITEM_CATEGORY);

        Assert.assertEquals(countItemBySearch, countItemInMenResult + countItemInWomenResult,
                "Search box did not find all the items with item name '"
                        + ITEM_CATEGORY + "' or find extra items");
    }
}

