package seedu.duke.storage;

import seedu.duke.exceptions.EmptyParameterException;
import seedu.duke.commands.AddCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.CreateCommand;
import seedu.duke.data.ApplianceList;
import seedu.duke.data.HomeLocations;

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
            String file = myReader.nextLine();
            int start = file.indexOf("[") + 1;
            int end = file.indexOf("]");
            String when = file.substring(start, end);
            String[] stringSplit = when.split(",");
            for (int j = 0; j < stringSplit.length; j++) {
                Command newLocation = new CreateCommand(stringSplit[j].trim());
                newLocation.setData(appliances, homeLocations);
                newLocation.execute();
            }
            while (myReader.hasNextLine()) {
                file = myReader.nextLine();
                String[] splitString = file.split("\\|", 7);
                Command abc = new AddCommand(splitString[1], splitString[0], splitString[2], splitString[3]);
                abc.setData(appliances, homeLocations);
                abc.execute();
                appliances.getAppliance(i).updatePowerConsumption(splitString[5]);
                if (splitString[4].toLowerCase().equals("on")) {
                    appliances.getAppliance(i).switchOn();
                }
                i++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Load File Does not Exist. No contents will be loaded.");
        } catch (EmptyParameterException e) {
            System.out.println("Null location found in save file.");
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
            System.out.println("An error occured");
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
