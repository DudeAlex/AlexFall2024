import com.ecommerce.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class ovdroFooterListTitlesTest extends BaseTest {
    @Test
    public void testFooterForHimListTitles() throws InterruptedException {

        String men = driver.findElement(By.id("menu-item-1263")).getText(); // перепутан порядок айди
        Assert.assertEquals(men, "Men");

        String mensJeans = driver.findElement(By.id("menu-item-1259")).getText();
        Assert.assertEquals(mensJeans, "Men’s Jeans");

        String mensShirts = driver.findElement(By.id("menu-item-1260")).getText();
        Assert.assertEquals(mensShirts, "Men’s Shirts");

        String mensShoes = driver.findElement(By.id("menu-item-1261")).getText();
        Assert.assertEquals(mensShoes, "Men’s Shoes");

        String manAccessories = driver.findElement(By.id("menu-item-1262")).getText();
        Assert.assertEquals(manAccessories, "Accessories");

    }


    @Test
    public void testFooterForHerListTitles() throws InterruptedException {
        String text1 = driver.findElement(By.id("menu-item-1264")).getText();
        Assert.assertEquals(text1, "Women");

        String text2 = driver.findElement(By.id("menu-item-1266")).getText(); // айдишки не по порядку
        Assert.assertEquals(text2, "Women’s Jeans");

        String womenShirt  = driver.findElement(By.id("menu-item-1265")).getText();
        Assert.assertEquals(womenShirt, "Women’s Shirts");

        String womenShoes = driver.findElement(By.id("menu-item-1267")).getText();
        Assert.assertEquals(womenShoes, "Women’s Shoes");

        String womanAccessories = driver.findElement(By.id("menu-item-1268")).getText();
        Assert.assertEquals(womanAccessories, "Accessories");

    }
    @Test
    public void testFooterQuickLinkListTitles() throws InterruptedException {
        String text1 = driver.findElement(By.id("menu-item-1254")).getText();
        Assert.assertEquals(text1, "Home");

        String text2 = driver.findElement(By.id("menu-item-1255")).getText();
        Assert.assertEquals(text2, "About");

        String womenShirt  = driver.findElement(By.id("menu-item-1256")).getText();
        Assert.assertEquals(womenShirt, "My Account");

        String womenShoes = driver.findElement(By.id("menu-item-1257")).getText();
        Assert.assertEquals(womenShoes, "Cart");

        String womanAccessories = driver.findElement(By.id("menu-item-1258")).getText();
        Assert.assertEquals(womanAccessories, "Contact Us");
    }
    @Test
    public void testAppstoreIconFindClick() throws InterruptedException {
        // Получаем текущее окно
        String originalWindow = driver.getWindowHandle();
        driver.findElement(By.xpath("//a[@ href = 'https://www.apple.com/in/app-store/']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        // Переключаемся на новое окно
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        // Проверяем URL в новом окне
        String urlNewWindow = driver.getCurrentUrl();
        Assert.assertEquals(urlNewWindow,"https://www.apple.com/in/app-store/");
        // Возвращаемся обратно в исходное окно
        driver.switchTo().window(originalWindow);
        // Проверяем URL в исходном окне
        String urlPreviousWindow = driver.getCurrentUrl();
        Assert.assertEquals(urlPreviousWindow,"https://askomdch.com/");
        // можно вынести в метод
    }

    @Test
    public void testGooglePlayIconFindClick() throws InterruptedException {
        // Получаем текущее окна
        String originalWindow = driver.getWindowHandle();
        driver.findElement(By.xpath("//a[@ href ='https://play.google.com/store']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        // Переключаемся на новое окно
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        // Проверяем URL в новом окне
        String urlNewWindow = driver.getCurrentUrl();
        Assert.assertEquals(urlNewWindow,"https://play.google.com/store/games?device=windows");
        // Возвращаемся обратно в исходное окно
        driver.switchTo().window(originalWindow);
        // Проверяем URL в исходном окне
        String urlPreviousWindow = driver.getCurrentUrl();
        Assert.assertEquals(urlPreviousWindow,"https://askomdch.com/");
    }
}
