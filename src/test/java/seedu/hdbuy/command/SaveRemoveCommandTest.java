package seedu.hdbuy.command;

import org.junit.jupiter.api.Test;

import seedu.hdbuy.common.Unit;
import seedu.hdbuy.common.exception.DuplicateUnitException;
import seedu.hdbuy.data.SearchedUnits;
import seedu.hdbuy.data.ShortList;

import static org.junit.jupiter.api.Assertions.*;

class SaveRemoveCommandTest {

    @Test void saveRemoveExecuteTest() {
        Unit unit = new Unit("JURONG WEST", "4 ROOM", 429000, 990, " 82 years 06 months", "664A JURONG WEST ST 64",
                11111);
        SearchedUnits.clearSearchedUnits();
        SearchedUnits.addToResult(unit);
        SaveCommand saveCommand = new SaveCommand(1);
        saveCommand.execute();
        ShortlistCommand shortlistCommand = new ShortlistCommand();
        shortlistCommand.execute();
        saveCommand.execute(); // catch Duplicate
        SaveCommand saveCommandInvalidIndex = new SaveCommand(101);
        saveCommandInvalidIndex.execute(); // catch Invalid Index
        RemoveCommand removeCommand = new RemoveCommand(1);
        removeCommand.execute();
        RemoveCommand removeCommandInvalidIndex = new RemoveCommand(1);
        removeCommand.execute(); // catch Invalid Index
    }

}