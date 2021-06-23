package logic.parser;

import exceptions.workout.workoutsession.AddFormatException;
import exceptions.workout.workoutsession.DeleteFormatException;

import models.Exercise;

import java.util.Arrays;


//@@author yujinyang1998

/**
 * A class that deals with making sense of user's command inside Workout Session.
 */
public class WorkoutSessionParser {

    /**
     * Splits the user's input by space and return as a string array.
     *
     * @param input User's input arguments.
     * @return String array containing the user's inputs
     */
    public static String[] workoutSessionParser(String input) {

        return input.split(" ");
    }

    /**
     * Sorts the string array into parts for Exercise.
     *
     * @param input String array containing the user's inputs.
     * @return Exercise containing the user's input information.
     * @throws AddFormatException If input is empty or is negative.
     */
    public static Exercise addParser(String[] input) throws AddFormatException {
        String[] returnString = new String[4];
        Arrays.fill(returnString, "");
        int tracker = 0;
        for (String s : input) {
            if (s.compareTo("/n") == 0) {
                tracker = 2;
            } else if (s.compareTo("/w") == 0) {
                tracker = 3;
            } else if (s.compareTo("add") == 0) {
                tracker = 1;
            } else if (tracker == 2 || tracker == 3) {
                returnString[tracker] = s;
            } else {
                returnString[tracker] += (s + " ");
            }
        }
        int repetitions = Integer.parseInt(returnString[2]);
        double weight = Double.parseDouble(returnString[3]);
        String description = returnString[1].trim();
        if (repetitions < 0 || weight < 0 || description.length() <= 0) {
            throw new AddFormatException();
        }

        return new Exercise(description, repetitions, weight);
    }

    /**
     * Sorts the string array to return an delete value.
     *
     * @param input String array containing the user's inputs.
     * @return Integer for the index of the exercise to be deleted.
     * @throws DeleteFormatException If input is not an integer.
     */
    public static int deleteParser(String[] input) throws DeleteFormatException {
        int returnInt;
        try {
            returnInt = Integer.parseInt(input[1]);
        } catch (NumberFormatException e) {
            throw new DeleteFormatException();
        }
        return returnInt;
    }

    /**
     * Sorts the string array to return an string that represents the search term.
     *
     * @param input String array containing the user's inputs.
     * @return String that the user whats to search.
     */
    public static String searchParser(String[] input) {
        StringBuilder returnString = new StringBuilder();
        for (int i = 1; i < input.length; i++) {
            returnString.append(" ").append(input[i]);
        }
        return returnString.toString().trim();
    }
}
