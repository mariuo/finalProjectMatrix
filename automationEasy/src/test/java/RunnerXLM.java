import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to run testng.xml from VSCode.
 */
public class RunnerXLM {
    public static void main(String[] args) {
        List<String> xmlFiles = new ArrayList<String>();

        xmlFiles.add("./automationEasy/testng.xml");

        TestNG testNG = new TestNG();
        testNG.setTestSuites(xmlFiles);
        testNG.run();

    }

}
