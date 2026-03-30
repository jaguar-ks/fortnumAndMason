package fortnumAndMason;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.github.cdimascio.dotenv.Dotenv;

public class LoginPage {
    
    @FindBy(css="input[aria-label='Email Address']")
    private WebElement email;

    @FindBy(css="input[name='password']")
    private WebElement passWord;
    
    @FindBy(css="button[aria-label='Login'][type='submit']")
    private WebElement loginButton;

    @FindBy(css="button[aria-label='Close']")
    private WebElement closePopUpButton;

    @FindBy(css="button[data-testid='uc-deny-all-button']")
    private WebElement rejectAllCookies;

    @FindBy(css="button[aria-label='Sign-in'][type='submit']")
    private WebElement signInButton;

    private final Dotenv dotenv;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        dotenv = Dotenv.load();
    }

    public void setEmail() {
        email.sendKeys(dotenv.get("EMAIL"));
    }

    public void setPassWord() {
        passWord.sendKeys(dotenv.get("PASSWORD"));
    }

    public void pressLoginButton() {
        loginButton.click();
    }

    public void pressSignInButton() {
        signInButton.click();
    }

    public ExpectedCondition<WebElement> isPassWordReady() {
        return ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("input[name='password']")
        );
    }

    public ExpectedCondition<WebElement> isAlertActive() {
        return ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("div[role='alert']")
        );
    }

    public ExpectedCondition<WebElement> isCookiePopUpActiv() {
        return ExpectedConditions.visibilityOfElementLocated(
            By.id("uc-center-container")
        );
    }

    public ExpectedCondition<WebElement> isPageReady() {
        return ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("input[aria-label='Email Address']")
        );
    }

    public ExpectedCondition<WebElement> isPopUPActive() {
        return ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("button[aria-label='Close']")
        );
    }

    public void closePopUp() {
        closePopUpButton.click();
    }

    public void rejectCookies() {
        rejectAllCookies.click();
    }

    public boolean isLogedIn(WebDriver driver) {
        WebElement alertDivText = driver.findElement(
            By.xpath("//div[@role='alert']//div[text()='You are logged in!']")
        );
        return alertDivText.isDisplayed();
    }
}
