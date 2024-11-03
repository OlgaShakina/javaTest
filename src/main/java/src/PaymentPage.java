package src;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PaymentPage {

    private final WebDriver driver;

    @FindBy(xpath = "//div[contains(@class, 'pay__wrapper')]//h2")
    private WebElement blockTitle;

    @FindBy(xpath = "//div[contains(@class, 'pay__partners')]//img")
    private List<WebElement> paymentLogos;

    @FindBy(xpath ="//a[@href='/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/']")
    private WebElement serviceDetailsLink;

    @FindBy(xpath = "//div[contains(@class,'input-wrapper')]//input[@class='phone']")
    private WebElement phoneNumberField;

    @FindBy(xpath = "//input[@id='connection-sum']")
    private WebElement amountField;

    @FindBy(xpath = "//input[@id='connection-email']")
    private WebElement emailField;

    @FindBy(xpath = "//button[contains(@class,'button button__default')]")
    private WebElement continueButton;

    @FindBy(xpath = "//li[contains(@class,'select__item')]//p")
    private WebElement serviceDropdown;

    @FindBy(xpath = "//*[@id='cookie-agree']")
    private WebElement acceptCookiesButton;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void acceptCookies() {
        if (acceptCookiesButton.isDisplayed()) {
            acceptCookiesButton.click();
        }
    }

    public String getBlockTitleText() {
        return blockTitle.getText();
    }

    public boolean arePaymentLogosDisplayed() {
        return paymentLogos.stream().allMatch(WebElement::isDisplayed);
    }

    public void clickServiceDetailsLink() {
        serviceDetailsLink.click();
    }

    public void enterAmount(String amount) {
        amountField.sendKeys(amount);
    }

    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public boolean isContinueButtonEnabled() {
        return continueButton.isEnabled();
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    public boolean isConfirmationDisplayed() {
        return false;
    }

    public void selectServiceType(String service) {
    }

    public boolean areFieldsPresent() {
        return phoneNumberField.isDisplayed() && amountField.isDisplayed() && emailField.isDisplayed();
    }

    public boolean isPhoneNumberFilled() {
        return!phoneNumberField.getAttribute("value").isEmpty();
    }

    public boolean isAmountFilled() {
        return !amountField.getAttribute("value").isEmpty();
    }

    public boolean isEmailFilled() {
        return !emailField.getAttribute("value").isEmpty();
    }

    public void enterPhoneNumber(String number) {
    }
}