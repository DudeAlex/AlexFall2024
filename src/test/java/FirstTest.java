import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
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
}

