import java.util.Scanner;

public class StudyIt {
    public static void main(String[] args) {
        MainMenu.printWelcome();
    }

    public static void run() {
        CommandType commandType = CommandType.INITIAL;
        // Repeatedly receive & process user command until "exit" is given
        do {
            // Collect user's command & identify the type
            String command = Ui.inputCommand();
            commandType = CommandParser.getCommandType(command);

        } while (commandType != CommandType.EXIT);
    }
}
