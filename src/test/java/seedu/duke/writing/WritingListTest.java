package seedu.duke.writing;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.storageexceptions.FileEmptyException;
import seedu.duke.exceptions.ItemNotFoundedException;
import seedu.duke.writing.Poem;
import seedu.duke.writing.WritingList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.duke.commands.CommandChecker.START;
import static seedu.duke.commands.CommandChecker.TYPE;
import static seedu.duke.commands.CommandChecker.extractCommandType;
import static seedu.duke.constants.FluffleMessages.ASKING_FOR_TITLE;
import static seedu.duke.constants.FluffleMessages.ASKING_FOR_TOPIC;
import static seedu.duke.constants.FluffleMessages.ASKING_FOR_TYPE;
import static seedu.duke.constants.FluffleMessages.ASKING_FOR_REMINDER;

import static seedu.duke.constants.Logos.PLAIN_TEXT_DIVIDER;

public class WritingListTest {
    private static WritingList writings;

    public static void initializeTestDatabase() {
        LocalDate date = LocalDate.parse("28/10/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        writings.addPoem("fantasy", 1, "thih", "sdfa", "jdkfa", date);
        writings.addPoem("fantasy", 12, "thih", "sdfa", "jdkfa", date);
        writings.addPoem("fantasy", 20, "thih", "sdfa", "jdkfa", date);
        writings.addEssay("fantasy", 23, "thih", "sdfa", "jdkfa", date);
        writings.addEssay("fantasy", 13, "thih", "sdfa", "jdkfa", date);
    }

    private static void initializeDerErlkonigPoem() {
        LocalDate date = LocalDate.parse("28/10/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String content = "Wer reitet so spät durch Nacht und Wind?\n"
                + "Es ist der Vater mit seinem Kind;\n"
                + "Er hat den Knaben wohl in dem Arm,\n"
                + "Er faßt ihn sicher, er hält ihn warm.\n";
        String author = "Goethe";
        writings.addPoem("Der Erlkonig",123,"ghost story", content, author, date);
    }

    private static void initializeLoremEssay() {
        LocalDate date = LocalDate.parse("28/10/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String content = "Lorem ipsum dolor sit amet, vel et natum ludus laboramus. Vitae nostrud euripidis ei duo. "
                + "Nec veri dicit veniam te. Tantas causae consectetuer ex sit, sit an sumo magna posidonium, "
                + "id usu falli nominavi postulant. Dico phaedrum disputationi qui et, eos no noluisse gloriatur. "
                + "Mutat evertitur delicatissimi mea eu, cu quo nusquam patrioque, persius omittam tincidunt mei an. "
                + "Modus persecuti has ad, animal dissentiet signiferumque ad per.\n";
        String author = "none";
        writings.addEssay("Lorem", 123, "dummy", content, author, date);
    }

    @Test
    public void clearAll_getWritingInformation_getTheNumberOfItemsAfterDeleted() {
        writings.clearAll();
        initializeTestDatabase();
        assertEquals(5, writings.getWritingSize());
        assertEquals("Poem", writings.writingList.get(0).getType());
        assertEquals("Essay", writings.writingList.get(3).getType());
        writings.clearAll();
        assertEquals(0, writings.getWritingSize());
    }

    @Test
    public void addErlkonigPoem_getInformation() {
        writings.clearAll();
        initializeDerErlkonigPoem();
        assertEquals(31, writings.writingList.get(0).getNumberOfWords());
        assertEquals(4, writings.writingList.get(0).getNumberOfLines());
        assertEquals(0, writings.writingList.get(0).getNumberOfSentences());
        assertEquals(1, writings.getCountWritings());
        assertEquals(null, writings.writingList.get(0).printEssayProperties());
        String thisPoemProperties = "This poem has " + 4 + " line(s)"
                + " and " + 31 + " word(s)";
        assertEquals(thisPoemProperties, writings.writingList.get(0).printPoemProperties());
        String reminderDate = "28/10/2020";
        assertEquals(reminderDate, writings.writingList.get(0).getReminderDateString());
        String thisWritingReminder = "  Id: " + 123 + "\n"
                + "  Title: " + "DER ERLKONIG" + "\n"
                + PLAIN_TEXT_DIVIDER;
        assertEquals(thisWritingReminder, writings.writingList.get(0).printWritingsReminder());
        assertEquals("ghost story", writings.writingList.get(0).getTopic());
    }

    @Test
    public void addLoremEssay_getInformation() {
        writings.clearAll();
        initializeLoremEssay();
        assertEquals(0, writings.writingList.get(0).getNumberOfLines());
        assertEquals(7, writings.writingList.get(0).getNumberOfSentences());
        assertEquals(1, writings.getCountWritings());
        assertEquals(66, writings.writingList.get(0).getNumberOfWords());
        assertEquals(null, writings.writingList.get(0).printPoemProperties());
        String thisEssayProperties = "This essay has " + 7 + " sentence(s)"
                + " and " + 66 + " word(s)";
        assertEquals(thisEssayProperties, writings.writingList.get(0).printEssayProperties());
    }

    @Test
    public void checkStartTest_checkTypeTest_checkPrintWritings() {
        writings.clearAll();
        initializeTestDatabase();
        assertEquals(START, writings.checkStart("start"));
        assertEquals(START, extractCommandType("start"));
        assertEquals(TYPE, writings.checkType("type"));
        assertEquals(5, writings.printWritings());
    }

    @Test
    public void testPrintEmptyWriting_testPrintWritingSizeContent_testRemoveAWriting_testRemvoingNonExistedWriting() {
        writings.clearAll();
        assertEquals(0, writings.printWritings());
        String expectedStringToBePrinted = "In our storage, there is/are currently 0 writing(s)";
        assertEquals(expectedStringToBePrinted, writings.printWritingSize());
        initializeTestDatabase();
        assertEquals(6, writings.add(new Poem()));
        try {
            assertEquals(5, writings.removeWriting(3));
        } catch (FileEmptyException e) {
            System.out.println("This file is empty");
        }
        assertThrows(ItemNotFoundedException.class, () -> writings.removeID(356));
        try {
            assertEquals(4, writings.removeID(23));
        } catch (FileEmptyException e) {
            System.out.println("This file is empty");
        } catch (ItemNotFoundedException e) {
            System.out.println("There is no such element in our database");
        }
    }

    @Test
    public void miscellaneousMessagesTest() {
        assertEquals(ASKING_FOR_TYPE, writings.printAskForType());
        assertEquals(ASKING_FOR_TITLE, writings.printAskForTitle());
        assertEquals(ASKING_FOR_TOPIC, writings.printAskForTopic());
        assertEquals(ASKING_FOR_REMINDER, writings.printAskForReminderDate());
    }

}

