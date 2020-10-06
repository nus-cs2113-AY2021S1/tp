package seedu.financeit.utils;

import java.util.ArrayList;

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
        System.out.println(getPrintListRow(row).replaceFirst("(|)", "") + "|");
    }

    public static void printList(String[][] input) {
        String[] header = input[0];
        printRowHeader(header);
        printHorizontalPartition(getRowWidth(header));
        for (int i = 1; i < input.length; i++) {
            printRow(input[1]);
        }
        printHorizontalPartition(getRowWidth(header));
    }

    public static void printList(ArrayList<String> input) {
        String[] header = input.get(0).split(";");
        setColWidth(header);

        System.out.println("\n");
        System.out.println(formatTitle(title, getRowWidth(header)));
        printRowHeader(header);
        printHorizontalPartition(getRowWidth(header));
        for (int i = 1; i < input.size(); i++) {
            String[] buffer = input.get(i).split(";");
            printRow(buffer);
        }
        printHorizontalPartition(getRowWidth(header));
        System.out.println("\n");
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

    public static String getPrintListRow(String[] input) {
        ArrayList<String> buffer = new ArrayList<>();
        String entry = "";
        for (int i = 0 ; i < input.length; i++) {
            entry = getPrintFormat(input[i], getColWidth(colWidth[i]));
            buffer.add(entry);
        }
        return String.join("", buffer);
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

    public static int getRowWidth (String[] row){
        String header = getPrintListRow(row);
        return header.length();
    }
}
