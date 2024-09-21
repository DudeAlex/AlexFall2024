package com.ecommerce.tests.components.footer;

import com.ecommerce.base.BaseTest;
import com.ecommerce.data.FooterQuickLinksData;
import net.bytebuddy.implementation.bytecode.ShiftRight;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class FooterQuickLinksTest extends BaseTest {
    @Test(description = "11.1-1.1-5 | TC > Verify Navigation Footer Menu List Quick Links # https://app.clickup.com/t/8689r5uyy ",
            dataProvider = "quickLinksData", dataProviderClass = FooterQuickLinksData.class)
    public void testFooterForHimNavigation(String url, String linkXpath){

        driver.findElement(By.xpath(linkXpath)).click();

        Assert.assertEquals(driver.getCurrentUrl(), url);
    }

    @Test(description = "11.1 - 5.1 | TC > Verify footer quick links with https requests # https://app.clickup.com/t/8689vptv7 ")
    public void testQuickLinksHTTPS() {
        List<WebElement> quickLinks = driver.findElements(By.xpath("//ul[@id = 'menu-quick-links']/li/a"));

        for (WebElement link : quickLinks) {
            String url = link.getAttribute("href");
            verifyQuickLink(url);
        }
    }
    private void verifyQuickLink(String url) {
         try {
             URL link = new URL(url);
             HttpsURLConnection httpsURLConnection = (HttpsURLConnection) link.openConnection();
             httpsURLConnection.setConnectTimeout(3000);
             httpsURLConnection.connect();
             if (httpsURLConnection.getResponseCode() == 200) {
                 System.out.println("Valid link: " + url + " with HTTP response code: " + httpsURLConnection.getResponseCode());
             } else {
                 System.out.println("Broken link: " + url + " with HTTP response code: " + httpsURLConnection.getResponseCode());
             }
         } catch (MalformedURLException malformedURLException) {
             System.out.println(url + " - is broken " + malformedURLException.getMessage());
        } catch (IOException ioException) {
             System.out.println(ioException.getMessage());
         }
    }

}
