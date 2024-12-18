package com.ecommerce.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public abstract class BaseModel {

    private WebDriver driver;

    public BaseModel(WebDriver driver){

        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

}

