// На главной странице переход по кнопке 'SHOP NOW' на страницу выбора товаров
// С помощью выпадающего меню 'Select a category' выбираем 'Mean's Jeans'
// Удостоверяемся, что надпись 'Men's Jeans' присутствует, то есть категория товаров выбрана
// С помощью слайдера выбираем диапазон цен, нажимаем кнопку 'FILTER'
// Переходим в выбранные товары, добавляем в корзину 'ADD TO CART'
// Убеждаемся, что надпись 'View cart' появилась, кликаем на ней
// Переходим в корзину, далее переходим к оформлению 'Proceed to checkout'
// На следующей странице заполняем все необходимые поля и создаем аккаунт (checkbox)
// Заполняем поля логина и пароля

import com.ecommerce.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SergeyVTest extends BaseTest {

    @Test
    public void testStoreName() {

        String websiteName = driver.findElement(By.xpath("//h1/a[@href='https://askomdch.com/']")).getText();

        Assert.assertEquals(websiteName,"AskOmDch");
    }
    @Test
    public void EndToEndTest() throws InterruptedException {

        driver.findElement(By.xpath("//*[@id='post-61']/div/div[1]/div/div/div/div/div[1]/a")).click();

        WebElement dropdown = driver.findElement(By.xpath("//*[@id='product_cat']"));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Men’s Jeans  (4)");

        String searchResult = driver.findElement(By.xpath("//*[@id='main']/div/header")).getText();
        Assert.assertEquals(searchResult, "Men's Jeans");

        //выбор необходимого параметра цены на слайдере
        driver.findElement(By.xpath("//*[@id='woocommerce_price_filter-3']/form/div/div[1]/div")).click();
        driver.findElement(By.xpath("//*[@id='woocommerce_price_filter-3']/form/div/div[1]/span[2]")).click();
        //нажатие на кнопку FILTER
        driver.findElement(By.xpath("//*[@id='woocommerce_price_filter-3']/form/div/div[2]/button")).click();

        driver.findElement(By.xpath("//*[@id='main']/div/ul/li[2]/div[2]/a[2]")).click();

        String viewCart = driver.findElement(By.xpath("//*[@id='main']/div/ul/li[2]/div[2]/a[3]")).getText();
        Assert.assertEquals(viewCart, "View cart");

        driver.findElement(By.xpath("//*[@id='main']/div/ul/li[2]/div[2]/a[3]")).click();
        driver.findElement(By.xpath("//*[@id='post-1220']/div/div/div/div/div[2]/div/div/a")).click();

        String checkout = driver.findElement(By.xpath("//*[@id='post-1221']/div/div/div/h1")).getText();
        Assert.assertEquals(checkout, "Checkout");

        //заполнение полей
        WebElement firstName = driver.findElement(By.xpath("//input[@name='billing_first_name']"));
        firstName.sendKeys("Sergey", Keys.ENTER);

        WebElement lastName = driver.findElement(By.xpath("//input[@name='billing_last_name']"));
        lastName.sendKeys("Vyshkvarok", Keys.ENTER);

        WebElement dropdownCountry = driver.findElement(By.xpath("//*[@id='billing_country']"));
        Select selectCountry = new Select(dropdownCountry);
        selectCountry.selectByVisibleText("Russia");

        WebElement streetAddress = driver.findElement(By.xpath("//input[@name='billing_address_1']"));
        streetAddress.sendKeys("Dzerzhinsky street", Keys.ENTER);

        WebElement city = driver.findElement(By.xpath("//input[@name='billing_city']"));
        city.sendKeys("Yakutsk");

        WebElement county = driver.findElement(By.xpath("//input[@name='billing_state']"));
        county.sendKeys("Sakha");

        WebElement postcode = driver.findElement(By.xpath("//input[@name='billing_postcode']"));
        postcode.sendKeys("677000");

        WebElement email = driver.findElement(By.xpath("//input[@name='billing_email']"));
        email.sendKeys("email@email.com");

        WebElement createAccount = driver.findElement(By.xpath("//*[@name='createaccount']"));
        createAccount.click();

        WebElement accountUsername = driver.findElement(By.xpath("//input[@name='account_username']"));
        accountUsername.sendKeys("Sergey_V");

        WebElement accountPassword = driver.findElement(By.xpath("//input[@name='account_password']"));
        accountPassword.sendKeys("123456");


        Thread.sleep(3000);
    }
}
