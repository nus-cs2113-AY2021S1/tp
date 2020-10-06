package seedu.duke.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static final String DIRECTORY_PATH = new File("data").getAbsolutePath();

    public ArrayList<String> load(String dataPath) {
        ArrayList<String> data = new ArrayList<>();
        final String FILE_PATH = DIRECTORY_PATH + dataPath;
        File file = new File(FILE_PATH);

        if(Files.exists(Path.of(DIRECTORY_PATH))) {
            data = readFile(file);
        } else {
            if(createDirectory()) {
                data = readFile(file);
            }
        }

        return data;
    }

    private ArrayList<String> readFile(File file) {
        ArrayList<String> data = new ArrayList<>();

        try {
            boolean fileCreated = file.createNewFile();

            if(!fileCreated) {
                Scanner sc = new Scanner(file);
                while(sc.hasNext()) {
                    String dataString = sc.nextLine();
                    data.add(dataString);
                }
            }
        } catch (IOException e) {
            System.out.println("There was an error reading from the file.");
        }

        return data;
    }

    private boolean createDirectory() {
        File file = new File(Storage.DIRECTORY_PATH);
        return file.mkdir();
    }
}
