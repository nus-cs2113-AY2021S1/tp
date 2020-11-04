package seedu.duke;

import seedu.duke.exceptions.CustomException;
import seedu.duke.model.favorite.FavList;
import seedu.duke.logic.parser.DescFavParser;
import seedu.duke.logic.parser.Parser;
import seedu.duke.logic.parser.RouteParser;
import seedu.duke.model.bus.BusInfo;
import seedu.duke.storage.FavStorage;
import seedu.duke.storage.FreqStorage;
import seedu.duke.ui.Ui;

import java.io.IOException;

public class Duke {

    private static final String DUMMY_PARAM = "Dummy";
    private static Parser parser;
    public static FavStorage favFile = new FavStorage("data/FavList.txt");
    public static FreqStorage freqFile = new FreqStorage("data/FreqList.txt");
    private static BusInfo busInfo = new BusInfo();
    private static FavList favList = new FavList();


    public Duke() {
        RouteParser.initLogger();
        DescFavParser.initLogger();
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) throws IOException {
        new Duke();
        readAllStorageFiles();
        Ui.printWelcomeMessage();
        parser = new Parser(DUMMY_PARAM);
        boolean isOngoing = true;
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

    private static void readAllStorageFiles() throws IOException {
        try {
            favFile.readFile();
            favFile.updateFile();
            freqFile.readFile();
            freqFile.updateFile();
        } catch (CustomException e) {
            System.out.println(e);
        }
    }
}
