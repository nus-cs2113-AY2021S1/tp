package logic.parser;

import exceptions.InvalidDateFormatException;
import exceptions.profile.InvalidCommandFormatException;
import logger.SchwarzeneggerLogger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class that is responsible for parsing user inputs in Diet Manager.
 */
public class DietManagerParser extends CommonParser {

    public Logger logger = SchwarzeneggerLogger.getInstanceLogger();

    /**
     * Extracts out date and time by looking for date strings in YYYY-MM-DD format.
     *
     * @param parsedParams user input for new diet session
     * @param extractDateMessage string builder that appends warnings and messages
     * @return date in MMM dd yyyy if the user inputs date in YYYY-MM-DD format;
     *         else returns original string
     * @throws IllegalStateException if the date is in wrong state
     * @throws InvalidDateFormatException if the date is in wrong format
     */
    public String extractNewDate(HashMap<String, String> parsedParams, StringBuilder extractDateMessage)
            throws IllegalStateException, InvalidDateFormatException {
        try {
            String dateString = parsedParams.get("/d").trim();
            if (!dateString.isEmpty()) {
                return DateParser.parseDate(dateString).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            } else {
                extractDateMessage.append("Date input is empty.\n");
            }
        } catch (InvalidDateFormatException e) {
            throw new InvalidDateFormatException();
        } catch (NullPointerException e) {
            extractDateMessage.append("No date input is detected.\n");
        }
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        extractDateMessage.append("\t I've replaced it with today's date.\n\t ");
        return dtf.format(now);
    }

    /**
     * Extracts out tag of the diet session.
     *
     * @param parsedParams user input for new diet session
     * @param extractMealMessage string builder that appends warnings and messages
     * @return tag input if there is any;
     *         else returns "unspecified"
     * @throws NullPointerException if there is nothing in tag input
     */
    public String extractNewTag(HashMap<String, String> parsedParams, StringBuilder extractMealMessage)
            throws NullPointerException {
        try {
            String tag = parsedParams.get("/t").trim();
            if (tag.isEmpty()) {
                extractMealMessage.append("Tag input is empty, "
                        + "and it is replaced with \"unspecified\".");
                return "unspecified";
            } else {
                return tag;
            }
        } catch (NullPointerException e) {
            extractMealMessage.append("No tag is detected, "
                    + "and the session is tagged as \"unspecified\".");
            return "unspecified";
        }
    }

    /**
     * Extracts out starting date, end date and tag information.
     *
     * @param cmd user command
     * @param commandArgs user input
     * @return a hashmap where each information corresponds to the correct separator
     * @throws InvalidCommandFormatException if user enters invalid commands
     */
    public HashMap<String, String> extractDietManagerCommandTagAndInfo(String cmd, String commandArgs)
            throws InvalidCommandFormatException {

        HashMap<String, String> parsedParams = new HashMap<>();
        int startIndex = 0;
        int endIndex = 0;
        if ((cmd.equals("search")) && (commandArgs.isEmpty() || !(commandArgs.contains("/t")
                || commandArgs.contains("/s") || commandArgs.contains("/e")))) {
            throw new InvalidCommandFormatException("Wrong format, please enter in the format:\n\t "
                    + "search </s [STARTING_DATE]> </e [END_DATE]> </t [TAG]>");
        }

        try {
            while (commandArgs.indexOf("/", startIndex) != -1) {
                endIndex = commandArgs.indexOf("/", startIndex + 1);

                if (endIndex == -1) {
                    endIndex = commandArgs.length();
                }

                String parsedOption = commandArgs.substring(startIndex + 2, endIndex).trim();
                String optionIndicator = commandArgs.substring(startIndex, startIndex + 2).trim().toLowerCase();
                parsedParams.put(optionIndicator, parsedOption);

                startIndex = endIndex;
            }

            return parsedParams;
        } catch (StringIndexOutOfBoundsException e) {
            if (cmd.equals("search")) {
                logger.log(Level.WARNING, "Wrong format for search input");
                throw new InvalidCommandFormatException("Wrong format, please enter in the format:\n\t "
                        + "search </s [STARTING_DATE]> </e [END_DATE]> </t [TAG]>");
            } else if (cmd.equals("new")) {
                logger.log(Level.WARNING, "Wrong format for create input");
                throw new InvalidCommandFormatException("Wrong format, please enter in the format:\n\t "
                        + "new /d [DATE] /t [TAG]");
            } else {
                throw new InvalidCommandFormatException("Wrong format");
            }
        }
    }

    /**
     * Extracts out search tag.
     *
     * @param parsedParams a hashmap that contains information about tag
     * @param searchResult string build that contains warning messages
     * @return search tag
     */
    public String extractSearchTag(HashMap<String, String> parsedParams, StringBuilder searchResult) {
        try {
            String tag = parsedParams.get("/t").trim();
            return tag;
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "It looks like there is no input for search tag");
        }
        return "";
    }

    /**
     * Extracts out starting date.
     *
     * @param parsedParams a hashmap that contains information about starting date
     * @param searchResult string build that contains warning messages
     * @return starting date
     * @throws InvalidDateFormatException if the date is in wrong format
     */
    public LocalDateTime extractStartDates(HashMap<String, String> parsedParams, StringBuilder searchResult)
            throws InvalidDateFormatException {

        try {
            String startDate = parsedParams.get("/s");
            if (!startDate.isEmpty()) {
                return DateParser.parseDate(startDate);
            }
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "It looks like there is no date input in start date");
        } catch (InvalidDateFormatException e) {
            logger.log(Level.WARNING, "Invalid date in start date");
            throw new InvalidDateFormatException();
        }
        searchResult.append("Starting date is empty, "
                + "and it is replaced with the earliest date.\n\t ");
        return DateParser.parseDate("0001-01-01");
    }

    /**
     * Extracts out end date.
     *
     * @param parsedParams a hashmap that contains information about end date
     * @param searchResult string build that contains warning messages
     * @return end date
     * @throws InvalidDateFormatException if the date is in wrong format
     */
    public LocalDateTime extractEndDates(HashMap<String, String> parsedParams, StringBuilder searchResult)
            throws InvalidDateFormatException {

        try {
            String endDate = parsedParams.get("/e");
            if (!endDate.isEmpty()) {
                return DateParser.parseDate(endDate);
            }
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "It looks like there is no date input in end date");
        } catch (InvalidDateFormatException e) {
            logger.log(Level.WARNING, "Invalid date in start date");
            throw new InvalidDateFormatException();
        }
        searchResult.append("End date is empty, "
                + "and it is replaced with the latest date.\n\t ");
        return DateParser.parseDate("9999-12-12");
    }

}
