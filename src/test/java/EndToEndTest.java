import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.HomePage;
import com.ecommerce.pom.pages.StorePage;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class EndToEndTest  extends BaseTest {

    @Test
    public void testProductToShoppingCart () {

        HomePage homePage = new HomePage(driver);
        StorePage storePage = homePage.navigateToStorePage();
        String searchResult = storePage.searchProduct("Blue").getSearchHeaderTitle();
        String item = storePage.getTextFromListProducts(0);

        Assert.assertEquals(searchResult, "Search results: “Blue”");

        driver.findElement(By.xpath("//a[@aria-label='Add “" + item + "” to your cart']")).click();
        driver.findElement(By.xpath("//a[@title='View cart']")).click();
        String sameItem = driver.findElement(By.xpath("//a[contains(text(),'" + item + "')]")).getText();

        Assert.assertEquals(sameItem, item);

        driver.findElement(By.xpath("//a[@class='checkout-button button alt wc-forward']")).click();
        driver.findElement(By.xpath("//input[@name='billing_first_name']")).sendKeys("Mihai");
        driver.findElement(By.xpath("//input[@name='billing_last_name']")).sendKeys("B");
        driver.findElement(By.xpath("//input[@name='billing_company']")).sendKeys("TBMH Radio");

        driver.findElement(By.xpath("//span[@id='select2-billing_country-container']")).click();
        driver.findElement(By.xpath("//li[contains(text(),'United States (US)')]")).click();

        driver.findElement(By.xpath("//input[@name='billing_address_1']")).sendKeys("32 Main st");
        driver.findElement(By.xpath("//input[@name='billing_city']")).sendKeys("Redmond");

        driver.findElement(By.xpath("//span[@id='select2-billing_state-container']")).click();
        driver.findElement(By.xpath("//li[contains(text(),'California')]")).click();

        driver.findElement(By.xpath("//input[@id='billing_postcode']")).sendKeys("98052");
        driver.findElement(By.xpath("//input[@id='billing_email']")).sendKeys("Testemail@gmail.com");

        String productOrder = driver.findElement(By.xpath("//td[@class='product-name']")).getText();
        Assert.assertEquals(productOrder, "Blue Shoes  × 1");

        WaitUtils.elementToBeClickable(driver, By.xpath("//button[@id='place_order']")).click();

        String checkOrder = driver.findElement(By.xpath("//p[@class='woocommerce-notice woocommerce-notice--success woocommerce-thankyou-order-received']")).getText();
        Assert.assertEquals(checkOrder, "Thank you. Your order has been received.");

    }

}
