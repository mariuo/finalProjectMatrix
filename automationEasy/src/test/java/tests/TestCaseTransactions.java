package tests;


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
import pages.TransactionPage;

/**
 * testNG class to the function Transactions.
 */
public class TestCaseTransactions extends TestCaseConfig {

    private HomePageLogin homePageLogin;
    private DashboardPage dashboardPage;
    private TransactionPage transactionPage;
    private Account accountPaul;
    private Account accountRingo;
    private Login loginPaul;

    @BeforeTest
    public void beforeClass() {
        loginPaul = LoginFactory.createLoginPaul();
        accountPaul = AccountFactory.createAccountPaul();
        accountRingo = AccountFactory.createAccountPaul();
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


    @Test(description = "Deposits should return all transactions by All bank." +
            "Deposits doit renvoyer tous les transactions de toutes banque.")
    public void tct01() throws InterruptedException {
        boolean result;
        String expect = accountRingo.getLastName() + " " + accountRingo.getFirstName();

        homePageLogin.openPage(urlPage);
        homePageLogin.fillFields(loginPaul);

        dashboardPage = homePageLogin.getDashboardPage();
        dashboardPage.gotoTransactions("deposits");

        transactionPage = dashboardPage.getTransactionPage();
        transactionPage.selectTransaction(" All Banks ");

        Thread.sleep(200);
        result = transactionPage.getOneTransaction(expect);

        Assert.assertTrue(result);
    }


}
