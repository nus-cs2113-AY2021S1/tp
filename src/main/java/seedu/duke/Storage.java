package seedu.duke;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import seedu.duke.bookmark.BookmarkList;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeExceptionType;
import seedu.duke.slot.Slot;
import seedu.duke.slot.Timetable;
import seedu.duke.slot.Module;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage<T> {

    private String filePath;
    private Class<T> storageClass;

    /**
     * Constructs a new Storage instance by storing the given pathname of the file.
     *
     * @param path The pathname of the file.
     */
    public Storage(String path, Class<T> storageClass) {
        this.filePath = path.replace('/', File.separatorChar);
        this.storageClass = storageClass;
    }

    /**
     * Returns the tasks found within the file.
     *
     * @return Objects T found in the file.
     * @throws DukeException If file is not found.
     */
    public T load() throws DukeException {
        String fileAsString;
        try {
            fileAsString = Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            return createNewInstance();
        }

        if (!fileAsString.equals("null")) {
            Gson gson = new Gson();
            return gson.fromJson(fileAsString, storageClass);
        } else {
            return createNewInstance();
        }
    }

    private T createNewInstance() throws DukeException {
        try {
            return storageClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new DukeException(DukeExceptionType.ERROR_LOADING_FILE);
        }
    }

    public T loadPlanner() throws DukeException {

        File folder = new File(filePath);
        if (folder.listFiles() == null) {
            return createNewInstance();
        }

        ArrayList<File> listOfFiles = new ArrayList<>(Arrays.asList(folder.listFiles()));

        StringBuilder fileAsString = new StringBuilder();
        String opening = "{" + System.lineSeparator() + "  \"modules\": [";
        String closing = System.lineSeparator() + "  ]" + System.lineSeparator() + "}";

        fileAsString.append(opening);
        for (File f : listOfFiles) {
            try {
                StringBuilder line = new StringBuilder(Files.readString(Paths.get(f.getPath())));
                fileAsString.append(line.delete(0,16));
                fileAsString.delete(fileAsString.length() - 6, fileAsString.length());
                fileAsString.append(",");
            } catch (IOException e) {
                return createNewInstance();
            }
        }

        fileAsString.delete(fileAsString.length() - 1, fileAsString.length());
        fileAsString.append(closing).append(System.lineSeparator());

        // System.out.println(fileAsString.toString());

        if (!fileAsString.toString().equals("null")) {
            Gson gson = new Gson();
            return gson.fromJson(fileAsString.toString(), storageClass);
        } else {
            return createNewInstance();
        }
    }

    public static Timetable initialiseEmptySlots(Timetable timetable) {

        ArrayList<Slot> slots = new ArrayList<>(timetable.getFullSlotList());

        ArrayList<ArrayList<Integer>> array = new ArrayList<>(7);
        for (int i = 0; i < 7; i++) {
            array.add(new ArrayList<>(Collections.nCopies(300, 0)));
        }

        for (Slot s: slots) {
            markAsFull(array, s.getStartMinutes(), s.getEndMinutes(), s.getDay());
        }

        Module module = new Module("EMPTY");
        generateEmptySlots(array, module);

        //System.out.println(module.getSlotList());

        Timetable emptyTimetable = new Timetable();
        emptyTimetable.addModule(module);
        return emptyTimetable;
    }

    private static void markAsFull(ArrayList<ArrayList<Integer>> array, int start, int end, String day) {
        int count = 0;
        for (String d: Slot.days) {
            if (d.equals(day)) {
                for (int i = start/5; i < end/5; i++) {
                    array.get(count).set(i, 1);
                }
            }
            count++;
        }
    }

    private static void generateEmptySlots(ArrayList<ArrayList<Integer>> array, Module module) {
        int count = 0;
        for (String s: Slot.days) {
            for (int i = 0; i < 287; i++) {
                if (array.get(count).get(i) == 0) {
                    int startHours = (i * 5) / 60;
                    int startMinutes = (i * 5) % 60;
                    LocalTime start = Slot.convertIntToLocalTime(startHours % 288, startMinutes % 288);
                    do {
                        i++;
                    } while (array.get(count).get(i) == 0 && i < 287);
                    int endHours = (i * 5) / 60;
                    int endMinutes = (i * 5) % 60;
                    LocalTime end = Slot.convertIntToLocalTime(endHours % 288, endMinutes % 288);
                    module.addSlot(new Slot(start, end, s, "<empty slot>"));
                }
            }
            count++;
        }
    }

    private ArrayList<String> getData(File f) throws FileNotFoundException {
        ArrayList<String> items = new ArrayList<>();
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            items.add(s.nextLine());
        }
        return items;
    }

    /**
     * This method creates the file if it does not exist and saves tasks data in the file.
     *
     * @param data An object to be converted into JSON and saved in the file.
     * @throws DukeException If there is an error writing to the file.
     */
    public void save(Object data) throws DukeException {
        try {
            createDirectory();

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson.toJson(data);

            writeToFile(jsonString);
        } catch (IOException e) {
            throw new DukeException(DukeExceptionType.WRITE_FILE_ERROR);
        }
    }

    private void createDirectory() throws IOException {
        String dirPath = getDirectory(filePath);
        Path path = Paths.get(dirPath);
        Files.createDirectories(path);
    }

    private String getDirectory(String path) {
        String dirPath;
        if (path.contains(File.separator)) {
            dirPath = path.substring(0, path.lastIndexOf(File.separator));
        } else {
            dirPath = path;
        }
        return dirPath;
    }

    private void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        fw.write(textToAdd);
        fw.close();
    }

}
