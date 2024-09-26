package com.ecommerce.tests.store;

import com.ecommerce.base.BaseTest;
import com.ecommerce.data.ProductsData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StorePageTest extends BaseTest {

    private static List<String> getAllItemsFromAllPages(By locator, WebDriver driver) {
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

    private static List<Double> getConvertedToDoublePriceList(List<String> priceTextList) {
        List<Double> actualPriceList = new ArrayList<>();
        for (String priceText : priceTextList) {
            String priceClearedFromSigns = priceText.replace("$", "").trim();
            double price = Double.parseDouble(priceClearedFromSigns);
            actualPriceList.add(price);
        }

        return actualPriceList;
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

    @Test(description = "2.3-1.1 | Store > Search functionality for product> Positive Scenario – Searching for an Existing Product # https://app.clickup.com/t/8689p8y0v")
    public void testStorePageSearchExistProduct() {
        String text = "Jeans";

        // Переход на страницу магазина
        driver.findElement(By.xpath("//a[@href='/store']")).click();

        // Ожидание загрузки страницы магазина
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='s']")));

        // Ввод текста "Jeans" в поисковую строку
        WebElement searchBox = driver.findElement(By.xpath("//input[@name='s']"));
        searchBox.sendKeys(text);

        // Нажатие кнопки поиска
        WebElement searchButton = driver.findElement(By.xpath("//button[@value='Search']"));
        searchButton.click();

        // Ожидание загрузки результатов поиска
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".products .product")));

        // Получение списка найденных продуктов
        List<WebElement> products = driver.findElements(By.cssSelector(".products .product"));

        // Проверка, что найдены хотя бы 1 или более продуктов
        Assert.assertTrue(products.size() > 0, "No products were found for the search term 'Jeans'.");

        // Дополнительно: Проверка, что хотя бы один продукт содержит текст "Jeans" в названии
        boolean productFound = false;
        for (WebElement product : products) {
            WebElement productName = product.findElement(By.cssSelector(".woocommerce-loop-product__title"));
            if (productName.getText().toLowerCase().contains("jeans")) {
                productFound = true;
                break;
            }
        }
        Assert.assertTrue(productFound, "No product with the name containing 'Jeans' was found.");
    }

    @Test(description = "2.3_2.1 | Store > Search functionality for product> Negative Scenario – Searching for a Product That Is Not Available # https://app.clickup.com/t/8689tnf46")
    public void testStorePageSearchNonExistentProduct() {
        String text = "Jacket";
        // Переход на страницу магазина
        driver.findElement(By.xpath("//a[@href='/store']")).click();

        // Ожидание загрузки страницы магазина
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='s']")));

        // Ввод текста "Jacket" в поисковую строку
        WebElement searchBox = driver.findElement(By.xpath("//input[@name='s']"));
        searchBox.sendKeys(text);

        // Нажатие кнопки поиска
        WebElement searchButton = driver.findElement(By.xpath("//button[@value='Search']"));
        searchButton.click();

        // Ожидание отображения результатов поиска
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".woocommerce-info")));

        // Получение сообщения о том, что товары не найдены
        WebElement noProductsMessage = driver.findElement(By.cssSelector(".woocommerce-info"));

        // Проверка, что сообщение содержит текст "No products were found matching your selection."
        String expectedMessage = "No products were found matching your selection.";
        Assert.assertEquals(noProductsMessage.getText(), expectedMessage, "The message about no products found is incorrect.");
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

    @Test(description = "2.3-1.2.1 | TC > Store > Search functionality for product > Case Insensitivity > Uppercase Letters # https://app.clickup.com/t/8689u4rkc")

    public void testStorePageSearchUppercaseProduct() {

        String searchText = "JEANS";

        // Переход на страницу магазина
        driver.findElement(By.xpath("//a[@href='/store']")).click();

        // Ожидание загрузки страницы магазина
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='s']")));

        // Ввод текста "JEANS" (все заглавные буквы) в поисковую строку
        WebElement searchBox = driver.findElement(By.xpath("//input[@name='s']"));
        searchBox.sendKeys(searchText);

        // Нажатие кнопки поиска
        WebElement searchButton = driver.findElement(By.xpath("//button[@value='Search']"));
        searchButton.click();

        // Ожидание отображения результатов поиска
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".products .product")));

        // Получение списка продуктов
        List<WebElement> products = driver.findElements(By.cssSelector(".products .product"));

        // Проверка, что результаты поиска найдены (список продуктов не пуст)
        Assert.assertTrue(products.size() > 0, "No products were found for the search term.");

        // Проверка, что заголовки продуктов содержат слово "Jeans" (регистр не учитывается)
        boolean allProductsContainJeans = products.stream()
                .allMatch(product -> product.getText().toLowerCase().contains("jeans"));

        Assert.assertTrue(allProductsContainJeans, "Not all products match the search term 'Jeans'.");
    }

    @Test(description = "2.3-1.2.2 | TC > Store > Search functionality for product > Case Insensitivity > Lowercase Letters # https://app.clickup.com/t/8689u4zdd")

    public void testStorePageSearchLowercaseProduct() {
        String searchText = "jeans";

        // Переход на страницу магазина
        driver.findElement(By.xpath("//a[@href='/store']")).click();

        // Ожидание загрузки страницы магазина
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='s']")));

        // Ввод текста "jeans" (все строчные буквы) в поисковую строку
        WebElement searchBox = driver.findElement(By.xpath("//input[@name='s']"));
        searchBox.sendKeys(searchText);

        // Нажатие кнопки поиска
        WebElement searchButton = driver.findElement(By.xpath("//button[@value='Search']"));
        searchButton.click();

        // Ожидание отображения результатов поиска
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".products .product")));

        // Получение списка продуктов
        List<WebElement> products = driver.findElements(By.cssSelector(".products .product"));

        // Проверка, что результаты поиска найдены (список продуктов не пуст)
        Assert.assertTrue(products.size() > 0, "No products were found for the search term.");

        // Проверка, что заголовки продуктов содержат слово "Jeans" (регистр не учитывается)
        boolean allProductsContainJeans = products.stream()
                .allMatch(product -> product.getText().toLowerCase().contains("jeans"));

        Assert.assertTrue(allProductsContainJeans, "Not all products match the search term 'Jeans'.");
    }

    @Test(description = "2.3-1.2.3 | TC > Store > Search functionality for product > Case Insensitivity > Combination of Uppercase and Lowercase Letters # https://app.clickup.com/t/8689u541z")
    public void testStorePageSearchCombiСaseProduct() {
        String searchText = "JeAnS";

        // Переход на страницу магазина
        driver.findElement(By.xpath("//a[@href='/store']")).click();

        // Ожидание загрузки страницы магазина
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='s']")));

        // Ввод текста "JeAnS" (комбинацию заглавных и строчных букв) в поисковую строку
        WebElement searchBox = driver.findElement(By.xpath("//input[@name='s']"));
        searchBox.sendKeys(searchText);

        // Нажатие кнопки поиска
        WebElement searchButton = driver.findElement(By.xpath("//button[@value='Search']"));
        searchButton.click();

        // Ожидание отображения результатов поиска
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".products .product")));

        // Получение списка продуктов
        List<WebElement> products = driver.findElements(By.cssSelector(".products .product"));

        // Проверка, что результаты поиска найдены (список продуктов не пуст)
        Assert.assertTrue(products.size() > 0, "No products were found for the search term.");

        // Проверка, что заголовки продуктов содержат слово "Jeans" (регистр не учитывается)
        boolean allProductsContainJeans = products.stream()
                .allMatch(product -> product.getText().toLowerCase().contains("jeans"));

        Assert.assertTrue(allProductsContainJeans, "Not all products match the search term 'Jeans'.");
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

    @Test(description = "2.3-3 | TC > Store > Search functionality for product> Empty Search Field (Edge Case) # https://app.clickup.com/t/8689uckje")
    public void testStorePageEmptySearchShowsAllProducts() {
        // Переход на страницу магазина
        driver.findElement(By.xpath("//a[@href='/store']")).click();

        // Ожидание загрузки страницы магазина
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='s']")));

        // Оставляем поле поиска пустым
        WebElement searchBox = driver.findElement(By.xpath("//input[@name='s']"));
        searchBox.clear(); // Очищаем поле на случай, если там что-то уже было

        // Нажатие кнопки поиска
        WebElement searchButton = driver.findElement(By.xpath("//button[@value='Search']"));
        searchButton.click();

        // Ожидание отображения списка всех продуктов
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".products .product")));

        // Получение списка продуктов
        List<WebElement> products = driver.findElements(By.cssSelector(".products .product"));

        // Проверка, что список продуктов не пуст (должны отобразиться все доступные продукты)
        Assert.assertTrue(products.size() > 0, "No products were found when searching with an empty search field.");

        // Дополнительная проверка, что на странице отображены все доступные продукты, известно, что общее количество продуктов 13, но на первой странице у меня помещается 8:
        int expectedProductCount = 8;
        Assert.assertEquals(products.size(), expectedProductCount, "The number of displayed products does not match the expected count.");
    }

    @Test(description = "2.3-2.2 | TC > Store > Search functionality for product> Negative Scenario - Search with Special Characters # https://app.clickup.com/t/8689uev2u")
    public void testStorePageSearchWithSpecialCharacters() {
        String searchText = "@#$$%";

        // Переход на страницу магазина
        driver.findElement(By.xpath("//a[@href='/store']")).click();

        // Ожидание загрузки страницы магазина
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='s']")));

        // Ввод специальных символов в поисковую строку
        WebElement searchBox = driver.findElement(By.xpath("//input[@name='s']"));
        searchBox.sendKeys(searchText);

        // Нажатие кнопки поиска
        WebElement searchButton = driver.findElement(By.xpath("//button[@value='Search']"));
        searchButton.click();

        // Ожидание появления сообщения о том, что товары не найдены
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".woocommerce-info")));

        // Получение сообщения о том, что товары не найдены
        WebElement noProductsMessage = driver.findElement(By.cssSelector(".woocommerce-info"));

        // Проверка, что сообщение содержит текст "No products were found matching your selection."
        String expectedMessage = "No products were found matching your selection.";
        Assert.assertEquals(noProductsMessage.getText(), expectedMessage, "The message about no products found is incorrect.");
    }

    @Test(dataProvider = "provideAllItemCategory", dataProviderClass = ProductsData.class,
            description = "2.12-1.1 | TC> Store> Verify items alphabetical order # https://app.clickup.com/t/8689vk3c5")
    public void testVerifyItemsAlphabeticalOrder(String category) {
        driver.findElement(By.xpath("//div[@id='ast-desktop-header']//a[text()='" + category + "']")).click();

        List<String> allItemList = getAllItemsFromAllPages(By.xpath("//ul//h2"), driver);

        List<String> alphabeticalAllItemList = new ArrayList<>(allItemList);
        Collections.sort(alphabeticalAllItemList);

        Assert.assertEquals(allItemList, alphabeticalAllItemList, "Items are not in alphabetical order");
    }

    @Test(dataProvider = "provideAllItemCategory", dataProviderClass = ProductsData.class,
            description = "2-1.3 | TC> Store> Sort low to high price # https://app.clickup.com/t/8689vk1yn")
    public void testSortByPriceLowToHigh(String category) {
        driver.findElement(By.xpath("//div[@id='ast-desktop-header']//a[text()='" + category + "']")).click();

        WebElement dropdown = driver.findElement(By.xpath("//select[@name='orderby']"));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Sort by price: low to high");

        List<String> priceTextList = getAllItemsFromAllPages(
                By.xpath("//span[@class='price']/*[not(@aria-hidden='true')]"), driver);

        List<Double> actualPriceList = getConvertedToDoublePriceList(priceTextList);

        List<Double> expectedLowToHighPriceList = new ArrayList<>(actualPriceList);
        Collections.sort(expectedLowToHighPriceList);

        Assert.assertEquals(actualPriceList, expectedLowToHighPriceList,
                "Prices are not sorted from low to high as expected");
    }

    @Test(dataProvider = "provideAllItemCategory", dataProviderClass = ProductsData.class,
            description = "2-1.2 | TC> Store> Sort high to low price # https://app.clickup.com/t/8689vjzgq")
    public void testSortByPriceHighToLow(String category) {
        driver.findElement(By.xpath("//div[@id='ast-desktop-header']//a[text()='" + category + "']")).click();

        WebElement dropdown = driver.findElement(By.xpath("//select[@name='orderby']"));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Sort by price: high to low");

        List<String> priceTextList = getAllItemsFromAllPages
                (By.xpath("//span[@class='price']/*[not(@aria-hidden='true')]"), driver);

        List<Double> actualPriceList = getConvertedToDoublePriceList(priceTextList);

        List<Double> expectedLowToHighPriceList = new ArrayList<>(actualPriceList);
        expectedLowToHighPriceList.sort(Comparator.reverseOrder());

        Assert.assertEquals(actualPriceList, expectedLowToHighPriceList,
                "Prices are not sorted from low to high as expected");
    }

    //testVerifyItemsCorrespondentCategories[Women] will fail, bug?!
    @Test(dataProvider = "provideAllItemCategory", dataProviderClass = ProductsData.class)
    public void testVerifyItemsCorrespondentCategories(String category) {
        driver.findElement(By.xpath("//div[@id='ast-desktop-header']//a[text()='" + category + "']")).click();

        List<String> allItemList = getAllItemsFromAllPages(
                By.xpath("//span[@class='ast-woo-product-category']"), driver);
        for (String item : allItemList) {
            Assert.assertEquals(item, category, "Item does not match the expected category");
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

    @Test(description = "2.3-2-3 | TC > Store > Search functionality for product> Negative Scenario - Search Error Handling | https://app.clickup.com/t/8689y3uy0")
    public void testStorePageSearchErrorHandling() {

        String searchText = "askMeaskMeasaskMeaskMeaskMekMeaskMeaskMe";
        String expectedMessage = "No products were found matching your selection.";

        driver.findElement(By.xpath("//a[@href='/store']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='s']")));

        Assert.assertEquals(searchBox.getAttribute("value"), "", "Search box is not empty before test.");

        searchBox.sendKeys(searchText);
        System.out.println("Entered search text: " + searchText);

        WebElement searchButton = driver.findElement(By.xpath("//button[@value='Search']"));
        searchButton.click();
        System.out.println("Search button clicked.");

        WebElement noProductsMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".woocommerce-info")));

        Assert.assertEquals(noProductsMessage.getText(), expectedMessage, "The message about no products found is incorrect.");
        System.out.println("Verified no products message: " + noProductsMessage.getText());
        }

}


