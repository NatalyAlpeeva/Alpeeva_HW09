import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Alpeeva_auth {
    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @AfterTest
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }
    @BeforeMethod
    public void beforeMethod() {

        driver.get("http://prestashop.qatestlab.com.ua/en/authentication?back=my-account#account-creation");
    }
    @Test
    public void authTest() {
        driver.findElement(By.name("email_create")).sendKeys("natalie.alpeeva@gmail.com");
        driver.findElement(By.name("SubmitCreate")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement result = driver.findElement(By.className("page-heading"));
        Assert.assertEquals(result.getText(), "CREATE AN ACCOUNT");
    }
    @Test
    public void negativeTest() {
        driver.findElement(By.name("email_create")).sendKeys("nataliealpeevagmail.com");
        driver.findElement(By.name("SubmitCreate")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

       WebElement errorMessage = driver.findElement(By.id("create_account_error"));
        Assert.assertEquals(errorMessage.getText(), "Invalid email address.");

    }
}
