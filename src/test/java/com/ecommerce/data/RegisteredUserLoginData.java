package com.ecommerce.data;

import org.testng.annotations.DataProvider;

public class RegisteredUserLoginData {


    @DataProvider(name = "registeredUserLogin")
    public static Object[][] getRegisteredUserData() {
        return new Object[][]{
                {
                        "//a[contains(text(), 'Account')]",
                        "//input [ @ id = 'username']",
                        "test_test@test.test", // login with email
                        "//input [ @ id = 'password']",
                        "12345",
                        "//li[contains(@class, 'woocommerce-MyAccount-navigation-link--customer-logout')]/a[text()='Logout']\n",
                        "Logout",
                        "//button[@class='woocommerce-button button woocommerce-form-login__submit' and text()='Log in']\n",
                        "LOG IN"
                        },
                {
                        "//a[contains(text(), 'Account')]",
                        "//input [ @ id = 'username']",
                        "test_test", // login with username
                        "//input [ @ id = 'password']",
                        "12345",
                        "//li[contains(@class, 'woocommerce-MyAccount-navigation-link--customer-logout')]/a[text()='Logout']\n",
                        "Logout",
                        "//button[@class='woocommerce-button button woocommerce-form-login__submit' and text()='Log in']\n",
                        "LOG IN"
                },

        };
    }
}
