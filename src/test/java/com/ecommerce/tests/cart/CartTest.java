package com.ecommerce.tests.cart;

import com.ecommerce.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class CartTest extends BaseTest {

    @Test(description = "9_1 | TC > Cart - Update the Cart # https://app.clickup.com/t/8689u7hd6")
    public void testUpdateQuantityInCart() {
        driver.findElement(By.xpath("//div[@id='ast-desktop-header']//a[text()='Store']")).click();

        driver.findElement(By.xpath("//div[@class='astra-shop-summary-wrap']//a[text()='Add to cart']")).click();
        WebElement viewCart = driver.findElement(By.linkText("View cart"));
        String viewCartText = viewCart.getText();

        Assert.assertEquals(viewCartText, "View cart");

        viewCart.click();

        String priceStr = driver.findElement(By.xpath("//td[@class = 'product-subtotal']/span[@class='woocommerce-Price-amount amount']")).getText();
        if(priceStr.charAt(0) == '$'){ //since the price is a string that has $, I need to remove the $ first
            priceStr = priceStr.substring(1); //creating the substring without $
        }
        double price = Double.valueOf(priceStr); //change string price to double

        WebElement quantity = driver.findElement(By.xpath("//input[@class='input-text qty text']"));
        quantity.click();
        quantity.clear();
        quantity.sendKeys("3");

        String updatedPriceString = driver.findElement(By.xpath("//td[@class = 'product-subtotal']/span[@class='woocommerce-Price-amount amount']")).getText();
        driver.findElement(By.xpath("//button[@name='update_cart']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String finalUpdatedPriceStr = updatedPriceString;
        // After the clicking Update price button, I need wait until the price will be updated
        wait.until(driver -> {
            String checkPrice = driver.findElement(By.xpath("//td[@class = 'product-subtotal']/span[@class='woocommerce-Price-amount amount']")).getText();
            if(checkPrice.equals(finalUpdatedPriceStr)) { // checking if price still equal to original price (before update)
                return false;
            }
            return true;  //when price is updated (not equal anymore) returning true
        });

        //  Getting the updated subtotal price
        String updatedPriceStr = driver.findElement(By.xpath("//td[@class = 'product-subtotal']/span[@class='woocommerce-Price-amount amount']")).getText();
        if(updatedPriceStr.charAt(0) == '$'){
            updatedPriceStr = updatedPriceStr.substring(1);
        }

        double updatedPrice = Double.valueOf(updatedPriceStr);
        Assert.assertEquals(updatedPrice, (price * 3));
    }
    @Test(description = "9_2 | TC > Cart - Remove single item from the cart # https://app.clickup.com/t/8689ucy2m")
    public void testAddAndRemoveSingleItemFromCart() {
        driver.findElement(By.xpath("//div[@id='ast-desktop-header']//a[text()='Store']")).click();

        driver.findElement(By.xpath("//div[@class='astra-shop-summary-wrap']//a[text()='Add to cart']")).click();
        WebElement viewCart = driver.findElement(By.linkText("View cart"));
        String viewCartText = viewCart.getText();

        Assert.assertEquals(viewCartText, "View cart");

        viewCart.click();
        driver.findElement(By.xpath("//a[@class = 'remove']")).click();

        String itemRemovedMassage = driver.findElement(By.xpath("//*[contains(text(),'removed')]")).getText();
        Assert.assertTrue(itemRemovedMassage.contains("removed"));
    }
}