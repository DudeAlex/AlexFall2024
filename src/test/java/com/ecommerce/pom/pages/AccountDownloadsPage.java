package com.ecommerce.pom.pages;

import org.openqa.selenium.WebDriver;

import static com.ecommerce.pom.EndPoints.ACCOUNT_DOWNLOADS_URL;

public class AccountDownloadsPage extends AccountPage{
    public AccountDownloadsPage(WebDriver driver) {
        super(driver);
    }

    public AccountDownloadsPage load(){
        getDriver().get(ACCOUNT_DOWNLOADS_URL);
        return this;
    }

}
