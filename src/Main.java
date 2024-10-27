import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.WebDriver;


public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "junit5/src/main/resources/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://google.com");
        driver.quit();
    }
}