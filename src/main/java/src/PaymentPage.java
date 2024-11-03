package src;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.NoSuchElementException;

public class PaymentPage {

    @FindBy(xpath = "//div[@class='pay__wrapper']//h2")
    private WebElement blockTitle;

    @FindBy(xpath = "//div[contains(@class, 'pay__partners')]//img")
    private List<WebElement> paymentLogos;

    @FindBy(xpath = "//a[@href='/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/']")
    private WebElement serviceDetailsLink;

    @FindBy(xpath = "//div[contains(@class,'input-wrapper')]//input[@class='phone']")
    private WebElement phoneNumberField;

    @FindBy(xpath = "//input[@id='connection-sum']")
    private WebElement amountField;

    @FindBy(xpath = "//input[@id='connection-email']")
    private WebElement emailField;

    @FindBy(xpath = "//button[contains(@class,'button button__default')]")
    private WebElement clickContinueButton;

    @FindBy(xpath = "//li[contains(@class,'select__item')]//p")
    private WebElement serviceDropdown;

    @FindBy(xpath = "//button[@id='cookie-agree']")
    private WebElement acceptCookiesButton;

    public PaymentPage(WebDriver driver, List<WebElement> paymentLogos) {
        this.paymentLogos = paymentLogos;
        PageFactory.initElements(driver, this);
    }

    public void acceptCookies() {
        try {
            if (acceptCookiesButton.isDisplayed()) {
                acceptCookiesButton.click();
            }
        } catch (NoSuchElementException e) {
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
        return clickContinueButton.isEnabled();
    }

    public void clickContinueButton() {
        clickContinueButton.click();
    }

    public void selectServiceType(String service) {
    }

    public boolean areFieldsPresent() {
        return phoneNumberField.isDisplayed() && amountField.isDisplayed() && emailField.isDisplayed();
    }

    public void enterPhoneNumber(String number) {
        phoneNumberField.sendKeys(number);
    }

    public void closeCookieNotification() {
    }
}