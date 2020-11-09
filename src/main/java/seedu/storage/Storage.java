package seedu.storage;

import seedu.exception.WhereGotTimeException;
import seedu.task.Event;
import seedu.user.Cryptography;
import seedu.user.User;
import seedu.user.UserList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages the loading and saving of data to or from a text file.
 */
public class Storage {
    private static final String FP = "data/WhereGotTime.txt";
    private static final String[] days = {"mon", "tue", "wed", "thu", "fri", "sat", "sun"};

    /**
     * Creates a new file if it does not exist.
     *
     * @throws WhereGotTimeException if an I/O error has occurred.
     */
    public Storage() throws WhereGotTimeException {
        initialise();
    }

    /**
     * Initialises a file by checking path validity.
     *
     * @throws WhereGotTimeException if an I/O error has occurred.
     */
    private void initialise() throws WhereGotTimeException {
        File storageFile = new File(FP);
        File storageFolder = new File(storageFile.getParent());
        if (storageFolder.exists() && storageFolder.isDirectory()) {
            System.out.println("Data folder found! Finding WhereGotTime.txt...");
        } else {
            System.out.println("Data folder not found! Creating one...");
            if (!storageFolder.mkdir()) {
                throw new WhereGotTimeException("Attempt to create a data folder failed.");
            }
        }
        try {
            if (storageFile.createNewFile()) {
                System.out.println("No existing WhereGotTime.txt found! Creating one...");
            } else {
                System.out.println("Existing WhereGotTime.txt found. Loading previously saved "
                        + "timetable information...");
                System.out.println("Timetable information loaded successfully!");
            }
        } catch (IOException e) {
            throw new WhereGotTimeException("Attempt to create WhereGotTime.txt failed.");
        } finally {
            System.out.println("Storage initialisation completed without issue.");
        }
    }
    
    /**
     * Loads data from the text file to task arraylist.
     *
     * @throws WhereGotTimeException if an I/O error has occurred.
     */
    public void load(UserList users) throws WhereGotTimeException {
        Scanner reader;
        
        try {
            reader = new Scanner(new File(FP));
        } catch (FileNotFoundException e) {
            throw new WhereGotTimeException("Attempt to read WhereGotTime.txt failed.");
        }
        while (reader.hasNextLine()) {
            loadTask(reader, users);
        }
    }


    /**
     * Parses the saved tasks according to specified format in order to be loaded.
     *
     * @param reader reads user's string input.
     * @param users object of UserList containing all available user's data
     * @throws WhereGotTimeException if an I/O error has occurred.
     */
    private void loadTask(Scanner reader, UserList users) throws WhereGotTimeException {
        String firstLine = reader.nextLine();
        int totalUser = 0;
        if (firstLine.contains("Total user: ")) {
            totalUser = Integer.parseInt(firstLine.substring(12));
        } else {
            throw new WhereGotTimeException("WhereGotTime.txt formatting is incorrect.");
        }

        for (int i = 0; i < totalUser; i++) {
            String[] userCredentials = reader.nextLine().split("\\|");
            User newUser = new User(userCredentials[0].trim(), 
                    Cryptography.decipherPassword(userCredentials[1].trim()));
            users.addUser((newUser));

            for (String day : days) {
                reader.nextLine();
                String line = reader.nextLine();

                if (line.isEmpty()) {
                    continue;
                }

                String[] timetables = line.split("\\|");
                for (String s : timetables) {
                    String[] lessons = s.split("/");
                    String description = lessons[1].trim();
                    String location = lessons[2].trim();
                    String timeRange = lessons[3].trim();
                    String[] times = timeRange.split("-");
                    Event lessonEvent = new Event(description, location, times[0].trim(), times[1].trim());
                    ArrayList<Event> timetable = users.getUser(i + 1).getTimetable().getTimetable(day);
                    timetable.add(lessonEvent);
                }
            }
        }
    }

    /**
     * Writes data to the text file.
     * 
     * @param users object of UserList containing all available user's data
     * @throws WhereGotTimeException if an I/O error has occurred.
     */
    public void write(UserList users) throws WhereGotTimeException {
        //ArrayList<Task> tasks = taskList.getTaskList();
        try {
            FileWriter fw = new FileWriter(new File(FP));
            writeTask(fw, users);
            fw.close();
        } catch (IOException e) {
            throw new WhereGotTimeException("Attempt to write to WhereGotTime.txt failed.");
        }
    }

    /**
     * Writes specific task details to the text file in given format.
     *
     * @param fw FileWriter object.
     * @param users object of UserList containing all available user's data          
     * @throws IOException if an I/O error has occurred.
     */
    private void writeTask(FileWriter fw, UserList users) throws IOException, WhereGotTimeException {
        fw.write("Total user: " + users.getTotalUserCount());
        fw.append("\n");
        for (User u : users.getUserList()) {
            fw.write(u.getName() + " | " + u.getEncipheredPassword());
            fw.append("\n");

            for (String day : days) {
                fw.write(day);
                fw.append("\n");
                ArrayList<Event> timetableIndividualDay = u.getTimetable().getTimetable(day);
                int timetableSize = timetableIndividualDay.size();

                for (int k = 0; k < timetableSize; k++) {
                    String classDescription = timetableIndividualDay.get(k).getDescription();
                    String location = timetableIndividualDay.get(k).getLocation();
                    String startTime = timetableIndividualDay.get(k).getTimeStart();
                    String endTime = timetableIndividualDay.get(k).getTimeEnd();
                    fw.write("/" + classDescription + " /" + location + " /" + startTime
                            + "-" + endTime);
                    if (!(k == (timetableSize - 1))) {
                        fw.write(" | ");
                    }
                }
                fw.append("\n");
            }
        }
    }
}
