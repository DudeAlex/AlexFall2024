package com.ecommerce.utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class CollectToListUtils {

    public static List<String> productsCategories(WebDriver driver) {
        List<WebElement> allProducts = driver.findElements(By.className("ast-woo-product-category"));
        List<String> allProductsCategories = new ArrayList<>();
        for(WebElement product : allProducts) {
            allProductsCategories.add(product.getText());
        } return allProductsCategories;
    }
}
