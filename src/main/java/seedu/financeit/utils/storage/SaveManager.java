package seedu.financeit.utils.storage;

import seedu.financeit.Financeit;
import seedu.financeit.common.CommandPacket;
import seedu.financeit.datatrackers.entrytracker.EntryTracker;
import seedu.financeit.datatrackers.manualtracker.ManualTracker;
import seedu.financeit.datatrackers.recurringtracker.RecurringTracker;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.TablePrinter;
import seedu.financeit.ui.UiManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


public class SaveManager {
    public static final String fullPath = "./data/backup/names.txt";
    public static final String dirPath = "./data/backup";
    private static String prompt = "";
    private static boolean menu = true;

    public static void main() {
        while (true) {
            if (menu == true) {
                helpMenu();
            }
            InputParser parser = InputParser.getInstance();
            CommandPacket packet = parser.parseInput(UiManager.handleInput());
            menu = true;
            switch (packet.getCommandString()) {
            case "list":
                listSaves(packet);
                break;
            case "add":
                if (checkValidity(packet) == true) {
                    addSave(packet);
                }
                break;
            case "load":
                if (checkValidity(packet) == true) {
                    loadSave(packet);
                }
                break;
            case "reset":
                resetSave();
                break;
            case "delete":
                if (checkValidity(packet) == true) {
                    deleteSave(packet);
                }
                break;
            case "help":
                menu = true;
                break;
            case "exit":
                return;
            default:
                prompt = "Invalid Command";
            }
        }
    }

    private static boolean checkValidity(CommandPacket packet) {
        if (packet.getParam("/name") == null) {
            prompt = "Invalid Command";
            return false;
        }
        return true;
    }

    private static void helpMenu() {
        UiManager.refreshPage();
        status();
        TablePrinter.setTitle("Welcome to Save Manager");
        TablePrinter.addRow("No.; Commands                                           ; Syntax                    ");
        TablePrinter.addRow("[1]; List local saves; list");
        TablePrinter.addRow("[2]; Add/Overwrite save; add /name");
        TablePrinter.addRow("[3]; Load save; load /name");
        TablePrinter.addRow("[4]; Delete save; delete /name");
        TablePrinter.addRow("[5]; Reset program; reset");
        TablePrinter.addRow("[5]; Quit to main; exit");
        TablePrinter.printList();
    }

    private static void status() {
        System.out.println("Status: " + prompt);
        prompt = "";
    }

    private static void helpMsg() {
        System.out.println("Enter <help> for a list of commands");
    }

    private static void resetSave() {
        GoalTrackerSaver.clear();
        AutoTrackerSaver.clear();
        ManualTrackerSaver.clear();
        prompt = "Program has been reset";
    }

    public static void resetAllLists() {
        ManualTracker.getLedgerList().removeAllItems();
        EntryTracker.getEntryList().removeAllItems();
        RecurringTracker.getRecurringEntryList().removeAllItems();
    }

    private static void listSaves(CommandPacket packet) {
        try {
            menu = false;
            UiManager.refreshPage();
            helpMsg();
            TablePrinter.setTitle("List Saves");
            TablePrinter.addRow("No.; Names                                           ");
            SaveHandler.buildFile(dirPath, fullPath);
            File file = new File(fullPath);
            Scanner scanner = new Scanner(file);
            for (int i = 1; scanner.hasNext(); ) {
                String name = scanner.nextLine();
                if (!name.equals("")) {
                    TablePrinter.addRow("[" + i + "]; " + name);
                    i++;
                }
            }
            TablePrinter.printList();
        } catch (Exception e) {
            menu = true;
            prompt = e.toString();
        }
    }

    public static void addSave(CommandPacket packet) {
        try {
            String name = packet.getParam("/name");
            String path = dirPath + "/" + name;
            GoalTrackerSaver.getInstance().save(dirPath, path + "_gt.txt");
            ManualTrackerSaver.getInstance().save(dirPath, path + "_mt.txt");
            AutoTrackerSaver.getInstance().save(dirPath, path + "_at.txt");
            SaveHandler.buildFile(dirPath, fullPath);
            File file = new File(fullPath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String saveString = scanner.nextLine();
                if (saveString.equals(name)) {
                    prompt = name + " has been overwritten!";
                    return;
                }
            }
            FileWriter fileWriter = new FileWriter(fullPath, true);
            fileWriter.write("\n" + name);
            fileWriter.close();
            prompt = name + " has been added!";

        } catch (Exception e) {
            prompt = e.toString();
        }
    }

    public static void loadSave(CommandPacket packet) {
        try {
            String name = packet.getParam("/name");
            String path = dirPath + "/" + name;
            String desAuto = AutoTrackerSaver.getInstance().fullPath;
            String desGoal = GoalTrackerSaver.getInstance().fullPath;
            String desManual = ManualTrackerSaver.getInstance().fullPath;
            AutoTrackerSaver.getInstance().buildFile();
            GoalTrackerSaver.getInstance().buildFile();
            ManualTrackerSaver.getInstance().buildFile();
            File file = new File(fullPath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String saveString = scanner.nextLine();
                if (saveString.equals(name)) {
                    FileChannel sourceChannel = new FileInputStream(path + "_gt.txt").getChannel();
                    FileChannel destChannel = new FileOutputStream(desGoal).getChannel();
                    destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());

                    sourceChannel = new FileInputStream(path + "_mt.txt").getChannel();
                    destChannel = new FileOutputStream(desManual).getChannel();
                    destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());

                    sourceChannel = new FileInputStream(path + "_at.txt").getChannel();
                    destChannel = new FileOutputStream(desAuto).getChannel();
                    destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());

                    sourceChannel.close();
                    destChannel.close();
                    clear();
                    Financeit.load();
                    prompt = name + " has been loaded!";
                    return;
                }
            }
            prompt = name + " cannot be found!";
        } catch (Exception e) {
            prompt = e.toString();
        }
    }

    public static void clear() {
        GoalTrackerSaver.clear();
        AutoTrackerSaver.clear();
        ManualTrackerSaver.clear();
    }

    public static void deleteSave(CommandPacket packet) {
        try {
            String name = packet.getParam("/name");
            StringBuilder nameList = new StringBuilder();
            File file = new File(fullPath);
            Scanner scanner = new Scanner(file);
            prompt = name + " is not found!";
            while (scanner.hasNext()) {
                String saveString = scanner.nextLine();
                if (!saveString.equals(name)) {
                    nameList.append(saveString + "\n");
                } else {
                    String path = dirPath + "/" + name;
                    Files.deleteIfExists(Paths.get(path + "_gt.txt"));
                    Files.deleteIfExists(Paths.get(path + "_mt.txt"));
                    Files.deleteIfExists(Paths.get(path + "_at.txt"));
                    prompt = name + " has been removed!";
                }
            }
            FileWriter fileWriter = new FileWriter(fullPath, false);
            fileWriter.write(nameList.toString());
            fileWriter.close();

        } catch (Exception e) {
            prompt = e.toString();
        }
    }
}
