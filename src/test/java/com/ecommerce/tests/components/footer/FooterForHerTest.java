package com.ecommerce.tests.components.footer;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.ecommerce.pom.EndPoints.*;

public class FooterForHerTest extends BaseTest {
    @Test(description = "11.1-2.1-5 | TC > Verify Navigation Footer Menu List For Her # https://app.clickup.com/t/8689r60e5")
    public void testFooterForHerNavigation(){

        HomePage homePage = new HomePage(driver);

        homePage.getFooter().navigateToWomenPageFromFooter();
        Assert.assertEquals(driver.getCurrentUrl(), WOMEN_URL);

        homePage.getFooter().navigateToWomenJeansFromFooter();
        Assert.assertEquals(driver.getCurrentUrl(), WOMENS_JEANS_URL);

        homePage.getFooter().navigateToWomenShirtsFromFooter();
        Assert.assertEquals(driver.getCurrentUrl(), WOMENS_SHIRTS_URL);

        homePage.getFooter().navigateToWomenShoesFromFooter();
        Assert.assertEquals(driver.getCurrentUrl(), WOMENS_SHOES_URL);

        homePage.getFooter().navigateToForHerAccessoriesFromFooter();
        Assert.assertEquals(driver.getCurrentUrl(), ACCESSORIES_URL);

    }
}
