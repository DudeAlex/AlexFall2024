package org.example.collections;

import org.checkerframework.checker.units.qual.A;

public class MainAnimal {

    public static void main(String[] args) {

        Object dog = new Dog();

        System.out.println( dog.getClass());

        Dog dog2 = (Dog) dog;
        dog2.isBarking();






    }
}
