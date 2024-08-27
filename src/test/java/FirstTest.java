import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstTest extends BaseTest{

    @Test
    public void testFirst() throws InterruptedException {
        String text = driver.findElement(By.tagName("h1")).getText();

        Assert.assertEquals(text, "AskOmDch");
    }

    @Test
    public void testSecond() throws InterruptedException {
        driver.findElement(By.xpath("//a[@href='/store']")).click();
        String text = driver.findElement(By.tagName("h1")).getText();

        Assert.assertEquals(text, "Store");
    }

    @Test

    public void testShoppingCartIconValidationTest() {
        driver.findElement(By.xpath("//a[@class='wp-block-button__link']")).click();
        driver.findElement(By.xpath("//a[contains(@href, '?add-to-cart=1205')]")).click();
        driver.findElement(By.xpath("//div[@class='site-primary-header-wrap ast-builder-grid-row-container site-header-focus-item ast-container']//span[@class='count'][normalize-space()='1']")).click();
        String text = driver.findElement(By.xpath("//a[normalize-space()='Basic Blue Jeans']")).getText();

        Assert.assertEquals(text, "Basic Blue Jeans");

    }

    @Test

    public void ShoppingCartPopUpValidationTest() {
        driver.findElement(By.xpath("//a[@class='wp-block-button__link']")).click();
        driver.findElement(By.xpath("//a[@aria-label='Add “Anchor Bracelet” to your cart']")).click();
        WebElement shoppingCart = driver.findElement(By.xpath("//div[@class='site-primary-header-wrap ast-builder-grid-row-container site-header-focus-item ast-container']//span[@class='count'][normalize-space()='1']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(shoppingCart).perform();
        WebElement viewCart = driver.findElement(By.xpath("//div[@class='site-primary-header-wrap ast-builder-grid-row-container site-header-focus-item ast-container']//a[@class='button wc-forward'][normalize-space()='View cart']"));
        viewCart.click();
        String text = driver.findElement(By.xpath("//a[normalize-space()='Anchor Bracelet']")).getText();

        Assert.assertEquals(text, "Anchor Bracelet");

    }
}

