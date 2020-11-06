package seedu.financeit.ui;

import seedu.financeit.common.Common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UiManager {
    private static Scanner scanner = new Scanner(System.in);

    public static String getLineWithSymbol(int width, String symbol) {
        // return new String(new char[width]).replace("\0", symbol);
        return symbol.repeat(width);
    }

    public static void printLineWithSymbol(int width, String symbol) {
        System.out.println(getLineWithSymbol(width, symbol));
    }

    public static void drawPartition() {
        printLineWithSymbol(Common.MAX_PARTITION_LINE_LEN, "_");
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
        return input;
    }

    public static void printWithStatusIcon(Common.PrintType printType, String... messages) {
        for (int i = 0; i < messages.length; i++) {
            if (i == 0) {
                System.out.println(String.format("[ %-15s]: %s", printType, messages[i]));
            } else {
                System.out.println(String.format("%-20s%s", " ", messages[i]));
            }
        }
    }

    public static String getStringPrintWithStatusIcon(Common.PrintType printType, String... messages) {
        String message = "";
        for (int i = 0; i < messages.length; i++) {
            if (i == 0) {
                message += String.format("[ %-15s]: %s\n", printType, messages[i]);
            } else {
                message += String.format("%-20s%s\n", " ", messages[i]);
            }
        }
        return message;
    }

    public static void printInputPromptMessage() {
        printWithStatusIcon(Common.PrintType.INSTRUCTION,
                "Enter a command! ",
                "Input \"commands\" for list of commands."
        );
    }

    public static void refreshPage() {
        for (int i = 0; i < 2; i++) {
            System.out.println();
        }
    }

    public static void printLogo() {
        String logo =
            "   ||====================================================================||\n"
                + "   ||||$||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||$||||\n"
                + "   ||(100)==================|     Welcome to       |================(100)||\n"
                + "   ||||$||        ~         '------========--------'                ||$||||\n"
                + "   ||<< |        |$|              || ____ ||                         | >>||\n"
                + "   ||>>|  12    ||L||            || ///..) ||         L38036133B   12 |<<||\n"
                + "   ||<<|        || ||           || <||  >)  ||                        |>>||\n"
                + "   ||>>|         |$|            ||  $$ --)  ||        One Hundred     |<<||\n"
                + "||====================================================================||>||\n"
                + "||||$||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||$||||>||\n"
                + "||(100)==================|     FinanceIt!       |================(100)||>||\n"
                + "||||$||        ~         '------========--------'                ||$||||>||\n"
                + "||<< |        |$|              || ____ ||                         | >>||)||\n"
                + "||>>|  12    ||L||            || ///..) ||         L38036133B   12 |<<||/||\n"
                + "||<<|        || ||           || <||  >)  ||                        |>>||=||\n"
                + "||>>|         |$|            ||  $$ --)  ||        One Hundred     |<<||\n"
                + "||<<|      L38036133B        *||  |(_)  ||* series                 |>>||\n"
                + "||>>|  12                     *||(___)_||*   1989                  |<<||\n"
                + "||<<|      Treasurer     ______(  V2.0   )________     Secretary 12 |>>||\n"
                + "||||$|                 ~| A finanace tracking app |~               |$||||\n"
                + "||(100)===================  ONE HUNDRED DOLLARS =================(100)||\n"
                + "||||$||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||$//||\n"
                + "||====================================================================||\n";
        System.out.print(logo);
    }
}
