package timetable;

import studyit.StudyItLog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
                System.out.println("data/timetable.txt created for time table");
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
}
