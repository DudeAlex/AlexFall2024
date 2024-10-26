package com.ecommerce.tests.components.footer;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.ecommerce.pom.EndPoints.*;

public class FooterForHimTest extends BaseTest {
    @Test(description = "11.1-3.3-1 | TC > Verify Navigation Footer Menu List For Him  # https://app.clickup.com/t/8689r62er ")
    public void testFooterForHimNavigation(){

        HomePage homePage = new HomePage(driver);

        homePage.getFooter().navigateToMenPageFromFooter();
        Assert.assertEquals(driver.getCurrentUrl(), MEN_URL);

        homePage.getFooter().navigateToMensJeansFromFooter();
        Assert.assertEquals(driver.getCurrentUrl(), MENS_JEANS_URL);

        homePage.getFooter().navigateToMensShirtsFromFooter();
        Assert.assertEquals(driver.getCurrentUrl(), MENS_SHIRTS_URL);

        homePage.getFooter().navigateToMensShoesFromFooter();
        Assert.assertEquals(driver.getCurrentUrl(), MENS_SHOES_URL);

        homePage.getFooter().navigateToForHimAccessoriesFromFooter();
        Assert.assertEquals(driver.getCurrentUrl(), ACCESSORIES_URL);

    }
}
