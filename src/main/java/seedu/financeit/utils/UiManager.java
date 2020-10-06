package seedu.financeit.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UiManager {
    private static Scanner scanner = new Scanner(System.in);
    private static InputParser inputParser = new InputParser();

    public static String getLineWithSymbol(int width, String symbol) {
        return new String(new char[width]).replace("\0", symbol);
    }

    public static void printLineWithSymbol(int width, String symbol) {
        System.out.println(getLineWithSymbol(width, symbol));
    }

    public static void drawPartition() {
        printLineWithSymbol(Constants.MAX_PARTITION_LINE_LEN, "_");
    }

    public static String getPrintListRow(String[] input) {
        ArrayList<String> buffer = new ArrayList<>();
        Arrays.stream(input)
                .forEach(s -> buffer.add(getPrintFormat(s)));
        return String.join("", buffer);
    }

    public static String getPrintFormat(String s) {
        return String.format("| %-15s", s);
    }

    public static void printRowHeader(String[] row) {
        String header = getPrintListRow(row);
        System.out.println(header);
        System.out.println(new String(new char[header.length()]).replace("", "-"));
    }

    public static void printInputPrompt() {
        drawPartition();
        System.out.print(">>> ");
    }

    public static CommandPacket handleInput() {
        UiManager.printInputPrompt();
        String input = scanner.nextLine();
        return inputParser.parseInput(input.toLowerCase());
    }
}
