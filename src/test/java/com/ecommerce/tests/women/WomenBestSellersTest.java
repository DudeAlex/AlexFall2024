package com.ecommerce.tests.women;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.WomenPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WomenBestSellersTest extends BaseTest {
    @Test(description = "4_5 TC | Find and verify best seller item on Women Page# https://app.clickup.com/t/8689p8y1t")
    public void testVerifyBestSellerItems(){
        WomenPage womenPage = new WomenPage(driver);
        womenPage.load();
        Assert.assertEquals(womenPage.getBestSellerTitle(),"Our Best Sellers","Best Seller Title is not correct");

    }
}
