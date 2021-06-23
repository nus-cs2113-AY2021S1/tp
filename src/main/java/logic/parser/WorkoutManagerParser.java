package logic.parser;

import exceptions.InvalidDateFormatException;
import exceptions.workout.workoutmanager.NotANumberException;
import logger.SchwarzeneggerLogger;
import models.PastWorkoutSessionRecord;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.logging.Level;

//@@author wgzesg
/**
 * A class that is responsible for parsing user inputs in Workout Manager.
 */
public class WorkoutManagerParser {

    public static final String TAG_SPECIFIER = "/t";
    public static final String TAG_SPLITTER = ",";
    public static final String DATE_SPECIFIER = "/d";
    public static final String START_DATE_SPECIFIER = "/s";
    public static final String END_DATE_SPECIFIER = "/e";
    private static WorkoutManagerParser wmp;

    /**
     * Gets instance of PastRecordList.
     *
     * @return Instance of PastRecordList.
     */
    public static WorkoutManagerParser getInstance() {
        if (wmp == null) {
            wmp = new WorkoutManagerParser();
        }
        return wmp;
    }

    /**
     * Parses user inputs into single words.
     * @param comm User's raw input.
     * @return An array of strings.
     */
    public String[] parseCommandKw(String comm) {
        return comm.split(" ", 2);
    }

    /**
     * Parses user input into a list of tags.
     * @param arr User inputs.
     * @return A list of tags.
     */
    public ArrayList<String> parseTags(String arr) {
        ArrayList<String> result = new ArrayList<>();
        String[] content = arr.split(" ", 2);

        try {
            if (!content[0].equals(TAG_SPECIFIER)) {
                return result;
            }
            String[] tags = content[1].split(TAG_SPLITTER);
            for (String tag : tags) {
                if (!result.contains(tag.trim())) {
                    result.add(tag.trim());
                }
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return result;
    }

    /**
     * Parses user input into a list of predicates which will be used as search conditions.
     * @param arr User input.
     * @return A list of predicate which will be used as search conditions.
     * @throws InvalidDateFormatException handles invalid date input.
     */
    public ArrayList<Predicate<PastWorkoutSessionRecord>> parseSearchConditions(String arr)
            throws InvalidDateFormatException {
        ArrayList<String> tags = new ArrayList<>();
        ArrayList<Predicate<PastWorkoutSessionRecord>> test = new ArrayList<>();

        // parse tag conditions
        try {
            String[] part1 = arr.split(TAG_SPECIFIER);
            String[] tagPart = part1[1].split(DATE_SPECIFIER);
            String[] tgs = tagPart[0].split(",");
            for (String t : tgs) {
                tags.add(t.trim());
            }
            test.add(x -> x.containsAll(tags));
        } catch (ArrayIndexOutOfBoundsException e) {
            SchwarzeneggerLogger.getInstanceLogger().log(Level.INFO, "No tag identifier is given.");
        }

        // parse date conditions
        try {
            String[] part2 = arr.split(DATE_SPECIFIER);
            String[] datePart = part2[1].split(TAG_SPECIFIER);
            LocalDateTime finalDate = DateParser.parseDate(datePart[0].trim());
            if (finalDate != null) {
                test.add(x -> x.isCreatedOn(finalDate.toLocalDate()));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            SchwarzeneggerLogger.getInstanceLogger().log(Level.INFO, "No date identifier is given.");
        }

        return test;
    }

    /**
     * Parses the given input into a integer index.
     *
     * @param args user input.
     * @return a integer which is the index given.
     * @throws NotANumberException if args is null, empty or not a number.
     */
    public int parseIndex(String args) throws NotANumberException {
        int index;
        try {
            index = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new NotANumberException();
        }
        return index;
    }

    /**
     * Parses user input into a list of predicates which will be used to limit period of record being listed.
     *
     * @param args user input.
     * @return predicates to limit period of record being listed.
     * @throws InvalidDateFormatException handles invalid date input.
     */
    public ArrayList<Predicate<PastWorkoutSessionRecord>> parseList(String args)
            throws InvalidDateFormatException {
        ArrayList<Predicate<PastWorkoutSessionRecord>> test = new ArrayList<>();

        // parse start date
        try {
            String[] part2 = args.split(START_DATE_SPECIFIER);
            String[] datePart = part2[1].split(END_DATE_SPECIFIER);
            LocalDateTime start = DateParser.parseDate(datePart[0].trim());
            if (start != null) {
                test.add(x -> x.isCreatedAfter(start.toLocalDate()));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            SchwarzeneggerLogger.getInstanceLogger().log(Level.INFO, "No start date identifier is given.");
        }

        // parse end date
        try {
            String[] part2 = args.split(END_DATE_SPECIFIER);
            String[] datePart = part2[1].split(START_DATE_SPECIFIER);
            LocalDateTime end = DateParser.parseDate(datePart[0].trim());
            if (end != null) {
                test.add(x -> x.isCreatedBefore(end.toLocalDate()));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            SchwarzeneggerLogger.getInstanceLogger().log(Level.INFO, "No end date identifier is given.");
        }

        return test;
    }
}
