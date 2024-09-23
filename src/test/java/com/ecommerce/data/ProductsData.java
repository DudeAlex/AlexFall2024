package com.ecommerce.data;

import com.ecommerce.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class ProductsData extends BaseTest {
    public static int countItemsContainingItemText(List<WebElement> items, String product) {
        int count = 0;
        for (WebElement item : items) {
            String itemText = item.getText().toLowerCase();
            if (itemText.contains(product.toLowerCase())) {
                count++;
            }
        }

        return count;
    }

    public static List<String> getAllItemsFromAllPages(By locator, WebDriver driver) {
        List<String> allItemList = new ArrayList<>();
        boolean hasNextPage = true;

        while (hasNextPage) {
            List<WebElement> itemList = driver.findElements(locator);
            for (WebElement item : itemList) {
                allItemList.add(item.getText());
            }

            try {
                WebElement nextPageArrow = driver.findElement(By.xpath("//a[@class='next page-numbers']"));
                nextPageArrow.click();
            } catch (NoSuchElementException e) {
                hasNextPage = false;
            }
        }
        return allItemList;
    }

    public static List<Double> getConvertedToDoublePriceList(List<String> priceTextList) {
        List<Double> actualPriceList = new ArrayList<>();
        for (String priceText : priceTextList) {
            String priceClearedFromSigns = priceText.replace("$", "").trim();
            double price = Double.parseDouble(priceClearedFromSigns);
            actualPriceList.add(price);
        }

        return actualPriceList;
    }

    @DataProvider
    public static Object[][] provideAllItemCategory() {
        return new Object[][]{
                {"//li[@id='menu-item-1227']"},
                {"//li[@id='menu-item-1228']"},
                {"//li[@id='menu-item-1229']"},
                {"//li[@id='menu-item-1230']"}
        };
    }

    @DataProvider
    public static Object[][] provideAllItemLocatorsWithNames() {
        return new Object[][]{
                {"//li[@id='menu-item-1228']", "Men"},
                //bug or?
                // {"//li[@id='menu-item-1229']", "Women"},
                {"//li[@id='menu-item-1230']", "Accessories"}
        };
    }
}
