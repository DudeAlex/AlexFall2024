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
}
