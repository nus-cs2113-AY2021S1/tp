
import academic.AcademicCommandParser;
import academic.AcademicCommandType;
import exceptions.InvalidCommandException;
import exceptions.InvalidGradeException;
import exceptions.InvalidMcException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class AcademicParserTest {


    @Test
    void getCommandType_correctCommandInputs_success() throws InvalidCommandException {
        AcademicCommandParser parser = new AcademicCommandParser();

        assertEquals(AcademicCommandType.ADD_CONTACT, parser.getAcademicCommandType("add contact"));
        assertEquals(AcademicCommandType.LIST_CONTACT, parser.getAcademicCommandType("list contact"));
        assertEquals(AcademicCommandType.ADD_GRADE, parser.getAcademicCommandType("add grade"));
        assertEquals(AcademicCommandType.CHECK_CAP, parser.getAcademicCommandType("check cap"));
        assertEquals(AcademicCommandType.LIST_GRADE, parser.getAcademicCommandType("list grade"));
        assertEquals(AcademicCommandType.LIST_GRADE, parser.getAcademicCommandType("list grade"));
        assertEquals(AcademicCommandType.DELETE_PERSON, parser.getAcademicCommandType("delete contact"));
        assertEquals(AcademicCommandType.DELETE_GRADE, parser.getAcademicCommandType("delete grade"));
        assertEquals(AcademicCommandType.SU_GRADE, parser.getAcademicCommandType("su grade"));
        assertEquals(AcademicCommandType.STAR_GRADE, parser.getAcademicCommandType("star grade"));
        assertEquals(AcademicCommandType.STAR_CONTACT, parser.getAcademicCommandType("star contact"));
        assertEquals(AcademicCommandType.LIST_STAR, parser.getAcademicCommandType("list star"));
    }

    @Test
    void getCommandType_incorrectCommandInput_exceptionThrown() {
        AcademicCommandParser parser = new AcademicCommandParser();
        assertThrows(InvalidCommandException.class, () -> {
            parser.getAcademicCommandType("what is this command?");
        });
    }

    @Test
    void evaluateInput_incorrectGetContact_exceptionThrown() {
        AcademicCommandParser parser = new AcademicCommandParser();
        assertThrows(NumberFormatException.class, () -> {
            parser.getContact("add contact c/Prof Lim  m/number81234567  e/E7654321@u.nus.edu");
        });
    }

    @Test
    void evaluateInput_incorrectGetGrade_gradeExceptionThrown() {
        AcademicCommandParser parser = new AcademicCommandParser();
        assertThrows(InvalidGradeException.class, () -> {
            parser.getGrade("add grade n/CS2101  m/4  g/A+++");
        });

    }

    @Test
    void evaluateInput_incorrectGetGrade_mcExceptionThrown() {
        AcademicCommandParser parser = new AcademicCommandParser();
        assertThrows(InvalidMcException.class, () -> {
            parser.getGrade("add grade n/CS2101  m/0 g/A");
        });
    }

    @Test
    void evaluateInput_getContactCommand_parsedCorrectly() {
        AcademicCommandParser parser = new AcademicCommandParser();
        String input = "add contact c/Prof Lim  m/81234567  e/E7654321@u.nus.edu";
        final String[] result = parser.getContact(input);
        final String[] expectedResult = {"Prof Lim","81234567","E7654321@u.nus.edu"};
        assertArrayEquals(expectedResult,result);
    }

    @Test
    void evaluateInput_getGradeCommand_parsedCorrectly() throws InvalidGradeException, InvalidMcException {
        AcademicCommandParser parser = new AcademicCommandParser();
        String input = "add grade n/CS2101  m/4  g/A-";
        final String[] result = parser.getGrade(input);
        final String[] expectedResult = {"CS2101","4","A-"};
        assertArrayEquals(expectedResult,result);
    }

    @Test
    void evaluateInput_parseImportedGrade_parsedCorrectly() {
        AcademicCommandParser parser = new AcademicCommandParser();
        String input = "[G] | CS2101 | 4 | A- | true | true";
        final String[] result = parser.parseImportedGrade(input);
        final String[] expectedResult = {"CS2101","4","A-","true","true"};
        assertArrayEquals(expectedResult,result);
    }

    @Test
    void evaluateInput_parseImportedPerson_parsedCorrectly() {
        AcademicCommandParser parser = new AcademicCommandParser();
        String input = "[P] | Prof Lim | 81234567 | E7654321@u.nus.edu | false";
        final String[] result = parser.parseImportedPerson(input);
        final String[] expectedResult = {"Prof Lim","81234567","E7654321@u.nus.edu","false"};
        assertArrayEquals(expectedResult,result);
    }

    @Test
    void evaluateInput_parseDeletePerson_parsedCorrectly() {
        AcademicCommandParser parser = new AcademicCommandParser();
        String input = "delete person 5";
        final Integer result = parser.parseDeletePerson(input);
        final Integer expectedResult = 5;
        assertEquals(expectedResult,result);
    }

    @Test
    void evaluateInput_parseDeleteGrade_parsedCorrectly() {
        AcademicCommandParser parser = new AcademicCommandParser();
        String input = "delete grade 21";
        final Integer result = parser.parseDeleteGrade(input);
        final Integer expectedResult = 21;
        assertEquals(expectedResult,result);
    }

    @Test
    void evaluateInput_parseSuGrade_parsedCorrectly() {
        AcademicCommandParser parser = new AcademicCommandParser();
        String input = "su grade 1";
        final Integer result = parser.parseSuGrade(input);
        final Integer expectedResult = 1;
        assertEquals(expectedResult,result);
    }

    @Test
    void evaluateInput_parseStarGrade_parsedCorrectly() {
        AcademicCommandParser parser = new AcademicCommandParser();
        String input = "star grade 2";
        final Integer result = parser.parseStarGrade(input);
        final Integer expectedResult = 2;
        assertEquals(expectedResult,result);
    }

    @Test
    void evaluateInput_parseStarContact_parsedCorrectly() {
        AcademicCommandParser parser = new AcademicCommandParser();
        String input = "star contact 3";
        final Integer result = parser.parseStarContact(input);
        final Integer expectedResult = 3;
        assertEquals(expectedResult,result);
    }
}
