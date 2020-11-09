package seedu.revised.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.revised.card.Flashcard;
import seedu.revised.card.Topic;
import seedu.revised.command.flashcardcommand.AddFlashcardCommand;
import seedu.revised.command.flashcardcommand.DeleteFlashcardCommand;
import seedu.revised.exception.flashcardexception.InvalidFlashcardException;
import seedu.revised.exception.flashcardexception.RepeatedFlashcardException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FlashcardCommandTest {
    private Topic topic;
    private AddFlashcardCommand addCommand;
    private DeleteFlashcardCommand deleteCommand;

    @BeforeEach
    void setUp() {
        List flashcardList = new ArrayList<>(List.of(
                new Flashcard("What is the Speed of Light?","3.0x10^8m/s"),
                new Flashcard("What is the Speed of sound?","343m/s"),
                new Flashcard("How to get Speed from Distance and Time?","Distance/Time")
        ));
        topic = new Topic("Speed", flashcardList);
    }

    @Test
    public void addFlashcardCommand_validCommand_returnsSubjectTitle()
            throws RepeatedFlashcardException, InvalidFlashcardException {
        addCommand = new AddFlashcardCommand("add How to get Speed from Velocity and Time; Velocity*Time");
        addCommand.execute(topic);
        assertEquals("Q: How to get Speed from Velocity and Time\n   A: Velocity*Time", topic.getFlashcards().get(3).toString());
    }

    @Test
    public void addFlashcardCommand_NoFlashcardInputWithoutSpace_throwsException() {
        addCommand = new AddFlashcardCommand("add");
        assertThrows(InvalidFlashcardException.class, () -> addCommand.execute(topic));
    }

    @Test
    public void addFlashcardCommand_NoFlashcardInputWithSpace_throwsException() {
        addCommand = new AddFlashcardCommand("add ");
        assertThrows(InvalidFlashcardException.class, () -> addCommand.execute(topic));
    }

    @Test
    public void deleteFlashcardCommand_validCommand_returnsIndex() {
        deleteCommand = new DeleteFlashcardCommand("delete 2");
        deleteCommand.execute(topic);
        assertEquals(Integer.valueOf("2"), topic.getFlashcards().size());
    }

    @Test
    public void deleteFlashcardCommand_InputNonIntegerAsIndex_throwsException() {
        deleteCommand = new DeleteFlashcardCommand("delete What is the Speed of Light?");
        assertThrows(NumberFormatException.class, () -> deleteCommand.execute(topic));
    }
}
