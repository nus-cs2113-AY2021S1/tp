//@@author TomLBZ

package io;

import java.io.IOException;
import java.io.File;

/**
 * The type Io.
 */
public class IO {

    /**
     * The Path.
     */
    protected String path;
    /**
     * The File name.
     */
    protected String fileName;

    /**
     * Instantiates a new Io.
     *
     * @param path     the path
     * @param fileName the file name
     */
    public IO(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
    }

    /**
     * Is file invalid boolean.
     *
     * @return the boolean
     * @throws IOException the io exception
     */
    protected boolean isFileInvalid()
            throws IOException {
        boolean isValidated = true;
        File path = new File(this.path);
        if (!path.exists() || !path.isDirectory()) {
            isValidated = path.mkdirs();
        }
        File file = new File(this.path + "/" + this.fileName);
        if (!file.exists() || !file.isFile()) {
            isValidated = isValidated && file.createNewFile();
        }
        return !isValidated;
    }

}
