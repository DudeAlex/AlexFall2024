import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ItemTest extends BaseTest {

    private static final String ITEM_CATEGORY = "jeans";

    private int countItemsContainingItemText(List<WebElement> items) {
        int count = 0;
        for (WebElement item : items) {
            String itemText = item.getText().toLowerCase();
            if (itemText.contains(ITEM_CATEGORY.toLowerCase())) {
                count++;
            }
        }

        return count;
    }

    public List<String> getAllItemsFromAllPages(By locator) {
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

    private List<Double> getConvertedToDoublePriceList(List<String> priceTextList){
        List<Double> actualPriceList = new ArrayList<>();
        for (String priceText : priceTextList) {
            String priceClearedFromSigns = priceText.replace("$", "").trim();
            double price = Double.parseDouble(priceClearedFromSigns);
            actualPriceList.add(price);
        }

        return actualPriceList;
    }

    @Test
    public void testSearchReturnsAllItemsInCategories() {
        driver.findElement(By.xpath("//a[@href='/store']")).click();
        driver.findElement(By.xpath("//input[@type='search']")).sendKeys(ITEM_CATEGORY);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        List<WebElement> searchResultList = driver.findElements(By.xpath("//ul//h2"));
        Assert.assertFalse(searchResultList.isEmpty(), "Search results are empty.");
        int countItemBySearch = countItemsContainingItemText(searchResultList);

        driver.findElement(By.xpath("//li[@id='menu-item-1228']//a[text()='Men']")).click();
        List<WebElement> menItemsList = driver.findElements(By.xpath("//ul//h2"));
        int countItemInMenResult = countItemsContainingItemText(menItemsList);

        driver.findElement(By.xpath("//li[@id='menu-item-1229']//a[text()='Women']")).click();
        List<WebElement> womenItemsList = driver.findElements(By.xpath("//ul//h2"));
        int countItemInWomenResult = countItemsContainingItemText(womenItemsList);

        Assert.assertEquals(countItemBySearch, countItemInMenResult + countItemInWomenResult,
                "Search box did not find all the items with item name '"
                        + ITEM_CATEGORY + "' or find extra items");
    }

    @DataProvider
    public Object[][] provideAllItemCategory() {
        return new Object[][]{
                {"//li[@id='menu-item-1227']"},
                {"//li[@id='menu-item-1228']"},
                {"//li[@id='menu-item-1229']"},
                {"//li[@id='menu-item-1230']"}
        };
    }

    @Test(dataProvider = "provideAllItemCategory")
    public void testVerifyItemsAlphabeticalOrder(String locator) {
        driver.findElement(By.xpath(locator)).click();

        List<String> allItemList = getAllItemsFromAllPages(By.xpath("//ul//h2"));

        List<String> alphabeticalAllItemList = new ArrayList<>(allItemList);
        Collections.sort(alphabeticalAllItemList);

        Assert.assertEquals(allItemList, alphabeticalAllItemList, "Items are not in alphabetical order");
    }

    @Test(dataProvider = "provideAllItemCategory")
    public void testSortByPriceLowToHigh(String locator) {
        driver.findElement(By.xpath(locator)).click();

        WebElement dropdown = driver.findElement(By.xpath("//select[@name='orderby']"));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Sort by price: low to high");

        List<String> priceTextList = getAllItemsFromAllPages
                (By.xpath("//span[@class='price']/*[not(@aria-hidden='true')]"));

        List<Double> actualPriceList = getConvertedToDoublePriceList(priceTextList);

        List<Double> expectedLowToHighPriceList = new ArrayList<>(actualPriceList);
        Collections.sort(expectedLowToHighPriceList);

        Assert.assertEquals(actualPriceList, expectedLowToHighPriceList,
                "Prices are not sorted from low to high as expected");
    }

    @Test(dataProvider = "provideAllItemCategory")
    public void testSortByPriceHighToLow(String locator) {
        driver.findElement(By.xpath(locator)).click();

        WebElement dropdown = driver.findElement(By.xpath("//select[@name='orderby']"));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Sort by price: high to low");

        List<String> priceTextList = getAllItemsFromAllPages
                (By.xpath("//span[@class='price']/*[not(@aria-hidden='true')]"));

        List<Double> actualPriceList = getConvertedToDoublePriceList(priceTextList);

        List<Double> expectedLowToHighPriceList = new ArrayList<>(actualPriceList);
        expectedLowToHighPriceList.sort(Comparator.reverseOrder());

        Assert.assertEquals(actualPriceList, expectedLowToHighPriceList,
                "Prices are not sorted from low to high as expected");
    }
}

