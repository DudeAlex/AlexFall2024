package org.example.collections;

import java.util.Objects;

public class Product implements Comparable<Product>{
    private int id;
    private String productName;
    private double productPrice;

    public Product(String productName, int id, double productPrice) {
        this.productName = productName;
        this.id = id;
        this.productPrice = productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public int getId() {
        return id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return this.id == product.id && this.productPrice == product.productPrice &&
               this.productName.equals(product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, productPrice);
    }


    public int compareTo(Product otherProduct) {
        // Compare by product price (ascending order)
      return  Double.compare(this.productPrice, otherProduct.productPrice);

//        if (this.productPrice < otherProduct.productPrice) {
//            return -1;  // Current product is cheaper
//        } else if (this.productPrice > otherProduct.productPrice) {
//            return 1;   // Current product is more expensive
//        } else {
//            return 0;   // Prices are the same
//        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}


