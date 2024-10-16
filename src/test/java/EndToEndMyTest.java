import com.ecommerce.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class EndToEndMyTest extends BaseTest {

    @Test
    public void ketTest()  {

        driver.findElement(By.xpath("//a[@class='wp-block-button__link']")).click();
        driver.findElement(By.id("woocommerce-product-search-field-0")).sendKeys("Blue");
        driver.findElement(By.xpath("//button[@value='Search']")).click();

        String searchResult = driver.findElement(By.xpath
                ("//h1[@class='woocommerce-products-header__title page-title']")).getText();

        Assert.assertEquals(searchResult,"Search results: “Blue”");

        List<WebElement> blueProducts = driver.findElements(By.xpath
                ("//h2[@class='woocommerce-loop-product__title']"));
        String item = blueProducts.get(0).getText();

        driver.findElement(By.xpath("//a[@aria-label='Add “" + item + "” to your cart']")).click();
        driver.findElement(By.xpath("//a[@title='View cart']")).click();
        String sameItem = driver.findElement(By.xpath("//a[contains(text(),'" + item + "')]")).getText();

        Assert.assertEquals(sameItem,item);

        driver.findElement(By.xpath("//a[@class='checkout-button button alt wc-forward']")).click();

        String checkout = driver.findElement(By.xpath("//h1[@class='has-text-align-center']")).getText();
        Assert.assertEquals(checkout,"Checkout");

        driver.findElement(By.id("billing_first_name")).sendKeys("Ket");
        driver.findElement(By.id("billing_last_name")).sendKeys("Kir");
        driver.findElement(By.id("billing_address_1")).sendKeys("Reich,33");
        driver.findElement(By.id("billing_postcode")).sendKeys("88888");
        driver.findElement(By.id("billing_city")).sendKeys("Ber");
        driver.findElement(By.id("billing_email")).sendKeys("kat@mail.com");
        driver.findElement(By.id("place_order")).click();

        String thankYou =driver.findElement(
                By.xpath("//p[@class='woocommerce-notice woocommerce-notice--success woocommerce-thankyou-order-received']")).getText();
        Assert.assertEquals(thankYou,"Thank you. Your order has been received.");




    }




}




