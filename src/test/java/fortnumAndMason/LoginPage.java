package fortnumAndMason;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.github.cdimascio.dotenv.Dotenv;


/****************************/
/*     LOGIN PAGE MODEL     */
/****************************/
public class LoginPage {
    /******************[LOGIN PAGE ELEMETNS]******************/
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
    /*********************************************************/


    // ENV VARIABELS HANDLER
    private final Dotenv dotenv;
    
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        dotenv = Dotenv.load();
    }


    /******************[DATA SETERS]******************/
    public void setEmail() {
        email.sendKeys(dotenv.get("EMAIL"));
    }
    
    public void setPassWord() {
        passWord.sendKeys(dotenv.get("PASSWORD"));
    }
    /*************************************************/


    /********************[CHECKERS]********************/
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
    
    public ExpectedCondition<WebElement> isCookiePopUpActive() {
        return ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("button[data-testid='uc-deny-all-button']")
        );
    }
    
    public ExpectedCondition<WebElement> isPageReady() {
        return ExpectedConditions.elementToBeClickable(
            By.cssSelector("button[aria-label='Login'][type='submit']")
        );
    }
    
    public ExpectedCondition<WebElement> isPopUPActive() {
        return ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("button[aria-label='Close']")
        );
    }
    
    public boolean isLogedIn(WebDriver driver) {
        WebElement alertDivText = driver.findElement(
            By.xpath("//div[@role='alert']//div[text()='You are logged in!']")
        );
        return alertDivText.isDisplayed();
    }
    /**************************************************/
    
    
    /********************[ACTIONS]********************/
    public void closePopUp() {
        closePopUpButton.click();
    }
    
    public boolean rejectCookies() {
        rejectAllCookies.click();
        return false;
    }
    
    public void pressLoginButton() {
        loginButton.click();
    }
    
    public void pressSignInButton() {
        signInButton.click();
    }
    /*************************************************/
}
