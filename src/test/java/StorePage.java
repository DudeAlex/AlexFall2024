import com.ecommerce.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
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



    @Test ( description = "2.11-1.1 | TC > Store > See item's price.https://app.clickup.com/t/8689u8av6")

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

