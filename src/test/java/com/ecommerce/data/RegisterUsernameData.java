package com.ecommerce.data;

import org.testng.annotations.DataProvider;

public class RegisterUsernameData {

    @DataProvider
    public static Object[][] provideInvalidCharacters() {
        return new Object[][]{
                {"!"}, {"#"}, {"$"}, {"%"}, {"^"}, {"&"}, {"*"}, {"("}, {")"}, {">"},
                {"?"}, {"~"}, {"+"}, {"="}, {","}, {":"}, {";"}, {":"}, {"'"}
        };
    }
    }