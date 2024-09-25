package com.ecommerce.tests.components.header;
import org.openqa.selenium.WebElement;
import com.ecommerce.base.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class CheckMenuRedirectionTests extends BaseTest {
    private void navigateAndCheckUrl(String xpath, String expectedUrl) throws InterruptedException {
        WebElement element;
        try {
            element = driver.findElement(By.xpath(xpath));
            element.click();
        } catch (Exception e) {
            System.out.println("Element not found");
            throw new RuntimeException(e);
        }

        int count = 0;
        while (count < 10) {
            if (driver.getCurrentUrl().equals(expectedUrl)) {
                break;
            }
            Thread.sleep(200);
            count++;
        }

        // Verify the URL of the current page
        String currentUrl = driver.getCurrentUrl();
        if (!currentUrl.equals(expectedUrl)) {
            System.out.println("URL verification failed. Expected: " + expectedUrl + ", but got: " + currentUrl);
            throw new RuntimeException("URL verification failed. Expected: " + expectedUrl + ", but got: " + currentUrl);
        }
        System.out.println("URL verification passed: " + currentUrl);
        Thread.sleep(2000);  // Waiting after the operation
    }

    @Test
    public void testHomePageRedirection() throws InterruptedException {
        navigateAndCheckUrl("//*[@id=\"menu-item-1226\"]/a", "https://askomdch.com/");
    }

    @Test
    public void testStorePageRedirection() throws InterruptedException {
        navigateAndCheckUrl("//*[@id=\"menu-item-1227\"]/a", "https://askomdch.com/store/");
    }

    @Test
    public void testMensSectionRedirection() throws InterruptedException {
        navigateAndCheckUrl("//*[@id=\"menu-item-1228\"]/a", "https://askomdch.com/product-category/men/");
    }

    @Test
    public void testWomensSectionRedirection() throws InterruptedException {
        navigateAndCheckUrl("//*[@id=\"menu-item-1229\"]/a", "https://askomdch.com/product-category/women/");
    }

    @Test
    public void testAccessoriesSectionRedirection() throws InterruptedException {
        navigateAndCheckUrl("//*[@id=\"menu-item-1230\"]/a", "https://askomdch.com/product-category/accessories/");
    }

    @Test
    public void testAccountSectionRedirection() throws InterruptedException {
        navigateAndCheckUrl("//*[@id=\"menu-item-1237\"]/a", "https://askomdch.com/account/");
    }

    @Test
    public void testAboutSectionRedirection() throws InterruptedException {
        navigateAndCheckUrl("//*[@id=\"menu-item-1232\"]/a", "https://askomdch.com/about/");
    }

    @Test
    public void testContactUsSectionRedirection() throws InterruptedException {
        navigateAndCheckUrl("//*[@id=\"menu-item-1233\"]/a", "https://askomdch.com/contact-us/");
    }
}




