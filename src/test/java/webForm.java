import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class webForm {
    WebDriver driver;
    @BeforeAll
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @DisplayName("Fill the web-form by visiting the website and assert the expected message")
    @Test
    public void webFormFillUp() {
        driver.get("https://www.digitalunite.com/practice-webform-learners");

        driver.findElement(By.id("onetrust-reject-all-handler")).click();

        driver.findElement(By.id("edit-name")).sendKeys("Musfika Jahan");
        driver.findElement(By.id("edit-number")).sendKeys("01639199999");
        driver.findElement(By.xpath("//label[contains(text(),'20-30')]")).click();
        driver.findElement(By.id("edit-date")).click();
        DateFormat todayDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date currentDate = new Date();
        String todayDate = todayDateFormat.format(currentDate);
        driver.findElement(By.id("edit-date")).sendKeys(todayDate, Keys.ENTER);
        driver.findElement(By.id("edit-email")).sendKeys("suptimusfika1999@gmail.com");
        driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-")).sendKeys("Hi there! " +
                "I am a CSE graduate. Currently I am doing my MSC in CSE from Jahangirnagar University. " +
                "I am also doing job in BracIT Services Limited. My future goal is to become a " +
                "successful SQA Engineer.");

        WebElement uploadElement = driver.findElement(By.id("edit-uploadocument-upload"));
        //uploadElement.sendKeys("D:\\New folder\\Musfika_Jahan_Resume.pdf");
        File document = new File(".\\src\\test\\resources\\Musfika_Jahan_Resume.pdf");
        uploadElement.sendKeys(document.getAbsolutePath());

        driver.findElement(By.id("edit-age")).click();
        driver.findElement(By.id("edit-submit")).click();

        driver.switchTo().alert().accept();

        String actual_message = driver.findElement(By.className("page-title")).getText();
        String expected_message = "Thank you for your submission!";
        Assertions.assertEquals(actual_message,expected_message);

    }
    @AfterAll
    public void closeDriver() {
        driver.quit();

    }
}
