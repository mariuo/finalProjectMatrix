package common;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class FileResourcesUtils {

    /**
     * This Method has the object to get File from resources folder.
     *
     * @param fileName
     * @return
     * @throws URISyntaxException
     */
    static public File getFileFromResource(String fileName) throws URISyntaxException {

        ClassLoader classLoader = FileResourcesUtils.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }

    }

}
