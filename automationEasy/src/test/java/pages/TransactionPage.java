package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * This Class is Model from Transcations Page...
 */
public class TransactionPage {
    protected WebDriver driver;
    private By fieldSelect = By.xpath("//select [@name='transactions']");
    private By fieldTable = By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[2]/table[1]/tbody/tr");

    /**
     * Constructor.
     *
     * @param driver
     */
    public TransactionPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Method to select what transcation you want.
     *
     * @param trans : Selection wish type of transcation.
     */
    public void selectTransaction(String trans) {
        Select se = new Select(driver.findElement(fieldSelect));
        se.selectByVisibleText(trans);
    }

    /**
     * Method to get one line of the transcation.
     *
     * @return
     */
    public String getOneTransaction() {
        String line = driver.findElement(fieldTable).getText();
        return line;
    }

}
