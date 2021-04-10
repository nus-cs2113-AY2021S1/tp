package seedu.hdbuy.storage;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import seedu.hdbuy.common.Unit;
import seedu.hdbuy.common.exception.DuplicateUnitException;
import seedu.hdbuy.data.ShortList;

import static org.junit.jupiter.api.Assertions.fail;

class StorageManagerTest {

    private static final String filePath = "./shortlist.txt";

    @Test void readWriteTest() {
        Unit unit = new Unit("JURONG WEST", "4 ROOM", 429000, 990, " 82 years 06 months", "664A JURONG WEST ST 64",
                1126083864);
        try {
            StorageManager.read();
            ShortList.addToShortList(unit);
            StorageManager.read();
            Files.delete(Paths.get(filePath));
        } catch (DuplicateUnitException e) {
            // fallthrough
        } catch (IOException e) {
            if (!System.getProperty("os.name").contains("Windows")) {
                fail(); // file not created
            }
        }
    }

}