package seedu.duke.command;

import seedu.duke.data.UserData;
import seedu.duke.event.Personal;
import seedu.duke.exception.DateErrorException;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.InvalidExtractCommandException;
import seedu.duke.exception.TimeErrorException;
import seedu.duke.parser.DateTimeParser;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractCommand extends Command {
    private int dateCount;
    private int timeCount;


    /**
     * Constructor for parsing email/texts seedu.duke.
     *
     * @param command from user input.
     */
    public ExtractCommand(String command) {
        this.isExit = false;
        this.command = command;
    }

    /**
     * Extracts dates and timings from any block of text and creates Personal Event.
     *
     * @param data    object of UserData class containing user's data.
     * @param ui      containing the responses to print.
     * @param storage with the save file path to write to.
     */
    @Override
    public void execute(UserData data, Ui ui, Storage storage) throws DukeException {
        String[] arguments = command.split(";", 2);
        if (arguments.length != 2) {
            throw new InvalidExtractCommandException("The format for extract command is wrong!");
        }
        String textSubject = arguments[0];
        String textBody = arguments[1].trim();

        ArrayList<LocalDate> dateList = detectDate(textBody);
        LocalDate finalDate = chooseFinalDate(dateList, ui);

        if (finalDate == null) {
            if (textSubject.equals("")) {
                ui.printExtractEmptyTextMessage();
            } else {
                ui.printExtractNoDateEventMessage();
                Personal personalEvent = new Personal(textSubject);
                data.addToEventList("Personal", personalEvent);
            }
        } else {
            ArrayList<LocalTime> timeList = detectTime(textBody);
            LocalTime finalTime = chooseFinalTime(timeList, ui);
            if (finalTime == null) {
                ui.printExtractNoTimeEventMessage();
                Personal personalEvent = new Personal(textSubject, finalDate);
                data.addToEventList("Personal", personalEvent);
            } else {
                Personal personalEvent = new Personal(textSubject, finalDate, finalTime);
                data.addToEventList("Personal", personalEvent);
            }
        }
        ui.printEventAddedMessage(data.getEventList("Personal").getNewestEvent());
        storage.saveFile(storage.getFileLocation("Personal"), data, "Personal");
    }

    /**
     * Detects 12 and 24 hour time slots from a text.
     *
     * @param textBody A string of the text body that is scanned through.
     * @return An ArrayList of LocalTime objects for the time slots detected.
     */
    private ArrayList<LocalTime> detectTime(String textBody) {
        ArrayList<String> timeListInString = new ArrayList<>();
        String upperCaseTextBody = textBody.toUpperCase();
        Pattern timePattern = Pattern.compile("\\b(1[0-9]|0?[0-9]|2[0-3])([:.][0-5][0-9])?[\\h]?([AP][M])?\\b");
        Matcher timeMatcher = timePattern.matcher(upperCaseTextBody);

        while (timeMatcher.find()) {
            String time = timeMatcher.group(0);
            if (time.contains(".")) {
                time = time.replaceAll("\\.", ":");
            }
            if (time.contains("PM") || time.contains("AM")) {
                if (!time.contains(":")) {
                    time = time.substring(0, time.length() - 2) + ":00 " + time.substring(time.length() - 2);
                }
                if (!time.contains(" ")) {
                    time = time.substring(0, time.length() - 2) + " " + time.substring(time.length() - 2);
                }
            }
            time = time.toLowerCase();
            timeListInString.add(time);
        }

        ArrayList<LocalTime> timeList = verifyTime(timeListInString);
        timeCount = timeList.size();

        return timeList;
    }

    /**
     * Verifies whether the time slots detected are legitimate and converts to LocalTime.
     *
     * @param timeListInString An ArrayList of strings of time slots detected.
     * @return An ArrayList of LocalTime containing time slots that have been successfully converted.
     */
    private ArrayList<LocalTime> verifyTime(ArrayList<String> timeListInString) {
        ArrayList<LocalTime> timeList = new ArrayList<>();

        for (String timeInString : timeListInString) {
            try {
                LocalTime localTime = DateTimeParser.timeParser(timeInString);
                timeList.add(localTime);
            } catch (TimeErrorException e) {
                // something went wrong with date parsing
                // Log something?
            }
        }

        return timeList;
    }

    /**
     * Allows user to choose the time they want from timeList.
     *
     * @param timeList An ArrayList of LocalTime containing all time slots detected.
     * @param ui contains responses to print.
     * @return the LocalTime object chosen by user.
     */
    private LocalTime chooseFinalTime(ArrayList<LocalTime> timeList, Ui ui) {
        LocalTime finalTime = null;
        if (timeCount > 1) {
            ui.printExtractChooseTimeMessage(timeCount, timeList);
            boolean timeChosen = false;
            while (!timeChosen) {
                try {
                    int timeNumberChosen = Integer.parseInt(ui.receiveCommand());
                    if (timeNumberChosen > timeCount || timeNumberChosen <= 0) {
                        ui.printExtractInvalidTimeChosenMessage();
                    } else {
                        finalTime = timeList.get(timeNumberChosen - 1);
                        timeChosen = true;
                    }
                } catch (NumberFormatException e) {
                    ui.printErrorMessage("We couldn't detect a number! Please choose again!");
                }
            }
        } else if (timeCount == 0) {
            ui.printExtractNoTimeMessage();
        } else {
            finalTime = timeList.get(0);
            ui.printExtractSingleTimeDetectedMessage(finalTime);
        }

        return finalTime;

    }

    /**
     * Detects all the dates from a block of text.
     *
     * @param textBody A string containing the text to be scanned through.
     * @return An ArrayList of LocalDate objects for the dates detected.
     */
    private ArrayList<LocalDate> detectDate(String textBody) {
        ArrayList<String> dateListInString = new ArrayList<>();
        String upperCaseTextBody = textBody.toUpperCase();
        Pattern dayMonthYearPattern = Pattern.compile("\\b(([0]?[0-9])|([0-2][0-9])|([3][0-1]))(ST|ND|RD|TH)?[\\h-]"
                + "(JAN|JANUARY|FEB|FEBRUARY|MAR|MARCH|APR|APRIL|MAY|JUN|JUNE|JUL|JULY|AUG"
                + "|AUGUST|SEP|SEPTEMBER|OCT|OCTOBER|NOV|NOVEMBER|DEC|DECEMBER),?([\\h-]\\d{4})?\\b");
        Pattern monthDayYearPattern = Pattern.compile("\\b(JAN|JANUARY|FEB|FEBRUARY|MAR|MARCH|APR|APRIL|MAY|JUN|JUNE|"
                + "JUL|JULY|AUG|AUGUST|SEP|SEPTEMBER|OCT|OCTOBER|NOV|NOVEMBER|DEC|DECEMBER)[\\h-]"
                + "(([0]?[0-9])|([0-2][0-9])|([3][0-1]))(ST|ND|RD|TH)?,?([\\h-]\\d{4})?\\b");
        Matcher dayMonthYearMatcher = dayMonthYearPattern.matcher(upperCaseTextBody);
        Matcher monthDayYearMatcher = monthDayYearPattern.matcher(upperCaseTextBody);

        while (dayMonthYearMatcher.find()) {
            String date = dayMonthYearMatcher.group(0);
            String day = detectDay(date);
            String month = detectMonth(date);
            String year = detectYear(date);
            String combinedDate = day + "/" + month + "/" + year;
            dateListInString.add(combinedDate);
        }

        while (monthDayYearMatcher.find()) {
            String date = monthDayYearMatcher.group(0);
            String day = detectDay(date);
            String month = detectMonth(date);
            String year = detectYear(date);
            String combinedDate = day + "/" + month + "/" + year;
            dateListInString.add(combinedDate);
        }

        ArrayList<LocalDate> dateList = verifyDate(dateListInString);
        dateCount = dateList.size();
        return dateList;
    }

    /**
     * Verifies dates detected and converts them from string to LocalDate.
     *
     * @param dateListInString An ArrayList of strings containing dates.
     * @return An ArrayList of LocalDate that contains verified dates.
     */
    private ArrayList<LocalDate> verifyDate(ArrayList<String> dateListInString) {
        ArrayList<LocalDate> dateList = new ArrayList<>();
        for (String dateInString : dateListInString) {
            try {
                LocalDate localDate = DateTimeParser.dateParser(dateInString);
                dateList.add(localDate);
            } catch (DateErrorException e) {
                // something went wrong with date parsing
                // Log something?
            }
        }

        return dateList;
    }

    /**
     * Allows user to choose the dates they want from the dateList.
     *
     * @param dateList An ArrayList containing LocalDate objects to choose from.
     * @param ui contains responses to print.
     * @return A localDate object chosen for the event.
     */
    private LocalDate chooseFinalDate(ArrayList<LocalDate> dateList, Ui ui) {
        LocalDate finalDate = null;
        if (dateCount > 1) {
            ui.printExtractChooseDateMessage(dateCount, dateList);
            boolean dateChosen = false;
            while (!dateChosen) {
                try {
                    int dateNumberChosen = Integer.parseInt(ui.receiveCommand());
                    if (dateNumberChosen > dateCount || dateNumberChosen <= 0) {
                        ui.printExtractInvalidDateChosenMessage();
                    } else {
                        finalDate = dateList.get(dateNumberChosen - 1);
                        dateChosen = true;
                    }
                } catch (NumberFormatException e) {
                    ui.printErrorMessage("We couldn't detect a number! Please choose again!");
                }
            }
        } else if (dateCount == 0) {
            ui.printExtractNoDateMessage();
        } else {
            finalDate = dateList.get(0);
            ui.printExtractSingleDateDetectedMessage(finalDate);
        }

        return finalDate;
    }

    /**
     * Detects the day portion of the date.
     *
     * @param date String containing the full date detected.
     * @return String containing the day.
     */
    private String detectDay(String date) {
        String day = null;
        Pattern dayPattern = Pattern.compile("\\b(([0]?[0-9])|([0-2][0-9])|([3][0-1])){1,2}(ST|ND|RD|TH)?\\b");
        Matcher dayMatcher = dayPattern.matcher(date);
        if (dayMatcher.find()) {
            String dayMatch = dayMatcher.group(0);
            if (dayMatch.contains("ST") || dayMatch.contains("ND")
                    || dayMatch.contains("RD") || dayMatch.contains("TH")) {
                dayMatch = dayMatch.substring(0, dayMatch.length() - 2);
            }
            if (dayMatch.startsWith("0")) {
                day = dayMatch.substring(1);
            } else {
                day = dayMatch;
            }
        }
        return day;
    }

    /**
     * Detects the month from the date.
     *
     * @param date A string containing the full date detected.
     * @return A string containing the month detected.
     */
    private String detectMonth(String date) {
        String month = null;
        Pattern monthPattern = Pattern.compile("(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)");
        Matcher monthMatcher = monthPattern.matcher(date);
        if (monthMatcher.find()) {
            switch (monthMatcher.group(0)) {
            case "JAN":
                month = "1";
                break;
            case "FEB":
                month = "2";
                break;
            case "MAR":
                month = "3";
                break;
            case "APR":
                month = "4";
                break;
            case "MAY":
                month = "5";
                break;
            case "JUN":
                month = "6";
                break;
            case "JUL":
                month = "7";
                break;
            case "AUG":
                month = "8";
                break;
            case "SEP":
                month = "9";
                break;
            case "OCT":
                month = "10";
                break;
            case "NOV":
                month = "11";
                break;
            case "DEC":
                month = "12";
                break;
            default:
                break;
            }
        }

        return month;
    }

    /**
     * Detects the year from the date.
     *
     * @param date A string containing the full date.
     * @return A string containing the year portion of the date.
     */
    private String detectYear(String date) {
        String year = date.substring(date.length() - 4);
        try {
            if (Integer.parseInt(year) > 2100 || Integer.parseInt(year) < 1900) {
                year = getCurrentYear();
            }
        } catch (NumberFormatException e) {
            year = getCurrentYear();
        }
        return year;
    }

    /**
     * Retrieves the current year.
     *
     * @return A string containing the current year.
     */
    private String getCurrentYear() {
        Calendar currentDay = Calendar.getInstance();
        int yearInInt = currentDay.get(Calendar.YEAR);
        String year = String.valueOf(yearInInt);
        return year;
    }


}
