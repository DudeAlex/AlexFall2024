package com.ecommerce.pom;

import org.openqa.selenium.WebDriver;

public abstract class BaseModel {

    private WebDriver driver;

    public BaseModel(WebDriver driver){
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }
}

