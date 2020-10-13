package seedu.trippie;

import seedu.trippie.command.Command;

import java.io.IOException;

public class Trippie {
    private final Ui ui;
    private ExpenseList expenseList;
    private PlaceList placeList;
    private final Storage storage;

    public Trippie() {
        ui = new Ui();
        //private final Storage storage;
        storage = new Storage();
        try {
            expenseList = new ExpenseList();
            placeList = new PlaceList();
            storage.loadList();
        } catch (NullPointerException e) {
            System.out.println("No file detected");
            expenseList = new ExpenseList();
            placeList = new PlaceList();
        }
    }

    public static void main(String[] args) throws IOException {
        new Trippie().run();
    }

    public void run() throws IOException {
        ui.greetUser();
        boolean isExit = false;
        storage.setup(placeList,expenseList);
        while (!isExit) {
            storage.saveList();
            String fullCommand = ui.readCommand();
            ui.printLine();
            Command c = Parser.parse(fullCommand);
            if (c != null) {
                c.execute(ui, placeList, expenseList);
                isExit = c.isExit();
            }
            ui.printLine();
        }
    }
}
