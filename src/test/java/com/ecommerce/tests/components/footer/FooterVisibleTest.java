package com.ecommerce.tests.components.footer;

import com.ecommerce.base.BaseTest;
import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static com.ecommerce.pom.EndPoints.*;


public class FooterVisibleTest extends BaseTest {
    @Test(description = "11.1-6.1 | TC > The footer navigation menu is displayed at the down of every site page # https://app.clickup.com/t/8689wk9pk ")
    public void testFooterVisible() {

        driver.get(BASE_URL);

        WebElement footer = driver.findElement(By.tagName("footer"));

        Assert.isTrue(footer.isDisplayed(),"Footer is not visible on the page.");

        int footerY = footer.getLocation().getY();
        int footerHeight = footer.getSize().getHeight();

        Long totalPageHeight = (Long)((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");

        Assert.isTrue(footerY + footerHeight  <= totalPageHeight,"Footer is not correctly positioned.");
    }
}
