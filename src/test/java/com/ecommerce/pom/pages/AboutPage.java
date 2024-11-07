package com.ecommerce.pom.pages;

import com.ecommerce.pom.BasePage;
import com.ecommerce.pom.Loadable;
import com.ecommerce.utils.JSExecutorUtils;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.ecommerce.pom.EndPoints.ABOUT_URL;

public class AboutPage extends BasePage implements Loadable {

    By harveySpectorOuterImage = By.xpath("//h4[contains(text(), 'Harvey Spector')]/../..");
    By harveySpectorInnerImage = By.xpath("//h4[contains(text(), 'Harvey Spector')]/..//img");

    public AboutPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public AboutPage load() {
        getDriver().get(ABOUT_URL);
        return this;
    }

    public void scrollToOneThirdOfAboutUsPage() {
        JSExecutorUtils.scrollToOneThirdOfPage(getDriver());
    }

    public double getOuterImageWidth() {
        WebElement outerImage = WaitUtils.visibilityOfElementLocated(getDriver(), harveySpectorOuterImage);
        Rectangle rectangle = outerImage.getRect();
        return rectangle.getWidth();
    }

    public double getOuterImageHeight() {
        WebElement outerImage = WaitUtils.visibilityOfElementLocated(getDriver(), harveySpectorOuterImage);
        Rectangle rectangle = outerImage.getRect();
        return rectangle.getHeight();
    }

    public double getInnerImageWidth() {
        WebElement innerImage = WaitUtils.visibilityOfElementLocated(getDriver(), harveySpectorInnerImage);
        Rectangle rectangle = innerImage.getRect();
        return rectangle.getWidth();
    }

    public double getInnerImageHeight() {
        WebElement innerImage = WaitUtils.visibilityOfElementLocated(getDriver(), harveySpectorInnerImage);
        Rectangle rectangle = innerImage.getRect();
        return rectangle.getHeight();
    }


}
