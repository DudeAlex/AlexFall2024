package com.ecommerce.tests.about;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.AboutPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AboutUsOurTeamTest extends BaseTest {

    @Test (description = "7.1 - 1 | TC Verify team member's image alignment in About Us # https://app.clickup.com/t/868antxxf")
    public void testOurTeamImage() {

        AboutPage aboutPage = new AboutPage(driver);
        aboutPage.load()
                 .scrollToOneThirdOfAboutUsPage();

        double widthOfOuterImage = aboutPage.getOuterImageWidth();
        double widthOfInnerImage = aboutPage.getInnerImageWidth();

        double heightOfOuterImage = aboutPage.getOuterImageHeight();
        double heightOfInnerImage =  aboutPage.getInnerImageHeight();

        Assert.assertTrue(widthOfOuterImage > widthOfInnerImage && heightOfOuterImage > heightOfInnerImage,
            "Team Member's image is not properly aligned");
    }
}
