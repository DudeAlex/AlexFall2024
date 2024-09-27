package com.ecommerce.tests.components.header;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.ecommerce.base.BaseTest;
import com.ecommerce.utils.WaitUtils;

import java.time.Duration;

public class CheckMenuRedirectionTests extends BaseTest {

    private void navigateAndCheckUrl(By locator, String expectedUrl) throws InterruptedException {
        WebElement element;
        try {

            element = WaitUtils.visibilityOfElementLocated(driver, locator, 10);  // Wait for up to 10 seconds

            // Click the element to navigate
            element.click();

            // Wait for the URL to change (up to 10 seconds)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlToBe(expectedUrl)); // Wait for the URL to be the expected one

            // Verify the URL of the current page
            String currentUrl = driver.getCurrentUrl();
            if (!currentUrl.equals(expectedUrl)) {
                System.out.println("URL verification failed. Expected: " + expectedUrl + ", but got: " + currentUrl);
                throw new RuntimeException("URL verification failed. Expected: " + expectedUrl + ", but got: " + currentUrl);
            }
            System.out.println("URL verification passed: " + currentUrl);
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Test(description = "0.1 | Test Case 0.1.1: Redirection to Home Page # https://app.clickup.com/t/8689wweuq")
    public void testHomePageRedirection() throws InterruptedException {
        navigateAndCheckUrl(By.xpath("//*[@id='menu-item-1226']/a"), "https://askomdch.com/");
    }

    @Test(description = "0.1 | Test Case 0.1.2: Redirection to Store Page # https://app.clickup.com/t/8689wwg41")
    public void testStorePageRedirection() throws InterruptedException {
        navigateAndCheckUrl(By.xpath("//*[@id='menu-item-1227']/a"), "https://askomdch.com/store/");
    }

    @Test(description = "0.1 | Test Case 0.1.3: Redirection to Men's Section # https://app.clickup.com/t/8689wwgxf")
    public void testMensSectionRedirection() throws InterruptedException {
        navigateAndCheckUrl(By.xpath("//*[@id='menu-item-1228']/a"), "https://askomdch.com/product-category/men/");
    }

    @Test(description = "0.1 | Test Case 0.1.4: Redirection to Women’s Section # https://app.clickup.com/t/8689wwhgk")
    public void testWomensSectionRedirection() throws InterruptedException {
        navigateAndCheckUrl(By.xpath("//*[@id='menu-item-1229']/a"), "https://askomdch.com/product-category/women/");
    }

    @Test(description = "0.1 | Test Case 0.1.5: Redirection to Accessories Section # https://app.clickup.com/t/8689wwj34")
    public void testAccessoriesSectionRedirection() throws InterruptedException {
        navigateAndCheckUrl(By.xpath("//*[@id='menu-item-1230']/a"), "https://askomdch.com/product-category/accessories/");
    }

    @Test(description = "0.1 | Test Case 0.1.6: Redirection to Account Section # https://app.clickup.com/t/8689wwjun")
    public void testAccountSectionRedirection() throws InterruptedException {
        navigateAndCheckUrl(By.xpath("//*[@id='menu-item-1237']/a"), "https://askomdch.com/account/");
    }

    @Test(description = "0.1 | Test Case 0.1.7: Redirection to About section # https://app.clickup.com/t/8689wwkvh")
    public void testAboutSectionRedirection() throws InterruptedException {
        navigateAndCheckUrl(By.xpath("//*[@id='menu-item-1232']/a"), "https://askomdch.com/about/");
    }

    @Test(description = "0.1 | Test Case 0.1.8: Redirection to Contact us page # https://app.clickup.com/t/8689xrqf2")
    public void testContactUsSectionRedirection() throws InterruptedException {
        navigateAndCheckUrl(By.xpath("//*[@id='menu-item-1233']/a"), "https://askomdch.com/contact-us/");
    }
}
