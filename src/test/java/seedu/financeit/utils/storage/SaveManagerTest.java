package seedu.financeit.utils.storage;

import org.junit.Assert;
import org.junit.Test;
import seedu.financeit.common.CommandPacket;
import seedu.financeit.parser.InputParser;

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
        File saveTxtMt = new File(ManualTrackerSaver.getInstance().fullPath);
        File saveTxtGt = new File(GoalTrackerSaver.getInstance().fullPath);
        File saveTxtAt = new File(AutoTrackerSaver.getInstance().fullPath);
        Assert.assertEquals(saveTxtMt, manualTracker);
        Assert.assertEquals(saveTxtGt, goalTracker);
        Assert.assertEquals(saveTxtAt, autoTracker);
        SaveManager.deleteSave(packet);
    }
}
