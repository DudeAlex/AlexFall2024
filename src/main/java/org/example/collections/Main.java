package org.example.collections;

import org.checkerframework.checker.units.qual.C;

import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Main {


    public static void main(String[] args) {


        List<Product> ourProducts = new ArrayList<>();
        ourProducts.add(new Product("AZJeans", 10, 8.99));
        ourProducts.add(new Product("ABHat", 5, 6.69));
        ourProducts.add(new Product("ACGloves", 2, 12.99));

        Product product = new Product("BHat", 4, 8.59);

     //   System.out.println(ourProducts.contains(product));

     //  Collections.sort(ourProducts);

//        CompareById compareId = new CompareById();
//        Collections.sort(ourProducts, compareId);

//        CompareByNameLength compareNameLength = new CompareByNameLength();
//        Collections.sort(ourProducts, compareNameLength);

        CompareByName compareByName = new CompareByName();
        Collections.sort(ourProducts, compareByName);


        System.out.println(ourProducts);


//        Product prod1 = new Product("CJeans", 10, 5.99);
//        Product prod2 = new Product("CJeans", 10, 5.99);
//
//        System.out.println(prod1.equals(prod2));
//

//        String str1 = "Any String";
//        String str2 = "Any String";
//
//        System.out.println(str1==str2);
//        System.out.println(str1.equals(str2));
//
//        Integer int1 = 1000;
//        Integer int2 = 1000;
//
//        System.out.println(int1==int2);
//        System.out.println(int1.equals(int2));






    //    Collections.sort(ourProducts);





//        List<String> productNames = new ArrayList<>();
//        productNames.add("CJeans");
//        productNames.add("ATshirt");
//        productNames.add("BHat");
//
//        List<Integer> checkNumbers = new ArrayList<>();
//        checkNumbers.add(20);
//        checkNumbers.add(30);
//        checkNumbers.add(10);
//
//        Collections.sort(productNames);
//        Collections.reverse(productNames);
//
//        Collections.sort(checkNumbers);

//        System.out.println(productNames);
//        System.out.println(checkNumbers);
//

    }

}
