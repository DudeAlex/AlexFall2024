package com.ecommerce.tests.cart;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.HomePage;
import com.ecommerce.pom.pages.StorePage;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class CartTest extends BaseTest {

    @DataProvider(name = "numberToUpdateData")
    public Object[][] numberStrToUpdateData() {
        return new Object[][] {
                {"3", 3}, {"11", 11}, {"50", 50}
        };
    }

    @Test(description = "9.1_3_3.4 | TC > Cart - Update the Cart By typing the number inside of the Quantity input field # https://app.clickup.com/t/8689u7hd6",
    dataProvider = "numberToUpdateData")
    public void testUpdateQuantityInCart(String numbeerToSet, int priceIncreeaseToCheck) {
        new HomePage(driver).navigateToStorePage();
        new StorePage(driver).addToCartFromStorePage();

        WebElement viewCart = driver.findElement(By.linkText("View cart"));
        String viewCartText = viewCart.getText();

        Assert.assertEquals(viewCartText, "View cart");

        viewCart.click();

        String priceStr = driver.findElement(By.xpath("//td[@class = 'product-subtotal']/span[@class='woocommerce-Price-amount amount']")).getText();
        if(priceStr.charAt(0) == '$'){ //since the price is a string that has $, I need to remove the $ first
            priceStr = priceStr.substring(1); //creating the substring without $
        }
        double price = Double.valueOf(priceStr); //change string price to double
        double priceForAssert = price * priceIncreeaseToCheck;
        String priceXPath = String.format("%.2f", priceForAssert);

        WebElement quantity = driver.findElement(By.xpath("//input[@class='input-text qty text']"));
        quantity.click();
        quantity.clear();
        quantity.sendKeys(numbeerToSet);

        String updatedPriceString = driver.findElement(By.xpath("//td[@class = 'product-subtotal']/span[@class='woocommerce-Price-amount amount']")).getText();
        driver.findElement(By.xpath("//button[@name='update_cart']")).click();

        WaitUtils.visibilityOfElementLocated(driver, By.xpath("//td[@class = 'product-subtotal']/span/bdi[contains(text(), '" + priceXPath + "')]"));

        //  Getting the updated subtotal price
        String updatedPriceStr = driver.findElement(By.xpath("//td[@class = 'product-subtotal']/span[@class='woocommerce-Price-amount amount']")).getText();
        if(updatedPriceStr.charAt(0) == '$'){
            updatedPriceStr = updatedPriceStr.substring(1);
        }

        double updatedPrice = Double.valueOf(updatedPriceStr);
        Assert.assertEquals(updatedPrice, (price * priceIncreeaseToCheck));
    }

    @Test(description = "9.1_2_2.3 | TC > Cart - Remove single item by clicking the 'x' icon near the product in the cart # https://app.clickup.com/t/8689ucy2m")
    public void testAddAndRemoveSingleItemFromCart() {
        new HomePage(driver).navigateToStorePage();
        new StorePage(driver).addToCartFromStorePage();

        WebElement viewCart = driver.findElement(By.linkText("View cart"));
        String viewCartText = viewCart.getText();

        Assert.assertEquals(viewCartText, "View cart");

        viewCart.click();
        driver.findElement(By.xpath("//a[@class = 'remove']")).click();

        String itemRemovedMassage = driver.findElement(By.xpath("//*[contains(text(),'removed')]")).getText();
        Assert.assertTrue(itemRemovedMassage.contains("removed"));
    }

    @Test(description = "9.1_2_2.4 | TC > Cart > Remove multiple products by clicking x buttons # https://app.clickup.com/t/8689p8y04")
    public void testAddRemoveMultipleItemsInCart() throws InterruptedException {
        new HomePage(driver).navigateToStorePage();

        List<WebElement> products = driver.findElements(By.xpath("//div[@class='astra-shop-summary-wrap']//a[text()='Add to cart']"));
        Integer counter = 0;
        for (WebElement product : products) {
            product.click();
            counter++;
            final String finalCounter2 = counter.toString();
            WaitUtils.visibilityOfElementLocated(driver, By.xpath("//div[@class='ast-cart-menu-wrap']/span[contains(text(), '" + finalCounter2 +"')]"));
        }
        WebElement viewCart = driver.findElement(By.linkText("View cart"));
        String viewCartText = viewCart.getText();

        Assert.assertEquals(viewCartText, "View cart");

        viewCart.click();
        List<WebElement> deleteButtons = driver.findElements(By.xpath("//a[@class = 'remove']"));
        while(!deleteButtons.isEmpty()) {
            deleteButtons.get(0).click();
            counter--;
            final String finalCounter = counter.toString();

            WaitUtils.visibilityOfElementLocated(driver, By.xpath("//div[@class='ast-cart-menu-wrap']/span[contains(text(), '" + finalCounter +"')]"));
            deleteButtons = driver.findElements(By.xpath("//a[@class = 'remove']"));
        }

        String emptyCart = driver.findElement(By.xpath("//p[@class='cart-empty woocommerce-info']")).getText();
        Assert.assertEquals(emptyCart, "Your cart is currently empty.");
    }
    @Test(description = "9.2_1.1  | TC > Cart > Checkout # https://app.clickup.com/t/868a31pxz")
    public void testCheckout() throws InterruptedException {
        String notice = new HomePage(driver)
                .navigateToStorePage()
                .addToCartFromStorePage()
                .clickCartPage()
                .clickCheckoutButton()
                .inputFirstName("Evgenii")
                .inputLastName("Averianov")
                .inputCompanyName("CompanyExample")
                .inputStreetAddress("1234 Main Street")
                .inputTown("Los Angeles")
                .inputZip("90210")
                .inputEmail("mail@mail.com")
                .clickPlaceOrderButton()
                .collectNotice();
        Assert.assertEquals(notice, "Thank you. Your order has been received.");
        Thread.sleep(2000);
    }


}
