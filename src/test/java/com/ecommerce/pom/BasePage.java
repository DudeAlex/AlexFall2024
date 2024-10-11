package com.ecommerce.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    private WebDriver driver;


    /**
     * footer elements Quick Links
     */
    By quickLinksHome = By.xpath("//a[@class='menu-link' and text()='Home']"); // Home
    By quickLinksAbout = By.xpath("//a[@class='menu-link' and text()='About']"); // About
    By quickLinksMyAccount = By.xpath("//a[@class='menu-link' and text()='My Account']"); // My Account
    By quickLinksCart = By.xpath("//a[@class='menu-link' and text()='Cart']"); // Cart
    By quickLinksContactUs = By.xpath("//a[@class='menu-link' and text()='Contact Us']"); // ContactUs


    /**
     * footer elements For Her
     */
    By forHerWomen = By.xpath("//a[@class='menu-link' and text()='Women']"); // Women
    By forHerWomenJeans = By.xpath("//a[@class='menu-link' and text()='Women’s Jeans']"); // Women's Jeans
    By forHerWomenShirts = By.xpath("//a[@class='menu-link' and text()='Women’s Shirts']"); // Women's Shirts
    By forHerWomenShoes = By.xpath("//a[@class='menu-link' and text()='Women’s Shoes']"); // Women's Shoes
    By forHerAccessories = By.xpath("//a[@class='menu-link' and text()='Accessories']"); // Accessories


    /**
     * footer elements For Him
     */
    By forHimMen = By.xpath("//a[@class='menu-link' and text()='Men']"); // Men
    By forHimMenJeans = By.xpath("//a[@class='menu-link' and text()='Men’s Jeans']"); // Men's Jeans
    By forHimMenShirts = By.xpath("//a[@class='menu-link' and text()='Men’s Shirts']"); // Men's Shirts
    By forHimMenShoes = By.xpath("//a[@class='menu-link' and text()='Men’s Shoes']"); // Men's Shoes
    By forHimAccessories = By.xpath("//a[@class='menu-link' and text()='Accessories']"); // Accessories


    /**
     * footer elements AOD Mobile
     */
    By appStoreIcon = By.xpath("//a[@ href ='https://www.apple.com/in/app-store/']"); // app store icon
    By googlePlayIcon = By.xpath("//a[@ href ='https://play.google.com/store']"); // google play icon


    /**
     * header elements like links, buttons, etc.
     */
    By headerHome = By.id("menu-item-1226"); // Header Home
    By headerStore = By.id("menu-item-1227"); // Header Store
    By headerMen = By.id("menu-item-1228"); // Header Men
    By headerWomen = By.id("menu-item-1229"); // Header Women
    By headerAccessories = By.id("menu-item-1230"); // Header Accessories
    By headerAccount = By.id("menu-item-1237"); // Header Account
    By headerAbout = By.id("menu-item-1232"); // Header About
    By headerContactUs = By.id("menu-item-1233"); // Header Contact Us
    By headerCartButton = By.cssSelector("a[href='https://askomdch.com/cart/']"); // Header Cart



    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public By getQuickLinksHome() {
        return quickLinksHome;
    }

    public void setQuickLinksHome(By quickLinksHome) {
        this.quickLinksHome = quickLinksHome;
    }

    public By getQuickLinksAbout() {
        return quickLinksAbout;
    }

    public void setQuickLinksAbout(By quickLinksAbout) {
        this.quickLinksAbout = quickLinksAbout;
    }

    public By getQuickLinksMyAccount() {
        return quickLinksMyAccount;
    }

    public void setQuickLinksMyAccount(By quickLinksMyAccount) {
        this.quickLinksMyAccount = quickLinksMyAccount;
    }

    public By getQuickLinksCart() {
        return quickLinksCart;
    }

    public void setQuickLinksCart(By quickLinksCart) {
        this.quickLinksCart = quickLinksCart;
    }

    public By getQuickLinksContactUs() {
        return quickLinksContactUs;
    }

    public void setQuickLinksContactUs(By quickLinksContactUs) {
        this.quickLinksContactUs = quickLinksContactUs;
    }

    public By getForHerWomen() {
        return forHerWomen;
    }

    public void setForHerWomen(By forHerWomen) {
        this.forHerWomen = forHerWomen;
    }

    public By getForHerWomenJeans() {
        return forHerWomenJeans;
    }

    public void setForHerWomenJeans(By forHerWomenJeans) {
        this.forHerWomenJeans = forHerWomenJeans;
    }

    public By getForHerWomenShirts() {
        return forHerWomenShirts;
    }

    public void setForHerWomenShirts(By forHerWomenShirts) {
        this.forHerWomenShirts = forHerWomenShirts;
    }

    public By getForHerWomenShoes() {
        return forHerWomenShoes;
    }

    public void setForHerWomenShoes(By forHerWomenShoes) {
        this.forHerWomenShoes = forHerWomenShoes;
    }

    public By getForHerAccessories() {
        return forHerAccessories;
    }

    public void setForHerAccessories(By forHerAccessories) {
        this.forHerAccessories = forHerAccessories;
    }

    public By getForHimMen() {
        return forHimMen;
    }

    public void setForHimMen(By forHimMen) {
        this.forHimMen = forHimMen;
    }

    public By getForHimMenJeans() {
        return forHimMenJeans;
    }

    public void setForHimMenJeans(By forHimMenJeans) {
        this.forHimMenJeans = forHimMenJeans;
    }

    public By getForHimMenShirts() {
        return forHimMenShirts;
    }

    public void setForHimMenShirts(By forHimMenShirts) {
        this.forHimMenShirts = forHimMenShirts;
    }

    public By getForHimMenShoes() {
        return forHimMenShoes;
    }

    public void setForHimMenShoes(By forHimMenShoes) {
        this.forHimMenShoes = forHimMenShoes;
    }

    public By getForHimAccessories() {
        return forHimAccessories;
    }

    public void setForHimAccessories(By forHimAccessories) {
        this.forHimAccessories = forHimAccessories;
    }

    public By getAppStoreIcon() {
        return appStoreIcon;
    }

    public void setAppStoreIcon(By appStoreIcon) {
        this.appStoreIcon = appStoreIcon;
    }

    public By getGooglePlayIcon() {
        return googlePlayIcon;
    }

    public void setGooglePlayIcon(By googlePlayIcon) {
        this.googlePlayIcon = googlePlayIcon;
    }

    public By getHeaderHome() {
        return headerHome;
    }

    public void setHeaderHome(By headerHome) {
        this.headerHome = headerHome;
    }

    public By getHeaderStore() {
        return headerStore;
    }

    public void setHeaderStore(By headerStore) {
        this.headerStore = headerStore;
    }

    public By getHeaderMen() {
        return headerMen;
    }

    public void setHeaderMen(By headerMen) {
        this.headerMen = headerMen;
    }

    public By getHeaderWomen() {
        return headerWomen;
    }

    public void setHeaderWomen(By headerWomen) {
        this.headerWomen = headerWomen;
    }

    public By getHeaderAccessories() {
        return headerAccessories;
    }

    public void setHeaderAccessories(By headerAccessories) {
        this.headerAccessories = headerAccessories;
    }

    public By getHeaderAccount() {
        return headerAccount;
    }

    public void setHeaderAccount(By headerAccount) {
        this.headerAccount = headerAccount;
    }

    public By getHeaderAbout() {
        return headerAbout;
    }

    public void setHeaderAbout(By headerAbout) {
        this.headerAbout = headerAbout;
    }

    public By getHeaderContactUs() {
        return headerContactUs;
    }

    public void setHeaderContactUs(By headerContactUs) {
        this.headerContactUs = headerContactUs;
    }

    public By getHeaderCartButton() {
        return headerCartButton;
    }

    public void setHeaderCartButton(By headerCartButton) {
        this.headerCartButton = headerCartButton;
    }

    public abstract void load();
}
