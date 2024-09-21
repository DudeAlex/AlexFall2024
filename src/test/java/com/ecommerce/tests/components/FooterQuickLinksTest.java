package com.ecommerce.tests.components;

import com.ecommerce.base.BaseTest;
import com.ecommerce.data.FooterQuickLinksData;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FooterQuickLinksTest extends BaseTest {
    @Test(description = "11.1-1.1-5 | TC > Verify Navigation Footer Menu List Quick Links # https://app.clickup.com/t/8689r5uyy ",
            dataProvider = "quickLinksData", dataProviderClass = FooterQuickLinksData.class)
    public void testFooterForHimNavigation(String url, String linkXpath){

        driver.findElement(By.xpath(linkXpath)).click();

        Assert.assertEquals(driver.getCurrentUrl(), url);
    }


}
