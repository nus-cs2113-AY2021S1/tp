package seedu.duke.event;

import seedu.duke.ClassManager;
import seedu.duke.TestManager;
import seedu.duke.CcaManager;
import seedu.duke.ListSchedule;
import seedu.duke.exception.EmptyListException;

import java.util.ArrayList;

public class EventManager {
    private static ClassManager classManager;
    private static TestManager testManager;
    private static CcaManager ccaManager;

    public EventManager(EventParameter eventParameter) {
        classManager = new ClassManager(eventParameter.getClasses());
        testManager = new TestManager(eventParameter.getTests());
        ccaManager = new CcaManager(eventParameter.getCcas());
    }

    public ClassManager getClassManager() {
        return classManager;
    }

    public TestManager getTestManager() {
        return testManager;
    }

    public CcaManager getCcaManager() {
        return ccaManager;
    }

    public void listSchedule() {
        try {
            ListSchedule listSchedule = new ListSchedule(
                classManager.getClasses(), ccaManager.getCcaList(), testManager.getTestList());
            ArrayList<String> printedEvents = listSchedule.getAllEventsPrinted();
            printArray(printedEvents);
        } catch (EmptyListException e) {
            System.out.println("Schedule is empty. Add some!");
        }
    }

    private static void printArray(ArrayList<String> printedEvents) {
        assert printedEvents != null;
        for (String line : printedEvents) {
            System.out.println(line);
        }
    }
}
