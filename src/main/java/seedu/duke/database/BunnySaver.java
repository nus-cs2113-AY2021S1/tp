package seedu.duke.database;

import seedu.duke.bunny.Bunny;
import seedu.duke.ui.UI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static seedu.duke.constants.FilePaths.DEFAULT_BUNNY_FILE_PATH;
import static seedu.duke.constants.Logos.PLAIN_TEXT_DIVIDER;
import static seedu.duke.constants.Tags.NUM_BUNNY_TAG;

public class BunnySaver {
    public static final String NEWLINE = System.lineSeparator();

    public static void saveAllBunny(ArrayList<Bunny> bunniesList)
            throws IOException {
        File allTasksFile = new File(DEFAULT_BUNNY_FILE_PATH);

        if (allTasksFile.createNewFile()) {
            UI.createNewBunnyFile();
        }

        // clear the file
        new FileWriter(DEFAULT_BUNNY_FILE_PATH, false).close();

        // write file header
        FileFunctions.appendsStringToFile(NUM_BUNNY_TAG + " " + bunniesList.size(), DEFAULT_BUNNY_FILE_PATH);
        FileFunctions.appendsStringToFile(NEWLINE, DEFAULT_BUNNY_FILE_PATH);
        FileFunctions.appendsStringToFile(PLAIN_TEXT_DIVIDER, DEFAULT_BUNNY_FILE_PATH);
        FileFunctions.appendsStringToFile(NEWLINE, DEFAULT_BUNNY_FILE_PATH);

        for (int i = 0; i < bunniesList.size(); i++) {
            String printOut = "";
            printOut = (i + 1) + ".\n" + bunniesList.get(i).getDescription();
            FileFunctions.appendsStringToFile(printOut,DEFAULT_BUNNY_FILE_PATH);
            FileFunctions.appendsStringToFile(PLAIN_TEXT_DIVIDER, DEFAULT_BUNNY_FILE_PATH);
            FileFunctions.appendsStringToFile(NEWLINE, DEFAULT_BUNNY_FILE_PATH);
        }
    }
}
