package seedu.duke;

import seedu.duke.exceptions.CustomException;
import seedu.duke.favorite.FavList;
import seedu.duke.logic.parser.Parser;
import seedu.duke.logic.parser.RouteParser;
import seedu.duke.storage.FavStorage;
import seedu.duke.storage.FreqStorage;
import seedu.duke.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Duke {

    private static Parser parser;
    public static FavStorage favFile = new FavStorage("data/FavList.txt");
    public static FreqStorage freqFile = new FreqStorage("data/freqList.txt");
    private static BusInfo busInfo = new BusInfo();
    private static FavList favList = new FavList();


    public Duke() {
        RouteParser.initLogger();
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) throws IOException {
        new Duke();
        favFile.readFile();
        boolean isOngoing = true;
        try {
            freqFile.readFile();
        } catch (CustomException e) {
            Ui.showError(e);
        }
        Ui.printWelcomeMessage();
        parser = new Parser("lol");
        while (isOngoing) {
            try {
                String fullCommand = Ui.getCommand();
                parser.setUserInput(fullCommand);
                isOngoing = parser.extractType();
                freqFile.updateFile();
            } catch (CustomException error) {
                Ui.showError(error);
            }
            favFile.updateFile();
        }
    }
}
