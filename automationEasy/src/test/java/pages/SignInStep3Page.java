package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This Class is Model from Register Page...
 */
public class SignInStep3Page {
    protected WebDriver driver;
    private By fieldFileBrowse = By.xpath("//input[@id='imgInp']");
    private By stepBtn3 = By.xpath("//button[@name='submit_step3']");

    /**
     * Constructor
     *
     * @param driver
     */
    public SignInStep3Page(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * This method pass a file to field.
     *
     * @param fileBrowse
     */
    public void uploadFile(String fileBrowse) {

        driver.findElement(fieldFileBrowse).sendKeys(fileBrowse);
        driver.findElement(stepBtn3).click();
    }

    /**
     * Method return the object from the SignInStep4Page.
     *
     * @return SignInStep4Page
     */
    public SignInStep4Page getSignInStep4Page() {
        return new SignInStep4Page(driver);
    }
}
