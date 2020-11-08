package academic;

import exceptions.InvalidCommandException;
import exceptions.InvalidGradeException;
import exceptions.InvalidMcException;
import studyit.CommandParser;
import java.util.Arrays;
import java.util.List;


/**
 *Parses user inputs for academic mode.
 */
public class AcademicCommandParser extends CommandParser {
    /**
     * Takes in an input command to and return which type of academic command it falls under.
     * @param command input command from the user.
     * @return command type
     * @throws InvalidCommandException if command type is invalid
     */
    public static AcademicCommandType getAcademicCommandType(String command)
            throws InvalidCommandException {
        String commandModified = CommandParser.standardizeCommand(command);

        if (commandModified.startsWith("add contact")) {
            return AcademicCommandType.ADD_CONTACT;
        } else if (commandModified.equals("list contact")) {
            return AcademicCommandType.LIST_CONTACT;
        } else if (commandModified.startsWith("add grade")) {
            return AcademicCommandType.ADD_GRADE;
        } else if (commandModified.equals("check cap")) {
            return AcademicCommandType.CHECK_CAP;
        } else if (commandModified.equals("list grade")) {
            return AcademicCommandType.LIST_GRADE;
        } else if (commandModified.startsWith("delete contact")) {
            return AcademicCommandType.DELETE_PERSON;
        } else if (commandModified.startsWith("delete grade")) {
            return AcademicCommandType.DELETE_GRADE;
        } else if (commandModified.startsWith("su grade")) {
            return AcademicCommandType.SU_GRADE;
        } else if (commandModified.startsWith("star grade")) {
            return AcademicCommandType.STAR_GRADE;
        } else if (commandModified.startsWith("star contact")) {
            return AcademicCommandType.STAR_CONTACT;
        } else if (commandModified.equals("list star")) {
            return AcademicCommandType.LIST_STAR;
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Extract details of a contact from a string into a string array.
     * @param command input string
     * @return string array containing the various variables for the contact.
     * @throws NumberFormatException when phone number inputted is not a valid phone number.
     */
    public static String[] getContact(String command) throws NumberFormatException {
        String name = command.substring(command.indexOf("c/") + 2,
                command.indexOf("m/")).trim();
        String number = command.substring(command.indexOf("m/") + 2,
                command.indexOf("e/")).trim();
        String email = command.substring(command.indexOf("e/") + 2);
        int numberAsInteger = Integer.parseInt(number);
        if (numberAsInteger >= 100000000 || numberAsInteger <= 0) {
            throw new NumberFormatException();
        }

        return new String[]{name, number, email};
    }

    /**
     * Extract details of a module grade from a string into a string array.
     * @param command input string
     * @return string array containing the various variables for the module grade.
     * @throws InvalidGradeException grade inputted is nto a valid grade.
     * @throws InvalidMcException mc inputted is nto a valid mc.
     */
    public static String[] getGrade(String command) throws InvalidGradeException, InvalidMcException {
        String name = command.substring(command.indexOf("n/") + 2,
                command.indexOf("m/")).trim();
        String mc = command.substring(command.indexOf("m/") + 2,
                command.indexOf("g/")).trim();
        String grade = command.substring(command.indexOf("g/") + 2);

        List<String> list = Arrays.asList(Grade.listOfGrades);

        if (!list.contains(grade.toLowerCase())) {
            throw new InvalidGradeException();
        } else if (Integer.parseInt(mc) <= 0) {
            throw new InvalidMcException();
        }
        return new String[]{name, mc, grade};
    }

    /**
     * Extracts details of a contact from the storage file into a string array.
     * @param importedStatement input string from the storage file.
     * @return string array containing the various variables for the contact.
     */
    public static String[] parseImportedPerson(String importedStatement) {
        assert importedStatement.startsWith("[P]") : "Parsed statement should be a person";

        int positionOfFirstDivider = importedStatement.indexOf("|");
        int positionOfSecondDivider = importedStatement.indexOf("|",positionOfFirstDivider + 1);
        int positionOfThirdDivider = importedStatement.indexOf("|",positionOfSecondDivider + 1);
        int positionOfFourthDivider = importedStatement.lastIndexOf("|");

        String var1 = importedStatement.substring(positionOfFirstDivider + 1,positionOfSecondDivider).trim();
        String var2 = importedStatement.substring(positionOfSecondDivider + 1,positionOfThirdDivider).trim();
        String var3 = importedStatement.substring(positionOfThirdDivider + 1, positionOfFourthDivider).trim();
        String var4 = importedStatement.substring(positionOfFourthDivider + 1).trim();

        return new String[]{var1, var2, var3, var4};

    }

    /**
     * Extracts details of a module grade from the storage file into a string array.
     * @param importedStatement input string from the storage file.
     * @return string array containing the various variables for the module grade.
     */
    public static String[] parseImportedGrade(String importedStatement) {
        assert importedStatement.startsWith("[G]") : "Parsed statement should be a grade";

        int positionOfFirstDivider = importedStatement.indexOf("|");
        int positionOfSecondDivider = importedStatement.indexOf("|",positionOfFirstDivider + 1);
        int positionOfThirdDivider = importedStatement.indexOf("|", positionOfSecondDivider + 1);
        int positionOfFourthDivider = importedStatement.indexOf("|", positionOfThirdDivider + 1);
        int positionOfFifthDivider = importedStatement.lastIndexOf("|");


        String var1 = importedStatement.substring(positionOfFirstDivider + 1,positionOfSecondDivider).trim();
        String var2 = importedStatement.substring(positionOfSecondDivider + 1,positionOfThirdDivider).trim();
        String var3 = importedStatement.substring(positionOfThirdDivider + 1,positionOfFourthDivider).trim();
        String var4 = importedStatement.substring(positionOfFourthDivider + 1,positionOfFifthDivider).trim();
        String var5 = importedStatement.substring(positionOfFifthDivider + 1).trim();

        return new String[]{var1, var2, var3, var4, var5};

    }

    public static Integer parseDeletePerson(String command) {
        return Integer.parseInt(command.substring("delete contact".length()).trim());
    }

    public static Integer parseDeleteGrade(String command) {
        return Integer.parseInt(command.substring("delete grade".length()).trim());
    }

    public static Integer parseSuGrade(String command) {
        return Integer.parseInt(command.substring("su grade".length()).trim());
    }

    public static Integer parseStarGrade(String command) {
        return Integer.parseInt(command.substring("star grade".length()).trim());
    }

    public static Integer parseStarContact(String command) {
        return Integer.parseInt(command.substring("star contact".length()).trim());
    }
}