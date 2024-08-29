import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
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
    public void testClickOnStoreButton() throws InterruptedException {
        String url = "https://askomdch.com/store";

        driver.findElement(By.xpath("//a[@href='/store']")).click();

        String actualResult = driver.getCurrentUrl();

        Assert.assertEquals(actualResult, url);

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
}
