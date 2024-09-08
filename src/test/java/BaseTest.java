import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
        WebDriver driver;

        @BeforeMethod
        public void setUp(){
            driver = new ChromeDriver();
            driver.get("https://askomdch.com/");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        }

        @AfterMethod
        public void tearDown(){
            driver.quit();

        }
}
