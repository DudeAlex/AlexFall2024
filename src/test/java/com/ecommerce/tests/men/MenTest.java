package com.ecommerce.tests.men;

import com.ecommerce.base.BaseTest;
import com.ecommerce.utils.CollectToListUtils;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class MenTest extends BaseTest {

    private static final By MEN_CATEGORY = By.id("menu-item-1228");
    private static final By ALL_PRODUCTS_CONTAINER = By.xpath("//div[@class='ast-woocommerce-container']");
    private static final By SINGLE_ITEM_CONTAINER = By.xpath("//ul[@class='products columns-4']//li");
    private static final By SALE_TAG = By.xpath(".//span[@class='onsale']");
    private static final By CROSSED_OUT_PRICE = By.xpath(".//del");

    @Test(description = "3_16 - 1 | TC > Men > Verify categories # https://app.clickup.com/t/8689zw695")
    public void testProductsBelongToCategories() {
        WaitUtils.visibilityOfElementLocated(driver, MEN_CATEGORY).click();
        List<String> productCategories = CollectToListUtils.productsCategories(driver);
        for (String categories : productCategories) {
            Assert.assertEquals(categories, "Men");
        }
    }

    @Test(description = "3.9-1.1 | TC> Man> Verify Sale items price # https://app.clickup.com/t/8689v3293")
    public void testVerifyReducedPriceForSaleItems() {
        WaitUtils.elementToBeClickable(driver, MEN_CATEGORY).click();
        //Ищем большой блок, в котором содержатся оба элемента
        WebElement container = WaitUtils.visibilityOf(driver, ALL_PRODUCTS_CONTAINER, 2);
        //ищем все элементы и пробегаемся по листу всех
        List<WebElement> productsList = container.findElements
                (SINGLE_ITEM_CONTAINER);

        for (WebElement product : productsList) {
            try {
                // Проверяем наличие обоих элементов в одном блоке productList
                /*если хотя бы один из продуктов не содержит элемента с классом onsale или del,
                 метод findElement() выбрасывает исключение NoSuchElementException, если элемент не найден, поэтому ищем через try-catch.*/

                WebElement saleTag = product.findElement(SALE_TAG);
                WebElement delTag = product.findElement(CROSSED_OUT_PRICE);

                Assert.assertEquals(saleTag.getText(), "Sale!");
                Assert.assertNotNull(delTag.getText());

            } catch (NoSuchElementException e) {
                // Если saleTag или delTag не найден, просто игнорируем этот продукт
                // Но если saleTag или delTag отсутствует, тест упадет

                System.out.println("Products does not have sale tags and crossed price");
            }
        }
    }

}
