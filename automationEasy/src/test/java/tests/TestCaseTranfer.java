package tests;

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

/**
 * testNG class to the function Transfer "EASY BANK".
 */
public class TestCaseTranfer extends TestCaseConfig {

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
    private Account accountRingoInvalid;

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
        accountRingoInvalid = AccountFactory.createAccountRingoInvalid();
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

    @Test(description = "Transfer should return error message when negative amount." +
            "Transfer doit renvoyer message d'erreur lorsque montant négative.")
    public void tce01() {
        String expectMsgAlert = "Only Numbers (up to 7 digits)";
        String iCode = "";
        String mainAmount = "-100";
        String secAmount = "00";
        String reason = "Negative test";

        homePageLogin.openPage(urlPage);
        homePageLogin.fillFields(loginPaul);
        dashboardPage = homePageLogin.getDashboardPage();


        iCode = dashboardPage.transferBank("easy");

        transferPageEasy = dashboardPage.getTransferPage();
        transferPageEasy.fillTransfer(accountRingo, mainAmount, secAmount, reason,
                "" + iCode.charAt(0), "" + iCode.charAt(1), "" + iCode.charAt(2), "" + iCode.charAt(3));

        String resultMsgAlert = transferPageEasy.getMainAmountMsg();


        Assert.assertEquals(expectMsgAlert, resultMsgAlert);
    }

    @Test(description = "Transfer should return error message when null amount(zero)." +
            "Transfer doit renvoyer message d'erreur lorsque montant nulle(zero).")
    public void tce02() {
        String expectMsgAlert = "Your element is invalid Please try again.";
        String iCode = "";
        String mainAmount = "00";
        String secAmount = "00";
        String reason = "Negative test";

        homePageLogin.openPage(urlPage);
        homePageLogin.fillFields(loginPaul);
        dashboardPage = homePageLogin.getDashboardPage();

        iCode = dashboardPage.transferBank("easy");

        transferPageEasy = dashboardPage.getTransferPage();
        transferPageEasy.fillTransfer(accountRingo, mainAmount, secAmount, reason,
                "" + iCode.charAt(0), "" + iCode.charAt(1), "" + iCode.charAt(2), "" + iCode.charAt(3));

        String resultMsgAlert = transferPageEasy.getErrorMsg();


        Assert.assertEquals(resultMsgAlert, expectMsgAlert);
    }

    @Test(description = "Transfer should return success message when VALID data." +
            "Transfer doit renvoyer Main Application Page lorsque VALIDE donnee apres inscription.")
    public void tce03() {
        String expectMsgAlert = "This transfer was held successfully.";
        String iCode = "";
        double amountSubtract = 1000;
        String mainAmount = "1000";
        String secAmount = "00";
        String reason = "Any reason";



        homePageLogin.openPage(urlPage);
        homePageLogin.fillFields(loginPaul);
        dashboardPage = homePageLogin.getDashboardPage();

        // #### Get the balance from page, Casting to Double and subtract 1000.
        String beforeBalance = dashboardPage.getTotalBalance();
        double expectBalance = Double.parseDouble(beforeBalance) - amountSubtract;

        iCode = dashboardPage.transferBank("easy");

        transferPageEasy = dashboardPage.getTransferPage();
        transferPageEasy.fillTransfer(accountRingo, mainAmount, secAmount, reason,
                "" + iCode.charAt(0), "" + iCode.charAt(1), "" + iCode.charAt(2), "" + iCode.charAt(3));

        String resultMsgAlert = UtilAlertMsg.getAlertText(driver);

        // #### Get the balance from page after transfer, and casting to Double.
        String afterBalance = transferPageEasy.getTotalBalance();
        double resultBalance = Double.parseDouble(afterBalance);


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
        homePageLogin.fillFields(loginGeorge);
        dashboardPage = homePageLogin.getDashboardPage();

        // #### Get the balance from page, Casting to Double and subtract 20000.
        String beforeBalance = dashboardPage.getTotalBalance();
        double expectBalance = Double.parseDouble(beforeBalance) - amountSubtract;

        iCode = dashboardPage.transferBank("easy");

        transferPageEasy = dashboardPage.getTransferPage();
        transferPageEasy.fillTransfer(accountJohn, mainAmount, secAmount, reason,
                "" + iCode.charAt(0), "" + iCode.charAt(1), "" + iCode.charAt(2), "" + iCode.charAt(3));

        String resultMsgAlert = UtilAlertMsg.getAlertText(driver);

        // #### Get the balance from page after transfer, and casting to Double.
        String afterBalance = transferPageEasy.getTotalBalance();
        double resultBalance = Double.parseDouble(afterBalance);


        Assert.assertEquals(resultMsgAlert, expectMsgAlert);
        Assert.assertEquals(resultBalance, expectBalance);
    }

    @Test(description = "Transfer should return error message when amount bigger then day limit." +
            "Transfer doit renvoyer message d'erreur lorsque montant plus grand que la limite quotidien.")
    public void tce05() {
        String expectMsgAlert = "You have exceeded the transfer limit Apply for overtransfer.";
        String iCode = "";
        String mainAmount = "21000";
        String secAmount = "00";
        String reason = "Guitar payment";

        homePageLogin.openPage(urlPage);
        homePageLogin.fillFields(loginJohn);
        dashboardPage = homePageLogin.getDashboardPage();

        iCode = dashboardPage.transferBank("easy");

        transferPageEasy = dashboardPage.getTransferPage();
        transferPageEasy.fillTransfer(accountPaul, mainAmount, secAmount, reason,
                "" + iCode.charAt(0), "" + iCode.charAt(1), "" + iCode.charAt(2), "" + iCode.charAt(3));

        String resultMsgAlert = transferPageEasy.getErrorMsg();

        Assert.assertEquals(resultMsgAlert, expectMsgAlert);

    }

    @Test(description = "Transfer should return error message when null field(space)." +
            "Transfer doit renvoyer message d'erreur lorsque montant nulle(vide).")
    public void tce06() {
        String expectMsgAlert = "true";
        String iCode = "";
        String mainAmount = null;
        String secAmount = "00";
        String reason = "Negative test";

        homePageLogin.openPage(urlPage);
        homePageLogin.fillFields(loginPaul);
        dashboardPage = homePageLogin.getDashboardPage();

        iCode = dashboardPage.transferBank("easy");

        transferPageEasy = dashboardPage.getTransferPage();
        transferPageEasy.fillTransfer(accountRingo, mainAmount, secAmount, reason,
                "" + iCode.charAt(0), "" + iCode.charAt(1), "" + iCode.charAt(2), "" + iCode.charAt(3));

        String resultMsgAlert = transferPageEasy.getMainAmoundRequiredMsg();

        Assert.assertEquals(resultMsgAlert, expectMsgAlert);
    }

    @Test(description = "Transfer should return error message when charcters in amount field." +
            "Transfer doit renvoyer message d'erreur lorsque montant a des lettres.")
    public void tce07() {
        String expectMsgAlert = "Only Numbers (up to 7 digits)";
        String iCode = "";
        String mainAmount = "asdf";
        String secAmount = "00";
        String reason = "Negative test";

        homePageLogin.openPage(urlPage);
        homePageLogin.fillFields(loginPaul);
        dashboardPage = homePageLogin.getDashboardPage();


        iCode = dashboardPage.transferBank("easy");

        transferPageEasy = dashboardPage.getTransferPage();
        transferPageEasy.fillTransfer(accountRingo, mainAmount, secAmount, reason,
                "" + iCode.charAt(0), "" + iCode.charAt(1), "" + iCode.charAt(2), "" + iCode.charAt(3));

        String resultMsgAlert = transferPageEasy.getMainAmountMsg();


        Assert.assertEquals(resultMsgAlert, expectMsgAlert);
    }

    @Test(description = "Transfer should return error message when amount bigger balance." +
            "Transfer doit renvoyer message d'erreur lorsque montant plus grand que le solde.")
    public void tce08() {
        String expectMsgAlert = "You do not have enough balance to do this transfer.";
        String iCode = "";
        String mainAmount = "18000";
        String secAmount = "00";
        String reason = "Guitar payment";

        homePageLogin.openPage(urlPage);
        homePageLogin.fillFields(loginRingo);
        dashboardPage = homePageLogin.getDashboardPage();

        iCode = dashboardPage.transferBank("easy");

        transferPageEasy = dashboardPage.getTransferPage();
        transferPageEasy.fillTransfer(accountPaul, mainAmount, secAmount, reason,
                "" + iCode.charAt(0), "" + iCode.charAt(1), "" + iCode.charAt(2), "" + iCode.charAt(3));

        String resultMsgAlert = transferPageEasy.getErrorMsg();

        Assert.assertEquals(resultMsgAlert, expectMsgAlert);

    }

    @Test(description = "Transfer should return error message when invalid account number." +
            "Transfer doit renvoyer message d'erreur lorsque invalide nombre d'account ")
    public void tce09() {
        String expectMsgAlert = "Your elements is invalid Please try again.";
        String iCode = "";
        String mainAmount = "100";
        String secAmount = "00";
        String reason = "Negative test";

        homePageLogin.openPage(urlPage);
        homePageLogin.fillFields(loginPaul);
        dashboardPage = homePageLogin.getDashboardPage();

        iCode = dashboardPage.transferBank("easy");

        transferPageEasy = dashboardPage.getTransferPage();
        transferPageEasy.fillTransfer(accountRingoInvalid, mainAmount, secAmount, reason,
                "" + iCode.charAt(0), "" + iCode.charAt(1), "" + iCode.charAt(2), "" + iCode.charAt(3));

        String resultMsgAlert = transferPageEasy.getErrorMsg();


        Assert.assertTrue(expectMsgAlert.contains(resultMsgAlert));
    }


}
