package seedu.financeit;

import seedu.financeit.manualtracker.ManualTracker;
import seedu.financeit.utils.CommandPacket;
import seedu.financeit.utils.InputParser;
import seedu.financeit.utils.UiManager;

public class Financeit {
    public static void main(String[] args) {
        InputParser inputParser = new InputParser();
        // String inputString = "mom /m mom -d dad /s son /d daughter";
        String inputString = "mom";
        CommandPacket packet = inputParser.parseInput(inputString);
        packet.getParamTypes();

        String[][] input = {
                {"Name", "Age", "Place", "letters"},
                {"John", "15", "Woodlands", "g"},
                {"Mary", "18", "Johore", "l"}
        };
        String[][] input1 = {
                {"Name"},
                {"John"},
                {"Mary"}
        };

        UiManager.printList(input1);
        ManualTracker.main();
    }
}

// This prints a table in case we need it
