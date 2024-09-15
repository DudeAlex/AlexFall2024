import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
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

        //waiting for the products to appear on the screen
        waitForElementPresent(
                By.cssSelector("div.ast-woocommerce-container>ul.products"),
                "Products are not visible",
                Duration.ofSeconds(5));

        //Get list of products
        List<WebElement> products = driver.findElements(By.cssSelector("div ul.products li"));
        System.out.println("Amount of products in list: " + products.size());

        //Display names of all products
        for(WebElement product : products){
            System.out.println("Product on the page: " + product.findElement(By.cssSelector("ul li h2")).getText());
        }
        //Get every product and check
        for (int i = 0; i < products.size(); i++) {

            //Check if the product has a tag named "del" then it should have a "Sale" label/
            if (products.get(i).findElements(By.tagName("del")).size() > 0) {
                WebElement firstProductPict = driver.findElement(By.cssSelector("ul.products li.product:nth-child(1) a:nth-child(2)"));
                WebElement saleLabel = driver.findElement(By.cssSelector("ul.products li.product:nth-child(1) span.onsale"));
                Rectangle rect1 = firstProductPict.getRect();
                Rectangle rect2 = saleLabel.getRect();

                //check coordinates
                boolean isOverlapping = rect1.getX() < (rect2.getX() + rect2.getWidth()) &&
                        (rect1.getX() + rect1.getWidth()) > rect2.getX() &&
                        rect1.getY() < (rect2.getY() + rect2.getHeight()) &&
                        (rect1.getY() + rect1.getHeight()) > rect2.getY();

                //Output products with "Sale" label
                System.out.println("Product for sale: " + products.get(i).findElement(By.cssSelector("ul li h2")).getText());

                //Assert
                Assert.assertTrue(isOverlapping, "Sale icon doesn't overlap product picture");
            }

        }
    }
}
