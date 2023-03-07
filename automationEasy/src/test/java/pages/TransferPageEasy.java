package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TransferPageEasy {

    protected WebDriver driver;

    private By fieldFirstName = By.xpath("//input[@name=\"firstname\"]");
    private By fieldLastName = By.xpath("//input[@name=\"lastname\"]");
    private By fieldAccNum = By.xpath("//input[@name=\"account_no\"]");
    private By fieldMainAmount = By.xpath("//input[@name=\"main_amount\"]");
    private By fieldSecAmount = By.xpath("//input[@name=\"secondary_amount\"]");
    private By fieldReason = By.xpath("//textarea[@name='reason']");
    private By fieldCode1 = By.xpath("//input[@name=\"i_code1\"]");
    private By fieldCode2 = By.xpath("//input[@name=\"i_code2\"]");
    private By fieldCode3 = By.xpath("//input[@name=\"i_code3\"]");
    private By fieldCode4 = By.xpath("//input[@name=\"i_code4\"]");
    private By transBtn = By.xpath("//button[@name='transfer_easy_bank']");
    private By fieldBalance = By.xpath("/html/body/div[1]/div[1]/div[2]/div/div/ol/li");
    private By errorMsg = By.xpath("//div[@class='alert alert-danger']");


    /**
     * Constructor
     * @param driver
     */
    public TransferPageEasy(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * This Method use to fill all fields from Transfer Easy form.
     * @param firstName
     * @param lastName
     * @param accNum
     * @param mainAmount
     * @param secAmount
     * @param reason
     * @param iCode1
     * @param iCode2
     * @param iCode3
     * @param iCode4
     */
    public void fillTransfer(String firstName, String lastName, String accNum,
                             String mainAmount, String secAmount, String reason,
                             String iCode1, String iCode2, String iCode3, String iCode4){
        driver.findElement(fieldFirstName).sendKeys(firstName);
        driver.findElement(fieldLastName).sendKeys(lastName);
        driver.findElement(fieldAccNum).sendKeys(accNum);
        if(mainAmount == null){
            driver.findElement(fieldMainAmount).clear();
        }else{
            driver.findElement(fieldMainAmount).sendKeys(mainAmount);
        }
        driver.findElement(fieldSecAmount).sendKeys(secAmount);
        driver.findElement(fieldReason).sendKeys(reason);
        driver.findElement(fieldCode1).sendKeys(iCode1);
        driver.findElement(fieldCode2).sendKeys(iCode2);
        driver.findElement(fieldCode3).sendKeys(iCode3);
        driver.findElement(fieldCode4).sendKeys(iCode4);
        driver.findElement(transBtn).click();
    }

    /**
     *
     * @return String with totalBalance of the Transfer Easy Page.
     */
    public String getTotalBalance (){
        String text = driver.findElement(fieldBalance).getText();
        return text.substring(14);
    }

    /**
     * Method to use to verify what message is into field with attribute title.
     * @return String with the message in the field the name = "title".
     */
    public String getMainAmountMsg(){
        return driver.findElement(fieldMainAmount).getAttribute("title");
    }

    /**
     * Method to use to verify what message will recieve when attribute "required" is present.
     * @return String with the message in the field the name = "required".
     */
    public String getMainAmoundRequiredMsg(){
        return driver.findElement(fieldMainAmount).getAttribute("required");
    }
    /**
     * Method to get error message when try to transfer.
     *
     * @return String
     */
    public String getErrorMsg(){
        return driver.findElement(errorMsg).getText();
    }
}
