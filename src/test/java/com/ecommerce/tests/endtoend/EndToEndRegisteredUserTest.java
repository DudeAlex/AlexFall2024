package com.ecommerce.tests.endtoend;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.ecommerce.base.BaseTest;

import java.util.List;

public class EndToEndRegisteredUserTest extends BaseTest{

    @Test
    public void testEndToEndRegisteredUser() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id='menu-item-1237']/a")).click();
        driver.findElement(By.id("username")).sendKeys("qwerty");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.xpath("//*[@id='menu-item-1227']/a")).click();
        driver.findElement(By.id("woocommerce-product-search-field-0")).sendKeys("denim");
        driver.findElement(By.xpath("//button[@value='Search']")).click();

        String result = driver.findElement(By.xpath("//*[@id='main']/div/header/h1")).getText();
        System.out.println(result);
        Assert.assertEquals(result, "Search results: “%s”".formatted("denim"));

        List<WebElement> items = driver.findElements(By.xpath("//h2[@class='woocommerce-loop-product__title']"));

        driver.findElement(By.xpath("//a[@aria-label='Add “" + items.get(0).getText() + "” to your cart']")).click();
        Thread.sleep(1000);
        String count = driver.findElement(By.xpath("//span[@class='count']")).getText();
        System.out.println(count);
        Assert.assertEquals(count, "1");
        Assert.assertEquals(driver.findElement(By.xpath("//a[@title='View cart']")).getText(), "View cart");
        driver.findElement(By.xpath("//a[@title='View cart']")).click();

        driver.findElement(By.xpath("//a[@class='checkout-button button alt wc-forward']")).click();

        //to-do: на странице Checkout проверить заголовок, заполненность полей, сравнить, что они равны предопределенным данным в аккаунте для billing и для shipment (Ship to a different address)

        Thread.sleep(5000);

        driver.findElement(By.id("billing_first_name")).sendKeys("Ivan");
        driver.findElement(By.id("billing_last_name")).sendKeys("Petrov");
        driver.findElement(By.id("billing_address_1")).sendKeys("123");
        driver.findElement(By.id("billing_city")).sendKeys("Sydney");
        driver.findElement(By.id("billing_postcode")).sendKeys("11111");
        driver.findElement(By.id("billing_email")).sendKeys("111@aaaa.com");
        Thread.sleep(5000);

        driver.findElement(By.id("select2-billing_country-container")).click();
        driver.findElement(By.className("select2-search__field")).sendKeys("Australia");
        driver.findElement(By.id("select2-billing_country-results")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("select2-billing_state-container")).click();
        driver.findElement(By.className("select2-search__field")).sendKeys("Australian Capital Territory");
        driver.findElement(By.id("select2-billing_state-results")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//button[@id='place_order']")).click();
        Thread.sleep(5000);

        //to-do: проверить, что данные о товаре и адреса присутствуют на странице. Заказу присвоен номер. Отображается выбранный payment method.
        String orderNumber = driver.findElement(By.xpath("//li[@class='woocommerce-order-overview__order order']/strong")).getText();

        System.out.println(orderNumber);
        Assert.assertNotNull(orderNumber);
        Thread.sleep(3000);

    }
}
