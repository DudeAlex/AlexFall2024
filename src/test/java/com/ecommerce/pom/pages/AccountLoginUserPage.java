package com.ecommerce.pom.pages;

import com.ecommerce.pom.BasePage;
import com.ecommerce.pom.Loadable;
import com.ecommerce.pom.components.AccountDashboard;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AccountLoginUserPage extends BasePage {

    By welcomeNewUserText = By.xpath("//p[contains(text(),'Hello')]");
    By logoutLinkFromMainContent = By.xpath("//div[@class='woocommerce-MyAccount-content']//a[text()='Log out']");




    protected AccountDashboard accountDashboard;
    public AccountLoginUserPage (WebDriver driver) {
        super(driver);
        this.accountDashboard = new AccountDashboard(driver);
    }

    public AccountDashboard getAccountDashboard() {
        return accountDashboard;
    }


    public String getWelcomeNewUserText() {
        return WaitUtils.visibilityOf(getDriver(), welcomeNewUserText).getText();
    }

    public AccountPage logOutFromSideMenu() {
        return accountDashboard.clickDashboardLogOut();
    }

    public AccountLoginUserPage assertLogin() {
        List<WebElement> logoutLink = WaitUtils.visibilityOfAllElementsLocatedBy(getDriver(), logoutLinkFromMainContent, 2);
        if (logoutLink.isEmpty()) {
            throw new AssertionError("User was not logged-in");
        } else {
            System.out.println("Login was successful");
        }
        return this;
    }
}
