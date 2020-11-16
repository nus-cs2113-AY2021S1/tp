package seedu.financeit.utils.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

//@@author Feudalord
public abstract class SaveHandler {

    protected String fullPath;
    protected String dirPath;

    protected SaveHandler() {

    }

    protected SaveHandler(String directory, String filepath) {
        fullPath = filepath;
        dirPath = directory;
    }

    protected void buildFile() throws IOException {
        if (!Files.exists(Paths.get(dirPath))) {
            Files.createDirectory(Paths.get(dirPath));
        }
        if (!Files.exists(Paths.get(fullPath))) {
            Files.createFile(Paths.get(fullPath));
        }
    }

    /**
     * Checks if given directory or file exist. Create them if they do not exist.
     * @param dirPath directory where the file to be created is located
     * @param fullPath full path of the file to be created
     * @throws IOException File creation may throw IO exception if given path is invalid
     */
    public static void buildFile(String dirPath, String fullPath) throws IOException {
        if (!Files.exists(Paths.get(dirPath))) {
            Files.createDirectory(Paths.get(dirPath));
        }
        if (!Files.exists(Paths.get(fullPath))) {
            Files.createFile(Paths.get(fullPath));
        }
    }

    /**
     * General purpose function used to store some String to a text file in ./data directory.
     * @param input The String to be stored
     * @param fileName The name of the file to be stored to
     * @throws IOException File creation may throw IO exception if given path is invalid
     */
    public static void putString(String input, String fileName) throws IOException {
        buildFile("./data", "./data/" + fileName + ".txt");
        FileWriter fileWriter = new FileWriter("./data/" + fileName + ".txt");
        fileWriter.write(input);
        fileWriter.close();
    }

    /**
     * General purpose function used to return some String from a text file in ./data directory.
     * @param fileName The name of the file to be read from
     * @return The output String from the specified text file.
     * @throws IOException File creation may throw IO exception if given path is invalid
     */
    public static String takeString(String fileName) throws IOException {
        buildFile("./data", "./data/" + fileName + ".txt");
        File file = new File("./data/" + fileName + ".txt");
        Scanner scanner = new Scanner(file);
        return scanner.nextLine();
    }

    /**
     * Appends a simple header to the Save String to differentiate the type of save.
     * @param object Ledger or entry to add a header to.
     * @return Save string generated.
     */
    public static String getSaveString(Object object) {
        return object.getClass().getSimpleName() + ";" + object + System.lineSeparator();
    }

    public static String charRemoveAt(String str, int p) {
        return str.substring(0, p) + str.substring(p + 1);
    }

    public void save(String... paths) throws IOException {

    }

    public void load(String... paths) throws IOException {

    }

}
