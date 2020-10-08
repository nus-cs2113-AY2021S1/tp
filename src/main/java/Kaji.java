import access.Access;
import commands.Command;
import exception.InvalidInputException;
import manager.admin.Admin;
import manager.chapter.CardList;
import parser.Parser;
import storage.Storage;
import ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Kaji {
    private CardList cards;
    private Ui ui;
    private Access access;
    private Storage storage;


    public Kaji(String filePath) {
        ui = new Ui();
        cards = new CardList();
        storage = new Storage(filePath);
        try {
            Admin admin = new Admin(storage.loadModule());
            access = new Access(admin);
            ui.showLine(); // show the divider line ("_______")
            ui.showLine();
        } catch (FileNotFoundException e) {
            storage.createAdmin();
            access = new Access();
        }
    }

    public void run() {
        ui.showWelcome();
        ui.showHelpList();
        boolean isExit = false;
        Storage.getFileContents(cards);
        while (!isExit) {
            try {
                ui.showLevel(access);
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(cards, ui, access, storage);
                ui.printEmptyLine();
                Storage.writeToFile(cards);
                isExit = c.isExit();
            } catch (InvalidInputException e) {
                System.out.println("Invalid input given.\n");
            } catch (IOException e) {
                System.out.println("     Something went wrong: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Kaji("data/admin").run();
    }
}
