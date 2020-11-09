package fitr.common;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Handles the loading of resources.
 */
public class ResourceManager {
    /**
     * Loads the resource with a given file name.
     *
     * @param fileName Name of the file in the resources folder
     * @return an InputStreamReader of the loaded file
     */
    public static InputStreamReader loadResource(String fileName) {
        InputStream inputStream = ResourceManager.class.getClassLoader().getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException();
        } else {
            return new InputStreamReader(inputStream);
        }
    }
}
