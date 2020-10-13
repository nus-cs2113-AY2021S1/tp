import academic.Grade;
import exceptions.InvalidCommandException;
import exceptions.InvalidGradeException;
import exceptions.InvalidMcException;
import exceptions.InvalidModeException;

import java.util.Arrays;
import java.util.List;

public class AcademicCommandParser extends CommandParser {

    public static AcademicCommandType getAcademicCommandType(String command)
            throws InvalidCommandException {
        String commandModified = standardizeCommand(command);

        if (commandModified.startsWith("add contact")) {
            return AcademicCommandType.ADD_CONTACT;
        } else if (commandModified.startsWith("check contact")) {
            return AcademicCommandType.CHECK_CONTACT;
        } else if (commandModified.startsWith("add grade")) {
            return AcademicCommandType.ADD_GRADE;
        } else if (commandModified.startsWith("check grade")) {
            return AcademicCommandType.CHECK_GRADE;
        } else {
            throw new InvalidCommandException();
        }
    }

    public static String[] getContact(String command) throws NumberFormatException {
        //String commandModified = standardizeCommand(command);
        String name = command.substring(command.indexOf("c/") + 2,
                command.indexOf("m/")).trim();
        String number = command.substring(command.indexOf("m/") + 2,
                command.indexOf("e/")).trim();
        String email = command.substring(command.indexOf("e/") + 2);
        int numberFormatTest = Integer.parseInt(number);
        return new String[]{name, number, email};
    }

    public static String[] getGrade(String command) throws InvalidGradeException, InvalidMcException {
        //String commandModified = standardizeCommand(command);
        String name = command.substring(command.indexOf("n/") + 2,
                command.indexOf("m/")).trim();
        String mc = command.substring(command.indexOf("m/") + 2,
                command.indexOf("g/")).trim();
        String grade = command.substring(command.indexOf("g/") + 2);

        List<String> list = Arrays.asList(Grade.listOfGrades);
        if (!list.contains(grade.toLowerCase())) {
            throw new InvalidGradeException();
        } else if (mc.equals("0")) {
            throw new InvalidMcException();
        }

        return new String[]{name, mc, grade};
    }
}