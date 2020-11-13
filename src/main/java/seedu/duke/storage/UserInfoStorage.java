package seedu.duke.storage;

import seedu.duke.Trakcal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static seedu.duke.Trakcal.jarFilePath;

//@@author jlifah
/**
 * Updates the text file with user info.
 */
public class UserInfoStorage {

    public static final String FILE_PATH = jarFilePath + "/tpdata/tp.txt";

    /**
     * Create text file is present, if not create a new run.
     */
    public static ArrayList<String> update() {
        if (Files.exists(Path.of(jarFilePath))) {
            File file = new File(FILE_PATH);
            ArrayList<String> data = new ArrayList<>();
            try {
                file.createNewFile();
                Scanner s = new Scanner(file);
                while (s.hasNext()) {
                    String dataString = s.nextLine();
                    data.add(dataString);
                }
                s.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        } else {
            try {
                createDataFile();
            } catch (IOException e) {
                System.out.println("IO Exception found");
            }
            return new ArrayList<>();
        }
    }

    /**
     * Creates a new text file for user profile.
     */
    private static void createDataFile() throws IOException {
        File file = new File(jarFilePath);
        boolean isDirCreated = file.mkdir();

        if (isDirCreated) {
            file = new File(jarFilePath + "/tp.txt");
            file.createNewFile();
        }
    }

    /**
     * Writes user profile to text profile.
     */
    public void save(String test) throws IOException {
        String[] parts = test.split(",");
        Files.write(Path.of(FILE_PATH), Arrays.asList(parts));
    }
}
//@@author jlifah