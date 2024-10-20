package com.ecommerce.pom.pages;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.Loadable;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static com.ecommerce.pom.EndPoints.CART_URL;
import static com.ecommerce.pom.EndPoints.EDIT_ACCOUNT_URL;
import static java.sql.DriverManager.getDriver;

public class EditAccountPage extends BaseTest implements Loadable {


public EditAccountPage(WebDriver driver) {
    super(driver);

    }

    public EditAccountPage load() {
       getDriver().get(EDIT_ACCOUNT_URL);

        return this;

    }

}