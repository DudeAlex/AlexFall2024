package com.ecommerce.tests.women;

import com.ecommerce.base.BaseTest;
import com.ecommerce.data.LeftSideBarWomenData;
import com.ecommerce.pom.components.LeftSidebar;
import com.ecommerce.pom.pages.StorePage;
import com.ecommerce.pom.pages.WomenPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WomenSearchTest extends BaseTest {
    @Test(description = "4_4-1 | TC Verify Women Search filters products by  searched word # https://app.clickup.com/t/8689p8y60",
            dataProvider = "womenSearchData", dataProviderClass = LeftSideBarWomenData.class)
    public void testSearchField(String searchInput) {
        WomenPage womenPage = new WomenPage(driver);
        womenPage.load();
        LeftSidebar leftSidebar = new LeftSidebar(driver);
        leftSidebar.searchProduct(searchInput);
        StorePage storePage = new StorePage(driver);
        String searchTitle = storePage.getSearchHeaderTitle();

        Assert.assertTrue(searchTitle.contains(searchInput));
    }
}
