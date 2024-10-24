package com.ecommerce.tests.account.pojo;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserDataPool {

    public static List<UserData> getFakerUserDataList(int numberOfUsers){
        Faker faker = new Faker(new Locale("en-US"));
        List<UserData> list =new ArrayList<>();

        for(int i = 0; i < numberOfUsers; i++ ) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String country = faker.address().country();
            String address = faker.address().streetAddress();
            String town = faker.address().city();
            String state = faker.address().state();
            String zipCode = faker.address().zipCode();
            String emailAddress = faker.internet().emailAddress();
            list.add(new UserData(firstName, lastName, country, address, zipCode, state, town, emailAddress ));
        }
        return list;
    }

}
