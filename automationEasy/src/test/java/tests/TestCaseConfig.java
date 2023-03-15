package tests;

import common.FileResourcesUtils;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Class Parent to all classes. All the others classes will enhancer this class.
 */
public abstract class TestCaseConfig {
    protected WebDriver driver;

    /**
     * Var of config LOCAL or PROD.
     */
    protected static String urlPage = "https://easyphpprojet.000webhostapp.com/";
//    protected static String urlPage = "http://127.0.0.1:8000";


    String fileName = "mickjagger.png";
    String fileBrowse;

    {
        try {
            fileBrowse = FileResourcesUtils.getFileFromResource(fileName).getCanonicalPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
