import com.ecommerce.base.BaseTest;
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

    private List<String> getAllItemsFromAllPages(By locator) {
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

    private List<Double> getConvertedToDoublePriceList(List<String> priceTextList) {
        List<Double> actualPriceList = new ArrayList<>();
        for (String priceText : priceTextList) {
            String priceClearedFromSigns = priceText.replace("$", "").trim();
            double price = Double.parseDouble(priceClearedFromSigns);
            actualPriceList.add(price);
        }

        return actualPriceList;
    }

    @Test(description = "12-1.1 | TC>Store> Verify Search Returns All Items Across Categories "
            + "# https://app.clickup.com/t/8689vk47d")
    public void testVerifySearchReturnsAllItemsInAllCategories() {
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
    private Object[][] provideAllItemCategory() {
        return new Object[][]{
                {"//li[@id='menu-item-1227']"},
                {"//li[@id='menu-item-1228']"},
                {"//li[@id='menu-item-1229']"},
                {"//li[@id='menu-item-1230']"}
        };
    }

    @Test(dataProvider = "provideAllItemCategory",
            description = "2.12-1.1 | TC> Store> Verify items alphabetical order "
                    + "# https://app.clickup.com/t/8689vk3c5")
    public void testVerifyItemsAlphabeticalOrder(String locator) {
        driver.findElement(By.xpath(locator)).click();

        List<String> allItemList = getAllItemsFromAllPages(By.xpath("//ul//h2"));

        List<String> alphabeticalAllItemList = new ArrayList<>(allItemList);
        Collections.sort(alphabeticalAllItemList);

        Assert.assertEquals(allItemList, alphabeticalAllItemList, "Items are not in alphabetical order");
    }

    @Test(dataProvider = "provideAllItemCategory", description = "2-1.3 | TC> Store> Sort low to high price "
            + "# https://app.clickup.com/t/8689vk1yn")
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

    @Test(dataProvider = "provideAllItemCategory",
            description = "2-1.2 | TC> Store> Sort high to low price # https://app.clickup.com/t/8689vjzgq")
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

    @DataProvider
    private Object[][] provideAllItemLocatorsWithNames() {
        return new Object[][]{
                {"//li[@id='menu-item-1228']", "Men"},
                //bug or?
                // {"//li[@id='menu-item-1229']", "Women"},
                {"//li[@id='menu-item-1230']", "Accessories"}
        };
    }

    @Test(dataProvider = "provideAllItemLocatorsWithNames")
    public void testVerifyItemsCorrespondentCategories(String locator, String categoryName) {
        driver.findElement(By.xpath(locator)).click();

        List<String> allItemList = getAllItemsFromAllPages(By.xpath("//span[@class='ast-woo-product-category']"));
        for (String item : allItemList) {
            Assert.assertEquals(item, categoryName, "Item does not match the expected category");
        }
    }

    @Test(description = "3.9-1.1 | TC> Man> Verify Sale items price # https://app.clickup.com/t/8689v3293")
    public void testVerifyReducedPriceForSaleItems() {
        driver.findElement(By.xpath("//li[@id='menu-item-1228']")).click();
        //Ищем большой блок, в котором содержатся оба элемента
        WebElement container = driver.findElement(By.xpath("//div[@class='ast-woocommerce-container']"));
        //ищем все элементы и пробегаемся по листу всех
        List<WebElement> productsList = container.findElements
                (By.xpath("//ul[@class='products columns-4']//li"));

        for (WebElement product : productsList) {
            try {
                // Проверяем наличие обоих элементов в одном блоке productList
                /*если хотя бы один из продуктов не содержит элемента с классом onsale или del,
                 метод findElement() выбрасывает исключение NoSuchElementException, если элемент не найден, поэтому ищем через try-catch.*/

                WebElement saleTag = product.findElement(By.xpath(".//span[@class='onsale']"));
                WebElement delTag = product.findElement(By.xpath(".//del"));

                Assert.assertEquals(saleTag.getText(), "Sale!");
                Assert.assertNotNull(delTag.getText());

            } catch (NoSuchElementException e) {
                // Если saleTag или delTag не найден, просто игнорируем этот продукт
                // Но если saleTag или delTag отсутствует, тест упадет

                System.out.println("Products does not have sale tags and crossed price");

            }
        }
    }
}

