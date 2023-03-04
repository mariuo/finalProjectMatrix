package tests;

import factory.Login;
import factory.LoginFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.HomePageLogin;

import java.util.Arrays;
import java.util.List;

public class TestCaseLogin extends TestCaseConfig {
    private HomePageLogin homePageLogin;
    private DashboardPage dashboardPage;
    private Login loginPaul;
    private Login loginInvalidEmail;
    private Login loginInvalidPass;
    private Login loginInvalidPin;
    private Login loginInvalidPassPin;

    @BeforeTest
    public void beforeClass() {
        loginInvalidEmail = LoginFactory.createLoginInvalidEmail();
        loginInvalidPass = LoginFactory.createLoginInvalidPass();
        loginInvalidPin = LoginFactory.createLoginInvalidPin();
        loginInvalidPassPin = LoginFactory.createLoginInvalidPassPin();
        loginPaul = LoginFactory.createLoginPaul();

    }

    @BeforeMethod
    public void beforeMethod() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        homePageLogin = new HomePageLogin(driver);

    }

    @AfterMethod
    public void afterMethod() {
        driver.close();
    }

    @Test(description = "Login should return error message when invalid EMAIL." +
            "Login doit renvoyer un message d'erreur lorsque EMAIL invalide.")
    public void tcl01() {
        String expect = "This Email account is not registered.";
        String result;

        homePageLogin.openPage(urlPage);
        homePageLogin.fillFields(loginInvalidEmail);
        result = homePageLogin.getErrorMsg();

        Assert.assertTrue(result.contains(expect));

    }

    @Test(description = "Login should return error message when invalid PIN." +
            "Login doit renvoyer un message d'erreur lorsque PIN invalide.")
    public void tcl02() {
        String expect = "Your Login Pin is invalid.";
        String result;

        homePageLogin.openPage(urlPage);
        homePageLogin.fillFields(loginInvalidPin);
        result = homePageLogin.getErrorMsg();

        Assert.assertTrue(result.contains(expect));

    }

    @Test(description = "Login should return error message when invalid PASSWORD." +
            "Login doit renvoyer un message d'erreur lorsque PASSWORD invalide.")
    public void tcl03() {
        String expect = "Your Login Password is invalid.";
        String result;

        homePageLogin.openPage(urlPage);
        homePageLogin.fillFields(loginInvalidPass);
        result = homePageLogin.getErrorMsg();

        Assert.assertTrue(result.contains(expect));

    }
    @Test(description = "Login should return error message when invalid PASSWORD and Pin." +
            "Login doit renvoyer un message d'erreur lorsque PASSWORD et PIN invalide.")
    public void tcl04() {
        List<String> expectList = Arrays.asList("Your Login Password is invalid.", "Your Login Pin is invalid.");
        boolean result;

        homePageLogin.openPage(urlPage);
        homePageLogin.fillFields(loginInvalidPassPin);
        result = homePageLogin.verifyErrorElements(expectList);
        Assert.assertTrue(result);
    }

    @Test(description = "Login should return Main Application Page when VALID data." +
            "Login doit renvoyer Main Application Page lorsque VALIDE donnee.")
    public void tcl05() {
        List<String> expectList = Arrays.asList("Stripe", "Visa", "Paypal", "Jcb",
                "Mastercard", "Discover", "American Express", "Diners Club");
        String expectMsg = "You have successfully joined";

        homePageLogin.openPage(urlPage);
        homePageLogin.fillFields(loginPaul);
        dashboardPage = homePageLogin.getDashboardPage();
        boolean resultEle = dashboardPage.verifyElements(expectList);
        boolean resultMsg = dashboardPage.verifyMsgSuccess(expectMsg);

        Assert.assertTrue(resultEle);
        Assert.assertTrue(resultMsg);

    }
    @Test(description = "Logout should return LoginPage when Logout." +
            "Logout doit renvoyer LoginPage lorsque Logout.")
    public void tcl06() {
        String result = "";
        String expect = "index.php";

        homePageLogin.openPage(urlPage);
        homePageLogin.fillFields(loginPaul);

        dashboardPage = homePageLogin.getDashboardPage();
        dashboardPage.clickBtnLogout();
        dashboardPage.clickSubmitLogout();

        result = dashboardPage.getUrl();

        Assert.assertTrue(result.contains(expect));

    }
    @Test(description = "Logout should do nothing when Cancel logout." +
            "Logout doit faire rien lorsque Annulé le logout.")
    public void tcl07() {
        String result = "";
        String expect = "home.php";

        homePageLogin.openPage(urlPage);
        homePageLogin.fillFields(loginPaul);

        dashboardPage = homePageLogin.getDashboardPage();
        dashboardPage.clickBtnLogout();
        dashboardPage.clickCancelLogout();

        result = dashboardPage.getUrl();

        Assert.assertTrue(result.contains(expect));

    }


}
