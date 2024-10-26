package com.ecommerce.tests.components.footer;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pojo.User;
import com.ecommerce.pom.pages.*;
import com.ecommerce.utils.UserUtils;
import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.ecommerce.pom.EndPoints.*;


public class FooterVisibleEveryPagesTest extends BaseTest {
    @Test(description = "11.1-1-1 | TC > The footer navigation menu is displayed at the down of every site page # https://app.clickup.com/t/8689wk9pk ")
    public void testFooterVisible() throws IOException {

        HomePage homePage = new HomePage(driver);

        /**
         * Quick Links
         */

        homePage.getFooter().navigateToHomepageFromFooter();
        org.testng.Assert.assertEquals(driver.getCurrentUrl(), BASE_URL);
        testIsFooterVisible(BASE_URL);

        homePage.getFooter().navigateToAboutUsPageFromFooter();
        org.testng.Assert.assertEquals(driver.getCurrentUrl(), ABOUT_URL);
        testIsFooterVisible(ABOUT_URL);

        homePage.getFooter().navigateToMyAccountPageFromFooter();
        org.testng.Assert.assertEquals(driver.getCurrentUrl(), ACCOUNT_URL);
        testIsFooterVisible(ACCOUNT_URL);

        homePage.getFooter().navigateToCartPageFromFooter();
        org.testng.Assert.assertEquals(driver.getCurrentUrl(), CART_URL);
        testIsFooterVisible(CART_URL);

        homePage.getFooter().navigateToContactUsPageFromFooter();
        org.testng.Assert.assertEquals(driver.getCurrentUrl(), CONTACT_US_URL);
        testIsFooterVisible(CONTACT_US_URL);

        /**
         * For Her
         */

        homePage.getFooter().navigateToWomenPageFromFooter();
        org.testng.Assert.assertEquals(driver.getCurrentUrl(), WOMEN_URL);
        testIsFooterVisible(WOMEN_URL);

        homePage.getFooter().navigateToWomenJeansFromFooter();
        org.testng.Assert.assertEquals(driver.getCurrentUrl(), WOMENS_JEANS_URL);
        testIsFooterVisible(WOMENS_JEANS_URL);

        homePage.getFooter().navigateToWomenShirtsFromFooter();
        org.testng.Assert.assertEquals(driver.getCurrentUrl(), WOMENS_SHIRTS_URL);
        testIsFooterVisible(WOMENS_SHIRTS_URL);


        homePage.getFooter().navigateToWomenShoesFromFooter();
        org.testng.Assert.assertEquals(driver.getCurrentUrl(), WOMENS_SHOES_URL);
        testIsFooterVisible(WOMENS_SHOES_URL);

        homePage.getFooter().navigateToForHerAccessoriesFromFooter();
        org.testng.Assert.assertEquals(driver.getCurrentUrl(), ACCESSORIES_URL);
        testIsFooterVisible(ACCESSORIES_URL);

        /**
         * Hor Him
         */
        homePage.getFooter().navigateToMenPageFromFooter();
        org.testng.Assert.assertEquals(driver.getCurrentUrl(), MEN_URL);
        testIsFooterVisible(MEN_URL);

        homePage.getFooter().navigateToMensJeansFromFooter();
        org.testng.Assert.assertEquals(driver.getCurrentUrl(), MENS_JEANS_URL);
        testIsFooterVisible(MENS_JEANS_URL);

        homePage.getFooter().navigateToMensShirtsFromFooter();
        org.testng.Assert.assertEquals(driver.getCurrentUrl(), MENS_SHIRTS_URL);
        testIsFooterVisible(MENS_SHIRTS_URL);

        homePage.getFooter().navigateToMensShoesFromFooter();
        org.testng.Assert.assertEquals(driver.getCurrentUrl(), MENS_SHOES_URL);
        testIsFooterVisible(MENS_SHOES_URL);

        homePage.getFooter().navigateToForHimAccessoriesFromFooter();
        org.testng.Assert.assertEquals(driver.getCurrentUrl(), ACCESSORIES_URL);
        testIsFooterVisible(ACCESSORIES_URL);

        /**
         * Lost Password Page
         */

        AccountPage accountPage = new AccountPage(driver).load();
        accountPage.navigateToLostPasswordPage();
        org.testng.Assert.assertEquals(driver.getCurrentUrl(), LOST_PASSWORD_URL);
        testIsFooterVisible(LOST_PASSWORD_URL);



        /**
         * Dashboard
         * Orders
         * Downloads
         * Addresses
         * Account details
         */

        accountPage.load();

        User user = UserUtils.readUserFromJson("user.json");
        accountPage.logIn(user.getLogin(), user.getPassword());
        org.testng.Assert.assertEquals(driver.getCurrentUrl(), ACCOUNT_URL);
        testIsFooterVisible(ACCOUNT_URL);

        new AccountOrdersPage(driver).load();
        org.testng.Assert.assertEquals(driver.getCurrentUrl(), ACCOUNT_ORDERS_URL);
        testIsFooterVisible(ACCOUNT_ORDERS_URL);

        new AccountDownloadsPage(driver).load();
        org.testng.Assert.assertEquals(driver.getCurrentUrl(), ACCOUNT_DOWNLOADS_URL);
        testIsFooterVisible(ACCOUNT_DOWNLOADS_URL);

        new AccountAddressesPage(driver).load();
        org.testng.Assert.assertEquals(driver.getCurrentUrl(), ACCOUNT_ADDRESSES_URL);
        testIsFooterVisible(ACCOUNT_ADDRESSES_URL);

        new AccountDetailsPage(driver).load();
        org.testng.Assert.assertEquals(driver.getCurrentUrl(), ACCOUNT_DETAILS_URL);
        testIsFooterVisible(ACCOUNT_DETAILS_URL);


        /**
         * https://askomdch.com/checkout/
         */
        //---------------------------------------------------------------------------------------------------------
        //TODO:
        // 1. add product to the cart
        // 2. navigate to checkout
        // 3. check is the footer visible ob the checkout page
        //---------------------------------------------------------------------------------------------------------

    }

    @Test
    public void testIsFooterVisible (String url){
        driver.get(url);

        WebElement footer = driver.findElement(By.tagName("footer"));

        Assert.isTrue(footer.isDisplayed(),"Footer is not visible on the page.");

        int footerY = footer.getLocation().getY();
        int footerHeight = footer.getSize().getHeight();

        Long totalPageHeight = (Long)((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");

        Assert.isTrue(footerY + footerHeight  <= totalPageHeight,"Footer is not correctly positioned.");
    }
}
