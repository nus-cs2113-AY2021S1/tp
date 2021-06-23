package logic.parser;

import exceptions.workout.workoutsession.AddFormatException;
import exceptions.workout.workoutsession.DeleteFormatException;
import logic.parser.WorkoutSessionParser;
import models.Exercise;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@@author yujinyang1998
class WorkoutSessionParserTest {

    @Test
    void workoutSessionParser_validInput_success() {
        String[] actual = WorkoutSessionParser.workoutSessionParser("add bench /n 3244 /w 4324");
        String[] expected = {"add", "bench", "/n", "3244", "/w", "4324"};
        assertArrayEquals(expected, actual);
    }

    @Test
    void workoutSessionParser_emptyString() {
        String[] actual = WorkoutSessionParser.workoutSessionParser("");
        String[] expected = {""};
        assertArrayEquals(expected, actual);
    }

    @Test
    void addParser_validInput_success() throws AddFormatException {
        String[] inputString = {"add", "", "bench", "/n", "3244", "/w", "4324"};
        Exercise actual = WorkoutSessionParser.addParser(inputString);
        Exercise expected = new Exercise("bench", 3244, 4324);
        assertTrue(EqualsBuilder.reflectionEquals(expected, actual));
    }

    @Test
    void addParser_invalidInput_negativeRepetitions() {
        String[] inputString = {"add", "", "bench", "/n", "-3244", "/w", "4324"};
        assertThrows(AddFormatException.class, () -> WorkoutSessionParser.addParser(inputString));
    }

    @Test
    void addParser_invalidInput_formatError() {
        String[] inputString = {"add", "", "bench", "/w", "4324"};
        assertThrows(NumberFormatException.class, () -> WorkoutSessionParser.addParser(inputString));
    }

    @Test
    void deleteParser_validInput_success() throws DeleteFormatException {
        String[] inputString = {"delete", "1"};
        int actual = WorkoutSessionParser.deleteParser(inputString);
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    void deleteParser_invalidInput_formatError() {
        String[] inputString = {"delete", "fdsf"};
        assertThrows(DeleteFormatException.class, () -> WorkoutSessionParser.deleteParser(inputString));
    }

    @Test
    void searchParser_validInput_success() {
        String[] inputString = {"search", "test"};
        String actual = WorkoutSessionParser.searchParser(inputString);
        String expected = "test";
        assertEquals(expected, actual);
    }
}
