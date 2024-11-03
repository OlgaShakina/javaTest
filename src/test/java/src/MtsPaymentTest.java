package src;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Assertions;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class MtsPaymentTest {
    private WebDriver driver;
    private PaymentPage paymentPage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/olgabuinova/documents/chromedriver-mac-arm64/chromedriver");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://mts.by");
        paymentPage = new PaymentPage(driver);
        paymentPage.acceptCookies();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testBlockTitle() {
        Assertions.assertEquals("Онлайн пополнение без комиссии", paymentPage.getBlockTitleText(), "Заголовок блока неверен");
    }

    @Test
    public void testPaymentLogosDisplayed() {
        assertTrue(paymentPage.arePaymentLogosDisplayed(), "Логотипы платежных систем не отображаются");
    }

    @Test
    public void testServiceDetailsLink() {
        paymentPage.clickServiceDetailsLink();
        assertTrue(driver.getCurrentUrl().contains("mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/"), "Ссылка ведет на неверную страницу");
    }


    @Test
    public void testContinueButtonFunctionality() {
        paymentPage.enterPhoneNumber("297777777");
        paymentPage.enterAmount("10");
        paymentPage.enterEmail("test@example.com");

        Assertions.assertTrue(paymentPage.isContinueButtonEnabled(), "Кнопка 'Продолжить ' неактивна после заполнения полей формы");

        paymentPage.clickContinueButton();

        Assertions.assertTrue(paymentPage.isConfirmationDisplayed(), "Ожидаемое подтверждение не отображается после нажатия кнопки 'Продолжить'");
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

            Assertions.assertTrue(paymentPage.isPhoneNumberFilled(), "Поле номера телефона не заполнено для услуги: " + service);
            Assertions.assertTrue(paymentPage.isAmountFilled(), "Поле суммы не заполнено для услуги: " + service);
            Assertions.assertTrue(paymentPage.isEmailFilled(), "Поле электронной почты не заполнено для услуги: " + service);
        }
    }
}