package com.ecommerce.tests.women;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.components.LeftSidebar;
import com.ecommerce.pom.pages.WomenPage;
import com.ecommerce.utils.JSExecutorUtils;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WomenBrowseByCategoriesTest extends BaseTest {

    @Test (description = "4_6 - 1 | TC Browser by Categories In Women displays correct text # https://app.clickup.com/t/8689p8y7jadd ")
    public void testDefaultBrowseByCategoriesText() {

        WomenPage womenPage = new WomenPage(driver);
        womenPage.load();

        LeftSidebar leftSidebar = new LeftSidebar(driver);
        JSExecutorUtils.scrollIntoView(driver, leftSidebar.getBrowseByCategoriesTitle());
        leftSidebar.activateXIconByClickingTitle();

        String defaultText = leftSidebar.getDefaultBrowseByCategoriesText(driver);

        Assert.assertEquals(defaultText, "Women  (7)", "Expected default text doesn't match displayed text");
    }
}
