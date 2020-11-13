package seedu.duke.model;

import seedu.duke.command.GraphCommand;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import static seedu.duke.Trakcal.logging;

//@@author 1-Karthigeyan-1
/**
 * Properties of graph.
 */
public class GraphProperty {
    public static final int COLUMN = 11;
    private static String DATE_FORMAT = "dd/MM";
    private static int TARGET_TYPE = 2;
    private static int LIMIT_TYPE = 4;
    private static int DIVISOR = 10;
    private static int EMPTY = 0;
    public int targetRow;
    public int row;
    public final DayMap dayMap;
    ArrayList<LocalDate> keys;
    public int targetCalories;
    public int maxCalories;
    public int minCalories;
    public int[][] table;

    /**
     * Constructor for the graph.
     *
     * @param dayMap hashmap containing dates
     * @param targetCalories tagret calories from userprofile
     */
    public GraphProperty(DayMap dayMap, int targetCalories) {
        assert dayMap != null;
        this.dayMap = dayMap;
        this.targetCalories = targetCalories;

    }

    /**
     * Maximum days in graph is 7 or lower.
     *
     * @return number of days to be shown in graph
     */
    private int checkSize() {
        int size = dayMap.getHashMap().size();
        assert size != 0;
        logging.writeToLogInfo("Checking number of days for graph.");
        if (size < GraphCommand.MAXIMUM_DAYS) {
            return size;
        }
        return GraphCommand.MAXIMUM_DAYS;
    }

    /**
     * Set other properties by calculation.
     */
    public void setProperties() {
        this.row = checkSize();
        this.keys = sortKeys();
        ArrayList<Integer> calories = getCalories();
        this.table = initiateTable(calories);
        logging.writeToLogInfo("Properties has been set.");
    }

    /**
     * Initiates a 2 dimension table and fills the table with 0.
     */
    public int[][] setEmptyTable(int[][] table) {
        for (int[] row : table) {
            Arrays.fill(row, EMPTY);
        }
        logging.writeToLogInfo("Empty table set!");
        return table;
    }

    /**
     * Get and sort keys from hashmap.
     *
     * @return sorted keys in arraylist
     */
    public ArrayList<LocalDate> sortKeys() {
        ArrayList<LocalDate> keys = new ArrayList<>();
        for (LocalDate key : dayMap.getHashMap().keySet()) {
            keys.add(key);
        }
        //sort the keys by date
        keys.sort(LocalDate::compareTo);
        ArrayList<LocalDate> newKeys = new ArrayList<>();
        for (int i = keys.size() - row; i < keys.size(); i++) {
            newKeys.add(keys.get(i));
        }
        logging.writeToLogInfo("Keys sorted successfully.");
        return newKeys;
    }

    /**
     * Get calories from the dates.
     *
     * @return array of calories
     */
    public ArrayList<Integer> getCalories() {
        int currentCalories;
        int minCalories = targetCalories;
        int maxCalories = targetCalories;
        ArrayList<Integer> calories = new ArrayList<>();
        for (LocalDate date : keys) {
            currentCalories = dayMap.getNetCalorieOfDay(date);
            calories.add(currentCalories);
            maxCalories = findMaximum(maxCalories, currentCalories);
            minCalories = findMinimum(minCalories, currentCalories);
        }
        checkBoundaries(minCalories, maxCalories);
        return calories;
    }

    public void checkBoundaries(int minCalories, int maxCalories) {
        assert maxCalories != 0;
        if (maxCalories - minCalories < DIVISOR) {
            minCalories -= DIVISOR / 2;
            maxCalories += DIVISOR / 2;
        }
        this.minCalories = minCalories;
        this.maxCalories = maxCalories;
        logging.writeToLogInfo("Graph boundaries set!.");

    }

    /**
     * Compares and finds the minimum between 2 numbers.
     *
     * @param firstNumber first number to compare
     * @param secondNumber second number to compare
     * @return the lesser number
     */
    public int findMinimum(int firstNumber, int secondNumber) {
        if (firstNumber < secondNumber) {
            return firstNumber;
        } else {
            return secondNumber;
        }
    }

    /**
     * Compares and finds the maximum between 2 numbers.
     *
     * @param firstNumber first number to compare
     * @param secondNumber second number to compare
     * @return the greater number
     */
    public int findMaximum(int firstNumber, int secondNumber) {
        if (firstNumber < secondNumber) {
            return secondNumber;
        }
        return firstNumber;
    }

    /**
     * Calculates interval of the graph.
     *
     * @return interval value
     */
    public int calculateInterval() {
        assert maxCalories - minCalories != 0;
        return (maxCalories - minCalories) / DIVISOR;
    }


    /**
     * Fills up the table with appropriate values.
     * 0 -> empty spaces.
     * 1 -> middle portion of the bar.
     * 2 -> target row.
     * 3 -> target row  + middle portion of the bar.
     * 4 -> top symbol of the bar.
     *
     * @param table representation of graph in 2d array.
     * @param calories calories list
     */
    public void fillTable(int[][] table, ArrayList<Integer> calories) {
        assert table != null;
        assert calories != null;

        this.targetRow = calculateRowNumber(targetCalories);
        int limitNumber;
        for (int i = 0; i <= COLUMN - 1; i++) {
            for (int j = 0; j < row; j++) {
                limitNumber = calculateRowNumber(calories.get(j));
                setTable(table, limitNumber, i, j);
            }
        }
        logging.writeToLogInfo("2D Array table filled.");
    }

    /**
     * sets graph in table format.
     *
     * @param table table
     * @param limitNumber top limit for the calories
     * @param column current column number
     * @param row cuttent row number
     */
    private void setTable(int[][] table, int limitNumber, int column, int row) {
        if (limitNumber == column) {
            table[column][row] = LIMIT_TYPE;
        } else if (targetRow == column) {
            table[column][row] = TARGET_TYPE;
        }
        if (limitNumber > column) {
            table[column][row]++;
        }
    }

    /**
     * Parses the date into string.
     *
     * @return date in dd/MM format
     */
    public String parseDate(ArrayList<LocalDate> keys) {
        assert keys != null;
        String formattedDate = "";
        for (LocalDate key : keys) {
            formattedDate += key.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
            formattedDate += " ";
        }
        return formattedDate;
    }

    /**
     * Initiates a table.
     *
     * @return table
     */
    public int[][] initiateTable(ArrayList<Integer> calories) {
        assert calories != null;
        int [][]table = new int[COLUMN][row];
        setEmptyTable(table);
        fillTable(table, calories);
        return table;
    }

    /**
     * Find the row number corresponding to the calories.
     */
    public int calculateRowNumber(int calories) {
        assert calories >= minCalories;
        int interval = calculateInterval();
        assert interval != 0;
        return (calories - minCalories) / interval;
    }

}
