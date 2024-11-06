package com.ecommerce.data;

import org.testng.annotations.DataProvider;

public class LeftSideBarWomenData {
    @DataProvider(name = "womenSearchData")

    public static Object[][] getWomenSearchData (){
        return new Object[][]{
                {"jeans"},
                {"blue"},
                {"tshirt"}
        };
    }
}
