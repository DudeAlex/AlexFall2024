package com.ecommerce.tests.women;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.WomenPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WomenProductImageTest extends BaseTest {

    @Test (description = "4_11 - 1 | TC Verify Denim Blue Jeans Image # https://app.clickup.com/t/8689p8y3b")
    public void testProductImage() {

        String expectedImageSrcUrl = "https://askomdch.com/wp-content/uploads/2021/06/pro-9-300x300.jpg";

        WomenPage womenPage = new WomenPage(driver);
        womenPage.load()
                 .scrollToOneThirdOfWomenPage();

        Assert.assertEquals(womenPage.getImageAttribute("src"),
                expectedImageSrcUrl, "Wrong image is displayed");
    }
}
