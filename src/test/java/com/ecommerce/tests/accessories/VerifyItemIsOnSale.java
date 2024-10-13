package com.ecommerce.tests.accessories;
import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class VerifyItemIsOnSale extends BaseTest {

    @Test(description = "5.1-1-1 | TC > Accessories > Verify items belong to accessories #https://app.clickup.com/t/868a7t8vp")
    public void testVerifyItemIsOnSale() {
        new HomePage(driver)
                .navigateToAccessoriesPage();
        List<WebElement> oldPrice = driver.findElements(By.xpath("//ul[@class='products columns-4']//del[@aria-hidden='true']"));

        oldPrice.forEach(x -> {
            Assert.assertTrue(x.isDisplayed(), "The price did not change");
        });
    }
}