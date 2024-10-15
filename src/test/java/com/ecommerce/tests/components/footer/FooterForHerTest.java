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

        HomePage homePage = new HomePage(driver);
        homePage.getFooter().navigateToWomenPageFromFooter();

        //Assert.assertEquals(driver.getCurrentUrl(), url);

        homePage.getFooter().navigateToWomenJeansFromFooter();
        homePage.getFooter().navigateToWomenShirtsFromFooter();
        homePage.getFooter().navigateToWomenShoesFromFooter();
        homePage.getFooter().navigateToForHerAccessoriesFromFooter();

    }
}
