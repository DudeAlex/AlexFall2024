import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class First {

    @Test
    public void testFirst() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://askomdch.com/");
        driver.manage().window().maximize();
        Thread.sleep(1000);

        String text = driver.findElement(By.tagName("h1")).getText();

        Assert.assertEquals(text, "AskOmDch");

        Thread.sleep(1000);
        driver.quit();
    }
}
