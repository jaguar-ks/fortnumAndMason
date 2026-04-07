package fortnumAndMason;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private static WebDriver chromeDriver;
    private LoginPage login;

    @BeforeAll
    static void setUp() {
        ChromeOptions options = new ChromeOptions();
        chromeDriver = new ChromeDriver(options);
    }
    
    @AfterAll
    static void cleanUp() {
        if (chromeDriver != null)
            chromeDriver.quit();
    }
    
    @Test
    public void signIn() {
        chromeDriver.get("https://www.fortnumandmason.com/login");
        login = new LoginPage(chromeDriver);
        manageCookies();
        WebDriverWait wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
        wait.until(login.isPopUPActive());
        login.closePopUp();
        wait.until(login.isPageReady());
        login.setEmail();
        login.pressLoginButton();
        wait.until(login.isPassWordReady());
        login.setPassWord();
        login.pressSignInButton();
        wait.until(login.isAlertActive());
        assertTrue(login.isLogedIn(chromeDriver));
    }

    void manageCookies() {
        Thread thrd = new Thread(() -> {
            try {
                WebElement shadowHost = new FluentWait<>(chromeDriver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.presenceOfElementLocated(
                    By.id("usercentrics-root")
                ));
                try {
                    SearchContext shadowRoot = shadowHost.getShadowRoot();
                    WebElement rejectCookies = shadowRoot.findElement(
                        By.cssSelector("button[data-testid='uc-deny-all-button']")
                    );
                    rejectCookies.click();
                }
                catch (NoSuchElementException e) {}
            } catch (Exception e) {
                System.out.println("[Error] :: " + e);
            }
        });
        thrd.setDaemon(true);
        thrd.start();
    }
}
