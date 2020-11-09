package ui;

import cheatsheet.CheatSheetList;
import command.Command;
import editor.Editor;
import exception.CommandException;
import parser.Parser;
import settings.Settings;
import storage.DataFileReader;
import storage.DataFileWriter;
import storage.DataFileDestroyer;
import org.fusesource.jansi.AnsiConsole;

import java.io.IOException;

// This class manages the overall program flow of CheatLogs.
public class UserSession {
    /*
     * These are objects that will be injected to command subclasses
     * that allow them to execute.
     */
    CheatSheetList cheatSheetList;
    DataFileReader fileReader;
    DataFileWriter fileWriter;
    DataFileDestroyer fileDestroyer;
    Editor editor;
    Parser userCommandParser;
    Printer printer;
    Ui ui;
    Settings settings;

    public boolean isEditorEnabled;
    public boolean isFirstRun;

    /**
     * Constructor for the UserSession. Creates a single instance of all
     * common objects that are passed to the commands.
     */
    public UserSession() {
        cheatSheetList = new CheatSheetList();
        editor = new Editor();
        ui = new Ui();
        printer = new Printer();
        settings = new Settings(printer);
        fileReader = new DataFileReader(settings, printer, cheatSheetList);
        fileWriter = new DataFileWriter(settings, printer, cheatSheetList);
        fileDestroyer = new DataFileDestroyer(printer, cheatSheetList);
        userCommandParser = new Parser(cheatSheetList, editor, fileDestroyer, printer, ui, settings, fileReader);

        isEditorEnabled = true;
        isFirstRun = false;
    }

    /**
     * Initializes CheatLogs and greets the user.
     */
    public void start() {
        try {
            AnsiConsole.systemInstall();
        } catch (Exception e) {
            printer.print(e.getMessage());
        }
        if (isEditorEnabled) {
            editor.init();
        }
        if (isFirstRun) {
            fileReader.extractPreloadedCheatSheets();
        }

        fileReader.executeFunction();
        printer.printWelcomeScreen();
        if (settings.getDisplayingHelpMessages()) {
            printer.printStartHelpMessage();
        }
    }

    /**
     * A continuous loop that runs as long as this session is running.
     * Asks for user input which is parsed to generate a command object.
     * These command objects are then executed.
     * Any exceptions throw are handled.
     * This process loops till the command specifies to exit.
     */
    public void runProgramSequence() {
        do {
            printer.printUserInputPrompt();

            String userInput = ui.getUserInput();
            try {
                Command parsedUserCommand = userCommandParser.parse(userInput);
                parsedUserCommand.execute();

                if (parsedUserCommand.isExitCommand) {
                    return;
                }
            } catch (CommandException | InterruptedException | IOException c) {
                printer.print(c.getMessage());
                continue;
            }
            fileWriter.executeFunction();
        } while (true);
    }

    /**
     * Exits CheatLogs by closing certain objects and printing the exit logo.
     */
    public void exit() {
        ui.closeScanner();
        editor.dispose();
        printer.printExitLogo();
        AnsiConsole.systemUninstall();
    }
}
