package pages;

import common.UtilAlertMsg;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class DashboardPage {
    protected WebDriver driver;

    private By listElements = By.xpath("//div [@class='stat-text']");
    private By leftMenu = By.xpath("//div[@id='main-menu']//a[@class='dropdown-toggle']");
    private By leftMenuEasy = By.xpath("//a[contains(text(),' Easy Bank ')]");
    private By leftMenuAny = By.xpath("//a[contains(text(),' Anyone Bank ')]");
    private By leftMenuDeposits = By.xpath("//a[contains(text(),' Deposits ')]");
    private By leftMenuWithdrawals = By.xpath("//a[contains(text(),' Withdrawals ')]");
    private By fieldBalance = By.xpath("/html/body/div[1]/div[1]/div[2]/b/b/div/div/ol/li[3]");
    private By fieldMsgSuccess = By.xpath("//span[@class='badge badge-pill badge-success']");
    private By btnLogout = By.xpath("/html/body/div[1]/header/div/div[2]/div/a/img");
    private By submitLogout = By.xpath("/html/body/div[1]/header/div/div[2]/div/div/div/div/div[2]/div/form/button[2]");
    private By cancelLogout = By.xpath("/html/body/div[1]/header/div/div[2]/div/div/div/div/div[2]/div/form/button[1]");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    /*
     * ###############################################################
     * ### VERIFY and GET ELEMENTS
     * ###############################################################
     */
    /**
     * This method verify the elements of the center page.
     * Compare two list of Strings are the same, without order.
     * 
     * @param expectList : The List of Strings.
     * @return True if the list is the same.
     * @return False if the list is not the same.
     */
    public boolean verifyElements(List<String> expectList) {
        List<WebElement> elements = driver.findElements(listElements);
        List<String> listEle = new ArrayList<>();
        for (WebElement ele : elements) {
            listEle.add(ele.getText());
        }

        boolean result = expectList.stream().allMatch(listEle::contains);

        return result;
    }

    /**
     * This method verify if success message contains the string passed.
     * 
     * @param text : String you want to compare.
     * @return boolean TRUE If the string is the same.
     * @return boolean FALSE If the string is not the same.
     */
    public boolean verifyMsgSuccess(String text) {
        String result = driver.findElement(fieldMsgSuccess).getText();

        return result.contains(text);
    }

    /**
     * This method use to navigate specific Bank that you want and return I_CODE.
     * By default have two links.
     * 
     * @param bank : The param is a String: easy => Easy Bank
     * @param bank : The param is a String any => Anyone Bank
     * @return iCode : String I_CODE from the alert message when click Easy Bank or
     *         Anyone Bank submenu.
     * @throws IllegalArgumentException if you dont write correct the name of the
     *                                  bank.
     */
    public String transferBank(String bank) {
        String transfer = "Transfer";
        List<WebElement> elements = driver.findElements(leftMenu);
        String iCode = "";

        for (WebElement ele : elements) {
            if (ele.getText().contains(transfer)) {
                ele.click();
                switch (bank) {
                    case "easy":
                        driver.findElement(leftMenuEasy).click();
                        break;
                    case "any":
                        driver.findElement(leftMenuAny).click();
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid bank: " + bank);
                }

                String textAlert = UtilAlertMsg.getAlertText(driver);
                iCode = textAlert.substring(textAlert.length() - 4);
                break;
            }
        }
        return iCode;
    }

    /*
        
     */
    /**
     * This method use to navigate specific left submenu.
     * By default have two links.
     * 
     * @param trans : The param is a String: deposits => Deposits
     * @param trans : The param is a String: withdrawls => Withdrawls
     * @throws IllegalArgumentException if you dont write correct the string.
     */
    public void gotoTransactions(String trans) {
        String transfer = "Transactions";
        List<WebElement> elements = driver.findElements(leftMenu);

        for (WebElement ele : elements) {
            if (ele.getText().contains(transfer)) {
                ele.click();
                switch (trans) {
                    case "deposits":
                        driver.findElement(leftMenuDeposits).click();
                        break;
                    case "withdrawls":
                        driver.findElement(leftMenuWithdrawals).click();
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid transactions: " + trans);
                }
                break;
            }
        }
    }

    /**
     * 
     * @return String with totalBalance of the Dashboard Page.
     */
    public String getTotalBalance() {
        String text = driver.findElement(fieldBalance).getText();
        return text.substring(14);
    }

    /**
     * Method to click the button Logout.
     */
    public void clickBtnLogout() {
        driver.findElement(btnLogout).click();
    }

    /**
     * Method to click the button Submit.
     */
    public void clickSubmitLogout() {
        driver.findElement(submitLogout).click();
    }

    /**
     * Method to click the button Cancel of Logout.
     */
    public void clickCancelLogout() {
        driver.findElement(cancelLogout).click();
    }

    /**
     * Method to get the Current URL.
     * 
     * @return
     */
    public String getUrl() {
        return driver.getCurrentUrl();
    }

    /*
     * ###############################################################
     * ### GET OTHERS PAGES
     * ###############################################################
     */
    public TransferPageEasy getTransferPage(){
        return new TransferPageEasy(driver);
    }

}