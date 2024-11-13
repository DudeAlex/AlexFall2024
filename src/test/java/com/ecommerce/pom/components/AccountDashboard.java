package com.ecommerce.pom.components;

import com.ecommerce.pom.pages.*;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountDashboard extends BaseComponent {
    public AccountDashboard(WebDriver driver) {
        super(driver);
    }

    By dashboardLink = By.xpath("//a[normalize-space()='Dashboard']");
    By ordersLink = By.xpath("//a[normalize-space()='Orders']");
    By downloadsLink = By.xpath("//a[normalize-space()='Downloads']");
    By addressesLink = By.xpath("//a[text()='Addresses']");
    By accountDetailLink = By.xpath("//a[normalize-space()='Account details']");
    By logoutLinkFromSideMenu = By.xpath("//li[contains(@class, 'woocommerce-MyAccount-navigation-link--customer-logout')]/a[text()='Logout']");


    public AccountAddressesPage clickAddressesLink() {
        WaitUtils.elementToBeClickable(getDriver(), addressesLink).click();
        return new AccountAddressesPage(getDriver());
    }

    public AccountOrdersPage clickDashboardOrdersLink() {
        WaitUtils.elementToBeClickable(getDriver(), ordersLink).click();
        return new AccountOrdersPage(getDriver());
    }

    public AccountDownloadsPage clickDashboardDownloadLink() {
        WaitUtils.elementToBeClickable(getDriver(), downloadsLink).click();
        return new AccountDownloadsPage(getDriver());
    }

    public AccountDetailsPage clickAccountDetailsLink() {
        WaitUtils.elementToBeClickable(getDriver(), accountDetailLink).click();
        return new AccountDetailsPage(getDriver());
    }

    public AccountPage clickDashboardLogOut() {
        WaitUtils.elementToBeClickable(getDriver(),logoutLinkFromSideMenu).click();
        return new AccountPage(getDriver());
    }
}