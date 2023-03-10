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
import pages.TransferPageAny;

/**
 * testNG class to the function Transfer "ANY BANK".
 */
public class TestCaseTranferAny extends TestCaseConfig {

    private HomePageLogin homePageLogin;
    private DashboardPage dashboardPage;
    private TransferPageAny transferPageAny;
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
            "Transfer doit renvoyer message d'erreur lorsque montant n??gative.")
    public void tca01() {
        String expectMsgAlert = "Only Numbers (up to 7 digits)";
        String iCode = "";
        String mainAmount = "-100";
        String secAmount = "00";
        String reason = "Negative test";

        homePageLogin.openPage(urlPage);
        homePageLogin.fillFields(loginPaul);
        dashboardPage = homePageLogin.getDashboardPage();


        iCode = dashboardPage.transferBank("any");

        transferPageAny = dashboardPage.getTransferPageAny();
        transferPageAny.fillTransfer(accountRingo, mainAmount, secAmount, reason,
                "" + iCode.charAt(0), "" + iCode.charAt(1), "" + iCode.charAt(2), "" + iCode.charAt(3));

        String resultMsgAlert = transferPageAny.getMainAmountMsg();


        Assert.assertEquals(expectMsgAlert, resultMsgAlert);
    }

    @Test(description = "Transfer should return error message when null amount(zero)." +
            "Transfer doit renvoyer message d'erreur lorsque montant nulle(zero).")
    public void tca02() {
        String expectMsgAlert = "Your element is invalid Please try again.";
        String iCode = "";
        String mainAmount = "00";
        String secAmount = "00";
        String reason = "Negative test";

        homePageLogin.openPage(urlPage);
        homePageLogin.fillFields(loginPaul);
        dashboardPage = homePageLogin.getDashboardPage();

        iCode = dashboardPage.transferBank("any");

        transferPageAny = dashboardPage.getTransferPageAny();
        transferPageAny.fillTransfer(accountRingo, mainAmount, secAmount, reason,
                "" + iCode.charAt(0), "" + iCode.charAt(1), "" + iCode.charAt(2), "" + iCode.charAt(3));


        String resultMsgAlert = transferPageAny.getErrorMsg();


        Assert.assertEquals(resultMsgAlert, expectMsgAlert);
    }

    @Test(description = "Transfer should return success message when VALID data." +
            "Transfer doit renvoyer Main Application Page lorsque VALIDE donnee apres inscription.")
    public void tca03() {
        String expectMsgAlert = "This transfer was held successfully.";
        String iCode = "";
        double tax = 3;
        double amountSubtract = 100;
        String mainAmount = "100";
        String secAmount = "00";
        String reason = "Any reason";

        homePageLogin.openPage(urlPage);
        homePageLogin.fillFields(loginPaul);
        dashboardPage = homePageLogin.getDashboardPage();

        // #### Get the balance from page, Casting to Double and subtract 100.
        String beforeBalance = dashboardPage.getTotalBalance();
        double expectBalance = Double.parseDouble(beforeBalance) - amountSubtract - tax;

        iCode = dashboardPage.transferBank("any");

        transferPageAny = dashboardPage.getTransferPageAny();
        transferPageAny.fillTransfer(accountRingo, mainAmount, secAmount, reason,
                "" + iCode.charAt(0), "" + iCode.charAt(1), "" + iCode.charAt(2), "" + iCode.charAt(3));

        String resultMsgAlert = UtilAlertMsg.getAlertText(driver);

        // #### Get the balance from page after transfer, and casting to Double.
        String afterBalance = transferPageAny.getTotalBalance();
        double resultBalance = Double.parseDouble(afterBalance);


        Assert.assertEquals(resultMsgAlert, expectMsgAlert);
        Assert.assertEquals(resultBalance, expectBalance);
    }


    @Test(description = "Transfer should return success message when valid day limit transfer." +
            "Transfer doit renvoyer message de succes lorsque transfer limite quotidien valide.")
    public void tca04() {
        String expectMsgAlert = "This transfer was held successfully.";
        String iCode = "";
        double tax = 3;
        double amountSubtract = 2000;
        String mainAmount = "2000";
        String secAmount = "00";
        String reason = "Guitar payment";

        homePageLogin.openPage(urlPage);
        homePageLogin.fillFields(loginRingo);
        dashboardPage = homePageLogin.getDashboardPage();

        // #### Get the balance from page, Casting to double and subtract 2000.
        String beforeBalance = dashboardPage.getTotalBalance();
        double expectBalance = Double.parseDouble(beforeBalance) - amountSubtract - tax;

        iCode = dashboardPage.transferBank("any");

        transferPageAny = dashboardPage.getTransferPageAny();
        transferPageAny.fillTransfer(accountJohn, mainAmount, secAmount, reason,
                "" + iCode.charAt(0), "" + iCode.charAt(1), "" + iCode.charAt(2), "" + iCode.charAt(3));

        String resultMsgAlert = UtilAlertMsg.getAlertText(driver);

        // #### Get the balance from page after transfer, and casting to double.
        String afterBalance = transferPageAny.getTotalBalance();
        double resultBalance = Double.parseDouble(afterBalance);


        Assert.assertEquals(resultMsgAlert, expectMsgAlert);
        Assert.assertEquals(resultBalance, expectBalance);
    }

    @Test(description = "Transfer should return error message when amount bigger then day limit." +
            "Transfer doit renvoyer message d'erreur lorsque montant plus grand que la limite quotidien.")
    public void tca05() {
        String expectMsgAlert = "You have exceeded the transfer limit Apply for overtransfer.";
        String iCode = "";
        String mainAmount = "21000";
        String secAmount = "00";
        String reason = "Guitar payment";

        homePageLogin.openPage(urlPage);
        homePageLogin.fillFields(loginJohn);
        dashboardPage = homePageLogin.getDashboardPage();

        iCode = dashboardPage.transferBank("any");

        transferPageAny = dashboardPage.getTransferPageAny();
        transferPageAny.fillTransfer(accountPaul, mainAmount, secAmount, reason,
                "" + iCode.charAt(0), "" + iCode.charAt(1), "" + iCode.charAt(2), "" + iCode.charAt(3));

        String resultMsgAlert = transferPageAny.getErrorMsg();

        Assert.assertEquals(resultMsgAlert, expectMsgAlert);

    }

    @Test(description = "Transfer should return error message when null field(space)." +
            "Transfer doit renvoyer message d'erreur lorsque montant nulle(vide).")
    public void tca06() {
        String expectMsgAlert = "true";
        String iCode = "";
        String mainAmount = null;
        String secAmount = "00";
        String reason = "Negative test";

        homePageLogin.openPage(urlPage);
        homePageLogin.fillFields(loginPaul);
        dashboardPage = homePageLogin.getDashboardPage();

        iCode = dashboardPage.transferBank("any");

        transferPageAny = dashboardPage.getTransferPageAny();
        transferPageAny.fillTransfer(accountRingo, mainAmount, secAmount, reason,
                "" + iCode.charAt(0), "" + iCode.charAt(1), "" + iCode.charAt(2), "" + iCode.charAt(3));

        String resultMsgAlert = transferPageAny.getMainAmoundRequiredMsg();


        Assert.assertEquals(resultMsgAlert, expectMsgAlert);
    }

    @Test(description = "Transfer should return error message when charcters in amount field." +
            "Transfer doit renvoyer message d'erreur lorsque montant a des lettres.")
    public void tca07() {
        String expectMsgAlert = "Only Numbers (up to 7 digits)";
        String iCode = "";
        String mainAmount = "asdf";
        String secAmount = "00";
        String reason = "Negative test";

        homePageLogin.openPage(urlPage);
        homePageLogin.fillFields(loginPaul);
        dashboardPage = homePageLogin.getDashboardPage();


        iCode = dashboardPage.transferBank("any");

        transferPageAny = dashboardPage.getTransferPageAny();
        transferPageAny.fillTransfer(accountRingo, mainAmount, secAmount, reason,
                "" + iCode.charAt(0), "" + iCode.charAt(1), "" + iCode.charAt(2), "" + iCode.charAt(3));

        String resultMsgAlert = transferPageAny.getMainAmountMsg();


        Assert.assertEquals(resultMsgAlert, expectMsgAlert);
    }

    @Test(description = "Transfer should return error message when amount bigger balance." +
            "Transfer doit renvoyer message d'erreur lorsque montant plus grand que le solde.")
    public void tca08() {
        String expectMsgAlert = "You do not have enough balance to do this transfer.";
        String iCode = "";
        String mainAmount = "16000";
        String secAmount = "00";
        String reason = "Guitar payment";

        homePageLogin.openPage(urlPage);
        homePageLogin.fillFields(loginRingo);
        dashboardPage = homePageLogin.getDashboardPage();

        iCode = dashboardPage.transferBank("any");

        transferPageAny = dashboardPage.getTransferPageAny();
        transferPageAny.fillTransfer(accountPaul, mainAmount, secAmount, reason,
                "" + iCode.charAt(0), "" + iCode.charAt(1), "" + iCode.charAt(2), "" + iCode.charAt(3));

        String resultMsgAlert = transferPageAny.getErrorMsg();

        Assert.assertEquals(resultMsgAlert, expectMsgAlert);

    }

    @Test(description = "Transfer should return error message when invalid account number." +
            "Transfer doit renvoyer message d'erreur lorsque invalide nombre d'account ")
    public void tca09() {
        String expectMsgAlert = "Your elements is invalid Please try again.";
        String iCode = "";
        String mainAmount = "100";
        String secAmount = "00";
        String reason = "Negative test";

        homePageLogin.openPage(urlPage);
        homePageLogin.fillFields(loginPaul);
        dashboardPage = homePageLogin.getDashboardPage();

        iCode = dashboardPage.transferBank("any");

        transferPageAny = dashboardPage.getTransferPageAny();
        transferPageAny.fillTransfer(accountRingoInvalid, mainAmount, secAmount, reason,
                "" + iCode.charAt(0), "" + iCode.charAt(1), "" + iCode.charAt(2), "" + iCode.charAt(3));

        String resultMsgAlert = transferPageAny.getErrorMsg();


        Assert.assertTrue(expectMsgAlert.contains(resultMsgAlert));
    }


}
