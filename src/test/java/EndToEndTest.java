import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class EndToEndTest  extends BaseTest{

    @Test
    public void testProductToShoppingCart () throws InterruptedException {

        driver.findElement(By.xpath("//a[@class='wp-block-button__link']")).click();
        driver.findElement(By.id("woocommerce-product-search-field-0")).sendKeys("Blue");
        driver.findElement(By.xpath("//button[@value='Search']")).click();

        String searchResult = driver.findElement(By.xpath("//h1[@class='woocommerce-products-header__title page-title']")).getText();

        Assert.assertEquals(searchResult, "Search results: “Blue”");

        List<WebElement> blueProducts = driver.findElements(By.xpath("//h2[@class='woocommerce-loop-product__title']"));
        String item = blueProducts.get(0).getText();

        driver.findElement(By.xpath("//a[@aria-label='Add “" + item + "” to your cart']")).click();
        driver.findElement(By.xpath("//a[@title='View cart']")).click();
        String sameItem = driver.findElement(By.xpath("//a[contains(text(),'" + item + "')]")).getText();

        Assert.assertEquals(sameItem, item);

        driver.findElement(By.xpath("//a[@class='checkout-button button alt wc-forward']")).click();


        Thread.sleep(2000);





    }

}
