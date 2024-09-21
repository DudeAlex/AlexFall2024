package com.ecommerce.tests.components;

import com.ecommerce.base.BaseTest;
import com.ecommerce.data.FooterForHerData;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FooterForHerTest extends BaseTest {
    @Test(description = "11.1-2.1-5 | TC > Verify Navigation Footer Menu List For Her # https://app.clickup.com/t/8689r60e5",
            dataProvider = "forHerData", dataProviderClass = FooterForHerData.class)
    public void testFooterForHerNavigation(String url, String linkXpath){

        driver.findElement(By.xpath(linkXpath)).click();

        Assert.assertEquals(driver.getCurrentUrl(), url);
    }



}
