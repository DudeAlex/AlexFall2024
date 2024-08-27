import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

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
}
