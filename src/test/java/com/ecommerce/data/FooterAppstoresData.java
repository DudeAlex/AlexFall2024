package com.ecommerce.data;

import org.testng.annotations.DataProvider;

public class FooterAppstoresData {
    @DataProvider(name = "appstoresData")
    public static Object[][] getAppstoresData() {
        return new Object[][]{
                {"https://www.apple.com/in/app-store/","https://askomdch.com/","//a[@ href ='https://www.apple.com/in/app-store/']"},
                {"https://play.google.com/store/games?device=windows","https://askomdch.com/","//a[@ href ='https://play.google.com/store']"}
        };
    }
}
