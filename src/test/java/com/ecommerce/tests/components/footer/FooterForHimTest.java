package com.ecommerce.tests.components.footer;

import com.ecommerce.base.BaseTest;
import com.ecommerce.data.FooterForHimData;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FooterForHimTest extends BaseTest {
    @Test(description = "11.1-3.1-5 | TC > Verify Navigation Footer Menu List For Him  # https://app.clickup.com/t/8689r62er ",
            dataProvider = "forHimData", dataProviderClass = FooterForHimData.class)
    public void testFooterForHimNavigation(String url, String linkXpath){

        driver.findElement(By.xpath(linkXpath)).click();

        Assert.assertEquals(driver.getCurrentUrl(), url);
    }



}
