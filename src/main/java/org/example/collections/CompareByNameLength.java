package org.example.collections;

import java.util.Comparator;

public class CompareByNameLength implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        if (o1.getProductName().length() < o2.getProductName().length()) {
            return -1;  // Current product is cheaper
        } else if (o1.getProductName().length() > o2.getProductName().length()) {
            return 1;   // Current product is more expensive
        } else {
            return 0;   // Prices are the same
        }
    }
}
