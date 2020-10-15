package seedu.smarthomebot.storage;

import seedu.smarthomebot.common.Messages;
import seedu.smarthomebot.data.LocationList;
import seedu.smarthomebot.exceptions.EmptyParameterException;
import seedu.smarthomebot.commands.AddCommand;
import seedu.smarthomebot.commands.CreateCommand;
import seedu.smarthomebot.data.ApplianceList;
import seedu.smarthomebot.exceptions.FileCorrupted;
import seedu.smarthomebot.ui.TextUi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class StorageFile {

    private static final String filePath = "data/SmartHomeBot.txt";
    private static ApplianceList applianceList;
    private static LocationList locationList;
    private final TextUi ui = new TextUi();

    public StorageFile(ApplianceList applianceList, LocationList locationList) {
        StorageFile.locationList = locationList;
        StorageFile.applianceList = applianceList;
    }

    public void writeToFile() {
        try {
            assert filePath.equals("data/SmartHomeBot.txt") : "filePath should be data/SmartHome.txt";
            createFile();
            clearFile();
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(locationList.getLocations().toString() + "\n");
            for (int i = 0; i < applianceList.getAllAppliance().size(); i++) {
                myWriter.write(applianceList.getAppliance(i).writeFileFormat() + System.lineSeparator());
            }
            myWriter.close();
        } catch (IOException e) {
            ui.showToUser("An error occur");
        }
    }

    public void readFile() {
        try {
            assert filePath.equals("data/SmartHomeBot.txt") : "filePath should be data/SmartHome.txt";
            int i = 0;
            File myFile = new File(filePath);
            Scanner myReader = new Scanner(myFile);
            String locationList = myReader.nextLine();
            try {
                convertTextToLocationList(locationList);
                convertTextToApplianceList(i, myReader);
                ui.showToUser(Messages.MESSAGE_IMPORT);
            } catch (FileCorrupted e) {
                ui.showToUser(Messages.MESSAGE_FILE_CORRUPTED);
            }

            myReader.close();
        } catch (FileNotFoundException | EmptyParameterException e) {
            ui.showToUser("Load File Does not Exist. No contents will be loaded.");
        }
    }

    private void convertTextToApplianceList(int i, Scanner myReader) throws FileCorrupted {
        while (myReader.hasNextLine()) {
            try {
                String appliance = myReader.nextLine();
                String[] splitString = appliance.split("\\|", 7);
                if (splitString[1].isEmpty() || splitString[0].isEmpty()
                        || splitString[2].isEmpty() || splitString[3].isEmpty()) {
                    throw new FileCorrupted();
                }
                AddCommand add = new AddCommand(splitString[1], splitString[0], splitString[2], splitString[3], false);
                add.setData(applianceList, locationList);
                add.execute();
                applianceList.getAppliance(i).loadConsumptionFromFile(splitString[5]);
                if (splitString[4].toLowerCase().equals("on")) {
                    applianceList.getAppliance(i).switchOn();
                }
                i++;
            } catch (IndexOutOfBoundsException e) {
                throw new FileCorrupted();
            }

        }
    }

    private void convertTextToLocationList(String location) throws EmptyParameterException, FileCorrupted {
        try {
            int start = location.indexOf("[") + 1;
            int end = location.indexOf("]");
            String when = location.substring(start, end);
            String[] stringSplit = when.split(",");
            for (String s : stringSplit) {
                CreateCommand newLocation = new CreateCommand(s.trim(), false);
                newLocation.setData(applianceList, locationList);
                newLocation.execute();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new FileCorrupted();
        }

    }

    public void createFile() {
        try {
            assert filePath.equals("data/SmartHomeBot.txt") : "filePath should be data/SmartHome.txt";
            File myObj = new File(filePath);
            if (!myObj.getParentFile().exists()) {
                myObj.getParentFile().mkdirs();
            }
            if (myObj.exists()) {
                return;
            } else {
                myObj.createNewFile();
            }

        } catch (IOException e) {
            ui.showToUser("Unable to create file.");
        }
    }

    public void clearFile() {
        try {
            assert filePath.equals("data/SmartHomeBot.txt") : "filePath should be data/SmartHome.txt";
            PrintWriter writer = new PrintWriter(filePath);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            ui.showToUser("File is empty.");
        }
    }
}
