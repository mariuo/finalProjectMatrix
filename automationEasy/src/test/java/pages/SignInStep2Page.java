package pages;

import factory.Client;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This Class is Model from Register Page...
 */
public class SignInStep2Page {
    protected WebDriver driver;
    private By fieldFirstName = By.xpath("//input[@name=\"first_name\"]");
    private By fieldLastName = By.xpath("//input[@name=\"last_name\"]");
    private By fieldDay = By.xpath("//input[@name=\"day\"]");
    private By fieldMonth = By.xpath("//input[@name=\"month\"]");
    private By fieldYear = By.xpath("//input[@name=\"year\"]");
    private By fieldNationality = By.xpath("//input[@name=\"nationality\"]");
    private By fieldIdnumber = By.xpath("//input[@name=\"identity_number\"]");
    private By fieldCountryCode = By.xpath("//input[@name=\"country_code\"]");
    private By fieldCity = By.xpath("//input[@name=\"city\"]");
    private By fieldStreet = By.xpath("//input[@name=\"street\"]");
    private By fieldNumber = By.xpath("//input[@name=\"number\"]");
    private By fieldPostCode = By.xpath("//input[@name=\"post_code\"]");
    private By fieldTaxResidence = By.xpath("//input[@name=\"tax_residence\"]");
    private By fieldTaxIdNumber = By.xpath("//input[@name=\"tax_id_number\"]");
    private By stepBtn2 = By.xpath("//button[@name='submit_step2']");


    /**
     * Constructor
     *
     * @param driver
     */
    public SignInStep2Page(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * This method fill Fields of Register Form.
     *
     * @param client
     */
    public void fillFields(Client client) {
        driver.findElement(fieldFirstName).sendKeys(client.getFirstName());
        driver.findElement(fieldLastName).sendKeys(client.getLastName());
        driver.findElement(fieldDay).sendKeys(client.getDay());
        driver.findElement(fieldMonth).sendKeys(client.getMonth());
        driver.findElement(fieldYear).sendKeys(client.getYear());
        driver.findElement(fieldNationality).sendKeys(client.getNationality());
        driver.findElement(fieldIdnumber).sendKeys(client.getNatIdNumber());
        driver.findElement(fieldCountryCode).sendKeys(client.getCountryAddress());
        driver.findElement(fieldCity).sendKeys(client.getCity());
        driver.findElement(fieldStreet).sendKeys(client.getStreet());
        driver.findElement(fieldNumber).sendKeys(client.getStreetNumber());
        driver.findElement(fieldPostCode).sendKeys(client.getPostCode());
        driver.findElement(fieldTaxResidence).sendKeys(client.getTaxResidence());
        driver.findElement(fieldTaxIdNumber).sendKeys(client.getTaxIdNumber());

        driver.findElement(stepBtn2).click();
    }

    /**
     * Method return the object from the SignInStep3Page.
     *
     * @return SignInStep3Page
     */
    public SignInStep3Page getSignInStep3Page() {
        return new SignInStep3Page(driver);
    }
}
