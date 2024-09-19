import com.ecommerce.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

    @Test
    public void testHomeMenuHighlighted() {
        // get actual color
        String actualColor = getActiveMenuItemColor("menu-item-1226");

        // Expected color
        String expectedColor = "rgba(255, 255, 255, 1)";
        // Find the "Home" menu item by its ID
        //WebElement accessoriesMenuItem = getMenuItemById("menu-item-1226"); // ID для Home
        // Click on the "Home" menu item
        //accessoriesMenuItem.click();

        // Use the 'wait' object from com.ecommerce.base.BaseTest to wait until the "Home" menu item becomes active
        // This wait will ensure that the element with the class 'current-menu-item' is present in the DOM
        //WebElement activeMenuItem = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li.current-menu-item a")));

        // Get the CSS property 'color' of the active menu item (the "Home" menu item)
        //WebElement activeMenuItem = driver.findElement(By.cssSelector("li.current-menu-item a"));

        // Get the CSS property 'color' of the active menu item (the "Home" menu item)
        //String color = activeMenuItem.getCssValue("color");
        //System.out.println("The color of the active menu item is: " + color);

        // Verify that the color has changed to the expected purple color (rgba(96, 43, 183, 1))
        Assert.assertEquals(actualColor, expectedColor, "The 'Home' menu item should be highlighted in the expected color.");

    }


    @Test
    public void testStoreMenuHighlighted() {
        String actualColor = getActiveMenuItemColor("menu-item-1227");
        String expectedColor = "rgba(96, 43, 183, 1)";

        Assert.assertEquals(actualColor, expectedColor, "The 'Home' menu item should be highlighted in the expected color.");
    }



    @Test
    public void testMenMenuHighlighted() {
        String actualColor = getActiveMenuItemColor("menu-item-1228");
        String expectedColor = "rgba(96, 43, 183, 1)";

        Assert.assertEquals(actualColor, expectedColor, "The 'Home' menu item should be highlighted in the expected color.");
    }


    @Test
    public void testWomenMenuHighlighted() {
        String actualColor = getActiveMenuItemColor("menu-item-1229");
        String expectedColor = "rgba(96, 43, 183, 1)";

        Assert.assertEquals(actualColor, expectedColor, "The 'Home' menu item should be highlighted in the expected color.");
    }

    @Test
    public void testAccessoriesMenuHighlighted() {
        String actualColor = getActiveMenuItemColor("menu-item-1230");
        String expectedColor = "rgba(96, 43, 183, 1)";

        Assert.assertEquals(actualColor, expectedColor, "The 'Home' menu item should be highlighted in the expected color.");
    }

    @Test
    public void testAccountMenuHighlighted() {
        String actualColor = getActiveMenuItemColor("menu-item-1237");
        String expectedColor = "rgba(255, 255, 255, 1)";

        Assert.assertEquals(actualColor, expectedColor, "The 'Home' menu item should be highlighted in the expected color.");
    }

    @Test
    public void testAbout(){
        String actualColor = getActiveMenuItemColor("menu-item-1232");
        String expectedColor = "rgba(255, 255, 255, 1)";

        Assert.assertEquals(actualColor, expectedColor, "The 'Home' menu item should be highlighted in the expected color.");
    }

    @Test
    public void testContactUs(){
        String actualColor = getActiveMenuItemColor("menu-item-1233");
        String expectedColor = "rgba(255, 255, 255, 1)";

        Assert.assertEquals(actualColor, expectedColor, "The 'Home' menu item should be highlighted in the expected color.");
    }

}
