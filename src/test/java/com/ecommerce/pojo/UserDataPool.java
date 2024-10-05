package com.ecommerce.pojo;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserDataPool {

    public static UserData getUserData(){
       return new UserData("Mihai", "B", "US", "8709 st AVE", "95241",
               "California", "Redmond", "mb@gmail.com");
    }

    public static List<UserData> getUserDataList(){
        List<UserData> list = new ArrayList<>();
        list.add(new UserData("Mihai", "B", "US", "8709 st AVE", "95241",
                "California", "Redmond", "mb@gmail.com"));
        list.add(new UserData("Artur", "B", "US", "8709 st AVE", "95243",
                "California", "Redmond", "as@gmail.com"));
        list.add(new UserData("Alex", "B", "US", "8709 st AVE", "95241",
                "California", "San Francisco", "mb@gmail.com"));
        return list;
    }

    public static List<UserData> getFakerUserDataList(){
        Faker faker = new Faker(new Locale("en-US"));
        List<UserData> list =new ArrayList<>();

        for(int i = 0; i < 5; i++ ) {
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
