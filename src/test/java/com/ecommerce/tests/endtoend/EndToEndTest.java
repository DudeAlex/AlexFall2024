package com.ecommerce.tests.endtoend;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pojo.UserData;
import com.ecommerce.pojo.UserDataPool;
import com.ecommerce.pom.pages.CartPage;
import com.ecommerce.pom.pages.CheckoutPage;
import com.ecommerce.pom.pages.HomePage;
import com.ecommerce.pom.pages.StorePage;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EndToEndTest  extends BaseTest {

    @Test
    public void testProductToShoppingCart () {

       UserData userData = UserDataPool.getFakerUserDataList(10).get(4);
       String product = "Blue";

        HomePage homePage = new HomePage(driver);
        StorePage storePage = homePage.navigateToStorePage();
        String searchResult = storePage.searchProduct(product).getSearchHeaderTitle();
        String item = storePage.getTextFromListProducts(0);

        Assert.assertEquals(searchResult, "Search results: “%s”".formatted(product));

        storePage.chooseAnItemByClickingAddToCart(item);

        CartPage cartPage = new CartPage(driver);
        cartPage.clickViewCartButton();


        String sameItem = storePage.checkProductNameOnCartPage(item);
        Assert.assertEquals(sameItem, item);

        cartPage.clickCheckoutButton();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.inputFirstName(userData.getFirstName())
                    .inputLastName(userData.getLastName());

        checkoutPage.inputCountry(userData.getCountry())
                    .inputStreetAddress(userData.getAddress());
        checkoutPage.inputTown(userData.getTown());

        checkoutPage.clickBillingStateDropDown(userData.getState());

        checkoutPage.inputZip(userData.getZipCode());
        checkoutPage.inputEmail(userData.getEmailAddress());

        String productOrder = driver.findElement(By.xpath("//td[@class='product-name']")).getText();
        Assert.assertEquals(productOrder, "Blue Shoes  × 1");

        checkoutPage.clickPlaceOrderButton();

        String checkOrder = driver.findElement(By.xpath("//p[@class='woocommerce-notice woocommerce-notice--success woocommerce-thankyou-order-received']")).getText();
        Assert.assertEquals(checkOrder, "Thank you. Your order has been received.");

    }

}
