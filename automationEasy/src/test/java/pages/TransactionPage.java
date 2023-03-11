package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class is Model from Transcations Page...
 */
public class TransactionPage {
    protected WebDriver driver;
    private By fieldSelect = By.xpath("//select [@name='transactions']");
    private By fieldFullname = By.xpath("/html/body/div[1]/div[1]/div[1]/div/div/h1");
    private By listTables = By.xpath("//tr[@id='detail']//td");

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
     * Method to get one line of the transcation and validate has a String passed.
     *
     * @return
     */
    public boolean getOneTransaction(String expect) {
        List<WebElement> elements = driver.findElements(listTables);
        List<String> listEle = new ArrayList<>();

        for (WebElement tr : elements) {
            listEle.add(tr.getText());
        }
        boolean result = listEle.contains(expect);

        return result;
    }
}
