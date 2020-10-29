package seedu.financeit.utils.storage;

import org.junit.jupiter.api.Test;
import seedu.financeit.common.CommandPacket;
import seedu.financeit.parser.InputParser;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

public class SaveManagerTest {
    @Test
    public void test() {
        InputParser parser = InputParser.getInstance();
        CommandPacket packet = parser.parseInput("add /name testcase2149855246427094876");
        SaveManager.addSave(packet);
        String path = SaveManager.dirPath + "/testcase2149855246427094876";
        File goalTracker = new File(path + "_gt.txt");
        File manualTracker = new File(path + "_mt.txt");
        File autoTracker = new File(path + "_at.txt");
        File saveTxtMt = new File("./data/save.txt");
        File saveTxtGt = new File("./data/save1.txt");
        File saveTxtAt = new File("./data/save2.txt");
        assertEquals(saveTxtMt, manualTracker);
        assertEquals(saveTxtGt, goalTracker);
        assertEquals(saveTxtAt, autoTracker);
        SaveManager.deleteSave(packet);
    }
}
