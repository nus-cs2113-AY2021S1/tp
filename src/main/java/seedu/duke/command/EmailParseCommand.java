package seedu.duke.command;

import seedu.duke.data.UserData;
import seedu.duke.exception.DateErrorException;
import seedu.duke.exception.DukeException;
import seedu.duke.parser.DateTimeParser;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailParseCommand extends Command {
    private String emailSubject;
    private int dateCount;


    /**
     * Constructor for parsing emails seedu.duke.
     *
     * @param command from user input
     */
    public EmailParseCommand(String command) {
        this.isExit = false;
        emailSubject = command;
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
        System.out.println("Please enter the email body!");
        String emailBody = ui.receiveCommand();
        ArrayList<LocalDate> dateList = detectDates(emailBody);
        LocalDate finalDate = chooseFinalDate(dateList, ui);
    }

    private LocalDate chooseFinalDate(ArrayList<LocalDate> dateList, Ui ui) {
        LocalDate finalDate = null;
        if (dateCount > 1) {
            System.out.println("We have detected " + dateCount + " dates in this email body!");
            System.out.println("Please select the date you want for this event from the list below!");
            int dateNumber = 0;
            for (LocalDate date : dateList) {
                ui.printDividerLine();
                System.out.println(dateNumber + 1 + " " + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                ui.printDividerLine();
            }
            boolean dateChosen = false;
            while (!dateChosen) {
                try {
                    int dateNumberChosen = Integer.parseInt(ui.receiveCommand());
                    if (dateNumberChosen > dateCount || dateNumberChosen <= 0) {
                        System.out.println("Invalid date number to choose! Please choose again!");
                    } else {
                        finalDate = dateList.get(dateNumberChosen - 1);
                    }
                    dateChosen = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid date number to choose! Please choose again!");
                }
            }
        } else if (dateCount == 0) {
            System.out.println("No dates detected for this email body!");
        } else {
            finalDate = dateList.get(0);
            System.out.println("One date detected: " + finalDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }

        return finalDate;
    }

    private ArrayList<LocalDate> detectDates(String emailBody) {
        ArrayList<String> dateListInString = new ArrayList<>();
        String upperCaseEmailBody = emailBody.toUpperCase();
        Pattern dayMonthYearPattern = Pattern.compile("\\b(([0]?[0-9])|([0-2][0-9])|([3][0-1]))(ST|ND|RD|TH)?[\\h-]" +
                "(JAN|JANUARY|FEB|FEBRUARY|MAR|MARCH|APR|APRIL|MAY|JUN|JUNE|JUL|JULY|AUG|AUGUST|SEP|SEPTEMBER|OCT|OCTOBER|NOV|" +
                "NOVEMBER|DEC|DECEMBER),?([\\h-]\\d{4})?\\b");
        Pattern monthDayYearPattern = Pattern.compile("\\b(JAN|JANUARY|FEB|FEBRUARY|MAR|MARCH|APR|APRIL|MAY|JUN|JUNE|" +
                "JUL|JULY|AUG|AUGUST|SEP|SEPTEMBER|OCT|OCTOBER|NOV|NOVEMBER|DEC|DECEMBER)[\\h-]" +
                "(([0]?[0-9])|([0-2][0-9])|([3][0-1]))(ST|ND|RD|TH)?,?([\\h-]\\d{4})?\\b");
        Matcher dayMonthYearMatcher = dayMonthYearPattern.matcher(upperCaseEmailBody);
        Matcher monthDayYearMatcher = monthDayYearPattern.matcher(upperCaseEmailBody);

        while (dayMonthYearMatcher.find()) {
            //dateCount++;
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

        ArrayList<LocalDate> dateList = verifyDates(dateListInString);
        dateCount = dateList.size();
        return dateList;
    }

    private ArrayList<LocalDate> verifyDates(ArrayList<String> dateListInString) {
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
        Pattern dayPattern = Pattern.compile("\\b(([0]?[0-9])|([0-2][0-9])|([3][0-1])){2}\\b");
        Matcher dayMatcher = dayPattern.matcher(date);
        if (dayMatcher.find()) {
            if (dayMatcher.group(0).startsWith("0")) {
                day = dayMatcher.group(0).substring(1);
            } else {
                day = dayMatcher.group(0);
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
