package com.ecommerce.data;

import org.testng.annotations.DataProvider;

public class FooterForHimData {
    @DataProvider(name = "forHimData")
    public static Object[][] getForHimData() {
        return new Object[][]{
                {"https://askomdch.com/product-category/men/", "//a[@class='menu-link' and text()='Men']"},
                {"https://askomdch.com/product-category/mens-jeans/", "//a[@class='menu-link' and text()='Men’s Jeans']"},
                {"https://askomdch.com/product-category/mens-shirts/", "//a[@class='menu-link' and text()='Men’s Shirts']"},
                {"https://askomdch.com/product-category/mens-shoes/","//a[@class='menu-link' and text()='Men’s Shoes']"},
                {"https://askomdch.com/product-category/accessories/","//a[@class='menu-link' and text()='Accessories']"}
        };
    }
}
