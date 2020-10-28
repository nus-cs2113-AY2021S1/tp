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

    public static void buildFile(String dirPath, String fullPath) throws IOException {
        if (!Files.exists(Paths.get(dirPath))) {
            Files.createDirectory(Paths.get(dirPath));
        }
        if (!Files.exists(Paths.get(fullPath))) {
            Files.createFile(Paths.get(fullPath));
        }
    }

    public static void putString(String input, String fileName) throws IOException {
        buildFile("./data", "./data/" + fileName + ".txt");
        FileWriter fileWriter = new FileWriter("./data/" + fileName + ".txt");
        fileWriter.write(input);
        fileWriter.close();
    }

    public static String takeString(String fileName) throws IOException {
        buildFile("./data", "./data/" + fileName + ".txt");
        File file = new File("./data/" + fileName + ".txt");
        Scanner scanner = new Scanner(file);
        return scanner.nextLine();
    }

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
