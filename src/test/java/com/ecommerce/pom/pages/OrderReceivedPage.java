package com.ecommerce.pom.pages;

import com.ecommerce.pom.BasePage;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderReceivedPage extends BasePage {
    By notice = By.xpath("//p[@class='woocommerce-notice woocommerce-notice--success woocommerce-thankyou-order-received']");
    public OrderReceivedPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void load() {
        getDriver().get("");
    }

    public String collectNotice() {
        return WaitUtils.visibilityOfElementLocated(getDriver(), notice).getText();
    }
}
