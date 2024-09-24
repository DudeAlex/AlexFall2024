package com.ecommerce.data;

import org.testng.annotations.DataProvider;

public class ProductsData {

    @DataProvider
    public static Object[][] provideAllItemCategory() {
        return new Object[][]{
                {"Men"},
                {"Women"},
                {"Accessories"}
        };
    }
}