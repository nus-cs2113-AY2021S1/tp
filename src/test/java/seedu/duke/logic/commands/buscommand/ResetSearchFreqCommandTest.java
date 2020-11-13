package seedu.duke.logic.commands.buscommand;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.CustomException;
import seedu.duke.model.bus.BusStops;
import seedu.duke.model.favorite.Fav;
import seedu.duke.model.favorite.FavList;
import seedu.duke.storage.FreqStorage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ResetSearchFreqCommandTest {
    @Test
    public void resetSearchFreqCommand_success() throws CustomException {
        FreqStorage freqFile = new FreqStorage("src/test/StorageTestCases/FreqStorageTestCases/ValidFreqList");
        freqFile.readFile();
        ResetSearchFreqCommand command = new ResetSearchFreqCommand();
        command.executeCommand();
        assertEquals(0, BusStops.PGP.getSearchCount());
    }
}