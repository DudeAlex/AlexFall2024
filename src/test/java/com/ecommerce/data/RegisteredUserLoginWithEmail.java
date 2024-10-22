package com.ecommerce.data;

import org.testng.annotations.DataProvider;

public class RegisteredUserLoginWithEmail {

    @DataProvider(name = "registeredUserEmail")
        public static Object[][] getRegisteredUserLoginWithEmail() {
            return new Object[][]{
                    {
                            "//a[contains(text(), 'Account')]",
                            "//input [ @ id = 'username']",
                            "test123@test.com", // login with email
                            "//input [ @ id = 'password']",
                            "12345"
                    }
            };
        }
    }


