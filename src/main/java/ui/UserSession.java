package ui;

import cheatsheet.CheatSheetList;
import command.Command;
import editor.Editor;
import exception.CommandException;
import parser.Parser;
import storage.DataFileReader;
import storage.DataFileWriter;
import storage.DataFileDestroyer;

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

    public UserSession() {
        cheatSheetList = new CheatSheetList();
        editor = new Editor();
        ui = new Ui();
        printer = new Printer();
        fileReader = new DataFileReader(printer, cheatSheetList);
        fileWriter = new DataFileWriter(printer, cheatSheetList);
        fileDestroyer = new DataFileDestroyer(printer);
        userCommandParser = new Parser(cheatSheetList, editor, fileDestroyer, printer, ui);
    }

    public void runProgramSequence() {
        fileReader.executeFunction();
        printer.printWelcomeScreen();

        // Ask for new user input and executes it until user types the exit command
        do {
            printer.printUserInputPrompt();
            String userInput = ui.getUserInput();
            try {
                Command parsedUserCommand = userCommandParser.parse(userInput);
                parsedUserCommand.execute();
                if (parsedUserCommand.isExitCommand) {
                    return;
                }
            } catch (CommandException c) {
                printer.print(c.getMessage());
                continue;
            }
            fileWriter. executeFunction();
        } while (true);
    }

    public void exit() {
        ui.closeScanner();
        printer.printExitLogo();
    }
}
