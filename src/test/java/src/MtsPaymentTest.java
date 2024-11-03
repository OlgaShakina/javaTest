package src;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Assertions;

import java.time.Duration;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MtsPaymentTest {
    private WebDriver driver;
    private PaymentPage paymentPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://mts.by");
        paymentPage = new PaymentPage(driver, Collections.emptyList());
        paymentPage.acceptCookies();
        paymentPage.closeCookieNotification();
    }

    @Test
    public void testBlockTitle() {
        assertEquals("Онлайн пополнение без комиссии", paymentPage.getBlockTitleText(), "Заголовок блока неверен");
    }

    @Test
    public void testPaymentLogosDisplayed() {
        assertTrue(paymentPage.arePaymentLogosDisplayed(), "Логотипы платежных систем не отображаются");
    }

    @Test
    public void testServiceDetailsLink() {
        paymentPage.clickServiceDetailsLink();
        assertEquals("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/", driver.getCurrentUrl(), "Ссылка ведет на неверную страницу");
    }

    @Test
    public void testContinueButtonFunctionality() {
        paymentPage.enterPhoneNumber("297777777");
        paymentPage.enterAmount("10");
        paymentPage.enterEmail("test@example.com");

        Assertions.assertTrue(paymentPage.isContinueButtonEnabled(), "Кнопка 'Продолжить' неактивна после заполнения полей формы");

        paymentPage.clickContinueButton();

        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        assertEquals("Ожидаемый Заголовок", driver.getTitle(), "Заголовок нового окна не соответствует ожидаемому");
        System.out.println("Фактический заголовок: " + driver.getTitle());
    }

    @Test
    public void testServiceOptions() {
        String[] services = {"Услуги связи", "Домашний интернет", "Рассрочка", "Задолженность"};

        for (String service : services) {
            paymentPage.selectServiceType(service);
            Assertions.assertTrue(paymentPage.areFieldsPresent(), "Поля не отображаются для услуги: " + service);

            paymentPage.enterPhoneNumber("297777777");
            paymentPage.enterAmount("10");
            paymentPage.enterEmail("test@example.com");
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}