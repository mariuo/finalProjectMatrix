package tests;


import common.UtilAlertMsg;
import factory.Client;
import factory.ClientFactory;
import factory.Login;
import factory.LoginFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;

import java.util.Arrays;
import java.util.List;

/**
 * testNG class to the function Register.
 */
public class TestCaseRegister extends TestCaseConfig {

    private HomePageLogin homePageLogin;
    private SignInStep1Page signInStep1Page;
    private SignInStep2Page signInStep2Page;
    private SignInStep3Page signInStep3Page;
    private SignInStep4Page signInStep4Page;
    private DashboardPage dashboardPage;
    private AccountDetailsPage accountDetailsPage;
    private Client clientMick;
    private Login loginMick;
    private Client clientJohn;
    private Login loginJohn;

    @BeforeTest
    public void beforeClass() {
        clientMick = ClientFactory.createClientMick();
        clientJohn = ClientFactory.createClientJohn();

        loginJohn = LoginFactory.createLoginJohn();
        loginMick = LoginFactory.createLoginMick();
    }

    @BeforeMethod
    public void beforeMethod() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--no-sandbox");
        opt.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(opt);
        homePageLogin = new HomePageLogin(driver);
    }

    @AfterMethod
    public void afterMethod() {
        driver.close();
    }

    @Test(description = "SignIn should return success message when valid data." +
            "SignIn doit renvoyer un message de succes lorsque donnee valide")
    public void tcr01() {

        homePageLogin.openPage(urlPage);
        signInStep1Page = homePageLogin.gotoSignin();
        signInStep1Page.fillFields(clientMick);

        signInStep2Page = signInStep1Page.getSignInStep2Page();
        signInStep2Page.fillFields(clientMick);

        signInStep3Page = signInStep2Page.getSignInStep3Page();
        signInStep3Page.uploadFile(fileBrowse);

        signInStep4Page = signInStep3Page.getSignInStep4Page();
        signInStep4Page.uploadFile(fileBrowse);

        String result = UtilAlertMsg.getAlertText(driver);
        loginMick.setPin(UtilAlertMsg.getPin(result));

        Assert.assertFalse(result.isEmpty());

    }

    @Test(description = "Login should return Main Application Page when VALID data after register." +
            "Login doit renvoyer Main Application Page lorsque VALIDE donnee apres inscription.")
    public void tcr02() {
        List<String> expectList = Arrays.asList("Stripe", "Visa", "Paypal", "Jcb",
                "Mastercard", "Discover", "American Express", "Diners Club");
        String expectMsg = "You have successfully joined";

        homePageLogin.openPage(urlPage);
        homePageLogin.fillFields(loginMick);
        dashboardPage = homePageLogin.getDashboardPage();
        boolean resultEle = dashboardPage.verifyElements(expectList);
        boolean resultMsg = dashboardPage.verifyMsgSuccess(expectMsg);

        Assert.assertTrue(resultEle);
        Assert.assertTrue(resultMsg);
    }

    @Test(description = "Account should return fullName Client when request." +
            "Account doit renvoyer le Nom complet du Client lorsque requis.")
    public void tcr03() throws InterruptedException {
        String resultFullname;
        String expectFullname = clientJohn.getLastName() + " " + clientJohn.getFirstName();

        homePageLogin.openPage(urlPage);
        homePageLogin.fillFields(loginJohn);

        dashboardPage = homePageLogin.getDashboardPage();

        accountDetailsPage = dashboardPage.gotoAccount();
        accountDetailsPage.gotoPersonalDetail();

        Thread.sleep(100);
        resultFullname = accountDetailsPage.getFullname();
        resultFullname = resultFullname.toLowerCase();

        Assert.assertEquals(resultFullname, expectFullname);

    }

    @Test(description = "Account should return all Info Client when request." +
            "Account doit renvoyer tous l'informations du Client lorsque requis.")
    public void tcr04() throws InterruptedException {
        boolean resultAllInfo;

        homePageLogin.openPage(urlPage);
        homePageLogin.fillFields(loginJohn);

        dashboardPage = homePageLogin.getDashboardPage();

        accountDetailsPage = dashboardPage.gotoAccount();
        accountDetailsPage.gotoPersonalDetail();

        Thread.sleep(100);
        resultAllInfo = accountDetailsPage.verifyElements(clientJohn);

        Assert.assertTrue(resultAllInfo);

    }


}
