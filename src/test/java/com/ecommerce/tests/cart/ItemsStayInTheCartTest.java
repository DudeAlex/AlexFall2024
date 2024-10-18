package com.ecommerce.tests.cart;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pojo.User;
import com.ecommerce.pom.pages.AccountPage;
import com.ecommerce.pom.pages.CartPage;
import com.ecommerce.pom.pages.HomePage;
import com.ecommerce.pom.pages.StorePage;
import org.testng.annotations.Test;

public class ItemsStayInTheCartTest extends BaseTest {
    @Test
    public void testItemsStayInTheCart(){
//        User user = new User("test_test@test.test", "12345");
//        HomePage homePage = new HomePage(driver);
//        AccountPage accountPage = homePage.getHeader().navigateToAccountPage();
//        accountPage.logIn(user.getEmail(), user.getPassword());
//
//        CartPage cartPage = accountPage.getHeader().navigateToCartPage();
//        if (Integer.parseInt(cartPage.getCartItemsNumber()) > 0) {
//            cartPage.removeItemsFromCart();
//        }

        AddProductStorePageTest addProductStorePageTest = new AddProductStorePageTest();
        addProductStorePageTest.testAddProductStorePage();


    }

}
