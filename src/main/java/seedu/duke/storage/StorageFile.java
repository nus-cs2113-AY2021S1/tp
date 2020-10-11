package seedu.duke.storage;

import seedu.duke.common.Messages;
import seedu.duke.exceptions.EmptyParameterException;
import seedu.duke.commands.AddCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.CreateCommand;
import seedu.duke.data.ApplianceList;
import seedu.duke.data.HomeLocations;
import seedu.duke.exceptions.FileCorrupted;
import seedu.duke.exceptions.InvalidAddtionOfLocation;
import seedu.duke.ui.TextUi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class StorageFile {

    private static String filePath = "data/SmartHomeBot.txt";
    private static ApplianceList appliances;
    private static HomeLocations homeLocations;
    private static TextUi ui = new TextUi();

    public StorageFile(ApplianceList appliances, HomeLocations homeLocations) {
        this.homeLocations = homeLocations;
        this.appliances = appliances;
    }

    public static void writeToFile() {
        try {
            createFile();
            clearFile();
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(homeLocations.getLocations().toString() + "\n");
            for (int i = 0; i < appliances.getAllAppliance().size(); i++) {
                myWriter.write(appliances.getAppliance(i).getLocation()
                        + "|" + appliances.getAppliance(i).getName()
                        + "|" + appliances.getAppliance(i).getStringPower()
                        + "|" + appliances.getAppliance(i).getType()
                        + "|" + appliances.getAppliance(i).getStatus()
                        + "|" + appliances.getAppliance(i).getPowerConsumption() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occur");
        }
    }

    public static void readFile() {
        try {
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
            System.out.println("Load File Does not Exist. No contents will be loaded.");
        }
    }

    private static void convertTextToApplianceList(int i, Scanner myReader) throws FileCorrupted {
        while (myReader.hasNextLine()) {
            try {
                String applianceList = myReader.nextLine();
                String[] splitString = applianceList.split("\\|", 7);
                if (splitString[1].isEmpty() || splitString[0].isEmpty()
                        || splitString[2].isEmpty() || splitString[3].isEmpty()) {
                    throw new FileCorrupted();
                }
                AddCommand add = new AddCommand(splitString[1], splitString[0], splitString[2], splitString[3]);
                add.setData(appliances, homeLocations);
                add.sortIntoType();
                appliances.getAppliance(i).updatePowerConsumption(splitString[5]);
                if (splitString[4].toLowerCase().equals("on")) {
                    appliances.getAppliance(i).switchOn();
                }
                i++;
            } catch (IndexOutOfBoundsException e) {
                throw new FileCorrupted();
            }

        }
    }

    private static void convertTextToLocationList(String locationList) throws EmptyParameterException, FileCorrupted {
        try {
            int start = locationList.indexOf("[") + 1;
            int end = locationList.indexOf("]");
            String when = locationList.substring(start, end);
            String[] stringSplit = when.split(",");
            for (int j = 0; j < stringSplit.length; j++) {
                //CreateCommand newLocation = new CreateCommand(stringSplit[j].trim());
                //newLocation.setData(appliances, homeLocations);
                //newLocation.insertLocation();
                homeLocations.addLocation(stringSplit[j].trim());
            }
        } catch (IndexOutOfBoundsException | InvalidAddtionOfLocation e) {
            throw new FileCorrupted();
        }

    }

    public static void createFile() {
        try {
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
            System.out.println("An error occurred");
        }
    }

    public static void clearFile() {
        try {
            PrintWriter writer = new PrintWriter(filePath);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File is empty");
        }
    }
}
