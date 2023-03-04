package pages;

import factory.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HomePageLogin {
    protected WebDriver driver;

    private By fieldEmail = By.xpath("//input[@name=\"email\"]");
    private By fieldPass = By.xpath("//input[@name=\"password\"]");
    private By fieldPin = By.xpath("//input[@name=\"pin\"]");
    private By submitBtn = By.xpath("/html/body/div[1]/div/div/div[2]/form/div[5]/span/button");
    private By errorMsg = By.xpath("//div[@class='alert alert-danger']");

    public HomePageLogin(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Method to open the page and MAXIMISE the window.
     * 
     * @param urlPage
     */
    public void openPage(String urlPage) {
        driver.get(urlPage);
        driver.manage().window().maximize();
    }

    /**
     * Method to get error message when try to login.
     * 
     * @return
     */
    public String getErrorMsg() {
        return driver.findElement(errorMsg).getText();
    }

    /**
     * This method verify list of String that occurs when multiple errors messages.
     * 
     * @param listExpect
     * @return TRUE if the list is correct.
     * @return FALSE if the list is not correct.
     */
    public boolean verifyErrorElements(List<String> listExpect) {
        List<WebElement> listEle = driver.findElements(errorMsg);
        List<String> listStrs = new ArrayList<>();

        for (WebElement ele : listEle) {
            String text = ele.getText();
            text = text.substring(2);
            listStrs.add(text);

        }
        return listExpect.stream().allMatch(listStrs::contains);
    }

    /**
     * This Method use to fill all fields from Login form.
     * 
     * @param login : Object type Login.
     */
    public void fillFields(Login login) {
        driver.findElement(fieldEmail).sendKeys(login.getEmail());
        driver.findElement(fieldPass).sendKeys(login.getPassword());
        driver.findElement(fieldPin).sendKeys(login.getPin());
        driver.findElement(submitBtn).click();
    }

    /**
     * Method to get object of type DashboardPage.
     * 
     * @return DashboardPage
     */

    public DashboardPage getDashboardPage() {
        return new DashboardPage(driver);
    }

}
