package seedu.duke.model;

import java.time.LocalDate;
import java.util.ArrayList;

import static seedu.duke.Trakcal.logging;

//@@author 1-Karthigeyan-1
/**
 * Displays the graph of the net calorie gain/loss throughout the period.
 */
public class GraphDrawing {

    public GraphProperty graphProperty;

    private static final String BLANK_WIDTH = "   ";
    private static final String TARGET_WIDTH = "***";
    private static final String BAR_WIDTH = "| |";
    private static final String targetBarWidth = "|*|";
    private static final String TOP_BAR_WIDTH = "|-|";
    private static final String SPACE = " ";
    private static final String VERTICAL_LINE = "|";

    private String targetCaloriesString;
    private String maxCaloriesString;
    private String minCaloriesString;

    /**
     * Constructor.
     *
     * @param graphProperty properties of graph
     */
    public GraphDrawing(GraphProperty graphProperty) {
        this.graphProperty = graphProperty;
        setCalorieString();
    }

    private void setCalorieString() {
        this.targetCaloriesString = Integer.toString(graphProperty.targetCalories);
        this.maxCaloriesString = Integer.toString(graphProperty.maxCalories);
        this.minCaloriesString = Integer.toString(graphProperty.minCalories);
    }

    /**
     * Repeats character back to back.
     *
     * @param character character
     * @param size number of times to repeat
     * @return concatenated string
     */
    public String repeatCharacter(String character, int size) {
        assert size >= 0;
        String characterText = "";
        for (int i = 0; i < size; i++) {
            characterText += character;
        }
        return characterText;
    }

    /**
     * Generates the x_axis.
     *
     * @return x_axis String
     */
    public String generate_x_axis(int maxCalorieSize, int column) {
        String horizontalLine = "|-+";
        for (int i = 0; i < column - 1; i++) {
            horizontalLine += repeatCharacter("-", 5) + "+";
        }
        horizontalLine = repeatCharacter(SPACE, maxCalorieSize) + horizontalLine + "--\n";
        return horizontalLine;
    }

    /**
     * Generates date labels for the x-axis.
     *
     * @param maxCalorieSize Character length of maxCalorieSize
     * @return date labels
     */
    public String generateDateLabels(int maxCalorieSize,  ArrayList<LocalDate> keys) {
        logging.writeToLogInfo("Generating date labels for graph.");
        assert keys != null;
        assert maxCalorieSize >= 1;
        return repeatCharacter(SPACE, maxCalorieSize - 1) + SPACE + graphProperty.parseDate(keys);
    }

    /**
     * Generate vertical axis label.
     *
     * @param columnNumber column number
     * @param maxColumn maximum column
     * @param maxSize maximum size of labels in vertical axis
     * @return vertical axis label
     */
    private String generateVerticalAxisLabel(int columnNumber, int maxColumn, int maxSize) {
        logging.writeToLogInfo("Generating vertical axis for graph.");
        String label = "";
        if (columnNumber == maxColumn - 1) {
            label = this.maxCaloriesString + repeatCharacter(SPACE, maxSize - this.maxCaloriesString.length());
        } else if (columnNumber == graphProperty.targetRow) {
            label = this.targetCaloriesString
                    + repeatCharacter(SPACE, maxSize - this.targetCaloriesString.length());
        } else if (columnNumber == 0) {
            label = this.minCaloriesString
                    + repeatCharacter(SPACE, maxSize - this.minCaloriesString.length());
        } else {
            label = repeatCharacter(SPACE, maxSize);
        }
        logging.writeToLogInfo("Vertical axis generation complete.");
        return label;
    }

    /**
     * Calculate difference in string size.
     *
     * @param firstString first string
     * @param secondString second string
     * @return string length differemnce
     */
    public int calculateMaxSize(String firstString, String secondString) {
        if (firstString.length() > secondString.length()) {
            return firstString.length();
        } else {
            return secondString.length();
        }
    }

    /**
     * Adds width based on the row type.
     *
     * @param number row number
     * @return width
     */
    public String addWidth(int number) {
        String width = "";
        if (number == graphProperty.targetRow) {
            width += TARGET_WIDTH;
        } else {
            width += BLANK_WIDTH;
        }
        return width;
    }

    /**
     * Draws the graph.
     *
     * @return drawing
     */
    public String drawGraph() {
        int[][] table = graphProperty.table;
        ArrayList<LocalDate> keys = graphProperty.keys;
        assert table != null;
        assert keys != null;
        int maxSize = calculateMaxSize(maxCaloriesString, minCaloriesString);
        String drawing = "";


        for (int i = GraphProperty.COLUMN - 1; i >= 0; i--) {
            drawing += generateVerticalAxisLabel(i, GraphProperty.COLUMN, maxSize) + VERTICAL_LINE;
            for (int j = 0; j < graphProperty.row; j++) {
                drawing += generateHorizontalLine(table, i, j);
            }
            drawing += "\n";
        }
        drawing += generate_x_axis(maxSize, graphProperty.row);
        drawing += generateDateLabels(maxSize, keys);
        logging.writeToLogInfo("Graph drawn successfully");
        return drawing;
    }

    /**
     * Generates horizontal line.
     *
     * @param table table for graph
     * @param column current column number
     * @param row current rpw number
     * @return horizontl line
     */
    private String generateHorizontalLine(int[][] table, int column, int row) {
        String drawing = "";
        switch (table[column][row]) {
        case 0:
            drawing += BLANK_WIDTH;
            break;
        case 1:
            drawing += BAR_WIDTH;
            break;
        case 2:
            drawing += TARGET_WIDTH;
            break;
        case 3:
            drawing += targetBarWidth;
            break;
        case 4:
            drawing += TOP_BAR_WIDTH;
            break;
        default:
            //does nothing
            break;
        }
        drawing += addWidth(column);
        return drawing;
    }

}
