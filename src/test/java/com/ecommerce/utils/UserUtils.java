package com.ecommerce.utils;

import com.ecommerce.pojo.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

    public static User readUserFromJson(String filePath) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = UserUtils.class.getResourceAsStream(filePath);
        return objectMapper.readValue(inputStream, User.class);
    }

}

