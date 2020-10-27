package seedu.dietbook;

import java.io.FileNotFoundException;
import java.io.IOException;
import seedu.dietbook.database.DataBase;
import seedu.dietbook.list.FoodList;
import seedu.dietbook.exception.DietException;
import seedu.dietbook.command.Command;

/**
 * Main class of the program.
 * The DietBook program is an application which can store, display and check your daily dietary intake.
 *
 * @author tikimonarch
 */

public class DietBook {
    private FoodList foodList;
    private Ui ui;
    private Manager manager;
    private DataBase dataBase;
    public static boolean isExit = false;

    /**
     * Constructor for new DietBook.
     */
    public DietBook() {
        ui = new Ui();
        foodList = new FoodList();
        dataBase = new DataBase();
        manager = new Manager(foodList, dataBase);
    }  

    /**
     * Main method to run the program.
     */
    public static void main(String[] args) throws FileNotFoundException {
        DietBook dietBook = new DietBook();
        dietBook.ui.printWelcomeMessage();

        while (!isExit) {
            try {
                String userInput = dietBook.ui.readCommand();
                Command c = dietBook.manager.manage(userInput);
                c.execute(dietBook.manager, dietBook.ui);
            } catch (DietException e) {
                dietBook.ui.printErrorMessage(e.getMessage());
            }
        }
    }
}
