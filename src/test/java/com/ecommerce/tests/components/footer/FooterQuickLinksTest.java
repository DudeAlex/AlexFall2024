package com.ecommerce.tests.components.footer;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.ecommerce.pom.pages.EndPoints.*;

public class FooterQuickLinksTest extends BaseTest {
    @Test(description = "11.1-1.1-5 | TC > Verify Navigation Footer Menu List Quick Links # https://app.clickup.com/t/8689r5uyy ")
    public void testFooterForHimNavigation(){

        HomePage homePage = new HomePage(driver);

        homePage.getFooter().navigateToHomepageFromFooter();
        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL);

        homePage.getFooter().navigateToAboutUsPageFromFooter();
        Assert.assertEquals(driver.getCurrentUrl(), ABOUT_URL);

        homePage.getFooter().navigateToMyAccountPageFromFooter();
        Assert.assertEquals(driver.getCurrentUrl(), ACCOUNT_URL);

        homePage.getFooter().navigateToCartPageFromFooter();
        Assert.assertEquals(driver.getCurrentUrl(), CART_URL);

        homePage.getFooter().navigateToContactUsPageFromFooter();
        Assert.assertEquals(driver.getCurrentUrl(), CONTACT_US_URL);
    }

}
