
import academic.AcademicCommandParser;
import academic.AcademicCommandType;
import exceptions.InvalidCommandException;

import exceptions.InvalidGradeException;
import exceptions.InvalidMcException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


class AcademicParserTest {


    @Test
    void getCommandType_correctCommandInputs_success() throws InvalidCommandException {
        AcademicCommandParser academicCommandParser = new AcademicCommandParser();

        assertEquals(AcademicCommandType.ADD_CONTACT, academicCommandParser.getAcademicCommandType("add contact"));
        assertEquals(AcademicCommandType.CHECK_CONTACT, academicCommandParser.getAcademicCommandType("check contact"));
        assertEquals(AcademicCommandType.ADD_GRADE, academicCommandParser.getAcademicCommandType("add grade"));
        assertEquals(AcademicCommandType.CHECK_GRADE, academicCommandParser.getAcademicCommandType("check grade"));
        assertEquals(AcademicCommandType.LIST_GRADE, academicCommandParser.getAcademicCommandType("list grade"));
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
}
