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
}
