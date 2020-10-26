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
import java.time.format.DateTimeFormatter;
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
     * @param command from user input
     */
    public ExtractCommand(String command) {
        this.isExit = false;
        this.command = command;
    }

    /**
     * abstract class for commands.
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
                System.out.println("Empty text subject and no date/time fields, event won't be created!");
            } else {
                System.out.println("Since no date was detected in the text body, "
                        + "the personal event will only contain the description.");
                Personal personalEvent = new Personal(textSubject);
                data.addToEventList("Personal", personalEvent);
            }
        } else {
            ArrayList<LocalTime> timeList = detectTime(textBody);
            LocalTime finalTime = chooseFinalTime(timeList, ui);
            if (finalTime == null) {
                System.out.println("No time detected in text body!"
                        + " The personal event will only have the date and description.");
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

    private LocalTime chooseFinalTime(ArrayList<LocalTime> timeList, Ui ui) {
        LocalTime finalTime = null;

        if (timeCount > 1) {
            System.out.println("We have detected " + timeCount + " time slots in this text body!");
            System.out.println("Please select the time you want for this event from the list below!");
            int timeNumber = 0;
            ui.printDividerLine();
            for (LocalTime time : timeList) {
                System.out.println(timeNumber + 1 + ". " + time);
                timeNumber++;
            }
            ui.printDividerLine();
            boolean timeChosen = false;
            while (!timeChosen) {
                try {
                    int timeNumberChosen = Integer.parseInt(ui.receiveCommand());
                    if (timeNumberChosen > timeCount || timeNumberChosen <= 0) {
                        System.out.println("Invalid time slot number to choose! Please choose again!");
                    } else {
                        finalTime = timeList.get(timeNumberChosen - 1);
                        timeChosen = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("We couldn't detect a number! Please choose again!");
                }
            }
        } else if (timeCount == 0) {
            System.out.println("No time slots detected for this text body!");
        } else {
            finalTime = timeList.get(0);
            System.out.println("One time slot detected and chosen: " + finalTime);
        }

        return finalTime;

    }


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


    private LocalDate chooseFinalDate(ArrayList<LocalDate> dateList, Ui ui) {
        LocalDate finalDate = null;
        if (dateCount > 1) {
            System.out.println("We have detected " + dateCount + " dates in this text body!");
            System.out.println("Please select the date you want for this event from the list below!");
            int dateNumber = 0;
            ui.printDividerLine();
            for (LocalDate date : dateList) {
                System.out.println(dateNumber + 1 + ". " + date);
                //System.out.println(dateNumber + 1 + " " + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                dateNumber++;
            }
            ui.printDividerLine();
            boolean dateChosen = false;
            while (!dateChosen) {
                try {
                    int dateNumberChosen = Integer.parseInt(ui.receiveCommand());
                    if (dateNumberChosen > dateCount || dateNumberChosen <= 0) {
                        System.out.println("Invalid date number to choose! Please choose again!");
                    } else {
                        finalDate = dateList.get(dateNumberChosen - 1);
                        dateChosen = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("We couldn't detect a number! Please choose again!");
                }
            }
        } else if (dateCount == 0) {
            System.out.println("No date detected in the text!");
        } else {
            finalDate = dateList.get(0);
            System.out.println("One date detected and chosen: " + finalDate);
        }

        return finalDate;
    }

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

    private String getCurrentYear() {
        Calendar currentDay = Calendar.getInstance();
        int yearInInt = currentDay.get(Calendar.YEAR);
        String year = String.valueOf(yearInInt);
        return year;
    }

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


}
