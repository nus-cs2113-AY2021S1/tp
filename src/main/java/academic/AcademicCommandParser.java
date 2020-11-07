package academic;

import academic.Grade;
import exceptions.InvalidCommandException;
import exceptions.InvalidGradeException;
import exceptions.InvalidMcException;
import studyit.CommandParser;

import java.util.Arrays;
import java.util.List;

public class AcademicCommandParser extends CommandParser {

    public static AcademicCommandType getAcademicCommandType(String command)
            throws InvalidCommandException {
        String commandModified = CommandParser.standardizeCommand(command);

        if (commandModified.startsWith("add contact")) {
            return AcademicCommandType.ADD_CONTACT;
        } else if (commandModified.startsWith("list contact")) {
            return AcademicCommandType.LIST_CONTACT;
        } else if (commandModified.startsWith("add grade")) {
            return AcademicCommandType.ADD_GRADE;
        } else if (commandModified.startsWith("check cap")) {
            return AcademicCommandType.CHECK_CAP;
        } else if (commandModified.equalsIgnoreCase("list grade")) {
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
        } else if (commandModified.startsWith("list star")) {
            return AcademicCommandType.LIST_STAR;
        } else {
            throw new InvalidCommandException();
        }
    }

    public static String[] getContact(String command) throws NumberFormatException {
        String name = command.substring(command.indexOf("c/") + 2,
                command.indexOf("m/")).trim();
        String number = command.substring(command.indexOf("m/") + 2,
                command.indexOf("e/")).trim();
        String email = command.substring(command.indexOf("e/") + 2);
        int numberAsInteger = Integer.parseInt(number);
        if (numberAsInteger >= 100000000 || numberAsInteger < 0) {
            throw new NumberFormatException();
        }

        return new String[]{name, number, email};
    }

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

    public static String[] parseImportedPerson(String importedStatement) {
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

    public static String[] parseImportedGrade(String importedStatement) {
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