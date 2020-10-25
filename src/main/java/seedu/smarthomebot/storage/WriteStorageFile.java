package seedu.smarthomebot.storage;

import seedu.smarthomebot.data.appliance.ApplianceList;
import seedu.smarthomebot.data.location.LocationList;
import seedu.smarthomebot.ui.TextUi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class WriteStorageFile extends StorageFile {

    private static final String filePath = "data/SmartHomeBot.txt";
    private final TextUi ui = new TextUi();

    public WriteStorageFile(ApplianceList applianceList, LocationList locationList) {
        super(applianceList, locationList);
    }

    @Override
    public void execute() {
        try {
            assert filePath.equals("data/SmartHomeBot.txt") : "filePath should be data/SmartHome.txt";
            createFile();
            clearFile();
            FileWriter myWriter = new FileWriter(filePath, StandardCharsets.UTF_8);
            myWriter.write(locationList.getLocations().toString() + "\n");
            for (int i = 0; i < applianceList.getAllAppliance().size(); i++) {
                myWriter.write(applianceList.getAppliance(i).writeFileFormat() + System.lineSeparator());
            }
            myWriter.close();
        } catch (IOException e) {
            ui.printToUser("An error occur");
        }
    }

    public void createFile() {
        try {
            assert filePath.equals("data/SmartHomeBot.txt") : "filePath should be data/SmartHome.txt";
            File myObj = new File(filePath);
            if (!myObj.getParentFile().exists()) {
                myObj.getParentFile().mkdirs();
            }
            if (!myObj.exists()) {
                myObj.createNewFile();
            }

        } catch (IOException e) {
            ui.printToUser("Unable to create file.");
        }
    }

    public void clearFile() {
        try {
            assert filePath.equals("data/SmartHomeBot.txt") : "filePath should be data/SmartHome.txt";
            PrintWriter writer = new PrintWriter(filePath);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            ui.printToUser("File is empty.");
        }
    }
}
