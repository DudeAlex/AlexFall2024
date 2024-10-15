package com.ecommerce.tests.accessories;
import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.AccessoriesPage;
import com.ecommerce.pom.pages.CartPage;
import com.ecommerce.pom.pages.HomePage;
import com.ecommerce.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class VerifyItemsCategory extends BaseTest {

    @Test(description = "5.1-1-1 | TC > Accessories > Verify items belong to accessories #https://app.clickup.com/t/868a7t8vp")
    public void testItemBelongsToCategory() {
        List<String> productCategories = new HomePage(driver)
                .navigateToAccessoriesPage()
                .collectCategories();
        for (String categories : productCategories) {
            Assert.assertEquals(categories, "Accessories");
        }
    }

    @Test
    public void testTest (){
        StorePage storePage = new StorePage(driver);
        storePage.load();

        AccessoriesPage accessoriesPage = storePage.getHeader().navigateToAccessoriesPage();
        accessoriesPage.addToCartFromAccessoriesPage();

        storePage.getHeader().navigateToAccessoriesPage().addToCartFromAccessoriesPage();

    }
}