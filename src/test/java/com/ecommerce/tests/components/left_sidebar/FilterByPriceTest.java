package com.ecommerce.tests.components.left_sidebar;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.pages.StorePage;
import org.testng.annotations.Test;

public class FilterByPriceTest extends BaseTest {

    // Mokctest to test methods needed to manipulate 'Filter By price' slider
    @Test
    public void testFilterNodMoves() {
        StorePage storePage = new StorePage(driver);
        storePage.load();
        storePage
                .moveLeftNodOfPriceFilter(70)
                .moveRightNodOfPriceFilter(110)
                .clickFilterButton();
        // to be continued
    }
}
