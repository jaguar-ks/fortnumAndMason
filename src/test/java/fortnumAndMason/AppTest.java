package fortnumAndMason;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import fortnumAndMason.LoginPage;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.logging.LoggingHandler;
import org.openqa.selenium.support.*;
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
        // try {
        chromeDriver.get("https://www.fortnumandmason.com/login");
        login = new LoginPage(chromeDriver);
        WebDriverWait wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
        // wait.until(login.isCookiePopUpActiv());
        // login.rejectCookies();
        wait.until(login.isPopUPActive());
        login.closePopUp();
        wait.until(login.isPageReady());
        login.setEmail();
        login.pressLoginButton();
        wait.until(login.isPassWordReady());
        login.setPassWord();
        login.pressSignInButton();
        assertTrue(login.isLogedIn(chromeDriver));
        // } catch (Exception e) {
        //     System.out.println("[Error] :: " + e);
        // }
    }
}
