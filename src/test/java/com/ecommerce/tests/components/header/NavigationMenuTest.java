package com.ecommerce.tests.components.header;

import com.ecommerce.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class NavigationMenuTest extends BaseTest {
    private WebDriverWait wait;

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public WebElement getMenuItemById(String id) {
        return driver.findElement(By.id(id));
    }

    public String getActiveMenuItemColor(String menuItemId) {
        WebElement menuItem = getMenuItemById(menuItemId);
        menuItem.click();
        WebElement activeMenuItem = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li.current-menu-item a")));

        return activeMenuItem.getCssValue("color");
    }

    @Test(description = "10.1 | RF >  Verify active page is highlighted in the navigation bar # https://app.clickup.com/t/8689ufb6d")
    public void testHomeMenuHighlighted() {
        // get actual color
        String actualColor = getActiveMenuItemColor("menu-item-1226");
        // Expected color
        String expectedColor = "rgba(255, 255, 255, 1)";

        Assert.assertEquals(actualColor, expectedColor, "The 'Home' menu item should be highlighted in the expected color.");

    }


    @Test(description = "10.1 | RF >  Verify active page is highlighted in the navigation bar # https://app.clickup.com/t/8689ufb6d")
    public void testStoreMenuHighlighted() {
        String actualColor = getActiveMenuItemColor("menu-item-1227");
        String expectedColor = "rgba(96, 43, 183, 1)";

        Assert.assertEquals(actualColor, expectedColor, "The 'Home' menu item should be highlighted in the expected color.");
    }



    @Test(description = "10.1 | RF >  Verify active page is highlighted in the navigation bar # https://app.clickup.com/t/8689ufb6d")
    public void testMenMenuHighlighted() {
        String actualColor = getActiveMenuItemColor("menu-item-1228");
        String expectedColor = "rgba(96, 43, 183, 1)";

        Assert.assertEquals(actualColor, expectedColor, "The 'Home' menu item should be highlighted in the expected color.");
    }


    @Test(description = "10.1 | RF >  Verify active page is highlighted in the navigation bar # https://app.clickup.com/t/8689ufb6d")
    public void testWomenMenuHighlighted() {
        String actualColor = getActiveMenuItemColor("menu-item-1229");
        String expectedColor = "rgba(96, 43, 183, 1)";

        Assert.assertEquals(actualColor, expectedColor, "The 'Home' menu item should be highlighted in the expected color.");
    }

    @Test(description = "10.1 | RF >  Verify active page is highlighted in the navigation bar # https://app.clickup.com/t/8689ufb6d")
    public void testAccessoriesMenuHighlighted() {
        String actualColor = getActiveMenuItemColor("menu-item-1230");
        String expectedColor = "rgba(96, 43, 183, 1)";

        Assert.assertEquals(actualColor, expectedColor, "The 'Home' menu item should be highlighted in the expected color.");
    }

    @Test(description = "10.1 | RF >  Verify active page is highlighted in the navigation bar # https://app.clickup.com/t/8689ufb6d")
    public void testAccountMenuHighlighted() {
        String actualColor = getActiveMenuItemColor("menu-item-1237");
        String expectedColor = "rgba(255, 255, 255, 1)";

        Assert.assertEquals(actualColor, expectedColor, "The 'Home' menu item should be highlighted in the expected color.");
    }

    @Test(description = "10.1 | RF >  Verify active page is highlighted in the navigation bar # https://app.clickup.com/t/8689ufb6d")
    public void testAbout(){
        String actualColor = getActiveMenuItemColor("menu-item-1232");
        String expectedColor = "rgba(255, 255, 255, 1)";

        Assert.assertEquals(actualColor, expectedColor, "The 'Home' menu item should be highlighted in the expected color.");
    }

    @Test(description = "10.1 | RF >  Verify active page is highlighted in the navigation bar # https://app.clickup.com/t/8689ufb6d")
    public void testContactUs(){
        String actualColor = getActiveMenuItemColor("menu-item-1233");
        String expectedColor = "rgba(255, 255, 255, 1)";

        Assert.assertEquals(actualColor, expectedColor, "The 'Home' menu item should be highlighted in the expected color.");
    }

    @Test(description = "10.1 - 8.2 |  Verify Cart is empty when clicking the cart icon # https://app.clickup.com/t/8689wehf2")
    public void testCartIsEmpty() {
        // Locate the shopping cart icon/button and click it
        WebElement cartIcon = driver.findElement(By.cssSelector("a[href='https://askomdch.com/cart/']"));
        cartIcon.click();

        // Wait for the cart to display the message and capture it
        WebElement emptyCartMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@class = 'cart-empty woocommerce-info']")));

        // Verify the message is "No products in the cart."
        String expectedMessage = "Your cart is currently empty.";
        String actualMessage = emptyCartMessage.getText();

        Assert.assertEquals(actualMessage, expectedMessage, "The cart should be empty when no products are added.");
    }

    @Test(description = "10.1 - 8.2 |  Verify Cart is empty when clicking the cart icon # https://app.clickup.com/t/8689wej0n")
    public void testCartHoverDisplaysEmptyMessage() {
        // Locate the cart icon
        WebElement cartIcon = driver.findElement(By.cssSelector("a[href='https://askomdch.com/cart/']"));

        // Hover over the cart icon
        Actions action = new Actions(driver);
        action.moveToElement(cartIcon).perform();

        // Wait for the message to appear
        WebElement hoverMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("p.woocommerce-mini-cart__empty-message")));

        // Verify the message is correct
        String expectedMessage = "No products in the cart.";
        String actualMessage = hoverMessage.getText();

        Assert.assertEquals(actualMessage, expectedMessage, "The cart should display 'No products in the cart' on hover.");
    }

}
