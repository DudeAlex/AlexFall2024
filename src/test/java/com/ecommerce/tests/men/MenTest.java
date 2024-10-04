package com.ecommerce.tests.men;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.HomePage;
import com.ecommerce.utils.CollectToListUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class MenTest extends BaseTest {

    @Test(description = "3_16 - 1 | TC > Men > Verify categories # https://app.clickup.com/t/8689zw695")
    public void testProductsBelongToCategories() {
        List<String> productCategories = new HomePage(driver)
                .navigateToMenPage()
                .collectCategories();
        for (String categories : productCategories) {
            Assert.assertEquals(categories, "Men");
        }
    }

    @Test(description = "3.9-1.1 | TC> Man> Verify Sale items price # https://app.clickup.com/t/8689v3293")
    public void testVerifyReducedPriceForSaleItems() {
        List<Boolean> productList = new HomePage(driver)
                .navigateToMenPage()
                .areProductsOnSaleHaveCrossedPrice();

        for (Boolean isOnSalAndHasCrossedPrice : productList) {

            Assert.assertTrue(isOnSalAndHasCrossedPrice, "Product does not have sale tag or crossed-out price");
        }
    }
}