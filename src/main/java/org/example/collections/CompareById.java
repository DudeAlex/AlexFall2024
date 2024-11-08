package org.example.collections;

import java.util.Comparator;

public class CompareById implements Comparator<Product> {


    @Override
    public int compare(Product o1, Product o2) {
        return Integer.compare(o1.getId(), o2.getId());

//        if (o1.getId() < o2.getId()) {
//            return -1;  // Current product is cheaper
//        } else if (o1.getId() > o2.getId()) {
//            return 1;   // Current product is more expensive
//        } else {
//            return 0;   // Prices are the same
//        }
    }
}
