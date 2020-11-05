package parser;

import commands.AddCardCommand;
import exception.InvalidInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserUtilTest {
    private static final String INVALID_QUESTION = "1+1";
    private static final String INVALID_ANSWER = "2";
    private static final String INVALID_NAME = "../CS2!13:";

    private static final String VALID_QUESTION = "q:1+1";
    private static final String VALID_ANSWER = "a:2";
    private static final String VALID_NAME = "Chapter 1";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseQuestion_invalidInput_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class,
                () -> ParserUtil.parseQuestion(INVALID_QUESTION, AddCardCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseQuestion_validInput_returnsQuestion() throws Exception {
        assertEquals("1+1", ParserUtil.parseQuestion(VALID_QUESTION, AddCardCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseQuestion_validInput_returnsTrimmedQuestion() throws Exception {
        String questionWithWhitespaces = VALID_QUESTION + WHITESPACE;
        assertEquals("1+1", ParserUtil.parseQuestion(questionWithWhitespaces, AddCardCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseAnswer_invalidInput_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class,
                () -> ParserUtil.parseAnswer(INVALID_ANSWER, AddCardCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseAnswer_validInput_returnsAnswer() throws Exception {
        assertEquals("2", ParserUtil.parseAnswer(VALID_ANSWER, AddCardCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseAnswer_validInput_returnsTrimmedAnswer() throws Exception {
        String answerWithWhitespaces = VALID_ANSWER + WHITESPACE;
        assertEquals("2", ParserUtil.parseAnswer(answerWithWhitespaces, AddCardCommand.MESSAGE_USAGE));
    }

    @Test
    public void checkAlphanumericOnly_invalidInput_returnsFalse() {
        assertEquals(false, ParserUtil.checkAlphanumericOnly(INVALID_NAME));
    }

    @Test
    public void checkAlphanumericOnly_validInput_returnsTrue() {
        assertEquals(true, ParserUtil.checkAlphanumericOnly(VALID_NAME));
    }
}
