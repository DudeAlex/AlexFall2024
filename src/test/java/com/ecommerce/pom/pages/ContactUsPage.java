package com.ecommerce.pom.pages;

import com.ecommerce.pom.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactUsPage extends BasePage {
    @FindBy(xpath = "//p[text()='Email: ']/strong")
    WebElement email;

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    public String showContactEmail() {
        return email.getText();
    }
}
