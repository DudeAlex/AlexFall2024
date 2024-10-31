package com.ecommerce.tests.women;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.components.LeftSidebar;
import com.ecommerce.pom.pages.WomenPage;
import com.ecommerce.utils.JSExecutorUtils;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class WomenFilterByPriceTest extends BaseTest {

    @Test( description = "4_8-1 | TC Verify products in Women are filtered by price # https://app.clickup.com/t/8689p8y97")
    public void testFilterByPrice()  {
        WomenPage womenPage = new WomenPage(driver);
        womenPage.load();
        LeftSidebar leftSidebar = new LeftSidebar(driver);
        WebElement minPrice = WaitUtils.presenceOfElementLocated(driver,leftSidebar.getLeftSliderHandle());
        JSExecutorUtils.scrollIntoView(driver, leftSidebar.getLeftSliderHandle());
        Actions action = new Actions(driver);
        action.dragAndDropBy(minPrice, 90,0).perform();
        womenPage.clickFilterButton();

        Assert.assertTrue(leftSidebar.getMinAvailableFilterPrice() == 50);

        List<Double> filteredPrices =  womenPage.getAllProductsPriceList();
        Assert.assertTrue(filteredPrices.stream()
                .allMatch(element -> element <= 100.0 && element >= 50.0),"Prices don't match $50-100 range");
        }
    }

