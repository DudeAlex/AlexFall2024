package com.ecommerce.tests.home;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.HomePage;
import com.ecommerce.pom.pages.MenPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddAnyProductToCartTest extends BaseTest {

    /* This class is temporally added to check that the ProductGrid component and PurchasePage abstarct class don't
    have critical errors.
    Also, they can be used as an example for how to use the methods added to these new classes.
    This class will be deleted or changed to have 'real' tests later
     */
    @Test
    public void testMethodInPurchasePageForHomePage() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.load();

        homePage.getProductsGrid().clickAddToCartButton("Blue Shoes");
        homePage.getProductsGrid().clickAddToCartButton("Basic Blue Jeans");

        homePage.scrollToElement(driver.findElement(By
                .xpath("//div[@id='ast-desktop-header']//span[@class='count']")));
        Thread.sleep(500);

        int amountOfAddedProducts = homePage.getHeader().getAmountOfProductsOnCartIcon();

        Assert.assertEquals(amountOfAddedProducts, 2);
    }

    @Test
    public void testMethodInPurchasePageForMenPage() throws InterruptedException {
        MenPage menPage = new MenPage(driver);
        menPage.load();

        menPage.getProductsGrid().clickAddToCartButton("Green Tshirt");
        menPage.getProductsGrid().clickAddToCartButton("Red Shoes");

        Thread.sleep(1000);

        int amountOfAddedProducts = menPage.getHeader().getAmountOfProductsOnCartIcon();

        Assert.assertEquals(amountOfAddedProducts, 2);
    }


}
