package com.ecommerce.tests.men;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.HomePage;
import com.ecommerce.pom.pages.MenPage;
import com.ecommerce.pom.pages.SalesPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MenPageItemsOnSaleTest extends BaseTest {

    @Test(description = "3.9-1 | TC Men > Find items on sale # https://app.clickup.com/t/868a6xuu8")

    public void testFindItemsOnSaleOnMenPage(){

        HomePage homePage = new HomePage(driver);
        homePage.navigateToMenPage();
        MenPage menPage = new MenPage(driver);


    }

}
