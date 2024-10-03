package com.ecommerce.tests.store;

import com.ecommerce.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SeeItemsPicturesTest extends BaseTest {
    static WebElement nextPageButton;
    static WebElement numberOfTheCartsPageButton;

    @Test
    public void visibilityOfImagesOnTheProductCartTest(){

        driver.findElement(By.id("menu-item-1227")).click(); // go to the Store page

        Assert.assertEquals(driver.getCurrentUrl(), "https://askomdch.com/store/"); // check if current page is the Store page

        nextPageButton = driver.findElement(By.xpath("//a[@class='next page-numbers']")); // find the nextPage button

        numberOfTheCartsPageButton = driver.findElement(By.xpath("//span[@class='page-numbers current' and @aria-current='page' and text()='1']"));

        Assert.assertTrue(numberOfTheCartsPageButton.isDisplayed(), "Element is not visible"); // check if there are list of product pages

        checkVisibilityOfTheImages();

        do{
            checkVisibilityOfTheImages();
            nextPageButton.click();
        }while(checkIfNextPageExist());

    }

    private boolean checkIfNextPageExist() {
        try {nextPageButton.isDisplayed();
            return true;
        }
        catch (StaleElementReferenceException e) {
            return false;
        }
    }

    private void checkVisibilityOfTheImages(){

        List<WebElement> webElementList = driver.findElements(By.xpath("//li[contains(@class, 'product')" +
                " and contains(@class, 'type-product') and contains(@class, 'post-')]"));

        for (WebElement webElement: webElementList) {
            WebElement imageElement = webElement.findElement(By.tagName("img"));
            Assert.assertTrue(imageElement.isDisplayed());
        }
    }

}
