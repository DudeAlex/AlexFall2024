package com.ecommerce.pom;

import com.ecommerce.pom.components.Header;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    private WebDriver driver;
    private Header header;

//
//    /**
//     * footer elements Quick Links
//     */
//
//
//    By quickLinksHome = By.xpath("//*[@id=\"menu-item-1254\"]/a"); // Home
//    By quickLinksAbout = By.xpath("//a[@class='menu-link' and text()='About']"); // About
//    By quickLinksMyAccount = By.xpath("//a[@class='menu-link' and text()='My Account']"); // My Account
//    By quickLinksCart = By.xpath("//a[@class='menu-link' and text()='Cart']"); // Cart
//    By quickLinksContactUs = By.xpath("//a[@class='menu-link' and text()='Contact Us']"); // ContactUs
//
//
//    /**
//     * footer elements For Her
//     */
//    By forHerWomen = By.xpath("//a[@class='menu-link' and text()='Women']"); // Women
//    By forHerWomenJeans = By.xpath("//a[@class='menu-link' and text()='Women’s Jeans']"); // Women's Jeans
//    By forHerWomenShirts = By.xpath("//a[@class='menu-link' and text()='Women’s Shirts']"); // Women's Shirts
//    By forHerWomenShoes = By.xpath("//a[@class='menu-link' and text()='Women’s Shoes']"); // Women's Shoes
//    By forHerAccessories = By.xpath("//a[@class='menu-link' and text()='Accessories']"); // Accessories
//
//
//    /**
//     * footer elements For Him
//     */
//    By forHimMen = By.xpath("//a[@class='menu-link' and text()='Men']"); // Men
//    By forHimMenJeans = By.xpath("//a[@class='menu-link' and text()='Men’s Jeans']"); // Men's Jeans
//    By forHimMenShirts = By.xpath("//a[@class='menu-link' and text()='Men’s Shirts']"); // Men's Shirts
//    By forHimMenShoes = By.xpath("//a[@class='menu-link' and text()='Men’s Shoes']"); // Men's Shoes
//    By forHimAccessories = By.xpath("//a[@class='menu-link' and text()='Accessories']"); // Accessories
//
//
//    /**
//     * footer elements AOD Mobile
//     */
//    By appStoreIcon = By.xpath("//a[@ href ='https://www.apple.com/in/app-store/']"); // app store icon
//    By googlePlayIcon = By.xpath("//a[@ href ='https://play.google.com/store']"); // google play icon


    public BasePage(WebDriver driver){
        this.driver = driver;
        header = new Header(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public Header getHeader() {
        return header;
    }
//
//    public By getQuickLinksHome() {
//        return quickLinksHome;
//    }
//
//
//    public By getQuickLinksAbout() {
//        return quickLinksAbout;
//    }
//
//
//    public By getQuickLinksMyAccount() {
//        return quickLinksMyAccount;
//    }
//
//
//    public By getQuickLinksCart() {
//        return quickLinksCart;
//    }
//
//
//    public By getQuickLinksContactUs() {
//        return quickLinksContactUs;
//    }
//
//
//    public By getForHerWomen() {
//        return forHerWomen;
//    }
//
//
//    public By getForHerWomenJeans() {
//        return forHerWomenJeans;
//    }
//
//
//    public By getForHerWomenShirts() {
//        return forHerWomenShirts;
//    }
//
//
//    public By getForHerWomenShoes() {
//        return forHerWomenShoes;
//    }
//
//
//    public By getForHerAccessories() {
//        return forHerAccessories;
//    }
//
//
//    public By getForHimMen() {
//        return forHimMen;
//    }
//
//
//    public By getForHimMenJeans() {
//        return forHimMenJeans;
//    }
//
//
//    public By getForHimMenShirts() {
//        return forHimMenShirts;
//    }
//
//
//    public By getForHimMenShoes() {
//        return forHimMenShoes;
//    }
//
//
//    public By getForHimAccessories() {
//        return forHimAccessories;
//    }
//
//
//    public By getAppStoreIcon() {
//        return appStoreIcon;
//    }
//
//
//    public By getGooglePlayIcon() {
//        return googlePlayIcon;
//    }



}
