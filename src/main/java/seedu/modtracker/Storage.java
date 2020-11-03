package seedu.modtracker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Deals with loading tasks from and saving tasks to a file.
 */
public class Storage {
    private final String filePath;
    private File file;

    private final Logger logger = Logger.getLogger(Storage.class.getName());

    /**
     * Creates a new Storage object with a File created at the specified file path.
     *
     * @param filePath File path whereby data is loaded from and stored at.
     */
    public Storage(String filePath) {
        logger.setLevel(Level.INFO);

        if (filePath == null) {
            logger.log(Level.WARNING, "null file path");
            throw new NullPointerException();
        }

        this.filePath = filePath;
        try {
            file = new File(filePath);
            if (file.getParentFile().mkdirs()) {
                logger.log(Level.INFO, "new file directory created");
            }
            if (file.createNewFile()) {
                logger.log(Level.INFO, "new file created");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a scanner object to read the file.
     *
     * @return Scanner to read the file of the Storage object.
     */
    protected Scanner getReader() {
        assert file != null : "File should not be null";
        try {
            return new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Returns the username, if any.
     *
     * @return Name found in the file.
     */
    public String getName() {
        Scanner reader = getReader();
        assert reader != null : "Reader should not be null";
        if (!reader.hasNext()) {
            return null;
        }
        return reader.nextLine();
    }

    /**
     * Loads the rest of the data from the file.
     */
    public void loadData(ModTracker modTracker) {
        Parser parser = new Parser();
        Scanner reader = getReader();
        assert reader != null : "Reader should not be null";
        assert reader.hasNext() : "First line should be filled with the name";
        reader.nextLine();
        while (reader.hasNext()) {
            String input = reader.nextLine();
            parser.parse(input, modTracker.getModList(), "",
                    this, false, modTracker.getTaskList());
        }
    }

    /**
     * Appends the specified input to the storage file and creates a new line.
     *
     * @param input The input to be appended to the file.
     */
    public void appendToFile(String input) {
        assert filePath != null : "File path should not be null";
        assert file != null : "File should not be null";
        try {
            // creates a FileWriter in append mode
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(input + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearData() {
        String name = getName();
        try {
            FileWriter fw = new FileWriter(filePath, false);
            fw.write(name + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reset() {
        try {
            FileWriter fw = new FileWriter(filePath, false);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void unlockFile() {
        boolean unlock = file.setWritable(true);
        if (!unlock) {
            System.out.println("Error setting file to be writable");
        }
    }

    public void lockFile() {
        boolean lock = file.setReadOnly();
        if (!lock) {
            System.out.println("Error making data file read-only");
        }
    }
}
