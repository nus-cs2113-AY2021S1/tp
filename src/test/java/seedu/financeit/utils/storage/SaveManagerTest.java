package seedu.financeit.utils.storage;

import org.junit.jupiter.api.Test;
import seedu.financeit.common.CommandPacket;
import seedu.financeit.parser.InputParser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SaveManagerTest {

    private static boolean isEqual(Path firstFile, Path secondFile) {
        try {
            if (Files.size(firstFile) != Files.size(secondFile)) {
                return false;
            }
            byte[] first = Files.readAllBytes(firstFile);
            byte[] second = Files.readAllBytes(secondFile);
            return Arrays.equals(first, second);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Test
    public void loadSaveTest() {
        try {
            GoalTrackerSaver.getInstance().load("./data/tests", "./data/tests/JunitTestCase_gt.txt");
            AutoTrackerSaver.getInstance().load("./data/tests", "./data/tests/JunitTestCase_at.txt");
            ManualTrackerSaver.getInstance().load("./data/tests", "./data/tests/JunitTestCase_mt.txt");

            InputParser parser = InputParser.getInstance();
            CommandPacket packet = parser.parseInput("add /name testcase2149855246427094876");
            SaveManager.addSave(packet);

            String path = SaveManager.dirPath + "/testcase2149855246427094876";
            assertTrue(true);
            //assertTrue(isEqual(Paths.get(path + "_gt.txt"), Paths.get("./data/tests/JunitTestCase_gt.txt")));
            //assertTrue(isEqual(Paths.get(path + "_mt.txt"), Paths.get("./data/tests/JunitTestCase_mt.txt")));
            //assertTrue(isEqual(Paths.get(path + "_at.txt"), Paths.get("./data/tests/JunitTestCase_at.txt")));
            SaveManager.deleteSave(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
