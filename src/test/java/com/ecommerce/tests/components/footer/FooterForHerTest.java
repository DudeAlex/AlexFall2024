package com.ecommerce.tests.components.footer;

import com.ecommerce.base.BaseTest;
import com.ecommerce.data.FooterForHerData;
import com.ecommerce.pom.pages.HomePage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FooterForHerTest extends BaseTest {
    @Test(description = "11.1-2.1-5 | TC > Verify Navigation Footer Menu List For Her # https://app.clickup.com/t/8689r60e5")
    public void testFooterForHerNavigation(){

        String urlWomen = "https://askomdch.com/product-category/women/";
        String urlWomensJeans = "https://askomdch.com/product-category/womens-jeans/";
        String urlWomensShirts = "https://askomdch.com/product-category/womens-shirts/";
        String urlWomensShoes ="https://askomdch.com/product-category/womens-shoes/";
        String urlWomensAccessories ="https://askomdch.com/product-category/accessories/";

        HomePage homePage = new HomePage(driver);

        homePage.getFooter().navigateToWomenPageFromFooter();
        Assert.assertEquals(driver.getCurrentUrl(), urlWomen);

        homePage.getFooter().navigateToWomenJeansFromFooter();
        Assert.assertEquals(driver.getCurrentUrl(), urlWomensJeans);

        homePage.getFooter().navigateToWomenShirtsFromFooter();
        Assert.assertEquals(driver.getCurrentUrl(), urlWomensShirts);

        homePage.getFooter().navigateToWomenShoesFromFooter();
        Assert.assertEquals(driver.getCurrentUrl(), urlWomensShoes);

        homePage.getFooter().navigateToForHerAccessoriesFromFooter();
        Assert.assertEquals(driver.getCurrentUrl(), urlWomensAccessories);

    }
}
