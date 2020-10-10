import java.util.Scanner;

public class StudyIt {
    public static Mode mode = Mode.INITIAL;

    public static void main(String[] args) {
        MainMenu.printWelcome();
        run();
    }

    public static void run() {
        CommandType commandType;
        // Repeatedly receive & process user command until "exit" is given
        do {
            // Collect user's command & identify the type
            String command = Ui.inputCommand();
            commandType = CommandParser.getCommandType(command);

            Command.executeCommand(command, commandType);
        } while (commandType != CommandType.EXIT);
    }
}