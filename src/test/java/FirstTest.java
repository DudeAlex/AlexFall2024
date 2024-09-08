import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstTest extends BaseTest {
    @Test
    public void Button() throws InterruptedException {
        driver.findElement(By.xpath("//a[@class='wp-block-button__link']")).click();
        Thread.sleep(4000);
        String correctUrl = driver.getCurrentUrl();
        Assert.assertEquals(correctUrl,"https://askomdch.com/store");
    }

}

