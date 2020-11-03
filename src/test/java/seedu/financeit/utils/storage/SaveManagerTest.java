package seedu.financeit.utils.storage;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.parser.InputParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SaveManagerTest {

    private static boolean isEqual(Path firstFile, Path secondFile) {
        try {
            List<String> first = Files.readAllLines(firstFile);
            List<String> second = Files.readAllLines(secondFile);
            return first.equals(second);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void loadSaveTest() {
        try {
            SaveManager.clear();

            GoalTrackerSaver.getInstance().load("./data/tests", "./data/tests/JunitTestCase_gt.txt");
            AutoTrackerSaver.getInstance().load("./data/tests", "./data/tests/JunitTestCase_at.txt");
            ManualTrackerSaver.getInstance().load("./data/tests", "./data/tests/JunitTestCase_mt.txt");

            InputParser parser = InputParser.getInstance();
            CommandPacket packet = parser.parseInput("add /name testcase2149855246427094876");
            SaveManager.addSave(packet);

            String path = SaveManager.dirPath + "/testcase2149855246427094876";
            assertTrue(isEqual(Paths.get(path + "_gt.txt"), Paths.get("./data/tests/JunitTestCase_gt.txt")));
            assertTrue(isEqual(Paths.get(path + "_mt.txt"), Paths.get("./data/tests/JunitTestCase_mt.txt")));
            assertTrue(isEqual(Paths.get(path + "_at.txt"), Paths.get("./data/tests/JunitTestCase_at.txt")));

            SaveManager.deleteSave(packet);
            SaveManager.clear();
            SaveManager.resetAllLists();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
