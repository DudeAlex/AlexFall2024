package com.ecommerce.data;

import org.testng.annotations.DataProvider;

public class FooterForHerData {
    @DataProvider(name = "forHerData")
    public static Object[][] getForHerData() {
        return new Object[][]{
                {"https://askomdch.com/product-category/women/", "//a[@class='menu-link' and text()='Women']"},
                {"https://askomdch.com/product-category/womens-jeans/", "//a[@class='menu-link' and text()='Women’s Jeans']"},
                {"https://askomdch.com/product-category/womens-shirts/", "//a[@class='menu-link' and text()='Women’s Shirts']"},
                {"https://askomdch.com/product-category/womens-shoes/", "//a[@class='menu-link' and text()='Women’s Shoes']"},
                {"https://askomdch.com/product-category/accessories/", "//a[@class='menu-link' and text()='Accessories']"}
        };
    }


}
