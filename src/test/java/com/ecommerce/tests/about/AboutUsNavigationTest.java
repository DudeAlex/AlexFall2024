package com.ecommerce.tests.about;

import com.ecommerce.base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AboutUsNavigationTest extends BaseTest {

    @Test (description = "*-* | TC > Navigation to About Us from the Top bar")
    public void testAboutUsFromTopBar () {
        driver.findElement(By.xpath("(//*[contains(text(),'About')])[1]")).click(); //xPath = //*[@id="menu-item-1232"]/a

        String aboutUsHeader = driver.findElement(By.className("has-text-align-center")).getText();
        Assert.assertEquals(aboutUsHeader, "About Us");
    }

    @Test (description = "*-* | TC > Navigation to About Us from Footer")
    public void testAboutUsFromFooter() {
        driver.findElement(By.xpath("(//*[contains(text(),'About')])[3]")).click(); //xPath = //*[@id="menu-item-1255"]/a

        String aboutUsHeader = driver.findElement(By.className("has-text-align-center")).getText();
        Assert.assertEquals(aboutUsHeader, "About Us");
    }

    public void getPagesTitles() {

    }

}
