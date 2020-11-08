package seedu.smarthomebot.storage;

import seedu.smarthomebot.data.appliance.ApplianceList;
import seedu.smarthomebot.data.location.LocationList;
import seedu.smarthomebot.ui.TextUi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;

import static seedu.smarthomebot.commons.Messages.MESSAGE_CLEAR_FILE_ERROR;
import static seedu.smarthomebot.commons.Messages.MESSAGE_FILE_CREATION_ERROR;
import static seedu.smarthomebot.commons.Messages.MESSAGE_WRITE_FILE_ERROR;

//@@author TanLeeWei

/**
 * Represent the writing of data into the storage file.
 */
public class WriteStorageFile extends StorageFile {

    private static String FILE_PATH;
    private final TextUi ui = new TextUi();

    public WriteStorageFile(String filePath, ApplianceList applianceList, LocationList locationList) {
        super(applianceList, locationList);
        this.FILE_PATH = filePath;
    }

    /**
     * Execute the writing of data into storage file.
     */
    @Override
    public void execute() {
        try {
            assert FILE_PATH.equals("data/SmartHomeBot.txt") : "FILE_PATH should be data/SmartHome.txt";
            createFile();
            clearFile();
            FileWriter myWriter = new FileWriter(FILE_PATH);
            myWriter.write(locationList.getAllLocations().toString() + "\n");
            for (int i = 0; i < applianceList.getAllAppliance().size(); i++) {
                myWriter.write(applianceList.getAppliance(i).writeFileFormat() + System.lineSeparator());
            }
            storageLogger.log(Level.INFO, "Successfully written data into Storage File");
            myWriter.close();
        } catch (IOException e) {
            storageLogger.log(Level.WARNING, MESSAGE_WRITE_FILE_ERROR);
            ui.printToUser(MESSAGE_WRITE_FILE_ERROR);
        }
    }

    /**
     * Method to create the .txt storage file and the storage file directory.
     */
    private void createFile() {
        try {
            assert FILE_PATH.equals("data/SmartHomeBot.txt") : "FILE_PATH should be data/SmartHome.txt";
            File myObj = new File(FILE_PATH);
            if (!myObj.getParentFile().exists()) {
                myObj.getParentFile().mkdirs();
            }
            if (!myObj.exists()) {
                myObj.createNewFile();
            }
            storageLogger.log(Level.INFO, "Successfully created Storage File");
        } catch (IOException e) {
            storageLogger.log(Level.WARNING, MESSAGE_FILE_CREATION_ERROR);
            ui.printToUser(MESSAGE_FILE_CREATION_ERROR);
        }
    }

    /**
     * Method to clear the contents of the .txt storage file.
     */
    private void clearFile() {
        try {
            assert FILE_PATH.equals("data/SmartHomeBot.txt") : "FILE_PATH should be data/SmartHome.txt";
            PrintWriter writer = new PrintWriter(FILE_PATH);
            writer.print("");
            writer.close();
            storageLogger.log(Level.INFO, "Successfully cleared Storage File");
        } catch (FileNotFoundException e) {
            storageLogger.log(Level.WARNING, MESSAGE_CLEAR_FILE_ERROR);
            ui.printToUser(MESSAGE_CLEAR_FILE_ERROR);
        }
    }
}
