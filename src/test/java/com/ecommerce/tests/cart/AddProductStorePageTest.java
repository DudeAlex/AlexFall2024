package com.ecommerce.tests.cart;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.AccountPage;
import com.ecommerce.pom.pages.CartPage;
import com.ecommerce.pom.pages.HomePage;
import com.ecommerce.pom.pages.StorePage;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;



public class AddProductStorePageTest extends BaseTest {

    @Test(description = "9.1-1-1 | TC Add a product from the 'Store' page # https://app.clickup.com/t/8689zkdvk")
    public void testAddProductStorePage() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = homePage.navigateToAccountPage();
//        accountPage.logIn();

        driver.findElement(By.xpath("//input [ @ id = 'username']")).sendKeys("test_test@test.test"); // fill in the email field
        driver.findElement(By.xpath("//input [ @ id = 'password']")).sendKeys("12345"); // fill in the password field and log in
        driver.findElement(By.xpath("//button[@class='woocommerce-button button woocommerce-form-login__submit' and text()='Log in']")).click();

        CartPage cartPage = accountPage.navigateToCartPage();
        if(Integer.parseInt(cartPage.getCartItemsNumber()) > 0) {
            cartPage.removeItemsFromCart();
        }

        StorePage storePage = accountPage.navigateToStorePage();
        storePage.addToCartFromStorePage();
        cartPage = storePage.clickCartPage();
        Assert.assertEquals(cartPage.getCartItemsNumber(),"1");

    }

    }

