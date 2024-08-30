import org.openqa.selenium.By;
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
}
