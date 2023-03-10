package pages;

import factory.Client;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This Class is Model from Register Page...
 */
public class SignInStep1Page {
    protected WebDriver driver;
    private By fieldEmail = By.xpath("//input[@name=\"email\"]");
    private By fieldPass = By.xpath("//input[@name=\"password\"]");
    private By fieldAreacode = By.xpath("//input[@name=\"area_code\"]");
    private By fieldMobile = By.xpath("//input[@name=\"mobile\"]");
    private By stepBtn1 = By.xpath("/html/body/div[1]/div/div/div[2]/form/div[4]/span/button");

    /**
     * Constructor
     *
     * @param driver
     */
    public SignInStep1Page(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * This method fill Fields of Register Form.
     *
     * @param client
     */
    public void fillFields(Client client) {
        driver.findElement(fieldEmail).sendKeys(client.getEmail());
        driver.findElement(fieldPass).sendKeys(client.getPassword());
        driver.findElement(fieldAreacode).sendKeys(client.getAreaCode());
        driver.findElement(fieldMobile).sendKeys(client.getMobile());
        driver.findElement(stepBtn1).click();
    }

    /**
     * Method return the object from the SignInStep2Page.
     *
     * @return SignInStep2Page
     */
    public SignInStep2Page getSignInStep2Page() {
        return new SignInStep2Page(driver);
    }
}
