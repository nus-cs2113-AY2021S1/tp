package seedu.modtracker;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modtracker.Notification.CAPABLE;
import static seedu.modtracker.Notification.HARD_WORK;
import static seedu.modtracker.Notification.MAINTAIN;
import static seedu.modtracker.Notification.PUSH_ON;
import static seedu.modtracker.Notification.STRONGER;
import static seedu.modtracker.Notification.SUCCESS;
import static seedu.modtracker.Notification.TIME_MANAGEMENT;
import static seedu.modtracker.Notification.TODAY;

class NotificationTest {

    private static final String TEST_FILEPATH = "test/data/notification.txt";
    Storage storage = new Storage(TEST_FILEPATH);
    ModuleList modules = new ModuleList();
    Notification notification = new Notification();

    @Test
    void start_noNotifications_noMessage() {
        modules.clear();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        notification.start();
        String expected = "";
        assertEquals(expected, outContent.toString());
        modules.clear();
    }

    @Test
    void start_addOneModuleWithTooLittleTime_printStartMessage() {
        modules.clear();
        modules.addExp("addExp CS5555 3", true, storage);
        modules.addTime("addtime cs5555 1 1", true, storage);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        notification.start();
        String commandMsg = "Please type `open` to view the notification and an encouraging message.";
        String notification = "You have 1 notification." + System.lineSeparator();
        String expected = notification + commandMsg + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
        modules.clear();
    }

    @Test
    void getNumNotification_addModuleWithoutExp_numOfNotification() {
        modules.addMod("addMod CS1000", true, storage);
        notification.printNotification(modules);
        assertEquals(0, notification.getNumNotification(modules));
        modules.clear();
    }


    @Test
    void getNumNotification_addOneModuleWithOnlyExp_numOfNotification() {
        modules.clear();
        modules.addExp("addExp CS1000 8", true, storage);
        assertEquals(0, notification.getNumNotification(modules));
        modules.clear();
    }

    @Test
    void getNumNotification_addOneModuleWithTooLittleTime_numOfNotification() {
        modules.clear();
        modules.addExp("addExp CS1010 8", true, storage);
        modules.addTime("addtime cs1010 2 1", true, storage);
        assertEquals(1, notification.getNumNotification(modules));
    }

    @Test
    void printNotification_moduleWithTooLittleTime_tooLittleTimeMessage() {
        modules.clear();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        modules.addExp("addExp CS2000 8", true, storage);
        modules.addTime("addtime cs2000 2 1", true, storage);
        modules.addExp("addExp CS4000 2", true, storage);
        notification.printNotification(modules);
        boolean isPresent = false;
        boolean expIsPresent = false;
        String[] possibleInputs = {HARD_WORK, PUSH_ON, STRONGER, CAPABLE, TODAY};
        for (int i = 0; i < possibleInputs.length; i++) {
            if (outContent.toString().contains(possibleInputs[i])) {
                isPresent = true;
            }
        }
        String expected = "Oh no! It appears you are spending too little time on CS2000 in week 1.";
        if (outContent.toString().contains(expected)) {
            expIsPresent = true;
        }
        assertTrue(isPresent);
        assertTrue(expIsPresent);
    }

    @Test
    void printNotification_moduleWithJustRightTime_justRightTimeMessage() {
        modules.clear();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        modules.addExp("addExp CS3000 8", true, storage);
        modules.addTime("addtime cs3000 8 1", true, storage);
        notification.printNotification(modules);
        boolean msgIsPresent = false;
        boolean expIsPresent = false;
        String[] successInputs = {MAINTAIN, SUCCESS, TIME_MANAGEMENT};
        for (int i = 0; i < successInputs.length; i++) {
            if (outContent.toString().contains(successInputs[i])) {
                msgIsPresent = true;
            }
        }
        String expected = "You are on track in week 1. Well Done!";
        if (outContent.toString().contains(expected)) {
            expIsPresent = true;
        }
        assertTrue(msgIsPresent);
        assertTrue(expIsPresent);
        modules.clear();
    }

    @Test
    void printNotification_moduleWithTooMuchTime_tooMuchTimeMessage() {
        modules.clear();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        modules.addExp("addExp CS4000 5", false, storage);
        modules.addTime("addtime cs4000 10 1", false, storage);
        notification.printNotification(modules);
        String tooMuchTime = "Beware! Seems like you are spending too much time on CS4000 in week 1.";
        String expected = tooMuchTime + System.lineSeparator();
        assertEquals(expected + System.lineSeparator(), outContent.toString());
        modules.clear();
    }
}