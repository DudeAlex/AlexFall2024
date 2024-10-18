package com.ecommerce.pom.components;

import com.ecommerce.pom.BaseModel;
import org.openqa.selenium.WebDriver;

public abstract class BaseComponent extends BaseModel {

    public BaseComponent(WebDriver driver) {
        super(driver);
    }
}
