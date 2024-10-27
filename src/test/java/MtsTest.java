import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MtsTest {

    private WebDriver driver;
    private PaymentPage PaymentPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://mts.by");
        PaymentPage = new PaymentPage(driver);
        PaymentPage.acceptCookies();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testBlockTitle() {
        Assertions.assertEquals("Онлайн пополнение без комиссии", PaymentPage.getBlockTitleText(), "Заголовок блока неверен");
    }

    @Test
    public void testPaymentLogosDisplayed() {
        Assertions.assertTrue(PaymentPage.arePaymentLogosDisplayed(), "Логотипы платежных систем не отображаются");
    }

    @Test
    public void testServiceDetailsLink() {
        PaymentPage.clickServiceDetailsLink();
        assertTrue(driver.getCurrentUrl().contains("mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/"), "Ссылка ведет на неверную страницу");
    }

    @Test
    public void testContinueButtonFunctionality() {
        PaymentPage.enterPhoneNumber("297777777");
        PaymentPage.enterAmount("10");
        PaymentPage.enterEmail("test@example.com");

        Assertions.assertTrue(PaymentPage.isContinueButtonEnabled(), "Кнопка 'Продолжить' неактивна после заполнения полей формы");

        PaymentPage.clickContinueButton();

        Assertions.assertTrue(PaymentPage.isConfirmationDisplayed(), "Ожидаемое подтверждение не отображается после нажатия кнопки 'Продолжить'");
    }

    @Test
    public void testServiceOptions() {
        String[] services = {"Услуги связи", "Домашний интернет", "Рассрочка", "Задолженность"};

        for (String service : services) {
            PaymentPage.selectServiceType(service);

            Assertions.assertTrue(PaymentPage.areFieldsPresent(), "Поля не отображаются для услуги: " + service);

            PaymentPage.enterPhoneNumber("297777777");
            PaymentPage.enterAmount("10");
            PaymentPage.enterEmail("test@example.com");

            Assertions.assertEquals(PaymentPage.isPhoneNumberFilled(), "Поле номера телефона не заполнено для услуги: " + service);
            Assertions.assertEquals(PaymentPage.isAmountFilled(), "Поле суммы не заполнено для услуги: " + service);
            Assertions.assertEquals(PaymentPage.isEmailFilled(), "Поле электронной почты не заполнено для услуги: " + service);
        }
    }
}


