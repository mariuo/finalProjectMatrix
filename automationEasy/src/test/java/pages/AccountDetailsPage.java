package pages;

import factory.Client;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This Class is Model from Account Details.
 */
public class AccountDetailsPage {
    protected WebDriver driver;
    private By fieldPersonal = By.tagName("iframe");
    private By fieldFullname = By.xpath("/html/body/div/div[2]/div[1]/div[2]/div/div[2]/div/div[2]/div[2]/div/h2");
    private By fieldElements = By.xpath("//ul[@class='container details']//li");
    private By fieldDetails = By.xpath("//span[contains(text(), ' Personal Details')]");

    /**
     * Constructor.
     *
     * @param driver
     */
    public AccountDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    /*
     * ###############################################################
     * ### VERIFY and GET ELEMENTS
     * ###############################################################
     */

    /**
     * This method verify the elements of the center page.
     * Compare two list of elements and strings.
     *
     * @param client : The List of Strings.
     * @return True if the list is the same.
     * @return False if the list is not the same.
     */
    public boolean verifyElements(Client client) {
        List<WebElement> elements = driver.findElements(fieldElements);
        List<String> listEle = new ArrayList<>();
        List<String> expectList = Arrays.asList(
                "ID_DOCUMENT_NUMBER:   " + client.getNatIdNumber(),
                "TAX ID NUMBER:   " + client.getTaxIdNumber(),
                "DATE BIRTH:   " + client.getDay() + "-" + client.getMonth() + "-" + client.getYear(),
                "MOBILE NUMBER:   +" + client.getAreaCode() + " " + client.getMobile(),
                "NATIONALITY:   " + client.getNationality().toUpperCase(),
                "COUNTRY   " + client.getCountryAddress() + "     CITY:   " + client.getCity().toUpperCase(),
                "STREET:   " + client.getStreet().toUpperCase() + "     NUMBER:   " + client.getStreetNumber());

        boolean result = false;

        for (WebElement ele : elements) {
            listEle.add(ele.getText());

        }

        result = expectList.stream().allMatch(listEle::contains);
        return result;
    }


    /**
     * Method to navigate, click in the button Details from Iframe.
     */
    public void gotoPersonalDetail() {
        WebElement iframeEle = driver.findElement(fieldPersonal);
        this.driver.switchTo().frame(iframeEle);
        this.driver.findElement(fieldDetails).click();
    }

    /**
     * @return String with fullName from Details.
     */
    public String getFullname() {
        return driver.findElement(fieldFullname).getText();

    }


}
