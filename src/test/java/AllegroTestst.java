import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class AllegroTestst {
    WebDriver driver;

    @BeforeEach
    public void testStart() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 800));
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    @AfterEach
    public void testEnd() {
        driver.quit();
    }

    @Test
    //This test inputs data necessary to register a new account, registers it and checks if tester has been taken
    //to the page where they are asked to check if they received a confirmation e-mail.
    public void createNewAccount(){
        String email = "spoof@email.com";
        String password = "Password123";
        String confirmationURL = "https://allegro.pl/rejestracja/potwierdzenie";

        driver.get("https://allegro.pl/rejestracja");
        //Accept cookie policy;
        driver.findElement(By.cssSelector("button[data-role='accept-consent']")).click();
        //Checks whether correct acount tab is open;
        Assertions.assertFalse(driver.getPageSource().contains("label class=\"m-label\" for=\"radio1\""), "You are on business account tab");
        //Clicks on e-mail input field and types the e-mail address provided before;
        driver.findElement(By.cssSelector("input[id='email']")).sendKeys(email);
        //Clicks on password input field and types the password provided before;
        driver.findElement(By.cssSelector("input[id='password']")).sendKeys(password);
        //checks if a valid e-mail address has been provided;
        Assertions.assertTrue(driver.getPageSource().contains("class=\"ozg4u ftsdx f1sm5\""),
                "Invalid e-mail address");
        //checks if correct number of characters has been provided;
        Assertions.assertTrue(driver.getPageSource().contains("span class=\"ht13u6 c1tzf\">min. 8 znaków"),
                "Password too short");
        //checks if correct number of capital letters has been provided;
        Assertions.assertTrue(driver.getPageSource().contains("span class=\"ht13u6 c1tzf\">wielka litera"),
                "Capital letter missing");
        //checks if correct number of lower-case letters has been provided;
        Assertions.assertTrue(driver.getPageSource().contains("span class=\"ht13u6 c1tzf\">mała litera"),
                "Lowercase letter missing");
        //checks if correct number of digits has been provided;
        Assertions.assertTrue(driver.getPageSource().contains("span class=\"ht13u6 c1tzf\">cyfra"),
                "Digit missing");
        //Clicks on "Mam 18 lat i więcej" button;
        driver.findElement(By.cssSelector("label[for=\"isAdultYES\"]")).click();
        //Clicks the required consent;
        driver.findElement(By.cssSelector("label[for=\"agreementTerm\"]")).click();
        //Clicks the Zarejestruj button;
        driver.findElement(By.cssSelector("button[role=\"button\"][type=\"submit\"]")).click();
        //If redirection fails, Checks if required consent has been clicked;
        Assertions.assertFalse(driver.getPageSource().contains("class=\"_1dd5x mgmw_1p _44753_3g4PV\""));
        driver.findElement(By.cssSelector("img[class=\"ilg07 i1efi _44753_17QDo\"][alt=\"mail icon\"]"));
        //Checks if redirection worked;
        Assertions.assertEquals(confirmationURL, driver.getCurrentUrl(), "You have not been redirected to " + confirmationURL);
    }

    @Test
    //This test opens the login page and click login button. then it checks if all correct warnings are present.
    public void Test2(){
    driver.get("https://allegro.pl/logowanie");
    //Accept cookie policy;
    driver.findElement(By.cssSelector("button[data-role='accept-consent']")).click();
    //Checks whether correct account tab is open;
    Assertions.assertFalse(driver.getPageSource().contains("label class=\"m-label\" for=\"radio1\""), "You are on business account tab");
    //Finds and clicks login "Zaloguj się" button;
    driver.findElement(By.cssSelector("button[role=\"button\"][type=\"submit\"]")).click();
    //Checks the red tooltip message for empty login/e-mail field;
    Assertions.assertTrue(driver.getPageSource().contains("div id=\"login-error\""), "Login or e-mail field is not empty");
    //Checks the red tooltip message for empty password field;
    Assertions.assertTrue(driver.getPageSource().contains("div id=\"password-error\""), "Password field is not empty");
    }
}
