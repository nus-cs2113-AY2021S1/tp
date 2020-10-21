package seedu.financeit.financetools;

import seedu.financeit.common.exceptions.FolderNotFoundException;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class FileStorage {
    /**
     * Reads file content whenever FinanceTools starts up.
     *
     * @param filePath file path that contains all the tasks in text format.
     * @param infoText array that stores all tasks in text format.
     * @throws FileNotFoundException if file that contains data of tasks is not found.
     * @throws FolderNotFoundException if folder not found.
     */
    protected static void readFileContents(String filePath, ArrayList<String> infoText)
            throws FileNotFoundException, FolderNotFoundException {
        try {
            createFile(filePath);
        } catch (IOException e) {
            System.out.println("IO Error");
        }
        File f = new File(filePath); // create a File for the given file path
        File folder = new File("./data/"); // folder directory for duke.txt
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        // Check if folder exist
        if (folder.isDirectory() == false) {
            throw new FolderNotFoundException();
        }
        // Read tasks into FinanceTools
        while (s.hasNext()) {
            String line = s.nextLine();
            int countLines = 0;
            String textToAdd = line + "\n";
            while (countLines < 5 && s.hasNext()) {
                line = s.nextLine();
                textToAdd += line;
                if (countLines != 4) {
                    textToAdd += "\n";
                }
                countLines++;
            }
            infoText.add(textToAdd);
        }
    }

    /**
     * Writes task data to text file.
     *
     * @param filePath file path that contains all the tasks in text format.
     * @param textToAdd string that would be added to the text file.
     * @throws IOException if there are issues with input or output operations.
     */
    protected static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.write(System.getProperty("line.separator"));
        fw.close();
    }

    /**
     * Updates text file whenever an operation is done on the task,
     * for e.g. todo, event, deadline and delete.
     *
     * @param infoText array that stores all tasks in text format.
     * @param filePath file path that contains all the tasks in text format.
     * @throws FileNotFoundException if file that contains data of tasks is not found.
     */
    protected static void updateFile(ArrayList<String> infoText, String filePath) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(filePath); // reset .txt file
        int count = 0;
        for (int i = 0; i < infoText.size(); i++) {
            try {
                writeToFile(filePath, infoText.get(i));
            } catch (IOException e) {
                System.out.println("IO Error");
            }
        }
    }

    /**
     * Creates file if existing file is not available.
     *
     * @param filePath file path that contains all the tasks in text format.
     * @throws IOException if there are issues with input or output operations.
     */
    protected static void createFile(String filePath) throws IOException {
        File storageFile = new File(filePath);
        if (storageFile.exists()) {
            return;
        }
        if (!storageFile.getParentFile().exists()) {
            storageFile.getParentFile().mkdirs();
        }
        storageFile.createNewFile();
        System.out.println(storageFile.getAbsolutePath());
    }
}