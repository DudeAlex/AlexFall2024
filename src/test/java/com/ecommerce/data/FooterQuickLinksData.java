package com.ecommerce.data;

import org.testng.annotations.DataProvider;

public class FooterQuickLinksData {
    @DataProvider(name = "quickLinksData")
    public static Object[][] getQuickLinks() {
        return new Object[][]{
                {"https://askomdch.com/", "//a[@class='menu-link' and text()='Home']"},
                {"https://askomdch.com/about/", "//a[@class='menu-link' and text()='About']"},
                {"https://askomdch.com/account/", "//a[@class='menu-link' and text()='My Account']"},
                {"https://askomdch.com/cart/", "//a[@class='menu-link' and text()='Cart']"},
                {"https://askomdch.com/contact-us/", "//a[@class='menu-link' and text()='Contact Us']"}
        };
    }

}
