package seedu.parser;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class CalParser {
    public static int countExtractor(String splitted) {
        String[] splitCount = splitted.split("COUNT=");
        String[] lineSplit = splitCount[1].split(";");
        int count = Integer.parseInt(lineSplit[0]);
        return count;
    }

    public static String descriptionExtractor(String splitted) {
        String[] splitCount = splitted.split("SUMMARY:");
        String[] lineSplit = splitCount[1].split("\n");
        return lineSplit[0];
    }

    public static ArrayList<LocalDate> exceptionExtractor(String splitted) throws ParseException {
        ArrayList<LocalDate> exceptionDates = new ArrayList<LocalDate>();
        String[] splitCount = splitted.split("\n");
        for (String i : splitCount) {
            if (i.contains("EXDATE:")) {
                String exDate;
                exDate = i.split("EXDATE:")[1];
                LocalDate startDate = LocalDate.parse(exDate, DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'"));
                exceptionDates.add(startDate);
            }
        }
        return exceptionDates;
    }

    public static ArrayList<LocalDate> dateExtractor(String splitString, int count) throws ParseException {
        String[] splitCount = splitString.split("\n");
        ArrayList<LocalDate> dates = new ArrayList<LocalDate>();
        int tempIndex = 0;
        LocalDate startDate;
        for (String i : splitCount) {
            if (i.contains("DTSTART:")) {
                String exDate;
                exDate = i.split("DTSTART:")[1];
                startDate = LocalDate.parse(exDate, DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'"));
                dates.add(startDate);
                tempIndex++;
            }
        }
        for (int i = 1; i < count; i++) {
            LocalDate nextWeekDate = dates.get(i - 1).plusDays(7);
            boolean isNotException = exceptionChecker(dates, nextWeekDate);
            if (isNotException) {
                dates.add(nextWeekDate);
                tempIndex++;
            }
        }
        return dates;
    }

    public static boolean exceptionChecker(ArrayList<LocalDate> exceptionDates, LocalDate nextWeekDate)
            throws ParseException {
        for (LocalDate i : exceptionDates) {
            if (i.isEqual(nextWeekDate)) {
                return false;
            }
        }
        return true;
    }

    public static LocalTime[] timeExtractor(String splitted) throws ParseException {
        LocalTime startTime;
        LocalTime endTime;
        LocalTime[] taskDuration = new LocalTime[2];
        String[] splitCount = splitted.split("\n");
        for (String i : splitCount) {
            if (i.contains("DTSTART:")) {
                String exDate;
                exDate = i.split("DTSTART:")[1];
                LocalTime gmtTime = LocalTime.parse(exDate, DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'"));
                startTime = (gmtTime).plusHours(8);
                taskDuration[0] = startTime;
            }
            if (i.contains("DTEND:")) {
                String exDate;
                exDate = i.split("DTEND:")[1];
                LocalTime gmtTime = LocalTime.parse(exDate, DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'"));
                endTime = (gmtTime).plusHours(8);
                taskDuration[1] = endTime;
            }
        }
        return taskDuration;
    }

    public static String lineExtractor(File textFile) throws IOException {
        Scanner myReader = new Scanner(textFile);
        String taskData = "";
        while (myReader.hasNextLine()) {
            taskData += ("\n" + myReader.nextLine());
        }
        return taskData;
    }
}
