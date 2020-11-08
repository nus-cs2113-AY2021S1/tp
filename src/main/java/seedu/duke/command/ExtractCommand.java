package seedu.duke.command;

import seedu.duke.EventLogger;
import seedu.duke.data.UserData;
import seedu.duke.event.Personal;
import seedu.duke.event.Zoom;
import seedu.duke.exception.DateErrorException;
import seedu.duke.exception.InvalidExtractCommandException;
import seedu.duke.exception.TimeErrorException;
import seedu.duke.exception.InvalidListException;
import seedu.duke.parser.DateTimeParser;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractCommand extends Command {
    private int dateCount;
    private int timeCount;
    private int zoomLinkCount;
    private String textSubject = null;
    private String textBody = null;
    private String eventType;
    private static Logger logger = EventLogger.getEventLogger();

    /**
     * Constructor for parsing email/texts seedu.duke.
     *
     * @param command from user input.
     */
    public ExtractCommand(String command) {
        this.isExit = false;
        if (command.endsWith(";")) {
            textSubject = command.split(";", 2)[0];
        }
        eventType = "Personal";
        logger.fine("Extract command successfully constructed.");
    }

    /**
     * Extracts dates,timings and zoom links from any block of text and creates Personal Events or Zoom events.
     *
     * @param data    object of UserData class containing user's data.
     * @param ui      containing the responses to print.
     * @param storage with the save file path to write to.
     * @throws InvalidExtractCommandException The textBody or textSubject is null or empty.
     * @throws InvalidListException the eventlist that the event added to is not valid (should never occur).
     */
    @Override
    public void execute(UserData data, Ui ui, Storage storage) throws InvalidExtractCommandException, InvalidListException {
        if (textSubject == null) {
            throw new InvalidExtractCommandException("Text subject was not entered correctly!");
        }
        ui.printExtractTextBodyRequestMessage();
        ui.printDividerLine();
        textBody = receiveTextBody(ui);

        if (textBody == null) {
            throw new InvalidExtractCommandException("Text body was not entered correctly!");
        }
        if (textSubject.equals("")) {
            throw new InvalidExtractCommandException("There is no text subject entered!");
        }
        if (textBody.equals("")) {
            throw new InvalidExtractCommandException("There is no text body entered!");
        }

        ArrayList<String> zoomLinkList = detectZoomLink(textBody);
        String zoomLink = null;
        if (zoomLinkList.size() > 0) {
            eventType = "Zoom";
            zoomLink = chooseZoomLink(zoomLinkList, ui);
        }

        ArrayList<LocalDate> dateList = detectDate(textBody);
        LocalDate finalDate = chooseFinalDate(dateList, ui);

        ArrayList<LocalTime> timeList = detectTime(textBody);
        LocalTime finalTime = chooseFinalTime(timeList, ui);

        createEvent(data, ui, finalDate, finalTime, zoomLink);

        ui.printEventAddedMessage(data.getEventList(eventType).getNewestEvent());
        storage.saveFile(storage.getFileLocation(eventType), data, eventType);
        logger.fine("Extract command successfully executed, a new " + eventType + " event was created.");
    }

    /**
     * Creates a personal or zoom event based on if there is a zoom link and the detected fields.
     *
     * @param data object of UserData class containing user's data.
     * @param ui containing the responses to print.
     * @param finalDate final date chosen by user, may be null if no date detected.
     * @param finalTime final time chosen by user, may be null if no time detected.
     * @param zoomLink final zoom link chosen by user, may be null if no zoom link detected.
     * @throws InvalidListException the eventlist that the event added to is not valid (should never occur).
     */
    private void createEvent(UserData data, Ui ui, LocalDate finalDate, LocalTime finalTime, String zoomLink) throws InvalidListException {
        if (finalDate == null) {
            if (eventType.equals("Personal")) {
                ui.printExtractNoDatePersonalEventMessage();
                data.addToEventList("Personal", new Personal(textSubject));
            } else if (eventType.equals("Zoom")) {
                assert zoomLink != null : "Zoom link is not detected after choosing";
                ui.printExtractNoDateZoomEventMessage();
                data.addToEventList("Zoom", new Zoom(textSubject, zoomLink));
            }
        } else {
            if (finalTime == null) {
                if (eventType.equals("Personal")) {
                    ui.printExtractNoTimePersonalEventMessage();
                    data.addToEventList("Personal", new Personal(textSubject, finalDate));
                } else if (eventType.equals("Zoom")) {
                    assert zoomLink != null : "Zoom link is not detected after choosing";
                    ui.printExtractNoTimeZoomEventMessage();
                    data.addToEventList("Zoom", new Zoom(textSubject, zoomLink));
                }
            } else {
                if (eventType.equals("Personal")) {
                    data.addToEventList("Personal", new Personal(textSubject, finalDate, finalTime));
                } else if (eventType.equals("Zoom")) {
                    assert zoomLink != null : "Zoom link is not detected after choosing";
                    data.addToEventList("Zoom", new Zoom(textSubject, zoomLink, finalDate, finalTime));
                }
            }
        }
    }

    /**
     * Detects zoom links from the text the user inputs.
     *
     * @param textBody A string of the text body that is scanned through for zoom links.
     * @return An ArrayList of String with valid zoom links.
     */
    private ArrayList<String> detectZoomLink(String textBody) {
        ArrayList<String> zoomLinkList = new ArrayList<>();
        Pattern urlPattern = Pattern.compile("https?://(www\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\\."
                + "[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_+.~#?&//=]*)");
        Matcher urlMatcher = urlPattern.matcher(textBody);

        while (urlMatcher.find()) {
            String url = urlMatcher.group(0);
            if (url.contains(".zoom.")) {
                zoomLinkList.add(url);
            }
        }
        zoomLinkCount = zoomLinkList.size();
        return zoomLinkList;
    }

    /**
     * Allows the user to choose from Zoom Links detected.
     *
     * @param zoomLinkList An ArrayList of type String containing zoom links.
     * @param ui contains responses to print.
     * @return The zoom link in String chosen by the user.
     */
    private String chooseZoomLink(ArrayList<String> zoomLinkList, Ui ui) {
        String zoomLink = null;
        if (zoomLinkCount > 1) {
            ui.printExtractChooseZoomLinkMessage(zoomLinkCount, zoomLinkList);
            boolean zoomLinkChosen = false;
            while (!zoomLinkChosen) {
                try {
                    int zoomLinkNumberChosen = Integer.parseInt(ui.receiveCommand());
                    if (zoomLinkNumberChosen > zoomLinkCount || zoomLinkNumberChosen <= 0) {
                        ui.printExtractInvalidFieldChosenMessage("zoom link");
                    } else {
                        zoomLink = zoomLinkList.get(zoomLinkNumberChosen - 1);
                        zoomLinkChosen = true;
                        assert zoomLink != null : "zoomLink is null when chosen in extract";
                    }
                } catch (NumberFormatException e) {
                    logger.warning("NumberFormatException occured -- User chose an invalid zoom link number from list.");
                    ui.printErrorMessage("We couldn't detect a number! Please choose again!");
                }
            }
        } else {
            zoomLink = zoomLinkList.get(0);
            assert zoomLink != null : "zoomLink is null when chosen in extract";
            ui.printExtractSingleZoomLinkDetectedMessage(zoomLink);
        }
        return zoomLink;
    }

    /**
     * Takes in the textBody from the user.
     *
     * @param ui is used to receive Strings the user input.
     * @return String containing the full text body entered.
     */
    private String receiveTextBody(Ui ui) {
        String bodyLine = "";
        String fullTextBody = "";
        while (!bodyLine.equals("extractend")) {
            bodyLine = ui.receiveCommand().trim();
            fullTextBody = fullTextBody.concat(" " + bodyLine);
        }
        return fullTextBody;
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
                LocalTime localTime = DateTimeParser.timeParser(timeInString.trim());
                timeList.add(localTime);
            } catch (TimeErrorException e) {
                logger.fine(timeInString + " was detected but not parsed");
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
                        ui.printExtractInvalidFieldChosenMessage("timing");
                    } else {
                        finalTime = timeList.get(timeNumberChosen - 1);
                        timeChosen = true;
                        assert finalTime!= null : "date is null when chosen in extract";
                    }
                } catch (NumberFormatException e) {
                    logger.warning("NumberFormatException occured -- User chose an invalid time number from list.");
                    ui.printErrorMessage("We couldn't detect a number! Please choose again!");
                }
            }
        } else if (timeCount == 0) {
            ui.printExtractNoFieldMessage("timing");
        } else {
            finalTime = timeList.get(0);
            assert finalTime!= null : "date is null when chosen in extract";
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
                logger.fine(dateInString + " was detected but not parsed");
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
                        ui.printExtractInvalidFieldChosenMessage("date");
                    } else {
                        finalDate = dateList.get(dateNumberChosen - 1);
                        dateChosen = true;
                        assert finalDate != null : "date is null when chosen in extract";
                    }
                } catch (NumberFormatException e) {
                    logger.warning("NumberFormatException occured -- User chose an invalid date number from list.");
                    ui.printErrorMessage("We couldn't detect a number! Please choose again!");
                }
            }
        } else if (dateCount == 0) {
            ui.printExtractNoFieldMessage("date");
        } else {
            finalDate = dateList.get(0);
            assert finalDate != null : "date is null when chosen in extract";
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
