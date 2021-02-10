package seedu.trippie.command;

import seedu.trippie.Ui;
import seedu.trippie.data.TrippieData;

public class HelpCommand extends Command {
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Prints a list of commands available in Trippie.
     *
     * @param ui User interface of the program.
     * @param trippieData The current trip to fetch data from.
     */
    @Override
    public void execute(Ui ui, TrippieData trippieData) {
        System.out.println("Welcome to Trippie! A one-stop app to plan any of your upcoming trips!");
        System.out.println("Here is a list of commands which you can use to navigate your way\nthrough Trippie!\n");
        System.out.println("[new trip] |   Creates a new trip. You will be prompted to enter some details");
        System.out.println("           |   Format: new trip");
        printBorder();
        System.out.println("[load trip]|   Loads a specific trip from the saved list");
        System.out.println("           |   Format: load trip");
        printBorder();
        System.out.println("[edit trip]|   Edits a specific trip from the saved list");
        System.out.println("           |   Format: edit trip");
        printBorder();
        System.out.println("[delete    |   Deletes a specific trip from the saved list");
        System.out.println("trip]      |   Format: delete trip");
        printBorder();
        System.out.println("[help]     |   Shows a concise list of commands available");
        System.out.println("           |   Format: help");
        printBorder();
        System.out.println("[add]      |   Adds a place to your trip");
        System.out.println("           |   Format: add /n PLACE_NAME /d DAY /t TIME");
        System.out.println("           |   Example: add /n Dinner at Marina Bay Sands /d 2 /t 1800 to 2000");
        printBorder();
        System.out.println("[list]     |   Displays all places in a timetable format");
        System.out.println("(places)   |   Format: list /p [/d DAY]");
        System.out.println("           |   list /p /d 2");
        printBorder();
        System.out.println("[search]   |   Displays all places that includes the search keyword");
        System.out.println("(places)   |   Format: search KEYWORD");
        System.out.println("           |   search Dinner");
        printBorder();
        System.out.println("[delete]   |   Deletes the specified place from the list");
        System.out.println("(place)    |   Format: delete /p PLACE_INDEX");
        System.out.println("           |   Example: delete /p 1");
        printBorder();
        System.out.println("[budget]   |   Edits a budget to keep track of in expenditure list");
        System.out.println("           |   Format: budget AMOUNT");
        System.out.println("           |   Example: budget 100");
        printBorder();
        System.out.println("[buy]      |   Adds an item with its final cost into an expenditure list");
        System.out.println("           |   Format: buy /n ITEM_NAME /d DAY_NUMBER /c FINAL_COST");
        System.out.println("           |   Example: buy /n R&B Brown Sugar /d 2 /c 3.00");
        printBorder();
        System.out.println("[list]     |   Displays all the items bought in the expenditure list");
        System.out.println("(expenses) |   Format: list /e");
        printBorder();
        System.out.println("[delete]   |   Deletes the specified place from the list");
        System.out.println("(expense)  |   Format: delete /e EXPENSE_INDEX");
        System.out.println("           |   Example: delete /e 1");
        printBorder();
        System.out.println("[convert   |   Converts an amount to the requested currency");
        System.out.println("/to]       |   Format: convert /toSGD AMOUNT");
        System.out.println("           |   Format: convert /toSGD 500");
        System.out.println("           |   Format: convert /toFOR AMOUNT");
        System.out.println("           |   Format: convert /toFOR 500");
        printBorder();
        System.out.println("[exit]     |   Exit the program and auto-saves");
        System.out.println("           |   Format: exit");
    }

    /**
     * Prints a border between each row when printing list of commands.
     */
    public void printBorder() {
        System.out.println("___________|______________________________________________________________");
    }
}
