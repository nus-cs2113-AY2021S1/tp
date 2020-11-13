package seedu.duke.model;

import seedu.duke.model.ActivityList;
import seedu.duke.model.Food;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

//@@ chewyang
/**
 * This class is used to create a responsive interface for the listing feature after a list command.
 */
public class ListDrawer {
    private String date;
    protected ActivityList activityList;
    protected static final String INDENT = "        ";
    protected static final String DIVIDER = "-";
    protected static final String DELIMITER = ",";
    protected static final String DESCRIPTION = "Description";
    protected static final String DATE = "Date";
    protected static final String NUMBERS = "No.";
    protected static final String NEWLINE = "\n";
    protected static final String ACTIVITY_TYPE = "Type";
    protected static final String FOOD_TYPE = "Food";
    protected static final String EXERCISE_TYPE = "Exercise";
    protected static final String CALORIES_GAIN_OR_LOST = "Calories gain or lost";
    protected static final int START_INDEX_FOR_DESCRIPTION = 14 + INDENT.length() + ACTIVITY_TYPE.length();
    protected static final int START_INDEX_FOR_CALORIES = 72 + INDENT.length() + ACTIVITY_TYPE.length();
    protected static final int MAX_STRING_FOR_DESCRIPTION = 36 + DESCRIPTION.length();
    protected static final int START_INDEX_FOR_EXERCISE_TYPE = 9;
    protected static final int START_INDEX_FOR_FOOD_TYPE = 11;


    /**
     * Sets the date and activity list to be printed.
     *
     * @param date date which activity list to be used
     * @param activityList is the list to be used when creating the list
     */
    public ListDrawer(LocalDate date, ActivityList activityList) {
        this.date = date.toString();
        this.activityList = activityList;
    }

    /**
     * Constructor for the listDrawer class.
     *
     * @param lastSeenList list to be drawn.
     */
    public ListDrawer(ActivityList lastSeenList) {
        this.activityList = lastSeenList;
    }

    /**
     * Prints the list of activities.
     */
    public void printList() {
        System.out.print(headerBox()
                + listHeaderString()
                + NEWLINE
                + increaseStringLength(DIVIDER, listHeaderString().length())
                + NEWLINE
                + allActivityString());
    }


    /**
     * Multiplies the length of a string by a given length.
     *
     * @param stringToIncrease string to multiply in length
     * @param length length to increase by
     * @return a string of the increased length
     */
    protected String increaseStringLength(String stringToIncrease, int length) {

        return String.join("", Collections.nCopies(length, stringToIncrease));
    }

    /**
     * Generates the header for the list.
     *
     * @return string of header
     */
    protected String listHeaderString() {
        String header = NUMBERS
                + increaseStringLength(INDENT, 1) + ACTIVITY_TYPE
                + increaseStringLength(INDENT, 3) + DESCRIPTION
                + increaseStringLength(INDENT,3) + CALORIES_GAIN_OR_LOST;
        return header;
    }

    /**
     * Generates the header as a box with the net calorie and date information.
     *
     * @return string for the header box
     */
    protected String headerBox() {
        String headerString = "|  " + date + "  " + netCalorieString();
        String dividerString = increaseStringLength(DIVIDER, headerString.length());
        return dividerString + NEWLINE + headerString + NEWLINE + dividerString + NEWLINE;
    }

    protected String netCalorieString() {
        return "|  " + "Net Calorie: " + activityList.getNetCalorie() + " kcal  |";
    }


    /**
     * Generates the string for a single activity.
     *
     * @param index index of the activity
     * @return string of single activity
     */
    protected String singleActivityString(int index) {
        ArrayList<String> descriptions = new ArrayList<>();
        String restOfDescription = "";

        String indexString = Integer.toString(index + 1);
        int lengthOfIndex = indexString.length();

        String descriptionString = activityList.getActivity(index).getActivityDescription();
        int lengthOfDescription = descriptionString.length();

        String typeString;
        int lengthOfType;

        int lengthLeftForWhiteSpaceType;
        int lengthLeftForWhiteSpaceDescription;
        int lengthLeftForWhiteSpace;



        if (activityList.getActivity(index) instanceof Food) {
            typeString = FOOD_TYPE;
            lengthOfType = FOOD_TYPE.length();
            lengthLeftForWhiteSpaceType = START_INDEX_FOR_FOOD_TYPE - lengthOfIndex;

        } else {
            typeString = EXERCISE_TYPE;
            lengthOfType = EXERCISE_TYPE.length();
            lengthLeftForWhiteSpaceType = START_INDEX_FOR_EXERCISE_TYPE - lengthOfIndex;
        }

        lengthLeftForWhiteSpaceDescription = START_INDEX_FOR_DESCRIPTION
                - lengthOfIndex - lengthLeftForWhiteSpaceType - lengthOfType;



        if (lengthOfDescription > MAX_STRING_FOR_DESCRIPTION) {
            lengthLeftForWhiteSpace = START_INDEX_FOR_CALORIES - lengthOfIndex - lengthOfType
                    - lengthLeftForWhiteSpaceType - lengthLeftForWhiteSpaceDescription - MAX_STRING_FOR_DESCRIPTION;
        } else {
            lengthLeftForWhiteSpace = START_INDEX_FOR_CALORIES - lengthOfIndex - lengthOfType
                    - lengthLeftForWhiteSpaceType  - lengthLeftForWhiteSpaceDescription - lengthOfDescription;
        }


        //if the length of the description exceeds the maximum length, extract the rest of the description
        while (lengthOfDescription > MAX_STRING_FOR_DESCRIPTION) {
            String description = descriptionString.substring(0, MAX_STRING_FOR_DESCRIPTION);
            descriptions.add(description);
            descriptionString = descriptionString.substring(MAX_STRING_FOR_DESCRIPTION);
            lengthOfDescription = descriptionString.length();

        }
        descriptions.add(descriptionString);

        if (descriptions.size() > 1) {
            for (int i = 1; i < descriptions.size(); i++) {
                restOfDescription = restOfDescription
                        + increaseStringLength(" ", START_INDEX_FOR_DESCRIPTION)
                        + descriptions.get(i)
                        + NEWLINE;
            }


        }

        String calorieString = Integer.toString(activityList.getActivity(index).getActivityCalories());

        return indexString
                + increaseStringLength(" ", lengthLeftForWhiteSpaceType) + typeString
                + increaseStringLength(" ", lengthLeftForWhiteSpaceDescription) + descriptions.get(0)
                + increaseStringLength("_", lengthLeftForWhiteSpace) + calorieString
                + NEWLINE
                + restOfDescription;

    }

    /**
     * Generates the content for the list.
     *
     * @return string of the content
     */
    protected String allActivityString() {
        String allActivityString = "";
        for (int i = 0; i < activityList.getNumberOfActivities(); i++) {
            allActivityString = allActivityString + singleActivityString(i) + NEWLINE;
        }
        return allActivityString;
    }
}
