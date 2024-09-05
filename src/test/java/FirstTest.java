import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirstTest extends BaseTest{

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

        Assert.assertEquals(actualProductName,"Blue Denim Shorts");
    }

    @DataProvider(name="navigationData")
    public Object[][] getNavigationMenuData() {
        return new Object[][] {
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
    public void testNavigationMenu(String linkText, String url, String title){
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
        JavascriptExecutor jse = ((JavascriptExecutor)driver);

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
}

