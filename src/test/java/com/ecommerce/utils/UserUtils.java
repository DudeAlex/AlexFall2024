package com.ecommerce.utils;

import java.util.UUID;

public class UserUtils {

    public static String generateUniqueUsername() {
        String baseUsername = "user";
        return baseUsername + UUID.randomUUID();
    }

    public static String generateUniqueEmail() {
        String baseEmail = "user";
        String domain = "@example.com";
        return baseEmail + UUID.randomUUID() + domain;
    }

    public static String generateUniquePassword() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 10);
    }
}

