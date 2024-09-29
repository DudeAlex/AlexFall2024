package com.ecommerce.tests.men;

import com.ecommerce.base.BaseTest;
import com.ecommerce.utils.CollectToListUtils;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class MenTest extends BaseTest {

    @Test(description = "3_16 - 1 | TC > Men > Verify categories # https://app.clickup.com/t/8689zw695")
    public void testProductsBelongToCategories() {
        WaitUtils.visibilityOfElementLocated(driver, By
                .xpath("//a[@href='https://askomdch.com/product-category/men/']")).click();
        List<String> productCategories = CollectToListUtils.productsCategories(driver);
        for (String categories : productCategories) {
            Assert.assertEquals(categories, "Men");
        }
    }

}
