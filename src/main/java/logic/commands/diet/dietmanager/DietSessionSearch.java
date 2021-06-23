package logic.commands.diet.dietmanager;

import logic.commands.Command;
import logic.commands.CommandResult;
import logic.parser.DateParser;
import logic.parser.DietManagerParser;
import diet.dietsession.DietSession;
import exceptions.InvalidDateFormatException;
import exceptions.diet.InvalidSearchDateException;
import exceptions.profile.InvalidCommandFormatException;
import storage.diet.DietStorage;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static seedu.duke.Constants.PATH_TO_DIET_FOLDER;
import static ui.CommonUi.LS;
import static ui.diet.dietmanager.DietManagerUi.DIET_DATE_WRONG_FORMAT;
import static ui.diet.dietmanager.DietManagerUi.DIET_NO_SESSIONS_SAVED;
import static ui.diet.dietmanager.DietManagerUi.DIET_SEARCH_EMPTY_TAG;
import static ui.diet.dietmanager.DietManagerUi.DIET_SEARCH_RESULTS_MESSAGE;
import static ui.diet.dietmanager.DietManagerUi.EMPTY_STRING;

//@@author CFZeon
/**
 * A representation of the command for search commands in diet manager.
 */
public class DietSessionSearch extends Command {
    private final DietManagerParser parser = new DietManagerParser();

    /**
     * Overrides execute for search command to search diet sessions.
     *
     * @param input   user input for search command
     * @param storage storage file
     * @return CommandResult string variable
     * @throws InvalidDateFormatException if the date is in wrong format
     * @throws InvalidSearchDateException if the starting date is later than end date
     */
    public CommandResult execute(String input, DietStorage storage) throws InvalidDateFormatException,
            InvalidSearchDateException {
        String message = EMPTY_STRING;
        File folder = new File(PATH_TO_DIET_FOLDER);
        File[] listOfFiles = folder.listFiles();
        StringBuilder searchResult = new StringBuilder();
        try {
            HashMap<String, String> parsedParams = parser.extractDietManagerCommandTagAndInfo("search", input);
            LocalDateTime startDate = parser.extractStartDates(parsedParams, searchResult);

            LocalDateTime endDate = parser.extractEndDates(parsedParams, searchResult);
            if (startDate.compareTo(endDate) > 0) {
                throw new InvalidSearchDateException();
            }
            String tag = parser.extractSearchTag(parsedParams, searchResult);
            //check for empty tag in search parameter
            checkEmptyTag(searchResult, tag);
            searchResult.append(DIET_SEARCH_RESULTS_MESSAGE + "\n\t ");
            //check for presence of files in diet session save folder
            checkEmptyFolder(listOfFiles, searchResult);
            addToSearchResult(listOfFiles, searchResult, startDate, endDate, tag, storage);
            message = searchResult.toString();
        } catch (NullPointerException | InvalidCommandFormatException e) {
            message = "Wrong format, please enter in the format:\n\t "
                    + "search </s [STARTING_DATE]> </e [END_DATE]> </t [TAG]>";
        } catch (InvalidDateFormatException e) {
            searchResult.append(DIET_DATE_WRONG_FORMAT + "\n\t ");
            logger.log(Level.WARNING, "Invalid date format in diet session search");
            throw new InvalidDateFormatException();
        } catch (InvalidSearchDateException e) {
            logger.log(Level.WARNING, "Invalid date format in diet session search");
            throw new InvalidSearchDateException();
        } catch (NoSuchElementException e) {
            logger.log(Level.WARNING, "No such element in diet session search");
            ui.showToUser("Sorry, there is nothing found in your diet menu.");
        }
        return new CommandResult(message.trim());
    }

    /**
     * Checks if the folder is empty.
     *
     * @param listOfFiles Array for list of files in folder
     * @param searchResult String builder for search result
     */
    private void checkEmptyFolder(File[] listOfFiles, StringBuilder searchResult) {
        if (Objects.requireNonNull(listOfFiles).length == 0) {
            searchResult.append(DIET_NO_SESSIONS_SAVED + "\n\t ");
        }
    }

    /**
     * Checks if the tag is empty in the diet session.
     *
     * @param searchResult String builder for search result
     * @param tag string to check if tag is empty
     */
    private void checkEmptyTag(StringBuilder searchResult, String tag) {
        if (tag.isEmpty()) {
            searchResult.append(DIET_SEARCH_EMPTY_TAG + "\n\t ");
        }
    }

    //@@author CFZeon-reused
    /**
     * Prints search results.
     *
     * @param listOfFiles  list of files from local storage
     * @param searchResult string builder that accumulates warning messages
     * @param startDate    starting date for search
     * @param endDate      end date for search
     * @param tag          tag for search
     * @param storage      storage for diet sessions
     * @throws InvalidDateFormatException if date is in wrong format
     */
    private void addToSearchResult(File[] listOfFiles, StringBuilder searchResult, LocalDateTime startDate,
                                   LocalDateTime endDate, String tag, DietStorage storage)
            throws InvalidDateFormatException {
        //convert the file array to an arraylist for easier manipulation
        ArrayList<File> fileArrayList = new ArrayList<>();
        Collections.addAll(fileArrayList, listOfFiles);

        ArrayList<String> fileNames = (ArrayList<String>) fileArrayList.stream()
                .map(f -> f.getName().split(" ", 2)[1].trim()).collect(Collectors.toList());
        // get column boundaries for the table format for printing
        int descriptionMaxLenInt = Math.max(8,
                fileNames.stream().max(Comparator.comparingInt(String::length)).get().length());

        String descriptionFormat = "%-" + String.format("%d", descriptionMaxLenInt + 1) + "s";
        String returnString = String.format("%-8s", "Index") + String.format(descriptionFormat, "Date")
                + String.format("%-12s", "Tag") + String.format("%-10s", "Calories") + LS;
        searchResult.append(returnString);
        String listDescriptionFormat = "%-" + String.format("%d", descriptionMaxLenInt) + "s %-11s %s";
        int numberOfResult = 0;
        numberOfResult = addRow(listOfFiles, searchResult, startDate, endDate, tag, storage, fileArrayList,
                listDescriptionFormat, numberOfResult);
        String dietSessionSearchSize = "\n\t You have " + numberOfResult + " record(s)" + LS;
        if (numberOfResult == 0) {
            searchResult.setLength(0);
            searchResult.append("Sorry, there is nothing found in your diet menu.");
        }
        searchResult.append(dietSessionSearchSize);
    }

    /**
     * Adds to the string builder for table printing.
     *
     * @param listOfFiles  list of files from local storage
     * @param searchResult string builder that accumulates warning messages
     * @param startDate    starting date for search
     * @param endDate      end date for search
     * @param tag          tag for search
     * @param storage      storage for diet sessions
     * @param fileArrayList for file array converted to arraylist
     * @param listDescriptionFormat the description of the files in array list
     * @param numberOfResult integer of results
     * @return number of results total
     * @throws InvalidDateFormatException if date is in wrong format
     */
    private int addRow(File[] listOfFiles, StringBuilder searchResult, LocalDateTime startDate,
                       LocalDateTime endDate, String tag, DietStorage storage, ArrayList<File> fileArrayList,
                       String listDescriptionFormat, int numberOfResult) throws InvalidDateFormatException {
        for (int i = 0; i < fileArrayList.size(); i++) {
            //instantiates stored diet session to get total calorie count
            DietSession ds = storage.readDietSession(PATH_TO_DIET_FOLDER, listOfFiles[i].getName());
            double totalCalories = ds.getTotalCalories();
            //extract tags and dates and assigns to string from filename
            String fileTag = getFileTag(fileArrayList, i);
            String fileDate = getFileDate(fileArrayList, i);
            // converts extracted date string to java dateFormat
            LocalDateTime dateFormat = DateParser.parseDate(fileDate);
            if (startDate.compareTo(dateFormat) <= 0 && endDate.compareTo(dateFormat) >= 0 && fileTag.contains(tag)) {
                String rowContent = String.format(listDescriptionFormat, fileDate, fileTag, totalCalories);
                String row = String.format("%-8s", ++numberOfResult) + rowContent + LS;
                searchResult.append(row);
            }
        }
        return numberOfResult;
    }

    /**
     * Reads file name and returns date.
     *
     * @param fileArrayList list of files from local storage into array list
     * @param i index of file from search result
     * @return date as string
     */
    private String getFileDate(ArrayList<File> fileArrayList, int i) {
        String fileDate = fileArrayList.get(i).getName().replaceFirst("[.][^.]+$", "")
                .split(" ", 2)[0];
        return fileDate;
    }

    /**
     * Reads file name and returns tags.
     *
     * @param fileArrayList list of files from local storage into array list
     * @param i index of file from search result
     * @return date as string
     */
    private String getFileTag(ArrayList<File> fileArrayList, int i) {
        return fileArrayList.get(i).getName().replaceFirst("[.][^.]+$", "")
                        .split(" ", 2)[1];
    }
}
