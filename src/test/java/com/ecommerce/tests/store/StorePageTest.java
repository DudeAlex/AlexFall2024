package com.ecommerce.tests.store;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.components.LeftSidebar;
import com.ecommerce.pom.pages.HomePage;
import com.ecommerce.pom.pages.StorePage;
import com.ecommerce.utils.JSExecutorUtils;
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

        StorePage storePage = new StorePage(driver);
        storePage.load();
        int totalItemsOnPage = storePage.getProductsGrid().getProductsList().size();
        int allPricesList = storePage.getAllProductsPriceList().size();

        // the quantity of items should match the quantity of items on the Store page
        Assert.assertEquals(allPricesList, totalItemsOnPage,
                "The quantity of prices does not match the quantity of items on the Store page.");
    }


    @Test(description = "2.12-1.1 | TC> Store> Verify items alphabetical order # https://app.clickup.com/t/8689vk3c5")
    public void testVerifyItemsAlphabeticalOrder() {
        boolean isAlphabeticalOrder = new HomePage(driver)
                .getHeader().navigateToStorePage()
                .areItemsInAlphabeticalOrder();

        Assert.assertTrue(isAlphabeticalOrder, "Items are not in alphabetical order");
    }

    @Test(description = "2.11-1.2 |TC > Store > See item's price in Browse by category # https://app.clickup.com/t/8689yq16m")
    public void checkPricesBrowseByCategory() {

        StorePage storePage = new StorePage(driver);
        storePage.load();
        LeftSidebar leftSidebar = new LeftSidebar(driver);
        JSExecutorUtils.scrollIntoView(driver, leftSidebar.getBrowseByCategoryInputField());
        leftSidebar.browseByCategory("Men");

        List<WebElement> priceElement = storePage.getAllProductsPriceElements();
        List <String> labelList = storePage.getProductsGrid().getProductCategoryLabel();

        priceElement.forEach(x ->
            Assert.assertTrue(x.isDisplayed(), "The price is not displayed"));

        labelList.forEach(label ->
        Assert.assertEquals("Men", label, "Displayed category does not match selected 'Men' category"));
    }
}


