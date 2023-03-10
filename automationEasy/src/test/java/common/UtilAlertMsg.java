package common;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UtilAlertMsg {

    /**
     * This method return a String from alert box.
     *
     * @param driver : The webdriver.
     * @return String with the text Alert mensage.
     */
    public static String getAlertText(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        String text = alert.getText();

        alert.accept();

        return text;
    }

    /**
     * This method return String with 4 digital from the other text passed.
     *
     * @param textAlert : String with the text.
     * @return String 4 digital.
     */
    public static String getPin(String textAlert) {
        String pin;
        pin = textAlert.substring(textAlert.length() - 4);
        return pin;
    }
}
