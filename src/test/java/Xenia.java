
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class Xenia extends BaseTest{

    @Test
    public void testFindMoreButton() throws InterruptedException {
        org.openqa.selenium.WebElement element;
        try {
            element = driver.findElement(By.xpath("//*[@id=\"post-61\"]/div/div[1]/div/div/div/div/div[2]/a"));
        } catch (Exception e) {
            System.out.println("Element not found");
            throw new RuntimeException(e);
        }
        element.click();
        int count = 0;
        while (count < 10) {
            if (driver.getCurrentUrl().equals("https://askomdch.com/contact-us/")){
                break;
            }
            Thread.sleep(200);
            count++;
        }
        // Verify the URL of the current page
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.equals("https://askomdch.com/contact-us/")) {
            System.out.println("URL verification passed: " + currentUrl);
        } else {
            System.out.println("URL verification failed. Expected: https://askomdch.com/contact-us/, but got: " + currentUrl);
            throw new RuntimeException("URL verification failed. Expected: https://askomdch.com/contact-us/, but got: " + currentUrl);
        }
//*[@id="post-61"]/div/div[1]/div/div/div/div/div[1]/a
        Thread.sleep(2000);
    }

}




