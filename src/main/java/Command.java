public class Command {
    public static void executeCommand(String command, CommandType commandType) {
        if (commandType == CommandType.EXIT) {
            Ui.printExit();
        } else if (commandType == CommandType.LOCATION) {
            Ui.printLocation();
        } else if (commandType == CommandType.CHANGE_DIRECTORY) {
            Ui.changeMode(command);
        } else {
            ErrorMessage.printUnidentifiableCommand();
        }
    }
}