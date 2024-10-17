package com.ecommerce.tests.components.footer;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FooterQuickLinksTest extends BaseTest {
    @Test(description = "11.1-1.1-5 | TC > Verify Navigation Footer Menu List Quick Links # https://app.clickup.com/t/8689r5uyy ")
    public void testFooterForHimNavigation(){

        String urlHome = "https://askomdch.com/";
        String urlAbout = "https://askomdch.com/about/";
        String urlMyAccount = "https://askomdch.com/account/";
        String urlCart = "https://askomdch.com/cart/";
        String urlContactUs = "https://askomdch.com/contact-us/";

        HomePage homePage = new HomePage(driver);

        homePage.getFooter().navigateToHomepageFromFooter();
        Assert.assertEquals(driver.getCurrentUrl(), urlHome);

        homePage.getFooter().navigateToAboutUsPageFromFooter();
        Assert.assertEquals(driver.getCurrentUrl(), urlAbout);

        homePage.getFooter().navigateToMyAccountPageFromFooter();
        Assert.assertEquals(driver.getCurrentUrl(), urlMyAccount);

        homePage.getFooter().navigateToCartPageFromFooter();
        Assert.assertEquals(driver.getCurrentUrl(), urlCart);

        homePage.getFooter().navigateToContactUsPageFromFooter();
        Assert.assertEquals(driver.getCurrentUrl(), urlContactUs);
    }

}
