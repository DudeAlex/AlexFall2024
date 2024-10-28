package com.ecommerce.tests.components.header;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.components.Header;
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
    private Header header;

    private static final String WHITE_COLOR_ACTIVE = "rgba(255, 255, 255, 1)";
    private static final String BLUE_COLOR_ACTIVE = "rgba(96, 43, 183, 1)";

    @BeforeMethod
    @Override
    public void setUp(){
        super.setUp();
        header = new Header(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public WebElement getMenuItemById(String id) {
        return driver.findElement(By.id(id));
    }


    @Test(description = "10.1 | RF >  Verify active page is highlighted in the navigation bar # https://app.clickup.com/t/8689ufb6d")
    public void testHomeMenuHighlighted() {
        String actualColor = header.getActiveMenuColor(header.getHeaderHome());

        Assert.assertEquals(actualColor, WHITE_COLOR_ACTIVE,
                "The 'Home' menu item should be highlighted in the expected color.");

    }

    @Test(description = "10.1 | RF >  Verify active page is highlighted in the navigation bar # https://app.clickup.com/t/8689ufb6d")
    public void testStoreMenuHighlighted() {
        String actualColor = header.getActiveMenuColor(header.getHeaderStore());

        Assert.assertEquals(actualColor, BLUE_COLOR_ACTIVE,
                "The 'Store' menu item should be highlighted in the expected color.");
    }

    @Test(description = "10.1 | RF >  Verify active page is highlighted in the navigation bar # https://app.clickup.com/t/8689ufb6d")
    public void testMenMenuHighlighted() {
        String actualColor = header.getActiveMenuColor(header.getHeaderMen());

        Assert.assertEquals(actualColor, BLUE_COLOR_ACTIVE,
                "The 'Men' menu item should be highlighted in the expected color.");
    }

    @Test(description = "10.1 | RF >  Verify active page is highlighted in the navigation bar # https://app.clickup.com/t/8689ufb6d")
    public void testWomenMenuHighlighted() {
        String actualColor = header.getActiveMenuColor(header.getHeaderWomen());

        Assert.assertEquals(actualColor, BLUE_COLOR_ACTIVE,
                "The 'Women' menu item should be highlighted in the expected color.");
    }

    @Test(description = "10.1 | RF >  Verify active page is highlighted in the navigation bar # https://app.clickup.com/t/8689ufb6d")
    public void testAccessoriesMenuHighlighted() {
        String actualColor = header.getActiveMenuColor(header.getHeaderAccessories());

        Assert.assertEquals(actualColor, BLUE_COLOR_ACTIVE,
                "The 'Accessories' menu item should be highlighted in the expected color.");
    }

    @Test(description = "10.1 | RF >  Verify active page is highlighted in the navigation bar # https://app.clickup.com/t/8689ufb6d")
    public void testAccountMenuHighlighted() {
        String actualColor = header.getActiveMenuColor(header.getHeaderAccount());

        Assert.assertEquals(actualColor, WHITE_COLOR_ACTIVE,
                "The 'Account' menu item should be highlighted in the expected color.");
    }

    @Test(description = "10.1 | RF >  Verify active page is highlighted in the navigation bar # https://app.clickup.com/t/8689ufb6d")
    public void testAbout(){
        String actualColor = header.getActiveMenuColor(header.getHeaderAbout());

        Assert.assertEquals(actualColor, WHITE_COLOR_ACTIVE,
                "The 'About' menu item should be highlighted in the expected color.");
    }

    @Test(description = "10.1 | RF >  Verify active page is highlighted in the navigation bar # https://app.clickup.com/t/8689ufb6d")
    public void testContactUs(){
        String actualColor = header.getActiveMenuColor(header.getHeaderContactUs());

        Assert.assertEquals(actualColor, WHITE_COLOR_ACTIVE,
                "The 'ContactUs' menu item should be highlighted in the expected color.");
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
