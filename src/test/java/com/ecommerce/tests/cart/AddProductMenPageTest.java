package com.ecommerce.tests.cart;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddProductMenPageTest extends BaseTest {
    @Test(description = "9.1-1-1.2 | TC Add a product from the 'Men' page # https://app.clickup.com/t/868a40bq7")
    public void testAddProductMenPage()  {
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = homePage.navigateToAccountPage();
        accountPage.logIn();

//        driver.findElement(By.xpath("//input [ @ id = 'username']")).sendKeys("test_test@test.test"); // fill in the email field
//        driver.findElement(By.xpath("//input [ @ id = 'password']")).sendKeys("12345"); // fill in the password field and log in
//        driver.findElement(By.xpath("//button[@class='woocommerce-button button woocommerce-form-login__submit' and text()='Log in']")).click();

        CartPage cartPage = accountPage.navigateToCartPage();
        if(Integer.parseInt(cartPage.getCartItemsNumber()) > 0) {
            cartPage.removeItemsFromCart();
        }

        MenPage menPage = accountPage.navigateToMenPage();
        menPage.addToCartFromManPage();
        cartPage = menPage.clickCartPage();
        Assert.assertEquals(cartPage.getCartItemsNumber(),"1");

    }
}
