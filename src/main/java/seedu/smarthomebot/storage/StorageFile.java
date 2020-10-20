package seedu.smarthomebot.storage;

import seedu.smarthomebot.common.Messages;
import seedu.smarthomebot.data.AirConditioner;
import seedu.smarthomebot.data.ApplianceList;
import seedu.smarthomebot.data.Fan;
import seedu.smarthomebot.data.Lights;
import seedu.smarthomebot.data.LocationList;
import seedu.smarthomebot.data.WaterHeater;
import seedu.smarthomebot.exceptions.DuplicateDataException;
import seedu.smarthomebot.exceptions.EmptyParameterException;
import seedu.smarthomebot.exceptions.FileCorruptedException;
import seedu.smarthomebot.ui.TextUi;
import java.nio.charset.StandardCharsets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import static seedu.smarthomebot.common.Messages.MESSAGE_APPLIANCE_TYPE_NOT_EXIST;

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
            FileWriter myWriter = new FileWriter(filePath, StandardCharsets.UTF_8);
            myWriter.write(locationList.getLocations().toString() + "\n");
            for (int i = 0; i < applianceList.getAllAppliance().size(); i++) {
                myWriter.write(applianceList.getAppliance(i).writeFileFormat() + System.lineSeparator());
            }
            myWriter.close();
        } catch (IOException e) {
            //ui.printToUser("An error occur");
        }
    }

    public void readFile() {
        try {
            assert filePath.equals("data/SmartHomeBot.txt") : "filePath should be data/SmartHome.txt";
            int i = 0;
            File myFile = new File(filePath);
            Scanner myReader = new Scanner(myFile, StandardCharsets.UTF_8);
            String locationList = myReader.nextLine();
            try {
                readToLocationList(locationList);
                readToApplianceList(i, myReader);
                ui.printToUser(Messages.MESSAGE_IMPORT);
            } catch (FileCorruptedException e) {
                ui.printToUser(Messages.MESSAGE_FILE_CORRUPTED);
            }

            myReader.close();
        }  catch (FileNotFoundException | DuplicateDataException e) {
            ui.printToUser("Load File Does not Exist. No contents will be loaded.");
        } catch (IOException e) {
            ui.printToUser("Load File is corrupted.");
        }
    }

    private void readToApplianceList(int i, Scanner myReader) throws FileCorruptedException, DuplicateDataException {
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
                String status = splitString[4];
                String powerConsumption = splitString[5];
                String parameter = splitString[6];
                String dateTime = splitString[7];

                switch (type.toLowerCase()) {
                case Fan.TYPE_WORD:
                    Fan fan = new Fan(name, location, power);
                    applianceList.addAppliance(fan);
                    fan.getSpeedFromLoadFile(parameter);
                    break;
                case AirConditioner.TYPE_WORD:
                    AirConditioner ac = new AirConditioner(name, location, power);
                    applianceList.addAppliance(ac);
                    ac.getTemperatureFromLoadFile(parameter);
                    break;
                case Lights.TYPE_WORD:
                    Lights light = new Lights(name, location, power);
                    applianceList.addAppliance(light);
                    break;
                case WaterHeater.TYPE_WORD:
                    WaterHeater waterheater = new WaterHeater(name, location, power);
                    applianceList.addAppliance(waterheater);
                    waterheater.getTemperatureFromLoadFile(parameter);
                    break;
                default:
                    ui.printToUser(MESSAGE_APPLIANCE_TYPE_NOT_EXIST);
                }

                if (status.toLowerCase().equals("on")) {
                    applianceList.getAppliance(i).switchOn();
                }
                // when user exit, get the current system and save in datafile
                applianceList.getAppliance(i).loadDataFromFile(powerConsumption, dateTime);
                i++;
            } catch (IndexOutOfBoundsException e) {
                throw new FileCorruptedException();
            }

        }
    }

    private void readToLocationList(String location) throws FileCorruptedException {
        try {
            int openBracesIndex = location.indexOf("[") + 1;
            int closeBracesIndex = location.indexOf("]");
            String locations = location.substring(openBracesIndex, closeBracesIndex);
            String[] stringSplit = locations.split(",");
            for (String locationName : stringSplit) {
                locationList.addLocation(locationName.trim());
            }
        } catch (IndexOutOfBoundsException | DuplicateDataException e) {
            throw new FileCorruptedException();
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
            //ui.printToUser("Unable to create file.");
        }
    }

    public void clearFile() {
        try {
            assert filePath.equals("data/SmartHomeBot.txt") : "filePath should be data/SmartHome.txt";
            PrintWriter writer = new PrintWriter(filePath);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            //ui.printToUser("File is empty.");
        }
    }
}
