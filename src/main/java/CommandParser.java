public class CommandParser {

    public static CommandType getCommandType(String command) {
        String commandModified = command.trim().toLowerCase();

        if (commandModified.equals("exit")) {
            return CommandType.EXIT;
        } else {
            return CommandType.EXIT; //TODO: Fix this
        }
    }
}
