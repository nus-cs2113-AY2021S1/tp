package seedu.smarthomebot.storage;

import seedu.smarthomebot.commons.Messages;
import seedu.smarthomebot.commons.exceptions.DuplicateDataException;
import seedu.smarthomebot.data.appliance.ApplianceList;
import seedu.smarthomebot.data.appliance.type.AirConditioner;
import seedu.smarthomebot.data.appliance.type.Fan;
import seedu.smarthomebot.data.appliance.type.Lights;
import seedu.smarthomebot.data.appliance.type.SmartPlug;
import seedu.smarthomebot.data.location.LocationList;
import seedu.smarthomebot.logic.commands.exceptions.InvalidApplianceNameException;
import seedu.smarthomebot.logic.commands.exceptions.LocationNotFoundException;
import seedu.smarthomebot.storage.exceptions.FileCorruptedException;
import seedu.smarthomebot.ui.TextUi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.smarthomebot.commons.Messages.MESSAGE_APPLIANCE_TYPE_NOT_EXIST;
import static seedu.smarthomebot.commons.Messages.MESSAGE_EMPTY_FILE;
import static seedu.smarthomebot.commons.Messages.MESSAGE_FILE_CORRUPTED;
import static seedu.smarthomebot.commons.Messages.MESSAGE_FILE_DOES_NOT_EXIST;

//@@author TanLeeWei

/**
 * Represent reading of storage file back into the program.
 */
public class ReadStorageFile extends StorageFile {

    private static String FILE_PATH;
    private final TextUi ui = new TextUi();

    public ReadStorageFile(String filePath, ApplianceList applianceList, LocationList locationList) {
        super(applianceList, locationList);
        this.FILE_PATH = filePath;
    }

    /**
     *  Executing the reading of storage file.
     */
    @Override
    public void execute() {
        try {
            assert FILE_PATH.equals("data/SmartHomeBot.txt") : "FILE_PATH should be data/SmartHome.txt";
            int i = 0;
            File myFile = new File(FILE_PATH);
            Scanner myReader = new Scanner(myFile);
            String locationList =  myReader.nextLine();
            try {
                readToLocationList(locationList);
                readToApplianceList(i, myReader);
                if (locationList.equals("[]")) {
                    ui.printToUser(MESSAGE_EMPTY_FILE);
                } else {
                    ui.printToUser(Messages.MESSAGE_IMPORT);
                }
            } catch (FileCorruptedException e) {
                storageLogger.log(Level.WARNING, MESSAGE_FILE_CORRUPTED);
                ui.printToUser(Messages.MESSAGE_FILE_CORRUPTED);
            }
            storageLogger.log(Level.INFO, "Successfully loaded Save File");
            myReader.close();
        } catch (FileNotFoundException e) {
            storageLogger.log(Level.WARNING, MESSAGE_FILE_DOES_NOT_EXIST);
            ui.printToUser(MESSAGE_FILE_DOES_NOT_EXIST);
        } catch (NoSuchElementException e) {
            storageLogger.log(Level.WARNING, MESSAGE_FILE_CORRUPTED);
            ui.printToUser(Messages.MESSAGE_FILE_CORRUPTED);
        }
    }

    /**
     * Method to read appliance from the storage file into ApplianceList.
     * @param numberOfAppliance Keep track of the number of appliance.
     * @param myReader Read each line in the .txt storage file into the program.
     */
    private void readToApplianceList(int numberOfAppliance, Scanner myReader) throws FileCorruptedException {
        while (myReader.hasNextLine()) {
            try {
                String appliance = myReader.nextLine();
                String[] splitString = appliance.split("\\|", 9);
                if (splitString[1].isEmpty() || splitString[0].isEmpty()
                        || splitString[2].isEmpty() || splitString[3].isEmpty()) {
                    throw new FileCorruptedException();
                }
                String name = splitString[1];
                String location = splitString[0];
                String power = splitString[2];
                String type = splitString[3];
                String powerConsumption = splitString[4];
                String parameter = splitString[5];

                switch (type.toLowerCase()) {
                case Fan.TYPE_WORD:
                    Fan fan = new Fan(name, location, power, locationList);
                    applianceList.addAppliance(fan);
                    fan.getSpeedFromLoadFile(parameter);
                    break;
                case AirConditioner.TYPE_WORD:
                    AirConditioner ac = new AirConditioner(name, location, power, locationList);
                    applianceList.addAppliance(ac);
                    ac.getTemperatureFromLoadFile(parameter);
                    break;
                case Lights.TYPE_WORD:
                    Lights light = new Lights(name, location, power, locationList);
                    applianceList.addAppliance(light);
                    break;
                case SmartPlug.TYPE_WORD:
                    SmartPlug smartPlug = new SmartPlug(name, location, power, locationList);
                    applianceList.addAppliance(smartPlug);
                    break;
                default:
                    ui.printToUser(MESSAGE_APPLIANCE_TYPE_NOT_EXIST);
                }
                applianceList.getAppliance(numberOfAppliance).loadDataFromFile(powerConsumption);
                numberOfAppliance++;
                storageLogger.log(Level.INFO, "Successfully read appliance into appliancelist");
            } catch (Exception e) {
                throw new FileCorruptedException();
            }
        }
    }

    /**
     * Method to read location from the storage file into LocationList.
     * @param location appliance location read from the .txt storage file.
     */
    private void readToLocationList(String location) throws FileCorruptedException {
        try {
            int openBracesIndex = location.indexOf("[") + 1;
            int closeBracesIndex = location.lastIndexOf("]");
            String locations = location.substring(openBracesIndex, closeBracesIndex);
            String[] stringSplit = locations.split(",");
            for (String locationName : stringSplit) {
                if (!locationName.isEmpty() && !applianceList.isApplianceExist(locationName.trim())) {
                    locationList.addLocation(locationName.trim());
                }
            }
            storageLogger.log(Level.INFO, "Successfully read location into locationList");
        } catch (IndexOutOfBoundsException | DuplicateDataException e) {
            throw new FileCorruptedException();
        }

    }
}
