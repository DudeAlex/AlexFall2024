import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.*;

public class FirstTest extends BaseTest {

    private static final String LOGIN_TEST = "Testlogin";
    private static final String PASSWORD_TEST = "Testpassword";
    private static final String EMAIL_TEST = "test@gmail.com";

    @Test
    public void testFirst() throws InterruptedException {
        String text = driver.findElement(By.tagName("h1")).getText();

        Assert.assertEquals(text, "AskOmDch");
    }

    @Test
    public void testSecond() throws InterruptedException {
        driver.findElement(By.xpath("//a[@href='/store']")).click();
        String text = driver.findElement(By.tagName("h1")).getText();

        Assert.assertEquals(text, "Store");
    }

    @Test
    public void testShoppingCartIconValidationTest() {
        driver.findElement(By.xpath("//a[@class='wp-block-button__link']")).click();
        driver.findElement(By.xpath("//a[contains(@href, '?add-to-cart=1205')]")).click();
        driver.findElement(By.xpath("//div[@class='site-primary-header-wrap ast-builder-grid-row-container site-header-focus-item ast-container']//span[@class='count'][normalize-space()='1']")).click();
        String text = driver.findElement(By.xpath("//a[normalize-space()='Basic Blue Jeans']")).getText();

        Assert.assertEquals(text, "Basic Blue Jeans");

    }

    @Test
    public void ShoppingCartPopUpValidationTest() {
        driver.findElement(By.xpath("//a[@class='wp-block-button__link']")).click();
        driver.findElement(By.xpath("//a[@aria-label='Add “Anchor Bracelet” to your cart']")).click();
        WebElement shoppingCart = driver.findElement(By.xpath("//div[@class='site-primary-header-wrap ast-builder-grid-row-container site-header-focus-item ast-container']//span[@class='count'][normalize-space()='1']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(shoppingCart).perform();
        WebElement viewCart = driver.findElement(By.xpath("//div[@class='site-primary-header-wrap ast-builder-grid-row-container site-header-focus-item ast-container']//a[@class='button wc-forward'][normalize-space()='View cart']"));
        viewCart.click();
        String text = driver.findElement(By.xpath("//a[normalize-space()='Anchor Bracelet']")).getText();

        Assert.assertEquals(text, "Anchor Bracelet");

    }

    @Test
    public void testClickOnStoreButton() throws InterruptedException {
        String url = "https://askomdch.com/store";

        driver.findElement(By.xpath("//a[@href='/store']")).click();

        String actualResult = driver.getCurrentUrl();

        Assert.assertEquals(actualResult, url);

    }

    @Test
    public void testFeaturedProductAddToCart() throws InterruptedException {
        String testProduct = "Anchor Bracelet";

        driver.findElement(By.xpath("//a[contains(@aria-label, '" + testProduct + "')]")).click();

        driver.findElement(By.xpath("//a[contains(@title,'View cart')]")).click();

        String actualResult = driver.findElement(By.xpath("//td[@data-title='Product']")).getText();

        Assert.assertEquals(actualResult, testProduct);

    }

    @Test
    public void testCheckButtonsName() {
        List<WebElement> buttons = driver.findElements(By.cssSelector(".wp-block-buttons .wp-block-button__link"));

        List<String> expectedButtonNames = new ArrayList<>();
        expectedButtonNames.add("SHOP NOW");
        expectedButtonNames.add("FIND MORE");

        List<String> actualButtonNames = new ArrayList<>();
        actualButtonNames.add(buttons.get(0).getText().trim());
        actualButtonNames.add(buttons.get(1).getText().trim());

        Assert.assertEquals(actualButtonNames, expectedButtonNames, "Button names do not match!");
    }

    @Test
    public void testCheckButtonsClickRedirect() {
        List<WebElement> buttons = driver.findElements(By.cssSelector(".wp-block-buttons .wp-block-button__link"));
        buttons.get(0).click();
        String StorePageName = driver.findElement(By.tagName("h1")).getText().trim();
        String ExpectedStorePageName = "Store";

        Assert.assertEquals(StorePageName, ExpectedStorePageName, "Store page names do not match!");

        driver.navigate().back();
        buttons.get(1).click();
        String ContactUsPageName = driver.findElement(By.tagName("h1")).getText().trim();
        String ExpectedContactUs = "Contact Us";

        Assert.assertEquals(ContactUsPageName, ExpectedContactUs, "Contact Us page names do not match!");

        driver.navigate().back();
        buttons.get(2).click();
        String WomenPageName = driver.findElement(By.tagName("h1")).getText().trim();
        String ExpectedWomen = "Women";

        Assert.assertEquals(WomenPageName, ExpectedWomen, "Women page names do not match!");

        driver.navigate().back();
        buttons.get(3).click();
        String MenPageName = driver.findElement(By.tagName("h1")).getText().trim();
        String ExpectedMen = "Men";

        Assert.assertEquals(ContactUsPageName, ExpectedContactUs, "Men page names do not match!");

        driver.navigate().back();
        buttons.get(4).click();
        String AccessoriesName = driver.findElement(By.tagName("h1")).getText().trim();
        String ExpectedAccessories = "Accessories";

        Assert.assertEquals(AccessoriesName, ExpectedAccessories, "Accessories page names do not match!");

        driver.navigate().back();
        String MainPageName = driver.findElement(By.tagName("h1")).getText().trim();
        String ExpectedMainPage = "AskOmDch";

        Assert.assertEquals(MainPageName, ExpectedMainPage, "Main page names do not match!");
    }

    @Test
    public void testHomeButtonText() {

        String expectedButtonName = driver.findElement(By.id("menu-item-1226"))
                .getText();

        Assert.assertTrue(expectedButtonName.contains("Home"));
    }

    @Test
    public void testAddShoesToCartConfirmationMessage() {

        driver.findElement(By.linkText("Blue Shoes")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        String actualNotice = driver.findElement(By.className("woocommerce-message")).getText();

        Assert.assertTrue(actualNotice.contains("“Blue Shoes” has been added to your cart."));
    }

    @Test
    public void testAddShortsToCartAndCheckout() {

        driver.findElement(By.xpath("//a[@href='/store']")).click();
        driver.findElement(By.xpath("//*[@id='main']//li[4]")).click();
        driver.findElement(By.className("woocommerce-product-gallery__trigger")).click();
        driver.findElement(By.xpath("//button[@class='pswp__button pswp__button--close']")).click();
        driver.findElement(By.xpath("//button[@name='add-to-cart']")).click();
        driver.findElement(By.xpath("//div[@class='woocommerce-message']/a[.='View cart']")).click();

        String actualProductName = driver.findElement(By.linkText("Blue Denim Shorts")).getText();

        Assert.assertEquals(actualProductName, "Blue Denim Shorts");
    }

    @DataProvider(name = "navigationData")
    public Object[][] getNavigationMenuData() {
        return new Object[][]{
                {"Home", "https://askomdch.com/", "AskOmDch – Become a Selenium automation expert!"},
                {"Store", "https://askomdch.com/store/", "Products – AskOmDch"},
                {"Men", "https://askomdch.com/product-category/men/", "Men – AskOmDch"},
                {"Women", "https://askomdch.com/product-category/women/", "Women – AskOmDch"},
                {"Accessories", "https://askomdch.com/product-category/accessories/", "Accessories – AskOmDch"},
                {"Account", "https://askomdch.com/account/", "Account – AskOmDch"},
                {"About", "https://askomdch.com/about/", "About – AskOmDch"},
                {"Contact Us", "https://askomdch.com/contact-us/", "Contact Us – AskOmDch"}
        };
    }

    @Test(dataProvider = "navigationData")
    public void testNavigationMenu(String linkText, String url, String title) {
        driver.findElement(By.linkText(linkText)).click();

        Assert.assertEquals(url, driver.getCurrentUrl());
        Assert.assertEquals(title, driver.getTitle());

    }

    @Test

    public void testKet() throws Exception {

        driver.findElement(By.xpath("//a[@href='/store']")).click();

        driver.findElement(By.xpath("//a[@href='?add-to-cart=1198']")).click();

        driver.findElement(By.xpath("//a[@class='cart-container']")).click();

        //Assert.assertEquals(name,"Anchor Bracelet");
    }

    @Test
    public void testAddProductToCart() {
        driver.findElement(By.xpath("//a[@class= 'wp-block-button__link']")).click();
        driver.findElement(By.linkText("Basic Blue Jeans")).click();
        String itemName = driver.findElement(By.tagName("h1")).getText();

        driver.findElement(By.xpath("//button[@name='add-to-cart']")).click();
        driver.findElement(By.xpath("(//a[@title='View your shopping cart'])[1]")).click();
        String itemInTheCart = driver.findElement(By.xpath("//td[@class = 'product-name']/a")).getText();

        Assert.assertEquals(itemName, itemInTheCart);
    }

    /* ANNA TEST - START */
    @Test
    public void testAddAndRemoveSingleItemFromCart() {
        driver.findElement(By.xpath("//div[@id='ast-desktop-header']//a[text()='Store']")).click();

        driver.findElement(By.xpath("//div[@class='astra-shop-summary-wrap']//a[text()='Add to cart']")).click();
        WebElement viewCart = driver.findElement(By.linkText("View cart"));
        String viewCartText = viewCart.getText();

        Assert.assertEquals(viewCartText, "View cart");

        viewCart.click();
        driver.findElement(By.xpath("//a[@class = 'remove']")).click();

        String itemRemovedMassage = driver.findElement(By.xpath("//*[contains(text(),'removed')]")).getText();
        Assert.assertTrue(itemRemovedMassage.contains("removed"));
    }

    @Test
    public void testUpdateQuantityInCart() {
        driver.findElement(By.xpath("//div[@id='ast-desktop-header']//a[text()='Store']")).click();

        driver.findElement(By.xpath("//div[@class='astra-shop-summary-wrap']//a[text()='Add to cart']")).click();
        WebElement viewCart = driver.findElement(By.linkText("View cart"));
        String viewCartText = viewCart.getText();

        Assert.assertEquals(viewCartText, "View cart");

        viewCart.click();

        String priceStr = driver.findElement(By.xpath("//td[@class = 'product-subtotal']/span[@class='woocommerce-Price-amount amount']")).getText();
        if (priceStr.charAt(0) == '$') { //since the price is a string that has $, I need to remove the $ first
            priceStr = priceStr.substring(1); //creating the substring without $
        }
        double price = Double.valueOf(priceStr); //change string price to double

        WebElement quantity = driver.findElement(By.xpath("//input[@class='input-text qty text']"));
        quantity.click();
        quantity.clear();
        quantity.sendKeys("2");

        String updatedPriceString = driver.findElement(By.xpath("//td[@class = 'product-subtotal']/span[@class='woocommerce-Price-amount amount']")).getText();
        driver.findElement(By.xpath("//button[@name='update_cart']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String finalUpdatedPriceStr = updatedPriceString;
        // After the clicking Update price button, I need wait until the price will be updated
        wait.until(driver -> {
            String checkPrice = driver.findElement(By.xpath("//td[@class = 'product-subtotal']/span[@class='woocommerce-Price-amount amount']")).getText();
            if (checkPrice.equals(finalUpdatedPriceStr)) { // checking if price still equal to original price (before update)
                return false;
            }
            return true;  //when price is updated (not equal anymore) returning true
        });

        //  Getting the updated subtotal price
        String updatedPriceStr = driver.findElement(By.xpath("//td[@class = 'product-subtotal']/span[@class='woocommerce-Price-amount amount']")).getText();
        if (updatedPriceStr.charAt(0) == '$') {
            updatedPriceStr = updatedPriceStr.substring(1);
        }

        double updatedPrice = Double.valueOf(updatedPriceStr);
        Assert.assertEquals(updatedPrice, (price * 2));
    }

    /* ANNA TEST - END */

    @Test
    public void testSearchForJeans() {
        driver.findElement(By.xpath("//a[@href='/store']")).click();
        WebElement searchBox = driver.findElement(By.xpath("//input[@name='s']"));
        searchBox.sendKeys("jeans");

        WebElement searchButton = driver.findElement(By.xpath("//button[@value='Search']"));
        searchButton.click();

        List<WebElement> products = driver.findElements(By.cssSelector(".products .product"));

        Assert.assertEquals(products.size(), 5, "The number of products displayed is not 5");
    }

    @Test
    public void testFilteredPrices() {
        int min = 20;
        int max = 50;
        String setMinValue = "arguments[0].value='" + min + "';";
        String setMaxValue = "arguments[0].value='" + max + "';";
        List<WebElement> currentPricesElements;
        List<String> texts = new ArrayList<>();
        List<Double> prices = new ArrayList<>();
        JavascriptExecutor jse = ((JavascriptExecutor) driver);

        driver.findElement(By.cssSelector("a[href*='/store'")).click();

        jse.executeScript("window. scrollBy(0,550)", "");
        jse.executeScript(setMinValue, driver.findElement(By.id("min_price")));
        jse.executeScript(setMaxValue, driver.findElement(By.id("max_price")));

        driver.findElement(By.cssSelector("div:has(#max_price)>button")).click();

        currentPricesElements = driver.findElements(
                By.xpath("//span[@class='price']/ins | //span[@class='price']/span"));

        currentPricesElements.forEach(el -> texts.add(el.getText()));
        texts.forEach(text -> prices.add(Double.parseDouble(text.replace("$", ""))));
        prices.forEach(price -> {
            Assert.assertTrue(price >= min && price <= max,
                    "The price $" + price + " is not within the range of $" + min + " to $" + max + ".");
        });
    }

    @Test
    public void testSearchFieldInput() {

        String URL_BOHO = "https://askomdch.com/product/boho-bangle-bracelet/";

        WebElement shopNowButton = driver.findElement(By.className("wp-block-button"));
        shopNowButton.click();
        WebElement searchField = driver.findElement(By.className("search-field"));
        searchField.sendKeys(("boho"));
        WebElement searchButton = driver.findElement(By.xpath("//button[@value='Search']"));
        searchButton.click();
        String bohoBracelet = driver.findElement(By.tagName("h1")).getText();

        Assert.assertEquals("Boho Bangle Bracelet", bohoBracelet);
        Assert.assertEquals(driver.getCurrentUrl(), URL_BOHO);
    }

    @Test
    public void testBrowseByCategoriesDropdownItems() {
        WebElement shopNowButton = driver.findElement(By.className("wp-block-button"));
        shopNowButton.click();
        driver.findElement(By.className("dropdown_product_cat")).click();

        List<String> expectedProductCategories = new ArrayList<>(Arrays.asList("Accessories  (3)", "Men  (7)"
                , "Men’s Jeans  (4)", "Men’s Shirts  (1)", "Men’s Shoes  (1)", "Purses And Handbags  (1)", "Women  (7)"
                , "Women’s Jeans  (2)", "Women’s Shirts  (1)", "Women’s Shoes  (1)"));

        List<WebElement> productCategories = driver.findElements(By.xpath("//*[@class='level-0']"));
        List<String> actualProductCategories = WebElementToString(productCategories);

        Assert.assertEquals(actualProductCategories, expectedProductCategories);
    }

    public static List<String> WebElementToString(List<WebElement> elementList) {
        List<String> stringList = new ArrayList<>();
        for (WebElement element : elementList) {
            stringList.add(element.getText());
        }
        return stringList;
    }


    @Test
    public void testNumberLinksOnStorePage() {
        driver.findElement(By.xpath("//a[@href='/store']")).click();
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Total number of links: " + links.size());

        Assert.assertEquals(links.size(), 68, "The number of links is not 68");
    }

    @Test
    public void testNumberImagesOnStorePage() {
        driver.findElement(By.xpath("//a[@href='/store']")).click();
        List<WebElement> images = driver.findElements(By.tagName("img"));
        System.out.println("Total number of images: " + images.size());

        Assert.assertEquals(images.size(), 13, "The number of images is not 13");
    }

    @Test
    public void emptyPasswordFieldErrorMessage() {
        String emptyPasswordErrorMsg = "Error: The password field is empty.";
        String email = "qae15355@gmail.com";

        driver.findElement(By.xpath("//a[contains(text(), 'Account')]")).click();
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys(email);
        driver.findElement(By.xpath("//button[@name='login']")).click();
        String errorText = driver.findElement(By.xpath("//ul[@class='woocommerce-error']")).getText();

        Assert.assertEquals(errorText, emptyPasswordErrorMsg);
    }

    @Test
    public void emptyUserNameFieldErrorMessage() {
        String emptyUserNameErrorMsg = "Error: Username is required.";
        String password = "1111";

        driver.findElement(By.xpath("//a[contains(text(), 'Account')]")).click();
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@name='login']")).click();
        String errorText = driver.findElement(By.xpath("//ul[@class='woocommerce-error']")).getText();

        Assert.assertEquals(errorText, emptyUserNameErrorMsg);
    }

    @Test
    public void invalidUserNameErrorMessage() {
        String invalidUserName = "11111";
        String password = "1111";
        String invalidUserNameErrorMsgTemplate = "Error: The username %s is not registered on this site. If you are unsure of your username, try your email address instead.";
        String invalidUserNameErrorMsg = String.format(invalidUserNameErrorMsgTemplate, invalidUserName);

        driver.findElement(By.xpath("//a[contains(text(), 'Account')]")).click();
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys(invalidUserName);
        driver.findElement(By.xpath("//button[@name='login']")).click();
        String errorText = driver.findElement(By.xpath("//ul[@class='woocommerce-error']")).getText();

        Assert.assertEquals(errorText, invalidUserNameErrorMsg);
    }

    @Test
    public void invalidEmailErrorMessage() {
        String invalidEmailErrorMsg = "Unknown email address. Check again or try your username.";
        String invalidEmail = "q@gmai.co";
        String password = "1111";

        driver.findElement(By.xpath("//a[contains(text(), 'Account')]")).click();
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys(invalidEmail);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@name='login']")).click();
        String errorText = driver.findElement(By.xpath("//ul[@class='woocommerce-error']")).getText();

        Assert.assertEquals(errorText, invalidEmailErrorMsg);
    }

    @Test
    public void check20offPriceOnHoePageOnHoePage() {
        List<WebElement> products = driver.findElements(By.cssSelector("ul.products li.product"));

        for (WebElement product : products) {
            if (product.findElements(By.className("onsale")).size() > 0) {

                WebElement oldPriceElement = product.findElement(By.cssSelector("del .woocommerce-Price-amount"));
                WebElement newPriceElement = product.findElement(By.cssSelector("ins .woocommerce-Price-amount"));

                double oldPrice = Double.parseDouble(oldPriceElement.getText().replace("$", ""));
                double newPrice = Double.parseDouble(newPriceElement.getText().replace("$", ""));

                Assert.assertTrue(newPrice < oldPrice, "New price less then old");
                System.out.println("Off 20%: " + product.getText());
            } else {
                System.out.println("Without Off: " + product.getText());
            }
        }
    }

    @Test
    public void testProductNames() {
        driver.findElement(By.xpath("//a[@href='/store']")).click();
        List<WebElement> products = driver.findElements(By.xpath("//h2[@class='woocommerce-loop-product__title']"));
        List<String> expectedProductNames = Arrays.asList(
                "Anchor Bracelet",
                "Basic Blue Jeans",
                "Black Over-the-shoulder Handbag",
                "Blue Denim Shorts",
                "Blue Shoes",
                "Blue Tshirt",
                "Boho Bangle Bracelet",
                "Dark Brown Jeans"
        );
        List<String> actualProductNames = products.stream()
                .map(WebElement::getText)
                .toList();
        Assert.assertTrue(expectedProductNames.containsAll(actualProductNames),
                "The product names on the page don't meet expectations");
    }

    @Test
    public void testSearchFieldInputFromTheMenuStore() {

        String URL_t_shirt = "https://askomdch.com/product/blue-tshirt/";

        driver.findElement(By.xpath("//a[@href='/store']")).click();
        WebElement searchBox = driver.findElement(By.xpath("//input[@type='search']"));
        searchBox.sendKeys("Blue Tshirt");

        WebElement searchButton = driver.findElement(By.xpath("//button[@value='Search']"));
        searchButton.click();
        String t_shirt = driver.findElement(By.tagName("h1")).getText();

        Assert.assertEquals("Blue Tshirt", t_shirt);
        Assert.assertEquals(driver.getCurrentUrl(), URL_t_shirt);
    }

    @Test
    public void testFilterAccessoriesItem() {
        driver.findElement(By.id("menu-item-1230")).click();
        driver.findElement(By.xpath("//select[@name='orderby']"));
        driver.findElement(By.xpath("//option[text() ='Sort by average rating']")).click();

        String currentUrl = driver.getCurrentUrl();
        String checkUrlEnding = "?orderby=rating";

        Assert.assertTrue(currentUrl.endsWith(checkUrlEnding), "URL does not end with the expected endpoint: " + checkUrlEnding);
    }

    @Test
    public void testFilterWomenByPopularity() {
        driver.findElement(By.cssSelector("#menu-item-1229")).click();
        driver.findElement(By.xpath("//select[@name = 'orderby']")).click();
        driver.findElement(By.xpath("//option[contains(text(), 'popularity')]")).click();

        String currentUrl = driver.getCurrentUrl();
        String expectedUrlEnding = "?orderby=popularity";

        Assert.assertTrue(currentUrl.endsWith(expectedUrlEnding), "URL does not end with expected endpoint: " + expectedUrlEnding);
    }

    @Test
/*    The test verifies that items in the shop are ordered in the descending order (from high to low) according to
      their prices, when "Sort by price: high to low" option is chosen in the drop-down menu on the "Store" page.
 */
    public void testSortingItemsByPrice() {
//      Going from the Home page to the "Store" page and finding the dropdown menu
        driver.findElement(By.xpath("//a[@class= 'wp-block-button__link']")).click();
        WebElement dropdown = driver.findElement(By.xpath("//select[@name='orderby']"));

//      Selecting the menu option that we need for this test
        Select select = new Select(dropdown);
        select.selectByVisibleText("Sort by price: high to low");

//      Creating a list of all items shown on the page 1 of the Store after the sorting; Then, initializing
//      the array where all the prices will be added
        List<WebElement> allProductsPage1 = driver.findElements(By.xpath("//span[@class='price']"));
        List<String> allPrices = new ArrayList<>();

//      Collecting the prices into the array. If there is a discount, the discounted price is taken (try block)
//      If there is no discount, regular price is taken
        for (WebElement product : allProductsPage1) {
            try {
                String discountedPrice = product.findElement(By.xpath(".//ins")).getText();
                allPrices.add(discountedPrice);
            } catch (NoSuchElementException e) {
                String regularPrice = product.getText();
                allPrices.add(regularPrice);
            }
        }

//      Going to the 2nd page of the webstore
        driver.findElement(By.xpath("//a[@class='next page-numbers']")).click();

//      Creating a list with all the products on the 2nd page
        List<WebElement> allProductsPage2 = driver.findElements(By.xpath("//span[@class='price']"));

//      Collecting all the prices from the 2nd page to the 'allPrices' array
        for (WebElement product : allProductsPage2) {
            try {
                String discountedPrice = product.findElement(By.xpath(".//ins")).getText();
                allPrices.add(discountedPrice);
            } catch (NoSuchElementException e) {
                String regularPrice = product.getText();
                allPrices.add(regularPrice);
            }
        }

//      Initializing the list of Double values to put prices in the numeric type to it
        List<Double> pricesNumeric = new ArrayList<>();

//      Parcing String prices to Double format, removing the '$' sign, populating pricesNumeric list
        for (String price : allPrices) {
            pricesNumeric.add(Double.parseDouble(price.replace("$", "")));
        }

//      Verifying that the order of the prices in the pricesNumeric list is descending
        boolean isDescending = true;
        for (int i = 0; i < pricesNumeric.size() - 1; i++) {
            if (pricesNumeric.get(i) < pricesNumeric.get(i + 1)) {
                isDescending = false;
                break;
            }
            Assert.assertTrue(isDescending, "The prices are NOT in descending order!");
        }
    }

    @Test
    public void testBrowseByCategoriesSideMenu() {
        driver.findElement(By.xpath("//a[@class='wp-block-button__link']")).click();
        WebElement dropdown = driver.findElement(By.id("product_cat"));
        Select select = new Select(dropdown);
        select.selectByIndex(2);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='woocommerce-products-header__title page-title']")));

        List<String> actualSortedList = new ArrayList<>();
        List<WebElement> sortedList = driver.findElements(By.xpath("//span[@class='ast-woo-product-category']"));
        for (WebElement category : sortedList) {
            actualSortedList.add(category.getText());

            List<String> expectedMenSorting = new ArrayList<>(actualSortedList);
            Collections.sort(expectedMenSorting);

            Assert.assertEquals(actualSortedList, expectedMenSorting, "Sorting by Category Dropdown Did Not Apply to 'Men' Category");
        }
    }

    @Test
    public void testSearchProductBar() {
        driver.findElement(By.xpath("//a[@class='wp-block-button__link']")).click();
        WebElement searchBar = driver.findElement(By.id("woocommerce-product-search-field-0"));
        searchBar.sendKeys("blue");
        driver.findElement(By.xpath("//button[@value='Search']")).click();

        List<String> expectedSearchResultList = new ArrayList<>();
        List<WebElement> searchResult = driver.findElements(By.xpath("//h2[@class='woocommerce-loop-product__title']"));
        for (WebElement search : searchResult) {
            String text = search.getText();
            if (text.contains("blue")) {
                expectedSearchResultList.add(text);
            }
            for (String item : expectedSearchResultList) {
                Assert.assertTrue(item.toLowerCase().contains("blue"), "The search result does not contain the word 'blue': " + item);

                List<String> actualSearchResultList = List.of("Blue Shoes", "Denim Blue Jeans", "Faint Blue Jeans", "Blue Denim Shorts", "Basic Blue Jeans", "Blue Tshirt");

                Assert.assertEquals(actualSearchResultList, expectedSearchResultList);
            }
        }
    }

    @Test
    public void ButtonShopNow() throws InterruptedException {
        driver.findElement(By.xpath("//a[@class='wp-block-button__link']")).click();
        Thread.sleep(4000);
        String correctUrl = driver.getCurrentUrl();
        Assert.assertEquals(correctUrl, "https://askomdch.com/store");
    }

    @Test
    public void testSortByPriceLowToHigh2() {
        driver.findElement(By.xpath("//li[@id='menu-item-1227']//a[@class='menu-link'][normalize-space()='Store']")).click();
        WebElement dropDownSort = driver.findElement(By.xpath("//select[@name='orderby']"));
        Select dropDownSelect = new Select(dropDownSort);
        dropDownSelect.selectByValue("price");

        List<WebElement> sortedItems = driver.findElements(By.xpath("//span[@class='price']/*[not(@aria-hidden='true')]"));
        List<String> prices = new ArrayList<>(sortedItems.stream().map(WebElement::getText).toList());
        List<String> sortedPrices = new ArrayList<>(prices);

        sortedPrices.sort(Comparator.naturalOrder());

        Assert.assertEquals(sortedPrices, prices);
    }

    @Test
    public void testSortByPriceHighToLow() {
        driver.findElement(By.xpath("//li[@id='menu-item-1227']//a[@class='menu-link'][normalize-space()='Store']")).click();
        WebElement dropDownSort = driver.findElement(By.xpath("//select[@name='orderby']"));
        Select dropDownSelect = new Select(dropDownSort);
        dropDownSelect.selectByValue("price-desc");

        List<Double> prices = driver.findElements(By.xpath("//span[@class='price']/*[not(@aria-hidden='true')]"))
                .stream()
                .map(element -> element.getText().replace("$", ""))
                .map(Double::parseDouble)
                .toList();

        List<Double> sortedPrices = prices.stream()
                .sorted(Comparator.reverseOrder())
                .toList();
        Assert.assertEquals(sortedPrices, prices);
    }


    @Test
    public void testEDSortByPriceHighToLow() throws InterruptedException {
        driver.findElement(By.cssSelector("#menu-item-1227 a")).click();
        List<Double> pricesAllExpect = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            List<Double> pricesCurrentPage = driver.findElements(By.xpath("//span[@class='price']/*[not(@aria-hidden='true')]"))
                    .stream()
                    .map(element -> element.getText().replace("$", ""))
                    .map(Double::parseDouble)
                    .toList();
            pricesAllExpect.addAll(pricesCurrentPage);
            driver.findElement(By.xpath("//ul[@class='page-numbers']/li[last()]")).click();

        }
        pricesAllExpect.sort(Collections.reverseOrder());

        driver.findElement(By.cssSelector("#menu-item-1227 a")).click();
        WebElement dropDownSort = driver.findElement(By.name("orderby"));
        Select dropDownSelect = new Select(dropDownSort);
        dropDownSelect.selectByValue("price-desc");

        List<Double> pricesAllActual = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            List<Double> pricesCurrentPage = driver.findElements(By.xpath("//span[@class='price']/*[not(@aria-hidden='true')]"))
                    .stream()
                    .map(element -> element.getText().replace("$", ""))
                    .map(Double::parseDouble)
                    .toList();
            pricesAllActual.addAll(pricesCurrentPage);
            driver.findElement(By.xpath("//ul[@class='page-numbers']/li[last()]")).click();
        }

        Assert.assertEquals(pricesAllActual, pricesAllExpect);
    }


    @Test//price filter set up from min price ($10) to $60
    public void testStorePageFilterPriceCheck() {
        driver.findElement(By.xpath("//a[@class='wp-block-button__link']")).click();

        WebElement priceFilter = driver.findElement(By.xpath("//span[@tabindex='0' and contains(@class, 'ui-slider-handle') and @style='left: 100%;']"));
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(priceFilter, -150, 0).perform();
        WebElement filterBtn = driver.findElement(By.xpath("//button[normalize-space()='Filter']"));
        filterBtn.click();

        List<WebElement> filterPrice = driver.findElements(By.xpath("//span[@class='price']/*[not(@aria-hidden='true')]"));
        List<Double> expectingFilteringPricePage = new ArrayList<>();
        for (WebElement price : filterPrice) {
            String priceText = price.getText().replace("$", "");
            Double filteringPriceValue = Double.parseDouble(priceText);
            expectingFilteringPricePage.add(filteringPriceValue);

            boolean allPricesUnderSixty = true;
            for (Double expectPrice : expectingFilteringPricePage) {
                if (expectPrice > 60) {
                    allPricesUnderSixty = false;
                }
                Assert.assertTrue(allPricesUnderSixty, "Filter by price doesn't filter the price accordingly");
            }
        }
    }

    @Test
    public void closeCategory() {
        // ClickUp: 4_7 | Women > Clear browse by categories.

        String storePageUrl = "https://askomdch.com/store/";
        driver.findElement(By.xpath("//a[contains(text(), 'Women')]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement clearSelectionIcon = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='select2-selection__clear' and text()='×']")));
        clearSelectionIcon.click();

        WebElement selectCategoryField = driver.findElement(By.xpath("//select[@id='product_cat']"));
        Select select = new Select (selectCategoryField);
        String actualCategoryText = select.getOptions().get(0).getText();
        String noCategorySelectedText = "Select a category";

        Assert.assertEquals(actualCategoryText, noCategorySelectedText, "Category was not cleared out!");
        Assert.assertEquals(driver.getCurrentUrl(), storePageUrl, "User was not redirected to the Store page");
    }

    @Test
    public void testStoreAddItemToMyCart(){
        driver.findElement(By.xpath("//a[@href='/store']")).click();

        WebElement button = driver.findElement(By.xpath("//a[@href='?add-to-cart=1198']"));
        button.click();

        driver.findElement(By.cssSelector("div.ast-cart-menu-wrap"));
        WebElement amount = driver.findElement(By.cssSelector("span.count"));

        Assert.assertTrue(amount.isDisplayed());
    }

    @Test
    public void testAccessoriesAddItemToCart() throws InterruptedException {
        driver.findElement(By.xpath("//ul[@id='ast-hf-menu-1']//a[contains(text(),'Accessories')]")).click();

        WebElement firstProduct = driver.findElement(By.xpath("//ul[contains(@class,'products')]//li"));
        String firstProductTitle = firstProduct.findElement(By.xpath(".//h2")).getText();
        WebElement firstProductAddToCartButton = firstProduct
                .findElement(By.xpath(".//a[contains(text(),'Add to cart')]"));

        firstProductAddToCartButton.click();

        Thread.sleep(2000);

        driver.findElement(By.xpath("//a[@class='cart-container']")).click();

        Thread.sleep(2000);

        WebElement productName = driver.
                findElement(By.xpath("//table[contains(@class,'shop_table')]//td[@class='product-name']"));

        Assert.assertEquals(productName.getText(), firstProductTitle);
    }
}
