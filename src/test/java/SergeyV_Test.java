import com.ecommerce.base.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class SergeyV_Test extends BaseTest {

    @Test
    public void testSerg() throws InterruptedException {
        driver.findElement(By.xpath("//a[@href='https://askomdch.com/product-category/accessories/']")).click();

        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id=\'woocommerce_top_rated_products-3\']/ul/li[2]/a/span")).click();

        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id=\'product-1205\']/div[2]/form/button")).click();

        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id='main'']/div/div[1]/div/a")).click();

        Thread.sleep(2000);

    }

}
