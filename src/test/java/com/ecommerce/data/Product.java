package com.ecommerce.data;

import java.util.Objects;

public class Product implements Comparable<Product> {
        private final String name;
        private final Double price;
        private Double discountedPrice;
        private final boolean isDiscounted;
        private final String category;

        public Product(String name, Double price) {
            this.name = name;
            this.price = price;
            this.isDiscounted = false;
            this.category = null;
        }

    public Product(String name, Double price, String category) {
        this.name = name;
        this.price = price;
        this.isDiscounted = false;
        this.category = category;
    }

        public Product(String name, Double price, Double discountedPrice) {
            this.name = name;
            this.price = price;
            this.discountedPrice = discountedPrice;
            this.isDiscounted = true;
            this.category = null;
        }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Double getDiscountedPrice() {
        return discountedPrice;
    }

    public boolean isDiscounted() {
        return isDiscounted;
    }

    public String getCategory() {
            return category;
        }


    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", discountedPrice=" + discountedPrice +
                ", isDiscounted=" + isDiscounted +
                ", category='" + category + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return isDiscounted == product.isDiscounted && Objects.equals(name, product.name) && Objects.equals(price, product.price) && Objects.equals(discountedPrice, product.discountedPrice) && Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, discountedPrice, isDiscounted, category);
    }

    @Override
    public int compareTo(Product otherProduct) {
        return Double.compare(this.price, otherProduct.price);
    }
}
