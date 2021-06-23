package logic.parser;

import exceptions.InvalidDateFormatException;
import exceptions.workout.workoutmanager.NotANumberException;
import models.PastWorkoutSessionRecord;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class WorkoutManagerParserTest {

    private static final WorkoutManagerParser ps = WorkoutManagerParser.getInstance();

    @Test
    void parseTags_validInput_success() {
        String args = "/t legs, chest";
        ArrayList<String> result = ps.parseTags(args);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("legs");
        expected.add("chest");
        assertEquals(expected, result);
    }

    @Test
    void parseTags_missingOrWrongIdentifierKey_empty() {
        String in1 = "/tt legs chest";
        ArrayList<String> out1 = ps.parseTags(in1);
        ArrayList<String> expected = new ArrayList<>();
        assertEquals(expected, out1);

        String in2 = "legs /t chest";
        ArrayList<String> out2 = ps.parseTags(in2);
        assertEquals(expected, out2);

        String in3 = "legs chest";
        ArrayList<String> out3 = ps.parseTags(in3);
        assertEquals(expected, out3);
    }

    @Test
    void parseSearchConditions_correctDateTagCondition_success() {
        List<String> tg = Arrays.asList("legs", "chest");
        ArrayList<String> tags = new ArrayList<>(tg);

        LocalDateTime date = LocalDateTime.of(2020,
                10,
                17,
                12,
                0);
        PastWorkoutSessionRecord record = new PastWorkoutSessionRecord("random",
                date, date, tags);
        String in1 = "/d 20201017";
        List<Boolean> expected1 = Arrays.asList(true);
        ArrayList<Boolean> testResults1 = new ArrayList<>();
        ArrayList<Predicate<PastWorkoutSessionRecord>> tests = null;
        try {
            tests = ps.parseSearchConditions(in1);
        } catch (InvalidDateFormatException e) {
            fail();
        }

        for (Predicate<PastWorkoutSessionRecord> t : tests) {
            testResults1.add(t.test(record));
        }
        assertEquals(expected1, testResults1);

        String in2 = "/d 20201017 /t arms";
        List<Boolean> expected2 = Arrays.asList(false, true);
        ArrayList<Boolean> testResults2 = new ArrayList<>();
        try {
            tests = ps.parseSearchConditions(in2);
        } catch (InvalidDateFormatException e) {
            fail();
        }

        for (Predicate<PastWorkoutSessionRecord> t : tests) {
            testResults2.add(t.test(record));
        }
        assertEquals(expected2, testResults2);

        String in3 = "/d 20201017 /t legs, ch";
        List<Boolean> expected3 = Arrays.asList(true, true);
        ArrayList<Boolean> testResults3 = new ArrayList<>();
        try {
            tests = ps.parseSearchConditions(in3);
        } catch (InvalidDateFormatException e) {
            fail();
        }

        for (Predicate<PastWorkoutSessionRecord> t : tests) {
            testResults3.add(t.test(record));
        }
        assertEquals(expected3, testResults3);
    }

    @Test
    void parseSearchConditions_wrongConditionFormat_emptyChecks() {
        List<String> tg = Arrays.asList("legs", "chest");
        ArrayList<String> tags = new ArrayList<>(tg);

        LocalDateTime date = LocalDateTime.of(2020,
                10,
                17,
                12,
                0);
        PastWorkoutSessionRecord record = new PastWorkoutSessionRecord("random",
                date, date, tags);
        String in1 = "20201017 /d";
        List<Boolean> expected1 = new ArrayList<>();
        ArrayList<Boolean> testResults1 = new ArrayList<>();
        ArrayList<Predicate<PastWorkoutSessionRecord>> tests = null;
        try {
            tests = ps.parseSearchConditions(in1);
        } catch (InvalidDateFormatException e) {
            fail();
        }

        for (Predicate<PastWorkoutSessionRecord> t : tests) {
            testResults1.add(t.test(record));
        }
        assertEquals(expected1, testResults1);

        String in2 = "20201017 arms";
        List<Boolean> expected2 = new ArrayList<>();
        ArrayList<Boolean> testResults2 = new ArrayList<>();
        try {
            tests = ps.parseSearchConditions(in2);
        } catch (InvalidDateFormatException e) {
            fail();
        }

        for (Predicate<PastWorkoutSessionRecord> t : tests) {
            testResults2.add(t.test(record));
        }
        assertEquals(expected2, testResults2);
    }

    @Test
    void parseCommandKw_correctFormat_success() {
        String in1 = "list aa";
        String[] ex1 = {"list", "aa"};
        assertArrayEquals(ex1, ps.parseCommandKw(in1));

        String in = "list    /d 20201025";
        String[] ex2 = {"list", "   /d 20201025"};
        assertArrayEquals(ex2, ps.parseCommandKw(in));
    }

    @Test
    void parseCommandKw_empty_emptyOutput() {
        String in1 = "";
        String[] ex1 = {""};
        assertArrayEquals(ex1, ps.parseCommandKw(in1));

    }

    @Test
    void parseIndex_correctInput_success() throws NotANumberException {
        assertEquals(5, ps.parseIndex("5"));
    }

    @Test
    void parseIndex_inputNotANumber_throwNotANumberException() {
        assertThrows(NotANumberException.class, () -> ps.parseIndex("5.0"));
        assertThrows(NotANumberException.class, () -> ps.parseIndex("abc"));
        assertThrows(NotANumberException.class, () -> ps.parseIndex(""));
        assertThrows(NotANumberException.class, () -> ps.parseIndex(null));
    }

    @Test
    void parseList_correctFormat_success() {
        List<String> tg = Arrays.asList("legs", "chest");
        ArrayList<String> tags = new ArrayList<>(tg);

        LocalDateTime date = LocalDateTime.of(2020,
                10,
                17,
                12,
                0);
        PastWorkoutSessionRecord record = new PastWorkoutSessionRecord("random",
                date, date, tags);
        String in1 = "/s 20201017";
        List<Boolean> expected1 = Arrays.asList(true);
        ArrayList<Boolean> testResults1 = new ArrayList<>();
        ArrayList<Predicate<PastWorkoutSessionRecord>> tests = null;
        try {
            tests = ps.parseList(in1);
        } catch (InvalidDateFormatException e) {
            fail();
        }

        for (Predicate<PastWorkoutSessionRecord> t : tests) {
            testResults1.add(t.test(record));
        }
        assertEquals(expected1, testResults1);

        String in2 = "/e 20201019";
        List<Boolean> expected2 = Arrays.asList(true);
        ArrayList<Boolean> testResults2 = new ArrayList<>();
        try {
            tests = ps.parseList(in2);
        } catch (InvalidDateFormatException e) {
            fail();
        }

        for (Predicate<PastWorkoutSessionRecord> t : tests) {
            testResults2.add(t.test(record));
        }
        assertEquals(expected2, testResults2);

        String in3 = "/s 20201017 /e 20201019";
        List<Boolean> expected3 = Arrays.asList(true, true);
        ArrayList<Boolean> testResults3 = new ArrayList<>();
        try {
            tests = ps.parseList(in3);
        } catch (InvalidDateFormatException e) {
            fail();
        }

        for (Predicate<PastWorkoutSessionRecord> t : tests) {
            testResults3.add(t.test(record));
        }
        assertEquals(expected3, testResults3);
    }

    @Test
    void parseList_wrongDateFormat_throwInvalidDateFormatException() {

        String in1 = "/s aabb";
        assertThrows(InvalidDateFormatException.class, () -> ps.parseList(in1));
    }

    @Test
    void parseList_wrongDateIdentifier_success() {
        List<String> tg = Arrays.asList("legs", "chest");
        ArrayList<String> tags = new ArrayList<>(tg);
        LocalDateTime date = LocalDateTime.of(2020,
                10,
                17,
                12,
                0);
        PastWorkoutSessionRecord record = new PastWorkoutSessionRecord("random",
                date, date, tags);
        ArrayList<Predicate<PastWorkoutSessionRecord>> tests = null;
        String in2 = "/s 20201017 /e/e";
        List<Boolean> expected2 = Arrays.asList(true);
        ArrayList<Boolean> testResults2 = new ArrayList<>();
        try {
            tests = ps.parseList(in2);
        } catch (InvalidDateFormatException e) {
            fail();
        }

        for (Predicate<PastWorkoutSessionRecord> t : tests) {
            testResults2.add(t.test(record));
        }
        assertEquals(expected2, testResults2);

    }

}