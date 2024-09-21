package com.ecommerce.tests.components.footer;

import com.ecommerce.base.BaseTest;
import com.ecommerce.data.FooterAppstoresData;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class FooterAppstoresTest extends BaseTest {
    @Test(description = "11.1-4.1 | TC > Verify Click Footer Menu AOD # https://app.clickup.com/t/8689r628b ",
            dataProvider = "appstoresData", dataProviderClass = FooterAppstoresData.class)
    public void testAppstoresNavigation(String appStoreUrl, String homePageUrl, String iconXpath){
        driver.findElement(By.xpath(iconXpath)).click();
        String originalWindow = driver.getWindowHandle();
        switchToNewWindow(originalWindow);
        String urlNewWindow = driver.getCurrentUrl();

        Assert.assertEquals(urlNewWindow,appStoreUrl);

        driver.switchTo().window(originalWindow);

        String urlPreviousWindow = driver.getCurrentUrl();

        Assert.assertEquals(urlPreviousWindow,homePageUrl);
    }
    private WebDriverWait getWait(int seconds){
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }


    private void switchToNewWindow(String originalWindow){
        getWait(10).until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

}
