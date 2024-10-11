package com.ecommerce.tests.account;

import com.ecommerce.base.BaseTest;
import com.ecommerce.data.RegisteredUserLoginWithEmail;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OrdersHistoryCheckTest extends BaseTest {
    @Test(description = "6.4.1 -2.1| TC > Account Page>Order History #https://app.clickup.com/t/8689wh30p",
            dataProvider = "registeredUserEmail", dataProviderClass = RegisteredUserLoginWithEmail.class)

    public void testOderHistoryCheck(
            String accountHeaderLink,
            String emailField,
            String emailOrUserName,
            String passwordField,
            String password) throws InterruptedException {

        By placeOderBtn = By.xpath("//button[@id='place_order']");
        By orderLink = By.xpath("//a[normalize-space()='Orders']");
        By accountTopLink = By.xpath(("//li[@id='menu-item-1237']//a[@class='menu-link'][normalize-space()='Account']"));
        By checkoutBtn = By.xpath("//a[@class='checkout-button button alt wc-forward']");


        driver.findElement(By.xpath(accountHeaderLink)).click(); // find the account link in the header
        driver.findElement(By.xpath(emailField)).sendKeys(emailOrUserName, Keys.ENTER);
        driver.findElement(By.xpath(passwordField)).sendKeys(password, Keys.ENTER);
        driver.findElement(By.id("menu-item-1227")).click();//shop link
        driver.findElement(By.xpath("//a[@href='?add-to-cart=1205']")).click();//add item to the cart
        WebElement viewCart = driver.findElement(By.linkText("View cart"));//click cart
        viewCart.click();

        WaitUtils.elementToBeClickable(driver,checkoutBtn).click();


        driver.findElement(By.xpath("//input[@name='billing_first_name']")).clear();
        driver.findElement(By.xpath("//input[@name='billing_first_name']")).sendKeys("Mihai");

        driver.findElement(By.xpath("//input[@name='billing_last_name']")).clear();
        driver.findElement(By.xpath("//input[@name='billing_last_name']")).sendKeys("B");

        driver.findElement(By.xpath("//span[@id='select2-billing_country-container']")).click();
        driver.findElement(By.xpath("//li[contains(text(),'United States (US)')]")).click();

        driver.findElement(By.xpath("//input[@name='billing_address_1']")).clear();
        driver.findElement(By.xpath("//input[@name='billing_address_1']")).sendKeys("32 Main st");

        driver.findElement(By.xpath("//input[@name='billing_city']")).clear();
        driver.findElement(By.xpath("//input[@name='billing_city']")).sendKeys("Redmond");

        driver.findElement(By.xpath("//span[@id='select2-billing_state-container']")).click();
        driver.findElement(By.xpath("//li[contains(text(),'California')]")).click();

        driver.findElement(By.xpath("//input[@id='billing_postcode']")).clear();
        driver.findElement(By.xpath("//input[@id='billing_postcode']")).sendKeys("98052");

        WaitUtils.elementToBeClickable(driver,placeOderBtn).click();

        WebElement checkOut = driver.findElement(By.xpath("//h1[@class='has-text-align-center']"));
        Assert.assertEquals(checkOut.getText(),"Checkout");

        WaitUtils.elementToBeClickable(driver, accountTopLink).click();

        WaitUtils.elementToBeClickable(driver, orderLink,10).click();

        driver.findElement(By.xpath("//a[@class=\"woocommerce-button button view\"]")).click();//view order btn
        WebElement orderDetails = driver.findElement(By.xpath("//h2[@class='woocommerce-order-details__title']"));

        Assert.assertEquals(orderDetails.getText(), "Order details");

    }
}


