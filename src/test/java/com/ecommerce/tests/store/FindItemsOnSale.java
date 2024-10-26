package com.ecommerce.tests.store;
import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.components.Header;
import com.ecommerce.pom.pages.StorePage;
import org.testng.annotations.Test;


public class FindItemsOnSale extends BaseTest {

    @Test(description = "2.1-1-1 TC | Check if the product being sold has a 'Sale!' label on the 'Products' page # https://app.clickup.com/t/8689p8y28")
    public void isProductOnSaleContainsAppropriateLabel()
    {
        Header header = new Header(driver);
        header.navigateToStorePage();
        StorePage store = new StorePage(driver);
        store.checkLabelSaleOnEveryDiscountProduct();
    }
}
