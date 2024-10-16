package com.ecommerce.tests.components.footer;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FooterForHimTest extends BaseTest {
    @Test(description = "11.1-3.1-5 | TC > Verify Navigation Footer Menu List For Him  # https://app.clickup.com/t/8689r62er ")
    public void testFooterForHimNavigation(){

        String urlMen = "https://askomdch.com/product-category/men/";
        String urlMensJeans = "https://askomdch.com/product-category/mens-jeans/";
        String urlMensShirts = "https://askomdch.com/product-category/mens-shirts/";
        String urlMensShoes = "https://askomdch.com/product-category/mens-shoes/";
        String urlMensAccessories = "https://askomdch.com/product-category/accessories/";

        HomePage homePage = new HomePage(driver);

        homePage.getFooter().navigateToMenPageFromFooter();
        Assert.assertEquals(driver.getCurrentUrl(), urlMen);

        homePage.getFooter().navigateToMensJeansFromFooter();
        Assert.assertEquals(driver.getCurrentUrl(), urlMensJeans);

        homePage.getFooter().navigateToMensShirtsFromFooter();
        Assert.assertEquals(driver.getCurrentUrl(), urlMensShirts);

        homePage.getFooter().navigateToMensShoesFromFooter();
        Assert.assertEquals(driver.getCurrentUrl(), urlMensShoes);

        homePage.getFooter().navigateToForHimAccessoriesFromFooter();
        Assert.assertEquals(driver.getCurrentUrl(), urlMensAccessories);


    }
}
