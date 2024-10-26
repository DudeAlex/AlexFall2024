package com.ecommerce.utils;

import com.ecommerce.pojo.User;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        InputStream inputStream = UserUtils.class.getClassLoader().getResourceAsStream(filePath);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(inputStream, User.class);
    }

    public static boolean isOverlapping(org.openqa.selenium.Rectangle rect1, org.openqa.selenium.Rectangle rect2)
    {
        return rect1.getX() < (rect2.getX() + rect2.getWidth()) &&
                (rect1.getX() + rect1.getWidth()) > rect2.getX() &&
                rect1.getY() < (rect2.getY() + rect2.getHeight()) &&
                (rect1.getY() + rect1.getHeight()) > rect2.getY();
    }
}

