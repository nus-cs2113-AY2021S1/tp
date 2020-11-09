package athena;

import athena.commands.Command;
import athena.exceptions.command.CommandException;
import athena.ui.AthenaUi;
import athena.exceptions.storage.StorageException;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The main class of the ATHENA program.
 */
public class Athena {
    private AthenaUi athenaUi;
    private Storage storage;
    private TaskList taskList;
    private TimeAllocator timeAllocator;
    private Parser parser;

    /**
     * Creates an ATHENA object.
     */
    public Athena() {
        athenaUi = new AthenaUi();
        parser = new Parser();
        storage = new Storage("data.csv");
    }

    public static void main(String[] args) {
        Athena athena = new Athena();
        athena.runProgram();
    }

    /**
     * Runs the main code of the ATHENA program.
     */
    public void runProgram() {
        String inputString;
        athenaUi.printAthenaLogo();
        athenaUi.printWelcomeMessage();
        boolean isExit = false;
        try {
            taskList = storage.loadTaskListData();
        } catch (StorageException e) {
            e.printErrorMessage();
            isExit = true;
        }
        Scanner input = new Scanner(System.in);
        while (!isExit) {
            try {
                timeAllocator = new TimeAllocator(taskList);
                timeAllocator.runAllocate();
                athenaUi.printNewline();
                athenaUi.printUserInputIndicator();
                inputString = athenaUi.detectInput(input);
                Command userCommand = parser.parse(inputString, taskList);
                userCommand.execute(taskList, athenaUi);
                storage.saveTaskListData(taskList);
                isExit = userCommand.getIsExit();
            } catch (CommandException e) {
                e.printErrorMessage();
            } catch (NoSuchElementException e) {
                isExit = true;
            }
        }
        input.close();
    }
}
