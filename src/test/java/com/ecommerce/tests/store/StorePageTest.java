package com.ecommerce.tests.store;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.HomePage;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class StorePageTest extends BaseTest {

    private static final By NEXT_PAGE_NUMBER = By.xpath("//a[@class='next page-numbers']");
    private static final By ITEM_CATEGORY_BELOW_PRICE = By.xpath("//span[@class='ast-woo-product-category']");

    private static List<String> getAllItemsFromAllPages(By locator, WebDriver driver) {
        List<String> allItemList = new ArrayList<>();
        boolean hasNextPage = true;

        while (hasNextPage) {
            List<WebElement> itemList = WaitUtils.visibilityOfAllElementsLocatedBy(driver, locator);
            for (WebElement item : itemList) {
                allItemList.add(item.getText());
            }

            try {
                WebElement nextPageArrow = driver.findElement(NEXT_PAGE_NUMBER);
                nextPageArrow.click();
            } catch (NoSuchElementException e) {
                hasNextPage = false;
            }
        }
        return allItemList;
    }

    @Test(description = "2.4 - 1 | TC Store > Click search button. # https://app.clickup.com/t/8689p8y50")
    public void testSearchButton() {
        String text = "shoes";

        driver.findElement(By.xpath("//li[@id = 'menu-item-1227']/a[@href = 'https://askomdch.com/store/']")).click();
        driver.findElement(By.id("woocommerce-product-search-field-0")).sendKeys(text);
        driver.findElement(By.xpath("//button[contains(text(), 'Search')]")).click();

        List<WebElement> productList = driver.findElements(By.xpath("//ul[@class = 'products columns-4']/li//h2"));

        List<String> productNames = new ArrayList<>();
        for (WebElement item : productList) {
            productNames.add(item.getText().toLowerCase());
        }

        Assert.assertTrue(productNames.stream()
                        .allMatch(name -> name.contains(text)),
                "No product contains the name " + text);
    }

    @Test(description = "2_15 | Store > Go to the next page. https://app.clickup.com/t/8689p8y36")
    public void testStoreGoToTheNextPage() {

        String cartUrl = "https://askomdch.com/cart/";
        driver.findElement(By.xpath("//a[@href='/store']")).click();

        WebElement buttonAddToCart = driver.findElement(By.xpath("//a[@href='?add-to-cart=1198']"));
        buttonAddToCart.click();

        WebElement buttonViewCart = driver.findElement(By.cssSelector("a.added_to_cart.wc-forward"));
        buttonViewCart.click();

        Assert.assertEquals(driver.getCurrentUrl(), cartUrl);

        List<WebElement> cartItems = driver.findElements(By.cssSelector("tr.woocommerce-cart-form__cart-item.cart_item"));

        if (cartItems.size() > 0) {
            Assert.assertTrue(true, "Товары отображаются в корзине");
        } else {
            Assert.fail("Товары не отображаются в корзине");
        }
    }

    @Test(description = "2.11-1.1 | TC > Store > See item's price.https://app.clickup.com/t/8689u8av6")

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


    @Test(description = "2.12-1.1 | TC> Store> Verify items alphabetical order # https://app.clickup.com/t/8689vk3c5")
    public void testVerifyItemsAlphabeticalOrder() {
        boolean isAlphabeticalOrder = new HomePage(driver)
                .navigateToStorePage()
                .areItemsInAlphabeticalOrder();

        Assert.assertTrue(isAlphabeticalOrder, "Items are not in alphabetical order");
    }

    @Test(description = "2.11-1.2 |TC > Store > See itemion's price in Browse by category # https://app.clickup.com/t/8689yq16m")
    public void checkPricesBrowseByCategory() {

        driver.findElement(By.xpath("//a[@href='/store']")).click();
        WebElement browseByCategoryField = driver.findElement(By.cssSelector("#product_cat"));

        // scroll down to the Browse category field
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", browseByCategoryField);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(browseByCategoryField));

        // Select the "Men" category
        Select select = new Select(browseByCategoryField);
        select.selectByValue("men");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ast-woocommerce-container .products")));

        List<WebElement> allItems = driver.findElements(By.cssSelector(".ast-woocommerce-container .products >li"));
        List<WebElement> priceElement = driver.findElements(By.xpath("//span[@class='price']/*[self::ins or self::span]"));

        //  assert that the price is displayed on the item
        priceElement.forEach(x -> {
            Assert.assertTrue(x.isDisplayed(), "The price is not displayed");
        });
        // assert that the displayed category is correct and string price is not empty or null
        allItems.forEach(item -> {
            String categoryMenText = item.findElement(By.cssSelector(".astra-shop-summary-wrap .ast-woo-product-category")).getText();
            String priceOnItem = item.findElement(By.xpath(".//span[@class='price']/ins | .//span[@class='price']/span")).getText();

            Assert.assertEquals("Men", categoryMenText, "Displayed category does not match selected 'Men' category");
            Assert.assertTrue(!priceOnItem.isEmpty() && priceOnItem != null, " The price String is Empty or null ");
        });
    }
}


