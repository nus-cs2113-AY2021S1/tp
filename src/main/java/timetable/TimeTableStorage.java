package timetable;

import studyit.StudyItLog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class TimeTableStorage {

    private static File taskFile;
    private final String filePath;

    public TimeTableStorage(String filePath, DateList dateList) {
        // Creates data directory
        String dirPath = "data";
        File fileDir = new File(dirPath);

        if (!fileDir.exists()) {
            fileDir.mkdir();
        }

        this.filePath = filePath;
        taskFile = new File(filePath);
        checkFile(dateList);
    }

    public void checkFile(DateList dateList) {
        try {
            if (taskFile.createNewFile()) {
                System.out.println("data/timetable.txt is not found, creating a new file now!");
            } else {
                loadFile(dateList);
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            StudyItLog.logger.warning("Problem loading timetable storage file\n" + e);
        }
    }

    private void loadFile(DateList dateList) {
        try {
            Scanner s = new Scanner(taskFile);
            while (s.hasNext()) {
                String command = s.nextLine();
                TimeTableParser.fileParser(command, dateList);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException | NumberFormatException e) {
            Scanner in = new Scanner(System.in);
            System.out.println("Data file for timetable is corrupted do you want to format the file");
            if (in.nextLine().equals("yes")) {
                wipeFile();
                System.out.println("Data file for timetable have been formatted");
            } else {
                System.out.println("Please exit the program and edit timetable.txt "
                        + "manually before using this feature.");
            }
        }
    }

    public void writeFile(Event event) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            if (event.eventType.equals(EventType.L)) {
                fw.write("L|" + event.name + "|" + event.linkOrVenue + "|" + event.isOnline
                        + event.getStorageString() + System.lineSeparator());
            } else if (event.eventType.equals(EventType.A)) {
                fw.write("A|" + event.name + "|" + event.linkOrVenue + "|" + event.isOnline
                        + event.getStorageString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong" + e.getMessage());
            StudyItLog.logger.warning("Problem writing to timetable storage file\n" + e);
        }
    }

    public void wipeFile() {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong" + e.getMessage());
        }
    }
}
