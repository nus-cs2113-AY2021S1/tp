package seedu.dietbook;

import java.io.FileNotFoundException;
import java.io.IOException;
import seedu.dietbook.database.DataBase;
import seedu.dietbook.list.FoodList;

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
                String userInput = dietBook.manager.readCommand();
                Parser.parse(userInput, dietBook.manager, dietBook.ui);
            } catch (DietException e) {
                dietBook.ui.printErrorMessage(e.getMessage());
            } finally {
                System.out.println("__________________");
            }
        }
    }
}
