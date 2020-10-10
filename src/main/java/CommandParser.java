public class CommandParser {

    public static CommandType getCommandType(String command) {
        String commandModified = command.trim().toLowerCase();

        if (commandModified.equals("exit")) {
            return CommandType.EXIT;
        } else if (commandModified.equals("location")) {
            return CommandType.LOCATION;
        } else {
            return CommandType.UNIDENTIFIABLE;
        }
    }
}