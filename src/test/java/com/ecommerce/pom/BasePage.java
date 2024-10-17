package com.ecommerce.pom;

import com.ecommerce.pom.components.Footer;
import com.ecommerce.pom.components.Header;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    private WebDriver driver;
    private Header header;

    private Footer footer;


    public BasePage(WebDriver driver){
        this.driver = driver;
        header = new Header(driver);
        footer = new Footer(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public Header getHeader() {
        return header;
    }

    public Footer getFooter() {

        return footer;
    }

}
