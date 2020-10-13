import exceptions.InvalidCommandException;
import exceptions.InvalidModeException;

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

    public static String[] getContact(String command) {
        String commandModified = standardizeCommand(command);
        String name = commandModified.substring(commandModified.indexOf("c/") + 2,
                commandModified.indexOf("m/")).trim();
        String number = commandModified.substring(commandModified.indexOf("m/") + 2,
                commandModified.indexOf("e/")).trim();
        String email = commandModified.substring(commandModified.indexOf("e/") + 2);
        return new String[]{name, number, email};
    }

    public static String[] getGrade(String command) {
        String commandModified = standardizeCommand(command);
        String name = commandModified.substring(commandModified.indexOf("n/") + 2,
                commandModified.indexOf("m/")).trim();
        String mc = commandModified.substring(commandModified.indexOf("m/") + 2,
                commandModified.indexOf("g/")).trim();
        String grade = commandModified.substring(commandModified.indexOf("g/") + 2);
        return new String[]{name, mc, grade};
    }
}