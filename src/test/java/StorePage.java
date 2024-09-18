import com.ecommerce.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class StorePage extends BaseTest {

    @Test
    public void testSearchButton() {
        String text = "shoes";

        driver.findElement(By.xpath("//li[@id = 'menu-item-1227']/a[@href = 'https://askomdch.com/store/']")).click();
        driver.findElement(By.id("woocommerce-product-search-field-0")).sendKeys(text);
        driver.findElement(By.xpath("//button[contains(text(), 'Search')]")).click();

        List<WebElement> productList = driver.findElements(By.xpath("//ul[@class = 'products columns-4']/li//h2"));

        List<String> productNames = new ArrayList<>();
        for (WebElement item: productList) {
            productNames.add(item.getText().toLowerCase());
        }

        Assert.assertTrue( productNames.stream()
                .allMatch(name -> name.contains(text)),
                "No product contains the name " + text);
    }

    @Test

    public void testCheckPrices() {
        driver.findElement(By.xpath("//li[@id = 'menu-item-1227']/a[@href = 'https://askomdch.com/store/']")).click();

        WebElement listOfItems = driver.findElement(By.xpath("//ul[@class='products columns-4']"));
        List<WebElement> listOfItemsSize = driver.findElements(By.xpath("//ul[@class='products columns-4']/li"));
        int totalItemsOnPage = listOfItemsSize.size();

        List<WebElement> listOfPricesOnSale = listOfItems.findElements(By.xpath("//ul[@class='products columns-4']//span[@class='price']/ins"));
        List<WebElement> listOfPricesWithoutSale = listOfItems.findElements(By.xpath("//ul[@class='products columns-4']//span[@class='price'][not(del[@aria-hidden='true'])]"));

        List<String> allPricesList = new ArrayList<>();
        listOfPricesOnSale.forEach(x -> allPricesList.add(x.getText()));
        listOfPricesWithoutSale.forEach(x -> allPricesList.add(x.getText()));

        // the quantity of items should match the quantity of items on the Store page
        Assert.assertEquals(allPricesList.size(), totalItemsOnPage,
                "The quantity of prices does not match the quantity of items on the Store page.");
       }
    }

