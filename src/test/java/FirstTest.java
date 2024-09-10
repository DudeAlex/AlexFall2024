import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class FirstTest extends BaseTest {

    private static final String LOGIN_TEST = "Testlogin";
    private static final String PASSWORD_TEST = "Testpassword";
    private static final String EMAIL_TEST = "test@gmail.com";
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

        Assert.assertEquals(countItemBySearch, countItemInMenResult + countItemInWomenResult, "Search box did not find all the items with item name '" + ITEM_CATEGORY + "' or find extra items");
    }

    @Test
    public void testVerifyItemsAlphabeticalOrder() {
        driver.findElement(By.xpath("//li[@id='menu-item-1227']")).click();

        List<String> allItemList = new ArrayList<>();
        boolean hasNextPage = true;

        while (hasNextPage) {
            List<WebElement> itemList = driver.findElements(By.xpath("//ul//h2"));
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

        List<String> alphabeticalAllItemList = new ArrayList<>(allItemList);
        Collections.sort(alphabeticalAllItemList);

        Assert.assertEquals(allItemList, alphabeticalAllItemList, "Items are not in alphabetical order");
    }

    @Test
    public void testSortByPriceLowToHigh() {
        driver.findElement(By.id("menu-item-1230")).click();

        WebElement dropdown = driver.findElement(By.xpath("//select[@name='orderby']"));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Sort by price: low to high");

        List<String> actualPriceList = new ArrayList<>();
        List<WebElement> priceList = driver.findElements(By.xpath("//span[@class='price']/*[not(@aria-hidden='true')]"));
        for (WebElement price : priceList) {
            actualPriceList.add(price.getText());
        }

        List<String> expectedLowToHighPriceList = new ArrayList<>(actualPriceList);
        Collections.sort(expectedLowToHighPriceList);

        Assert.assertEquals(actualPriceList, expectedLowToHighPriceList, "Prices are not sorted from high to low as expected");
    }

    @Test
    public void testUserRegistration() {
        driver.findElement(By
                        .xpath("//li[@id='menu-item-1237']//a[@class='menu-link'][normalize-space()='Account']"))
                .click();
        driver.findElement(By.xpath("//input[@id='reg_username']")).sendKeys(LOGIN_TEST);
        driver.findElement(By.xpath("//input[@id='reg_email']")).sendKeys(EMAIL_TEST);
        driver.findElement(By.xpath("//input[@id='reg_password']")).sendKeys(PASSWORD_TEST);
        driver.findElement(By.xpath("//button[@name='register']")).click();
        String accountText = driver.findElement(By.xpath("//p[2]")).getText();

        Assert.assertEquals(accountText,
                "From your account dashboard you can view your recent orders, " +
                        "manage your shipping and billing addresses, and edit your password and account details.");
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
        public void testSearchProductBar () {
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
            public void ButtonShopNow () throws InterruptedException {
                driver.findElement(By.xpath("//a[@class='wp-block-button__link']")).click();
                Thread.sleep(4000);
                String correctUrl = driver.getCurrentUrl();
                Assert.assertEquals(correctUrl, "https://askomdch.com/store");
            }
        }
