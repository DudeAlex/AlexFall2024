import com.ecommerce.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;

public class MyFirstTest extends BaseTest {
    private static final String URLADDR = "https://askomdch.com/";

    private static final List<String> PAGES = List.of(
            "",
            "store/",
            "product-category/men/",
            "product-category/women/",
            "product-category/accessories/",
            "account/",
            "about/",
            "contact-us/"
    );
    private static final List<String> HEADERS = List.of("" +
            "Raining Offers for Hot Summer!",
            "About Us",
            "Account",
            "Cart",
            "Contact Us");

    public WebElement waitForElementPresent(By by, String error_message, Duration time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    @Test
    public void isClickOnEveryHeaderElementEqualsToCorrEndpoint() {
        //---------------------------------------------------------------------------------------------------------
        //TODO:
        // 1. Click on every element in the top navbar,
        // 2. Compare actual url and expected url
        //---------------------------------------------------------------------------------------------------------

        List<WebElement> menuItems = driver.findElement(By.id("ast-hf-menu-1")).findElements(By.tagName("li"));
        for (int i = 0; i < PAGES.size(); i++) {
            waitForElementPresent(
                    By.xpath("//div[@id='ast-desktop-header']//ul"),
                    "Element is absent",
                    Duration.ofSeconds(5));
            System.out.println(menuItems.get(i).getText());
            menuItems.get(i).click();
            menuItems = driver.findElement(By.id("ast-hf-menu-1")).findElements(By.tagName("li"));
            Assert.assertEquals(driver.getCurrentUrl(), URLADDR.concat(PAGES.get(i)), "Not equal");
        }
    }

    //==============================================================================================================

    @Test
    public void isSaleElementContainsSaleLabel() {
        //------------------------------------------------------------------------------
        //TODO:
        // 1. Go to Store,
        // 2. choose list of products,
        // 3. get every element and check if the product has a tag named "del"
        // 4. if the product has a tag named "del" check then it must have a "Sale" tag
        // 5. use check that "Sale" label overlaps product element
        //------------------------------------------------------------------------------

        //Go to store
        driver.get("https://askomdch.com/store/");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - ");
        //waiting for the products to appear on the screen
        waitForElementPresent(
                By.cssSelector("div.ast-woocommerce-container>ul.products"),
                "Products are not visible",
                Duration.ofSeconds(5));

        //Get list of products
        List<WebElement> products = driver.findElements(By.cssSelector("div ul.products li"));
        System.out.println("Amount of products in list: " + products.size());
        System.out.println();

        //Display names of all products
        for (WebElement product : products) {
            System.out.println("Product on the page: " + product.findElement(By.cssSelector("ul li h2")).getText());
        }
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println();
        //Counter for elements for sale locator
        int counter = 1;

        //Get every product and check
        for (int i = 0; i < products.size(); i++) {

            //Check if the product has a tag named "del" then it should have a "Sale" label
            if (products.get(i).findElements(By.tagName("del")).size() > 0) {

                //Output products with "Sale" label
                System.out.println("Product for sale: " + products.get(i).findElement(By.cssSelector("ul li h2")).getText());

                WebElement firstProductPict = driver.findElement(By.cssSelector("ul.products li.product:nth-child(" + counter + ") a:nth-child(2)"));
                WebElement saleLabel = driver.findElement(By.cssSelector("ul.products li.product:nth-child(" + counter + ") span.onsale"));
                Rectangle rect1 = firstProductPict.getRect();
                Rectangle rect2 = saleLabel.getRect();

                System.out.println("x1 = " + rect1.getX());
                System.out.println("y1 = " + rect1.getY());
                System.out.println("x2 = " + rect2.getX());
                System.out.println("y2 = " + rect2.getY());

                //check coordinates
                boolean isOverlapping = rect1.getX() < (rect2.getX() + rect2.getWidth()) &&
                        (rect1.getX() + rect1.getWidth()) > rect2.getX() &&
                        rect1.getY() < (rect2.getY() + rect2.getHeight()) &&
                        (rect1.getY() + rect1.getHeight()) > rect2.getY();


                //Assert
                Assert.assertTrue(isOverlapping, "Sale icon doesn't overlap product picture");
                System.out.println("---------------------------------------------------------------------------------");
                System.out.println();
            }
            counter += 1;

        }
    }

    //==================================================================================================================
    @Test
    public void checkQuickLinksElementsInFooter() throws InterruptedException {
        //--------------------------------------------------------------------------------------------------------------
        //TODO:
        // 1. Open main page "https://askomdch.com/"
        // 2. Go to footer
        // 3. Check that each footer link in Quick Links column leads to the corresponding page
        // 4. use main page header for check
        //--------------------------------------------------------------------------------------------------------------

        //Create list of footer Quick links
        List<WebElement> quickLinksList = driver.findElement(By.id("menu-quick-links")).findElements(By.cssSelector("li>a"));
        System.out.println("=========================================================================================");
        System.out.println("size of list = " + quickLinksList.size());
        System.out.println("++++++++");
        for (int i = (quickLinksList.size() - 1);i >= 0 ; i--) {

            //Get text from link
            String textLink = quickLinksList.get(i).getText();
            System.out.println("name of link = " + textLink);

            //scroll to the footer element and click on it
            new Actions(driver).moveToElement(
                    quickLinksList.get(i))
                    .click()
                    .perform();
            String header = driver.findElement(By.cssSelector("h1.has-text-align-center")).getText();
            System.out.println("Name of header " + driver.findElement(By.cssSelector("h1.has-text-align-center")).getText());
            System.out.println("HEADERS element " + HEADERS.get(i));
            System.out.println("++++++++");

            //refreshing the list of items
            quickLinksList = driver.findElement(By.id("menu-quick-links")).findElements(By.cssSelector("li>a"));

            //Assert that the header on the page matches corresponding element in list HEADERS
            Assert.assertEquals(HEADERS.get(i), header);
        }
    }
}


