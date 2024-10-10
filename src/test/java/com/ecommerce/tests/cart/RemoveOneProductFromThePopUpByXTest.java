package com.ecommerce.tests.cart;

import com.ecommerce.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


public class RemoveOneProductFromThePopUpByXTest extends BaseTest {

    @Test(description = "9.1-2-2.1 | TC Product removed by clicking 'x' icon on the 'cart preview' window # https://app.clickup.com/t/868a5jk11")
    public void testAddProductStorePage() {

        driver.findElement(By.xpath("//a[@class='wp-block-button__link']")).click();
        driver.findElement(By.xpath("//a[@aria-label='Add “Anchor Bracelet” to your cart']")).click();

        WebElement shoppingCart = driver.findElement(By.xpath("//span[@class='count'][normalize-space()='1']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(shoppingCart).perform();

        WebElement removeXButton = driver.findElement(By.xpath("//div/header/div[1]/div[1]/div/div/div/div[2]/div[2]/div/div[2]/div/div/ul/li/a[1]"));
        removeXButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBe(By.xpath("//span[@class='count']"), "0"));
        WebElement updatedShoppingCart = driver.findElement(By.xpath("//span[@class='count']"));
        Assert.assertEquals(updatedShoppingCart.getText(), "0");


    }

}

