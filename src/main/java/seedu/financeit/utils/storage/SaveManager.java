package seedu.financeit.utils.storage;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.TablePrinter;
import seedu.financeit.ui.UiManager;

public class SaveManager {
    private static String prompt = "";

    public static void main() {

        while (true) {
            printMenu();
            InputParser parser = InputParser.getInstance();
            CommandPacket packet = parser.parseInput(UiManager.handleInput("Please no echo"));
            switch (packet.getCommandString()) {
            case "list":
                listSaves(packet);
                break;
            case "add":
                addSave(packet);
                break;
            case "load":
                loadSave(packet);
                break;
            case "exit":
                return;
            default:
                prompt = "Invalid Command";
            }
        }
    }

    private static void printMenu() {
        UiManager.refreshPage();
        status();
        TablePrinter.setTitle("Welcome to Save Manager");
        TablePrinter.addRow("No.; Commands                                           ; Syntax                    ");
        TablePrinter.addRow("[1]; List local saves; list");
        TablePrinter.addRow("[2]; Add save; add /name");
        TablePrinter.addRow("[3]; Load save; load /name");
        TablePrinter.addRow("[4]; Quit to main; exit");
        TablePrinter.printList();
    }

    private static void status() {
        System.out.println("Status: " + prompt);
        prompt = "";
    }

    private static void listSaves(CommandPacket packet) {

    }

    private static void addSave(CommandPacket packet) {

    }

    private static void loadSave(CommandPacket packet) {

    }
}
