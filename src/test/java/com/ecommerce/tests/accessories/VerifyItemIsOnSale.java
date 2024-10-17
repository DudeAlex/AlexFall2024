package com.ecommerce.tests.accessories;
import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class VerifyItemIsOnSale extends BaseTest {

    @Test(description = "5.2-3-1 | TC > Accessories > Verify items on sale #https://app.clickup.com/t/868a7y2ax")
    public void testVerifyItemIsOnSale() {
        new HomePage(driver)
                .getHeader().navigateToAccessoriesPage();
        List<WebElement> oldPrice = driver.findElements(By.xpath("//ul[@class='products columns-4']//del[@aria-hidden='true']"));

        oldPrice.forEach(x -> {
            Assert.assertTrue(x.isDisplayed(), "The price did not change");
        });
    }
}