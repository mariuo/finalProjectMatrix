package tests.undo;

import common.UtilAlertMsg;
import factory.Account;
import factory.AccountFactory;
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
import pages.DashboardPage;
import pages.HomePageLogin;
import pages.TransferPageEasy;
import tests.TestCaseConfig;

/**
 * testNG class to the function UNDO the Transfer "EASY BANK".
 */
public class TestCaseTranferUndo extends TestCaseConfig {

    private HomePageLogin homePageLogin;
    private DashboardPage dashboardPage;
    private TransferPageEasy transferPageEasy;
    private Login loginPaul;
    private Login loginJohn;
    private Login loginGeorge;
    private Login loginRingo;
    private Account accountPaul;
    private Account accountJohn;
    private Account accountGeorge;
    private Account accountRingo;

    @BeforeTest
    public void beforeClass() {
        loginPaul = LoginFactory.createLoginPaul();
        loginJohn = LoginFactory.createLoginJohn();
        loginGeorge = LoginFactory.createLoginGeorge();
        loginRingo = LoginFactory.createLoginRingo();

        accountPaul = AccountFactory.createAccountPaul();
        accountJohn = AccountFactory.createAccountJohn();
        accountGeorge = AccountFactory.createAccountGeorge();
        accountRingo = AccountFactory.createAccountRingo();
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

    @Test(description = "Transfer should return success message when VALID data." +
            "Transfer doit renvoyer Main Application Page lorsque VALIDE donnee apres inscription.")
    public void tce03() {
        String expectMsgAlert = "This transfer was held successfully.";
        String iCode = "";
        long amountSubtract = 1000;
        String mainAmount = "1000";
        String secAmount = "00";
        String reason = "Any reason";

        homePageLogin.openPage(urlPage);
        homePageLogin.fillFields(loginRingo);
        dashboardPage = homePageLogin.getDashboardPage();

        // #### Get the balance from page, Casting to Long and subtract 1000.
        String beforeBalance = dashboardPage.getTotalBalance();
        long expectBalance = Long.parseLong(beforeBalance) - amountSubtract;

        iCode = dashboardPage.transferBank("easy");

        transferPageEasy = dashboardPage.getTransferPage();
        transferPageEasy.fillTransfer(accountPaul, mainAmount, secAmount, reason,
                "" + iCode.charAt(0), "" + iCode.charAt(1), "" + iCode.charAt(2), "" + iCode.charAt(3));

        String resultMsgAlert = UtilAlertMsg.getAlertText(driver);

        // #### Get the balance from page after transfer, and casting to long.
        String afterBalance = transferPageEasy.getTotalBalance();
        long resultBalance = Long.parseLong(afterBalance);


        Assert.assertEquals(resultMsgAlert, expectMsgAlert);
        Assert.assertEquals(resultBalance, expectBalance);
    }

    @Test(description = "Transfer should return success message when valid day limit transfer." +
            "Transfer doit renvoyer message de succes lorsque transfer limite quotidien valide.")
    public void tce04() {
        String expectMsgAlert = "This transfer was held successfully.";
        String iCode = "";
        double amountSubtract = 20000;
        String mainAmount = "20000";
        String secAmount = "00";
        String reason = "Guitar payment";

        homePageLogin.openPage(urlPage);
        homePageLogin.fillFields(loginJohn);
        dashboardPage = homePageLogin.getDashboardPage();

        // #### Get the balance from page, Casting to Long and subtract 20000.
        String beforeBalance = dashboardPage.getTotalBalance();
        double expectBalance = Double.parseDouble(beforeBalance) - amountSubtract;

        iCode = dashboardPage.transferBank("easy");

        transferPageEasy = dashboardPage.getTransferPage();
        transferPageEasy.fillTransfer(accountGeorge, mainAmount, secAmount, reason,
                "" + iCode.charAt(0), "" + iCode.charAt(1), "" + iCode.charAt(2), "" + iCode.charAt(3));

        String resultMsgAlert = UtilAlertMsg.getAlertText(driver);

        // #### Get the balance from page after transfer, and casting to long.
        String afterBalance = transferPageEasy.getTotalBalance();
        double resultBalance = Long.parseLong(afterBalance);


        Assert.assertEquals(resultMsgAlert, expectMsgAlert);
        Assert.assertEquals(resultBalance, expectBalance);
    }
}
