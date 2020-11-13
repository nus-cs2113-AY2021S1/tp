package seedu.duke.storage;

import seedu.duke.model.ActivityList;
import seedu.duke.model.DayMap;
import seedu.duke.model.Exercise;
import seedu.duke.model.Food;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static seedu.duke.Trakcal.logging;
import static seedu.duke.ui.Ui.displayNotSavedMessage;

//@@author chewyang
/**
 * Storage class to decode and encode the .csv file.
 */
public class Storage {
    String filePath;
    File dataFile;
    public static final String LOAD_DATA_SUCCESS_MESSAGE = "Data successfully loaded into application";
    public static final String LOAD_DATA_FAILURE_MESSAGE = "Unable to load data from the file, the CSV file might "
            + "be corrupted";
    public static final String BOTH_PARENT_AND_DATA_EXIST_MESSAGE = "Parent folder /tpdata and data file "
            + "/tpdata/tpcsv already exists, "
            + "proceeding to use this folder.";
    public static final String ONLY_PARENT_FOLDER_EXISTS_MESSAGE = "Parent folder /tpdata exists but data file "
            + "/tpdata/tpcsv has not been "
            + "created yet, proceeding to create this file";
    public static final String PARENT_FOLDER_NOT_EXIST_MESSAGE = "Parent folder /tpdata does not exist, "
            + "proceeding to create both the"
            + " parent folder /tpdata and data file /tpdata/tpcsv";

    /**
     * Constructor for the storage class.
     *
     * @param filePath path of file to be stored into
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        dataFile = new File(filePath);
    }

    /**
     * Creates the file hierarchy if not present.
     *
     * @throws IOException if there is an issue
     */
    protected void createFileHierarchy() throws IOException {
        if (dataFile.getParentFile().exists()) {
            if (dataFile.exists()) {
                logging.writeToLogInfo(BOTH_PARENT_AND_DATA_EXIST_MESSAGE);
                return;
            } else {
                dataFile.createNewFile();
                logging.writeToLogInfo(ONLY_PARENT_FOLDER_EXISTS_MESSAGE);
            }
        } else {
            dataFile.getParentFile().mkdir();
            dataFile.createNewFile();
            logging.writeToLogInfo(PARENT_FOLDER_NOT_EXIST_MESSAGE);
        }
    }

    /**
     * Appends to the file.
     *
     * @param textToAdd string to append the file with
     */
    private void appendToFile(String textToAdd) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(textToAdd);
            pw.flush();
            pw.close();
            //System.out.println("record saved");
            //displaySavedMessage();
        } catch (Exception e) {
            //System.out.println("record not saved");
            displayNotSavedMessage();
        }
    }

    /**
     * Overwrites the file.
     *
     * @param textToAdd string to override the file with
     */
    private void writeToFile(String textToAdd) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(filePath);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to write to file!");
        }

    }

    /**
     * Updates the file.
     *
     * @param dayMap dayMap to update the file with
     */
    public void updateFile(DayMap dayMap) {
        HashMap<LocalDate, ActivityList> dayHashMap;
        dayHashMap = dayMap.getHashMap();
        Iterator it = dayHashMap.entrySet().iterator();
        writeToFile("");
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            String activities = pair.getValue().toString();
            appendToFile(pair.getKey().toString() + ", " + activities);
        }
        //displaySaveMessage();
    }
    //@@author chewyang

    //@@author poonchuanan
    /**
     * Loads saved CSV data into the list when the program starts.
     *
     * @param calList used to store the current activities
     */
    public void loadData(DayMap calList) {
        try {
            createFileHierarchy();
            //Read from CSV- reads the file line by line and stores the lines in an array list
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            List<String> lines = new ArrayList<>();
            String line = null;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            //Read line by line
            for (int i = 0; i < lines.size(); i++) {
                processData(calList, lines.get(i));
            }
            logging.writeToLogInfo(LOAD_DATA_SUCCESS_MESSAGE);
        } catch (IOException e) {
            System.out.println(LOAD_DATA_FAILURE_MESSAGE);
            logging.writeToLogWarning(LOAD_DATA_FAILURE_MESSAGE);
        }
    }

    /**
     * Splits the current line of CSV data into date and activity, then processes the activity.
     *
     * @param calList used to store the current activities
     * @param data line of CSV data
     */
    private void processData(DayMap calList, String data) {
        String dateString = data.substring(0, data.indexOf(','));
        LocalDate date = LocalDate.parse(dateString);
        //If the date is the same as today, append it to our list for the day
        //if (date.compareTo(LocalDate.now()) == 0) {
        String activities = data.substring(data.indexOf(",") + 1);
        String firstActivityString = null;
        while (activities.contains(",")) {
            firstActivityString = activities.substring(0, activities.indexOf(','));
            processActivity(calList, firstActivityString, date.atStartOfDay());
            activities = activities.substring(activities.indexOf(",") + 1);
        }
        processActivity(calList, activities, date.atStartOfDay());

    }

    /**
     * Splits the activity data into food or exercise, then adds the activity to calList.
     *
     * @param calList used to store the current activities
     * @param activity activity data in to format of type, description and calories
     * @param date date the activity was conducted
     */
    private void processActivity(DayMap calList, String activity, LocalDateTime date) {
        char typeOfActivity = activity.charAt(2);
        String description = activity.substring(activity.indexOf("|") + 1);
        description = description.substring(0, description.indexOf("|")).trim();
        int calorieStartIndex = activity.lastIndexOf(' ');
        String calorieString = activity.substring(calorieStartIndex).trim();
        int calories = Integer.parseInt(calorieString);
        switch (typeOfActivity) {
        case 'F':
            Food food = new Food(description, calories,date.toLocalDate(), true);
            calList.addActivity(date, food);
            break;
        case 'E':
            Exercise exercise = new Exercise(description, calories, date.toLocalDate(), true);
            calList.addActivity(date, exercise);
            break;
        default:
            System.out.println("Corrupted data. Activity should be either exercise or food");
            logging.writeToLogWarning(LOAD_DATA_FAILURE_MESSAGE);
        }
    }
}
