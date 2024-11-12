package com.ecommerce.tests.men;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.components.LeftSidebar;
import com.ecommerce.pom.pages.MenPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MenPageBrowseByCategoryTest extends BaseTest {

    @Test(description = "3.10 - 1.1 | TC > Men Page >Default \"Men\" option for Browse by Category with listed products # https://app.clickup.com/t/868afb8g3")

    public void testBrowseByCategoryDefaultFilterOption() throws InterruptedException {

        MenPage menPage = new MenPage(driver).load();
     //   String browseByCategoryFilter = menPage.getBrowseByCategoryFilterText();

//        LeftSidebar leftSidebar = new LeftSidebar(driver);
//        leftSidebar.clickOnHeaderFilterByPriceText();
       //     Thread.sleep(4000);

    //    Assert.assertEquals(browseByCategoryFilter, "×\n" + "Men  (7)");

    }

    @Test(description = "3.10 - 1.2 - 1.3 | TC > Men Page > Browse by Category filter options # https://app.clickup.com/t/868afb8mg")

    public void testBrowseByCategoryFilterOptions(){

    }

}
