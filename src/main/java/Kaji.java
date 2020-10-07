import commands.Command;
import exception.InvalidInputException;
import manager.chapter.CardList;
import parser.Parser;
import storage.Storage;
import ui.Ui;

import java.io.IOException;

public class Kaji {
    private CardList cards;
    private Ui ui;

    public Kaji() {
        ui = new Ui();
        cards = new CardList();
    }

    public void run() {
        ui.showWelcome();
        ui.showHelpList();
        boolean isExit = false;
        Storage.getFileContents(cards);
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(cards, ui);
                Storage.writeToFile(cards);
                isExit = c.isExit();
            } catch (InvalidInputException e) {
                System.out.println("Invalid input given");
            } catch (IOException e) {
                System.out.println("     Something went wrong: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Kaji().run();
    }
}
