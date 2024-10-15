package com.ecommerce.pom.components;

import com.ecommerce.pom.AboutPage;
import com.ecommerce.pom.pages.*;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class Footer {

    private WebDriver driver;

    public Footer(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {

        return driver;
    }

    By quickLinksHome = By.xpath("//section[@id='nav_menu-7']//a[contains(text(),'Home')]"); // Home
    By quickLinksAbout = By.xpath("//section[@id='nav_menu-7']//a[contains(text(),'About')]"); // About
    By quickLinksMyAccount = By.xpath("//section[@id='nav_menu-7']//a[contains(text(),'My Account')]"); // My Account
    By quickLinksCart = By.xpath("//section[@id='nav_menu-7']//a[contains(text(),'Cart')]"); // Cart
    By quickLinksContactUs = By.xpath("//section[@id='nav_menu-7']//a[contains(text(),'Contact Us')]"); // ContactUs

    By forHerWomen = By.xpath("//section[@id='nav_menu-8']//a[text()='Women']"); // Women
    By forHerWomenJeans = By.xpath("//section[@id='nav_menu-8']//a[text()='Women’s Jeans']"); // Women's Jeans
    By forHerWomenShirts = By.xpath("//section[@id='nav_menu-8']//a[text()='Women’s Shirts']"); // Women's Shirts
    By forHerWomenShoes = By.xpath("//section[@id='nav_menu-8']//a[text()='Women’s Shoes']"); // Women's Shoes
    By forHerAccessories = By.xpath("//section[@id='nav_menu-8']//a[text()='Accessories']"); // Accessories

    By forHimMen = By.xpath("//section[@id='nav_menu-12']//a[text()='Men']"); // Men
    By forHimMenJeans = By.xpath("//section[@id='nav_menu-12']//a[text()='Men’s Jeans']"); // Men's Jeans
    By forHimMenShirts = By.xpath("//section[@id='nav_menu-12']//a[text()='Men’s Shirts']"); // Men's Shirts
    By forHimMenShoes = By.xpath("//section[@id='nav_menu-12']//a[text()='Men’s Shoes']]"); // Men's Shoes
    By forHimAccessories = By.xpath("//section[@id='nav_menu-12']//a[text()='Accessories']"); // Accessories

    By appStoreIcon = By.xpath("//a[@ href ='https://www.apple.com/in/app-store/']"); // app store icon
    By googlePlayIcon = By.xpath("//a[@ href ='https://play.google.com/store']"); // google play icon

// methods for clicking on the links from the footer "Quick links" section

    public HomePage navigateToHomepageFromFooter(){
        WaitUtils.visibilityOfElementLocated(getDriver(), quickLinksHome).click();
        return new HomePage(getDriver());
    }

    public AboutPage navigateToAboutUsPageFromFooter(){
        WaitUtils.visibilityOfElementLocated(getDriver(), quickLinksAbout).click();
        return new AboutPage (getDriver());
    }

    public AccountPage navigateToMyAccountPageFromFooter(){
        WaitUtils.visibilityOfElementLocated(getDriver(), quickLinksMyAccount).click();
        return new AccountPage(getDriver());
    }

    public CartPage navigateToCartPageFromFooter(){
        WaitUtils.visibilityOfElementLocated(getDriver(), quickLinksCart).click();
        return new CartPage(getDriver());
    }

    public ContactUsPage navigateToContactUsPageFromFooter() {
        WaitUtils.visibilityOfElementLocated(getDriver(), quickLinksContactUs).click();
        return new ContactUsPage(getDriver());
    }

    // methods for clicking on the links from the footer "For Her" section

    public WomenPage navigateToWomenPageFromFooter() {
        WaitUtils.visibilityOfElementLocated(getDriver(), forHerWomen).click();
        return new WomenPage(getDriver());
    }
    public WomensJeansPage navigateToWomenJeansFromFooter() {
        WaitUtils.visibilityOfElementLocated(getDriver(), forHerWomenJeans).click();
        return new WomensJeansPage(getDriver());
    }
    public WomensShirtsPage navigateToWomenShirtsFromFooter() {
        WaitUtils.visibilityOfElementLocated(getDriver(), forHerWomenShirts).click();
        return new WomensShirtsPage(getDriver());
    }
    public WomensShoesPage navigateToWomenShoesFromFooter() {
        WaitUtils.visibilityOfElementLocated(getDriver(), forHerWomenShoes).click();
        return new WomensShoesPage(getDriver());
    }

    public AccessoriesPage navigateToForHerAccessoriesFromFooter() {
        WaitUtils.visibilityOfElementLocated(getDriver(), forHerAccessories).click();
        return new AccessoriesPage(getDriver());
    }

    //methods for clicking on the links from the footer "For Him" section

    public MenPage navigateToMenPageFromFooter() {
        WaitUtils.visibilityOfElementLocated(getDriver(), forHimMen).click();
        return new MenPage(getDriver());
    }

    public MensJeansPage navigateToMensJeansFromFooter() {
        WaitUtils.visibilityOfElementLocated(getDriver(), forHimMenJeans).click();
        return new MensJeansPage(getDriver());
    }

    public MensShirtsPage navigateToMensShirtsFromFooter() {
        WaitUtils.visibilityOfElementLocated(getDriver(), forHimMenShirts).click();
        return new MensShirtsPage(getDriver());
    }

    public MensShoesPage navigateToMensShoesFromFooter() {
        WaitUtils.visibilityOfElementLocated(getDriver(), forHimMenShoes).click();
        return new MensShoesPage(getDriver());
    }

    public AccessoriesPage navigateToForHimAccessoriesPageFromFooter() {
        WaitUtils.visibilityOfElementLocated(getDriver(), forHimAccessories).click();
        return new AccessoriesPage(getDriver());
    }

}
