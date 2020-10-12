package seedu.financeit.ui;

import seedu.financeit.common.Constants;
import seedu.financeit.parser.InputParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UiManager {
    private static Scanner scanner = new Scanner(System.in);
    private static InputParser inputParser = new InputParser();

    public static String getLineWithSymbol(int width, String symbol) {
        // return new String(new char[width]).replace("\0", symbol);
        return symbol.repeat(width);
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

    public static String handleInput() {
        return handleInput("echo");
    }

    public static String handleInput(String mode) {
        UiManager.printInputPrompt();
        String input = scanner.nextLine();
        if (mode.equals("echo")) {
            System.out.println(input);
        }
        return input.toLowerCase();
    }

    public static void printWithStatusIcon(Constants.PrintType printType, String... messages) {
        for (int i = 0; i < messages.length; i++) {
            if (i == 0) {
                System.out.println(String.format("[ %-15s]: %s", printType, messages[i]));
            } else {
                System.out.println(String.format("%-20s%s", " ", messages[i]));
            }
        }
    }

    public static void printInputPromptMessage() {
        printWithStatusIcon(Constants.PrintType.INSTRUCTION,
                "Enter a command! ",
                "Input \"commands\" for list of commands."
        );
    }

    public static void printSpace() {
        System.out.println("\n");
    }

    public static void refreshPage() {
        for (int i = 0; i < 4; i++) {
            System.out.println();
        }
    }
}
