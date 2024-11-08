package org.example.collections;

import org.checkerframework.checker.units.qual.C;

import java.text.CollationElementIterator;
import java.util.*;

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

//        CompareByNameLength compareNameLength = new CompareByNameLength(); -> leading into the next line
  //      Collections.sort(ourProducts, new CompareByNameLength());

        //  CompareByName compareByName = new CompareByName(); -> leading into the next line
//        Collections.sort(ourProducts, new Comparator<Product>() {
//            @Override
//            public int compare(Product o1, Product o2) {
//                return String.CASE_INSENSITIVE_ORDER.compare(o1.getProductName(), o2.getProductName());
//            }
//        });

      //  Collections.sort(ourProducts, (o1, o2) -> String.CASE_INSENSITIVE_ORDER.compare(o1.getProductName(), o2.getProductName()));
//        Collections.sort(ourProducts, (o1, o2) -> Double.compare(o1.getProductPrice(), o2.getProductPrice()));
//        Collections.sort(ourProducts, Comparator.comparingDouble(Product::getProductPrice));

//        Collections.sort(ourProducts, (o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
//        Collections.sort(ourProducts, (Comparator.comparingInt(Product::getId)));


       // Collections.sort(ourProducts, (o1, o2) -> Integer.compare(o1.getProductName().length(), o2.getProductName().length()));
        Collections.sort(ourProducts, Comparator.comparingInt(o -> o.getProductName().length()));



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
