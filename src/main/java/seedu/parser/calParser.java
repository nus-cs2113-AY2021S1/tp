package seedu.parser;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class calParser {
    public static int countExtractor(String splitted) {
        String[] splittedcount = splitted.split("COUNT=");
        String[] moreSplitted = splittedcount[1].split(";");
        int count = Integer.parseInt(moreSplitted[0]);
        return count;
    }

    public static String descriptionExtractor(String splitted) {
        String[] splittedcount = splitted.split("SUMMARY:");
        String[] moreSplitted = splittedcount[1].split("\n");
        return moreSplitted[0];
    }

    public static ArrayList<LocalDate> exceptionExtractor(String splitted) throws ParseException {
        ArrayList<LocalDate> exceptionDates = new ArrayList<LocalDate>();
        String[] splittedcount = splitted.split("\n");
        for (String i : splittedcount) {
            if (i.contains("EXDATE:")) {
                String ExDate;
                ExDate = i.split("EXDATE:")[1];
                LocalDate startDate = LocalDate.parse(ExDate, DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'"));
                exceptionDates.add(startDate);
            }
        }
        return exceptionDates;
    }

    public static ArrayList<LocalDate> dateExtractor(String splitString, int count) throws ParseException {
        String[] splittedcount = splitString.split("\n");
        ArrayList<LocalDate> Dates = new ArrayList<LocalDate>();
        int tempIndex = 0;
        LocalDate startDate;
        for (String i : splittedcount) {
            if (i.contains("DTSTART:")) {
                String ExDate;
                ExDate = i.split("DTSTART:")[1];
                startDate = LocalDate.parse(ExDate, DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'"));
                Dates.add(startDate);
                tempIndex++;
            }
        }
        for (int i = 1; i < count; i++) {
            LocalDate nextWeekDate = Dates.get(i - 1).plusDays(7);
            boolean isNotException = exceptionChecker(Dates, nextWeekDate);
            if (isNotException) {
                Dates.add(nextWeekDate);
                tempIndex++;
            }
        }
        return Dates;
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
        String[] splittedcount = splitted.split("\n");
        for (String i : splittedcount) {
            if (i.contains("DTSTART:")) {
                String ExDate;
                ExDate = i.split("DTSTART:")[1];
                LocalTime gmtTime = LocalTime.parse(ExDate, DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'"));
                startTime = (gmtTime).plusHours(8);
                taskDuration[0] = startTime;
            }
            if (i.contains("DTEND:")) {
                String ExDate;
                ExDate = i.split("DTEND:")[1];
                LocalTime gmtTime = LocalTime.parse(ExDate, DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'"));
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
