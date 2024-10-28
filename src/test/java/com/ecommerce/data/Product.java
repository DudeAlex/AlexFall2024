package com.ecommerce.data;

public class Product {
        private final String name;
        private final Double price;
        private Double discountedPrice;
        private final boolean isDiscounted;

        public Product(String name, Double price) {
            this.name = name;
            this.price = price;
            this.isDiscounted = false;
        }

        public Product(String name, Double price, Double discountedPrice) {
            this.name = name;
            this.price = price;
            this.discountedPrice = discountedPrice;
            this.isDiscounted = true;
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

    @Override
    public String toString() {
        return "Product{name='" + name + "', price=" + price + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        // Check equality based on 'name' and 'price'
        return Double.compare(product.price, price) == 0 &&
                (name != null ? name.equals(product.name) : product.name == null);
    }

    @Override
    public int hashCode() {
        // Generate hash based on 'name' and 'price'
        int result = name != null ? name.hashCode() : 0;
        long temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
