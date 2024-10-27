import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class PaymentPage {

    private final WebDriver driver;

    @FindBy(xpath = "//div[contains(@class, 'pay__wrapper')]//h2[contains(text(), 'Онлайн пополнение')]")
    private WebElement blockTitle;

    @FindBy(xpath = "//*[@id='pay-section']/div/div/div[2]/section/div/div[2]/ul")
    private WebElement paymentLogos;

    @FindBy(xpath =" //div[@id='pay-section']//a[text()='Подробнее о сервисе']")
    private WebElement serviceDetailsLink;

    @FindBy(xpath = "//*[@id='connection-phone']")
    private WebElement phoneNumberField;

    @FindBy(xpath = "//*[@id='connection-sum']")
    private WebElement amountField;

    @FindBy(xpath = "//*[@id='connection-email']")
    private WebElement emailField;

    @FindBy(xpath = "//*[@id='pay-connection']/button")
    private WebElement continueButton;

    @FindBy(xpath = "//*[@id='pay-section']/div/div/div[2]/section/div/div[1]/div[1]/div[2]/button/span[2]/svg")
    private WebElement serviceDropdown;

    @FindBy(xpath = "//*[@id='cookie-agree']")
    private WebElement acceptCookiesButton;

    public void acceptCookies() {
        if (acceptCookiesButton.isDisplayed()) {
            acceptCookiesButton.click();
        }
    }

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getBlockTitleText() {
        return blockTitle.getText();
    }

    public boolean arePaymentLogosDisplayed() {
        return paymentLogos.isDisplayed();
    }

    public boolean isContinueButtonEnabled() {
        return continueButton.isEnabled();
    }

    public boolean isConfirmationDisplayed() {
        WebElement confirmationMessage = driver.findElement(By.xpath("//div[@class='success-message']"));
        return confirmationMessage.isDisplayed();
    }

    public void clickServiceDetailsLink() {
        serviceDetailsLink.click();
    }
    public void clickContinueButton() {
        continueButton.click();
    }

    public boolean areFieldsPresent() {
        return phoneNumberField.isDisplayed() && amountField.isDisplayed() && emailField.isDisplayed();
    }

    public void selectServiceType(String service) {
        serviceDropdown.click();
    }

    public void enterPhoneNumber(String phoneNumber) {
        phoneNumberField.sendKeys(phoneNumber);
    }

    public void enterAmount(String amount) {
        amountField.sendKeys(amount);
    }

    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public boolean isPhoneNumberFilled() {
        return false;
    }

    public boolean isAmountFilled() {
        return false;
    }

    public boolean isEmailFilled() {
        return false;
    }

    public String getDisplayedAmount() {
        return "";
    }

    public String getDisplayedPhoneNumber() {
        return "";
    }

    public boolean arePaymentSystemIconsDisplayed() {
        return false;
    }
}
