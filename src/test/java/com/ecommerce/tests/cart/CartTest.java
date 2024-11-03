package com.ecommerce.tests.cart;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.components.Header;
import com.ecommerce.pom.pages.*;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static com.ecommerce.pom.EndPoints.*;

public class CartTest extends BaseTest {
    @DataProvider(name = "numberToUpdateData")
    public Object[][] numberStrToUpdateData() {
        return new Object[][] {
                {"3"}, {"11"}, {"50"}
        };
    }

    @DataProvider(name = "itemToRemoveFromCart")
    public Object[][] itemName() {
        return new Object[][] {
                {"Blue Denim Shorts"},
                {"Boho Bangle Bracelet"},
                {"Black Over-the-shoulder Handbag"}
        };
    }

    @Test(description = "9.1-3.4 | TC Update the Cart By typing the number inside of the Quantity input field",
    dataProvider = "numberToUpdateData")
    public void testUpdateQuantityInCart(String numberToSet) throws InterruptedException {
        new HomePage(driver).getHeader()
                .navigateToStorePage()
                .addFirstProductOnPageToCart()
                .getHeader()
                .getAmountOfProductsOnCartIcon();

         WaitUtils.waitForQuantityToBe(driver, new Header(driver).getHeaderCartIcon(), "1" );

        CartPage cartPage = new CartPage(driver);
        cartPage.getHeader()
                .navigateToCartPage()
                .clearCartQuantityField()
                .setProductQuantity(numberToSet);

        Thread.sleep(1000);

//        WaitUtils.waitForValueAttributeChanged(cartPage.getDriver(),
//                cartPage.getCartFirstProductQuantityField(), "value", numberToSet);
        int expectedSubtotal = cartPage.getProductPriceInt() * cartPage.getProductQuantityInt();

        Assert.assertEquals(cartPage.getProductSubtotalInt(), expectedSubtotal);
    }

    @Test(description = "9.1-2.3 | TC Remove single item by clicking the 'x' icon near the product in the cart",
    dataProvider = "itemToRemoveFromCart")
    public void testAddAndRemoveSingleItemFromCart(String itemName) {
         HomePage homePage = new HomePage(driver);
         homePage.getHeader()
                .navigateToStorePage()
                .addProductToCart(itemName);

        WaitUtils.waitForQuantityToBe(driver, homePage.getHeader().getHeaderCartIcon(), "1" );
        String itemRemovedMessage = homePage.getHeader()
                .navigateToCartPage()
                .removeItemsFromCart()
                .getItemRemovedMessage();

        Assert.assertTrue(itemRemovedMessage.contains("removed") &
                itemRemovedMessage.contains(itemName));
    }

    @Test(description = "9.1_2_2.4 | TC > Cart > Remove multiple products by clicking x buttons # https://app.clickup.com/t/8689p8y04")
    public void testAddRemoveMultipleItemsInCart() {
        new HomePage(driver).getHeader().navigateToStorePage();

        List<WebElement> products = driver.findElements(By.xpath("//div[@class='astra-shop-summary-wrap']//a[text()='Add to cart']"));
        Integer counter = 0;
        for (WebElement product : products) {
            product.click();
            counter++;
            final String finalCounter2 = counter.toString();
            WaitUtils.visibilityOfElementLocated(driver, By.xpath("//div[@class='ast-cart-menu-wrap']/span[contains(text(), " + finalCounter2));
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

    @Test(description = "9.1-4-4.1 | TC > Cart - Return from the Cart by clicking the Return To Shop button # https://app.clickup.com/t/868a68ebx")
        public void returnToShopFromCart() {
            new HomePage(driver).getHeader().navigateToCartPage();

            Assert.assertEquals(new CartPage(driver).getEmptyCartMessage(), "Your cart is currently empty.");

            new CartPage(driver).clickReturnToShopButton();

            Assert.assertEquals(new StorePage(driver).getSearchHeaderTitle(), "Store");
        }

            @Test(description = "9.2_1.1  | TC > Cart > Checkout # https://app.clickup.com/t/868a31pxz")
    public void testCheckout() throws InterruptedException {
        String notice = new HomePage(driver)
                .getHeader().navigateToStorePage()
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

    @Test(description = "9.1-1.5 | TC Add the same product to the cart (add a product twice) from the Store page")
    public void testAddTheSameProductToTheCartFromStorePage() {
       HomePage homePage = new HomePage(driver);
       AccountPage accountPage = homePage.getHeader()
               .navigateToAccountPage()
               .logInUsingConfigUtils()
               .assertLogin();

        StorePage storePage = accountPage.getHeader().navigateToStorePage();
        Assert.assertEquals(driver.getCurrentUrl(), STORE_URL);
        Assert.assertEquals(storePage.getHeader().getAmountOfProductsOnCartIcon(), 0);

        storePage.addproductToCartNumberOfTimes(2);
        WaitUtils.waitForQuantityToBe(storePage.getDriver(), storePage.getHeader().getHeaderCartIcon(), "2");

        Assert.assertEquals(storePage.getHeader().getAmountOfProductsOnCartIcon(), 2);
        CartPage cartPage = storePage.getHeader().navigateToCartPage();

        Assert.assertEquals(cartPage.getProductQuantityInt(), 2);
        Assert.assertEquals((cartPage.getProductPriceInt() * cartPage.getProductQuantityInt()), cartPage.getProductSubtotalInt());
    }

    @Test(description = "9.1-1.6 | TC Add the same product to the cart (add a product twice) from the Home page")
    public void testAddTheSameProductToTheCartFromHomePage() {
        HomePage homePage = new HomePage(driver);
        homePage.getHeader()
                .navigateToAccountPage()
                .logInUsingConfigUtils()
                .assertLogin()
                .getHeader()
                .navigateToHomePage();

        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL);
        Assert.assertEquals(homePage.getHeader().getAmountOfProductsOnCartIcon(), 0);

        homePage.addproductToCartNumberOfTimes(2);
        WaitUtils.waitForQuantityToBe(homePage.getDriver(), homePage.getHeader().getHeaderCartIcon(), "2");

        Assert.assertEquals(homePage.getHeader().getAmountOfProductsOnCartIcon(), 2);
        CartPage cartPage = homePage.getHeader().navigateToCartPage();

        Assert.assertEquals(cartPage.getProductQuantityInt(), 2);
        Assert.assertEquals((cartPage.getProductPriceInt() * cartPage.getProductQuantityInt()), cartPage.getProductSubtotalInt());
    }

    @Test(description = "9.1-1.7 | TC Add the same product to the cart (add a product twice) from the Men page")
    public void testAddTheSameProductToTheCartFromMenPage() {
        HomePage homePage = new HomePage(driver);
        MenPage menPage = homePage.getHeader()
                .navigateToAccountPage()
                .logInUsingConfigUtils()
                .assertLogin()
                .getHeader()
                .navigateToMenPage();

        Assert.assertEquals(driver.getCurrentUrl(), MEN_URL);
        Assert.assertEquals(homePage.getHeader().getAmountOfProductsOnCartIcon(), 0);

        menPage.addproductToCartNumberOfTimes(2);
        WaitUtils.waitForQuantityToBe(homePage.getDriver(), menPage.getHeader().getHeaderCartIcon(), "2");

        Assert.assertEquals(menPage.getHeader().getAmountOfProductsOnCartIcon(), 2);
        CartPage cartPage = menPage.getHeader().navigateToCartPage();

        Assert.assertEquals(cartPage.getProductQuantityInt(), 2);
        Assert.assertEquals((cartPage.getProductPriceInt() * cartPage.getProductQuantityInt()), cartPage.getProductSubtotalInt());
    }
}
