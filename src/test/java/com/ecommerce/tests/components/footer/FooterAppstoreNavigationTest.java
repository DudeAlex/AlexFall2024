package com.ecommerce.tests.components.footer;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.HomePage;
import com.ecommerce.utils.WaitUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FooterAppstoreNavigationTest extends BaseTest {
    @Test(description = "11.1-4.1 | TC > Verify Click Footer Menu AOD # https://app.clickup.com/t/8689r628b ")
    public void testAppstoresNavigation(){

        String appStoreUrl = "https://www.apple.com/in/app-store/";
        String homePageUrl = "https://askomdch.com/";

        HomePage homePage = new HomePage(driver);
        homePage.getFooter().navigateToAppStoreFromFooter();
        String originalWindow = driver.getWindowHandle();
        switchToNewWindow(originalWindow);
        String urlNewWindow = driver.getCurrentUrl();

        Assert.assertEquals(urlNewWindow,appStoreUrl);

        driver.switchTo().window(originalWindow);

        String urlPreviousWindow = driver.getCurrentUrl();

        Assert.assertEquals(urlPreviousWindow,homePageUrl);
    }

    private void switchToNewWindow(String originalWindow){
        WaitUtils.numberOfWindowsToBe(driver,2,2);
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
}