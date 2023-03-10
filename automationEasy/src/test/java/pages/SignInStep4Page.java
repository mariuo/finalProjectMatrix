package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This Class is Model from Register Page...
 */
public class SignInStep4Page {
    protected WebDriver driver;
    private By fieldFileBrowse = By.xpath("//input[@id='imgInp']");
    private By fieldAgree = By.xpath("/html/body/div[1]/div/div/div[2]/form/div[2]/label/input");
    private By stepBtn = By.xpath("//button[@name='submit_end']");

    /**
     * Constructor
     *
     * @param driver
     */
    public SignInStep4Page(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * This method pass a file to field, click to Aggreement Box and click submit.
     *
     * @param fileBrowse
     */
    public void uploadFile(String fileBrowse) {
        driver.findElement(fieldFileBrowse).sendKeys(fileBrowse);
        driver.findElement(fieldAgree).click();
        driver.findElement(stepBtn).click();
    }


}
