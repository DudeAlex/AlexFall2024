package com.ecommerce.tests.components.header;

import com.ecommerce.base.BaseTest;
import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class HeaderVisibleTest extends BaseTest {
    @Test(description = "10.1-1-1 | TC Verify Header Placement # https://app.clickup.com/t/8689zuu09 ")
    public void testHeaderVisible() {

        WebElement header = driver.findElement(By.tagName("header"));

        Assert.isTrue(header.isDisplayed(),"Header is not visible on the page.");

        int headerY = header.getLocation().getY();

        Assert.isTrue(headerY == 0,"Header is not correctly positioned.");
    }
}
