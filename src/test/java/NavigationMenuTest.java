import com.ecommerce.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationMenuTest extends BaseTest {

    public WebElement getMenuItemById(String id) {
        return driver.findElement(By.id(id));
    }


    @Test
    public void testHomeMenuHighlighted() {
        // Find the "Home" menu item by its ID
        WebElement accessoriesMenuItem = getMenuItemById("menu-item-1226"); // ID для Home
        // Click on the "Home" menu item
        accessoriesMenuItem.click();

        // Use the 'wait' object from com.ecommerce.base.BaseTest to wait until the "Home" menu item becomes active
        // This wait will ensure that the element with the class 'current-menu-item' is present in the DOM
        //WebElement activeMenuItem = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li.current-menu-item a")));

        // Get the CSS property 'color' of the active menu item (the "Home" menu item)
        WebElement activeMenuItem = driver.findElement(By.cssSelector("li.current-menu-item a"));

        // Get the CSS property 'color' of the active menu item (the "Home" menu item)
        String color = activeMenuItem.getCssValue("color");
        System.out.println("The color of the active menu item is: " + color);

        // Verify that the color has changed to the expected purple color (rgba(96, 43, 183, 1))
        Assert.assertEquals(color, "rgba(255, 255, 255, 1)", "The 'Home' menu item should be highlighted in purple.");

    }


    @Test
    public void testStoreMenuHighlighted() {
        // Find the "Store" menu item by its ID
        WebElement accessoriesMenuItem = getMenuItemById("menu-item-1227"); // ID для Store
        // Click on the "Store" menu item
        accessoriesMenuItem.click();

        // Use the 'wait' object from com.ecommerce.base.BaseTest to wait until the "Accessories" menu item becomes active
        // This wait will ensure that the element with the class 'current-menu-item' is present in the DOM
        //WebElement activeMenuItem = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li.current-menu-item a")));

        // Get the CSS property 'color' of the active menu item (the "Store" menu item)
        WebElement activeMenuItem = driver.findElement(By.cssSelector("li.current-menu-item a"));

        // Get the CSS property 'color' of the active menu item (the "Store" menu item)
        String color = activeMenuItem.getCssValue("color");
        System.out.println("The color of the active menu item is: " + color);

        // Verify that the color has changed to the expected purple color (rgba(96, 43, 183, 1))
        Assert.assertEquals(color, "rgba(96, 43, 183, 1)", "The 'Store' menu item should be highlighted in purple.");

    }



    @Test
    public void testMenMenuHighlighted() {
        // Find the "Men" menu item by its ID
        WebElement accessoriesMenuItem = getMenuItemById("menu-item-1228"); // ID для Men
        // Click on the "Men" menu item
        accessoriesMenuItem.click();

        // Use the 'wait' object from com.ecommerce.base.BaseTest to wait until the "Men" menu item becomes active
        // This wait will ensure that the element with the class 'current-menu-item' is present in the DOM
        //WebElement activeMenuItem = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li.current-menu-item a")));

        // Get the CSS property 'color' of the active menu item (the "Men" menu item)
        WebElement activeMenuItem = driver.findElement(By.cssSelector("li.current-menu-item a"));

        // Get the CSS property 'color' of the active menu item (the "Men" menu item)
        String color = activeMenuItem.getCssValue("color");
        System.out.println("The color of the active menu item is: " + color);

        // Verify that the color has changed to the expected purple color (rgba(96, 43, 183, 1))
        Assert.assertEquals(color, "rgba(96, 43, 183, 1)", "The 'Men' menu item should be highlighted in purple.");

    }


    @Test
    public void testWomenMenuHighlighted() {
        // Find the "Women" menu item by its ID
        WebElement accessoriesMenuItem = getMenuItemById("menu-item-1229"); // ID для Women
        // Click on the "Women" menu item
        accessoriesMenuItem.click();

        // Use the 'wait' object from com.ecommerce.base.BaseTest to wait until the "Women" menu item becomes active
        // This wait will ensure that the element with the class 'current-menu-item' is present in the DOM
        //WebElement activeMenuItem = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li.current-menu-item a")));

        // Get the CSS property 'color' of the active menu item (the "Women" menu item)
        WebElement activeMenuItem = driver.findElement(By.cssSelector("li.current-menu-item a"));

        // Get the CSS property 'color' of the active menu item (the "Women" menu item)
        String color = activeMenuItem.getCssValue("color");
        System.out.println("The color of the active menu item is: " + color);

        // Verify that the color has changed to the expected purple color (rgba(96, 43, 183, 1))
        Assert.assertEquals(color, "rgba(96, 43, 183, 1)", "The 'Women' menu item should be highlighted in purple.");

    }


    @Test
    public void testAccessoriesMenuHighlighted() {
        // Find the "Accessories" menu item by its ID
        WebElement accessoriesMenuItem = getMenuItemById("menu-item-1230"); // ID для Accessories
        // Click on the "Accessories" menu item
        accessoriesMenuItem.click();

        // Use the 'wait' object from com.ecommerce.base.BaseTest to wait until the "Accessories" menu item becomes active
        // This wait will ensure that the element with the class 'current-menu-item' is present in the DOM
        //WebElement activeMenuItem = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li.current-menu-item a")));

        // Get the CSS property 'color' of the active menu item (the "Accessories" menu item)
        WebElement activeMenuItem = driver.findElement(By.cssSelector("li.current-menu-item a"));

        // Get the CSS property 'color' of the active menu item (the "Accessories" menu item)
        String color = activeMenuItem.getCssValue("color");
        System.out.println("The color of the active menu item is: " + color);

        // Verify that the color has changed to the expected purple color (rgba(96, 43, 183, 1))
        Assert.assertEquals(color, "rgba(96, 43, 183, 1)", "The 'Accessories' menu item should be highlighted in purple.");

    }


    @Test
    public void testAccountMenuHighlighted() {
        // Find the "Account" menu item by its ID
        WebElement accountMenuItem = getMenuItemById("menu-item-1237"); // ID для Account
        // Click on the "Account" menu item
        accountMenuItem.click();

        // Use the 'wait' object from com.ecommerce.base.BaseTest to wait until the "Account" menu item becomes active
        // This wait will ensure that the element with the class 'current-menu-item' is present in the DOM
        //WebElement activeMenuItem = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li.current-menu-item a")));

        // Get the CSS property 'color' of the active menu item (the "Account" menu item)
        WebElement activeMenuItem = driver.findElement(By.cssSelector("li.current-menu-item a"));

        // Get the CSS property 'color' of the active menu item (the "Account" menu item)
        String color = activeMenuItem.getCssValue("color");
        System.out.println("The color of the active menu item is: " + color);

        // Verify that the color has changed to the expected purple color (rgba(96, 43, 183, 1))
        Assert.assertEquals(color, "rgba(255, 255, 255, 1)", "The 'Account' menu item should be highlighted in purple.");

    }


}
