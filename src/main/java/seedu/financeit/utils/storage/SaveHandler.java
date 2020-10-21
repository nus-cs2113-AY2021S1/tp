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

    public SaveHandler(){

    }

    public SaveHandler(String filepath, String directory) {
        fullPath = filepath;
        dirPath = directory;
    }

    public void save() throws IOException {

    }

    public void load() throws IOException {

    }

    public String getSaveString(Object object) {
        return object.getClass().getSimpleName() + ";" + object + System.lineSeparator();
    }

    public void setPath(String filepath, String directory) {
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

    protected void buildFile(String dirPath, String fullPath) throws IOException {
        if (!Files.exists(Paths.get(dirPath))) {
            Files.createDirectory(Paths.get(dirPath));
        }
        if (!Files.exists(Paths.get(fullPath))) {
            Files.createFile(Paths.get(fullPath));
        }
    }

    public void putString(String input) throws IOException {
        buildFile("./data","./data/saveString.txt");
        FileWriter fileWriter = new FileWriter("./data/saveString.txt");
        fileWriter.write(input);
        fileWriter.close();
    }

    public String takeString() throws IOException {
        buildFile("./data","./data/saveString.txt");
        File file = new File("./data/saveString.txt");
        Scanner scanner = new Scanner(file);
        return scanner.nextLine();
    }

    String charRemoveAt(String str, int p) {
        return str.substring(0, p) + str.substring(p + 1);
    }
}
