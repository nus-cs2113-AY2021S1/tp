package seedu.duke.model;

import seedu.duke.Trakcal;
import seedu.duke.exception.SameIndexForMoveCommandException;

import java.util.ArrayList;
import java.util.Arrays;

import static seedu.duke.ui.Ui.displayEmptyActivityCounterMessage;


//@@author chewyang
/**
 * List of activities for any day.
 */
public class ActivityList extends Trakcal {
    private ArrayList<Activity> activities;
    private int activityCounter;
    private int netCalorie;

    /**
     * Constructor of class ActivityList.
     */
    public ActivityList() {
        activities = new ArrayList<>();
        activityCounter = 0;
        netCalorie = 0;
    }

    /**
     * Returns the current number of activities in the list.
     *
     * @return current number of activities in the list
     */
    public int getNumberOfActivities() {
        return activities.size();
    }

    public ArrayList getArrayList() {
        return activities;
    }

    //@@author e0425705
    /**
     * Adds new activity to the list and updates the netCalorie accordingly to the activity type added.
     *
     * @param activity new activity that will be added to the current activity
     * @throws IndexOutOfBoundsException if the calories is not within the limits
     */
    public void addActivity(Activity activity) throws IndexOutOfBoundsException {
        activities.add(activity);
        activityCounter++;
        if (activity instanceof Food) {
            netCalorie += activity.calories;
        } else if (activity instanceof Exercise) {
            netCalorie -= activity.calories;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }


    /**
     * This method replaces the current activity at index with a new activity.
     * To change the description of the current activity.
     *
     * @param index    is the index of the current activity to be replaced
     * @param activity is the new activity that will be replacing the current activity
     */
    public void insertActivity(int index, Activity activity) throws IndexOutOfBoundsException {
        if (isValidIndex(index)) {

            // removes calories of previous in list index
            Activity activityToReplace = activities.get(index);
            if (activityToReplace instanceof Food) {
                netCalorie -= activityToReplace.calories;
            } else if (activityToReplace instanceof Exercise) {
                netCalorie += activityToReplace.calories;
            }

            activities.set(index, activity);

            // updates calories of edited list index
            if (activity instanceof Food) {
                netCalorie += activity.calories;
            } else if (activity instanceof Exercise) {
                netCalorie -= activity.calories;
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    //@@author chewyang
    /**
     * This method moves a activity from a given index and to a place below a given index.
     *
     * @param indexToBeMovedFrom     this is the index at which the activity will be moved from
     * @param indexToBeInsertedBelow this is the index at which the activity will be moved to below
     * @throws IndexOutOfBoundsException if the index is not within the limits
     */
    public void moveActivity(int indexToBeMovedFrom, int indexToBeInsertedBelow) throws IndexOutOfBoundsException,
            SameIndexForMoveCommandException {

        if (isValidIndexForMoveCommand(indexToBeMovedFrom) && isValidIndexForMoveCommand(indexToBeInsertedBelow)) {

            if (indexToBeInsertedBelow - indexToBeMovedFrom == 1) {
                throw new SameIndexForMoveCommandException();
            } else if (indexToBeMovedFrom > indexToBeInsertedBelow) {
                Activity activity = getActivity(indexToBeMovedFrom);
                activities.remove(indexToBeMovedFrom);
                activities.add(indexToBeInsertedBelow, activity);

            } else if (indexToBeMovedFrom < indexToBeInsertedBelow) {
                Activity activity = getActivity(indexToBeMovedFrom);
                activities.remove(indexToBeMovedFrom);
                activities.add(indexToBeInsertedBelow - 1, activity);
            } else {
                throw new SameIndexForMoveCommandException();
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }



    public int getNetCalorie() {
        return netCalorie;
    }


    public Activity getActivity(int index) throws IndexOutOfBoundsException {
        if (isValidIndex(index)) {
            return activities.get(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }


    //@@author 1-Karthigeyan-1
    /**
     * Removes an activity from the list via index.
     *
     * @param index index of activity in list
     */
    public void removeActivity(int index) throws IndexOutOfBoundsException {
        if (isValidIndex(index)) {
            Activity activityToRemove = activities.get(index);
            if (activityToRemove instanceof Food) {
                netCalorie -= activityToRemove.calories;
            } else if (activityToRemove instanceof Exercise) {
                netCalorie += activityToRemove.calories;
            }
            activities.remove(index);
            activityCounter--;
        } else {
            System.out.println("Please make sure index is within range");
            throw new IndexOutOfBoundsException();
        }
    }


    //@@author chewyang
    /**
     * Prints the list of activities.
     */
    public void printList() {
        if (activityCounter == 0) {
            displayEmptyActivityCounterMessage();
        } else {
            for (int i = 0; i < activityCounter; i++) {
                System.out.println((i + 1) + ". " + getActivity(i).toString());
            }
        }
    }


    /**
     * Checks if the index is valid for a move command.
     * This is added to fulfill edge cases.
     * @param index is the index to be checked for validity
     * @return
     */
    public boolean isValidIndexForMoveCommand(int index) {
        if ((index >= 0) && (index <= activityCounter)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the index is valid.
     *
     * @param index index of activity in list
     * @return true if index is within range, else false
     */
    public boolean isValidIndex(int index) {
        if ((index >= 0) && (index < activityCounter)) {
            return true;
        }
        return false;
    }

    //@@author 1-Karthigeyan-1
    /**
     * Clears the list of activities.
     */
    public void clearList() {
        activities.clear();
        activityCounter = 0;
        netCalorie = 0;
    }

    //@@author chewyang
    /**
     * Sets the activities as a string.
     * For e.g, [F] | apple | 50, [F] | banana | 100, [E] | push-up | 10, [E] | jogging | 60
     *
     * @return activities as a string
     */
    @Override
    public String toString() {
        String activitiesString = Arrays.toString(activities.toArray());
        activitiesString = activitiesString.substring(1, activitiesString.length() - 1);
        return (activitiesString);
    }
}
