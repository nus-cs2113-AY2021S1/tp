package seedu.financeit.ui;

import seedu.financeit.utils.RegexMatcher;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class TablePrinter {
    private static ArrayList<String> listContents = new ArrayList<>();
    private static String title = "";
    private static final int DEFAULT_COL_WIDTH = 15;
    private static int[] colWidth;
    private static int pad = 0;

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

    /**
     * Main table printing function.
     * @param input Input rows to print table contents.
     */
    public static void printList(ArrayList<String> input) {
        String[] header = input.get(0).split(";");
        //Set the width of the column based on the length of column header
        setColWidth(header);
        System.out.println(formatTitle(title, getHeaderRowWidth(header)));

        printRowHeader(header);
        printHorizontalHeaderPartition(getHeaderRowWidth(header));
        for (int i = 1; i < input.size(); i++) {
            String[] buffer = input.get(i).split(";");
            printRow(buffer);
            printHorizontalPartition(getHeaderRowWidth(header));
        }
    }

    public static void printList() {
        printList(listContents);
        listContents.clear();
        title = "";
    }

    public static int getColWidth(int length) {
        return length > DEFAULT_COL_WIDTH ? length : DEFAULT_COL_WIDTH;
    }

    public static ArrayList<String> adjustWordToColWidth(String rawInput, int maxLength) {
        maxLength = maxLength - 1;
        ArrayList<String> output = new ArrayList<>();
        String[] inputs = rawInput.split("[>]");
        for (String input: inputs) {
            Matcher matcher = RegexMatcher.regexMatcher(input, String.format(".{%s}|.{1,}$", maxLength));
            while (matcher.find()) {
                output.add(matcher.group());
            }
        }
        return output;
    }

    public static ArrayList<String> adjustContentToColWidth(String rawInput, int maxLength) {
        ArrayList<String> output = new ArrayList<>();
        String[] inputs = rawInput.split("[>]");
        String[] buffer;
        String feed = "";
        boolean hasParsedLongWord;
        for (String input: inputs) {
            // Break down input line into word tokens
            buffer = input.split(" ");
            int scannedWordCount = 0;
            // While the input line is not fully visited
            while (scannedWordCount + 1  <= buffer.length) {
                hasParsedLongWord = false;
                feed = "";
                // Acquire segment of buffer right before line feed exceeds the char limit
                if (buffer[scannedWordCount].length() > maxLength) {
                    //System.out.println("if: " + buffer[scannedWordCount]);
                    ArrayList<String> tokens = adjustWordToColWidth(buffer[scannedWordCount], maxLength);
                    for (int i = 0; i < tokens.size(); i++) {
                        feed = tokens.get(i);
                        if (i < tokens.size() - 1) {
                            feed += "-";
                        }
                        output.add(feed);
                    }
                    scannedWordCount++;
                } else {
                    //System.out.println("else: " + buffer[scannedWordCount]);
                    do {
                        feed += buffer[scannedWordCount] + " ";
                        scannedWordCount++;
                    } while ((scannedWordCount < buffer.length)
                        && (feed.length() + 1 + buffer[scannedWordCount].length() < maxLength - pad));
                    output.add(feed);
                }
            }
        }
        return output;
    }

    /**
     * Handles printing of each row for all columns.
     * @param input Input table row to print.
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
            token[col] = adjustContentToColWidth(input[col], getColWidth(colWidth[col]));
            if (token[col].size() > maxLines) {
                maxLines = token[col].size();
            }
        }

        for (int line = 0; line < maxLines; line++) {
            buffer.clear();
            for (int col = 0; col < input.length; col++) {
                // If the content of the box is fully printed,
                // no need to access it anymore
                if (line + 1 > token[col].size()) {
                    entry = getPrintFormat(" ", getColWidth(colWidth[col]));
                } else {
                    entry = getPrintFormat(token[col].get(line), getColWidth(colWidth[col]));
                }
                buffer.add(entry);
            }
            output += String.join("", buffer);
            output += (line < maxLines - 1) ? "|\n" : "";
        }
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
        System.out.println(UiManager.getLineWithSymbol(rowWidth / 2, " -"));
    }

    public static void printHorizontalHeaderPartition(int rowWidth) {
        System.out.println(UiManager.getLineWithSymbol(rowWidth, "-"));
    }

    public static void setColWidth(String[] row) {
        colWidth = new int[row.length];
        for (int i = 0; i < row.length; i++) {
            colWidth[i] = row[i].length();
        }
    }

    public static int getHeaderRowWidth(String[] row) {
        String header = getPrintListRow(row);
        String[] output = header.split("\\n");
        return output[0].length();
    }
}
