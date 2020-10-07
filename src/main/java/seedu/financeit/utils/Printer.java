package seedu.financeit.utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Printer {
    private static ArrayList<String> listContents = new ArrayList<>();
    private static String title = "";
    private static final int DEFAULT_COL_WIDTH = 15;
    private static int[] colWidth;

    public static String formatTitle(String input) {
        String output = UiManager.getLineWithSymbol(input.length() + 4, "=");
        output += "\n" + String.format("= %s =", input);
        output += "\n" + UiManager.getLineWithSymbol(input.length() + 4, "=");
        return output;
    }

    public static String formatTitle(String input, int width) {
        String output = UiManager.getLineWithSymbol(width, "=");
        output += "\n" + String.format("= %s %s=", input, UiManager.getLineWithSymbol(width - 4 - input.length(), " "));
        output += "\n" + UiManager.getLineWithSymbol(width, "=");
        return output;
    }


    public static void setTitle(String input) {
        title = input;
    }

    public static void addRow(String row) {
        listContents.add(row);
    }

    public static void printRow(String[] row) {
        System.out.println(getPrintListRow(row) + "|");
    }

    public static void printList(String[][] input) {
        String[] header = input[0];
        printRowHeader(header);
        printHorizontalPartition(getHeaderRowWidth(header));
        for (int i = 1; i < input.length; i++) {
            printRow(input[1]);
        }
        printHorizontalPartition(getHeaderRowWidth(header));
    }

    /**
     * Main table printing function
     * @param input
     */
    public static void printList(ArrayList<String> input) {
        String[] header = input.get(0).split(";");
        //Set the width of the column based on the length of column header
        setColWidth(header);
        System.out.println(formatTitle(title, getHeaderRowWidth(header)));

        printRowHeader(header);
        printHorizontalPartition(getHeaderRowWidth(header));
        for (int i = 1; i < input.size(); i++) {
            String[] buffer = input.get(i).split(";");
            printRow(buffer);
        }
        printHorizontalPartition(getHeaderRowWidth(header));
    }

    public static void printList() {
        printList(listContents);
        listContents.clear();
    }

    public static int getColWidth(int length) {
        return length > DEFAULT_COL_WIDTH ? length : DEFAULT_COL_WIDTH;
    }

//    public static String getPrintListRow(String[] input) {
//        ArrayList<String> buffer = new ArrayList<>();
//        Arrays.stream(input)
//                .forEach(s -> buffer.add(getPrintFormat(s, getColWidth(s))));
//        return String.join("", buffer);
//    }

    public static ArrayList<String> adjustToColWidth(String input, int length) {
        ArrayList<String> output = new ArrayList<>();
        Pattern pattern = Pattern.compile(String.format(".{%s}|.{1,}$", length));
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            output.add(matcher.group());
        }
        return output;
    }

    /**
     * Handles printing of each row for all columns
     * @param input
     */
    public static String getPrintListRow(String[] input) {
        int maxLines = 0;
        String output = "";
        ArrayList<String> buffer = new ArrayList<>();
        String entry = "";
        ArrayList<String>[] token = new ArrayList[input.length];

        // Determine how many lines needed for each column, and also segmenting
        // the contents of each box to each line.
        // This allows for warping of lines that would normally overflow from
        // the default column width.
        for (int col = 0; col < input.length; col++) {
            token[col] = adjustToColWidth(input[col], getColWidth(colWidth[col]));
            if (token[col].size() > maxLines) {
                maxLines = token[col].size();
            }
        }

        for (int line = 0; line < maxLines; line++) {
            for (int col = 0; col < input.length; col++) {
                // If the content of the box is fully printed,
                // no need to access it anymore
                if (line > token[col].size() - 1) {
                    continue;
                } else {
                    entry = getPrintFormat(token[col].get(line), getColWidth(colWidth[line]));
                    buffer.add(entry);
                }
            }
            output += String.join("", buffer) + "\n";
        }

        /*while(!endLine) {
            for (int i = 0; i < input.length; i++) {
                entry = getPrintFormat(input[i], getColWidth(colWidth[i]));
                isEndLine[i] = input[i].length() <= colWidth[i];

                buffer.add(adjustToColWidth(entry));
            }
            output += String.join("", buffer) + "\n";
        }*/
        return output;
    }

    public static String getPrintFormat(String s, int width) {
        String format = "| %-" + width + "s";
        return String.format(format, s);
    }

    public static void printRowHeader(String[] row) {

        String header = getPrintListRow(row);
        System.out.println(header + "|");
    }

    public static void printHorizontalPartition(int rowWidth) {
        System.out.println(UiManager.getLineWithSymbol(rowWidth, "-"));
    }

    public static void setColWidth(String[] row){
        colWidth = new int[row.length];
        for (int i = 0; i < row.length; i++) {
            colWidth[i] = row[i].length();
        }
    }

    public static int getHeaderRowWidth(String[] row){
        String header = getPrintListRow(row);
        return header.length();
    }
}
