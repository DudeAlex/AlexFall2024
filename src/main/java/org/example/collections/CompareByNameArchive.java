package org.example.collections;

import java.util.Comparator;

public class CompareByNameArchive implements Comparator<Product> {


    @Override
    public int compare(Product o1, Product o2) {
        return String.CASE_INSENSITIVE_ORDER.compare(o1.getProductName(), o2.getProductName());
    }
}
