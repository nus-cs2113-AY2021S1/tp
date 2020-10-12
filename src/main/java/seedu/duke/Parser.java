package seedu.duke;

public class Parser {
    public static Command parse(String userInput) {
        String[] fullCommand = userInput.split("\\s+");
        switch (fullCommand[0].toLowerCase()) {
        case "food":
            if (fullCommand.length == 1) {
                return new InvalidCommand(userInput);
            }
            return new AddCommand(userInput);
        case "exercise":
            if (fullCommand.length == 1) {
                return new InvalidCommand(userInput);
            }
            return new AddCommand(userInput);
        case "view":
            return new ViewCommand(userInput);
        case "delete":
            if (fullCommand.length == 1) {
                return new InvalidCommand(userInput);
            }
            return new DeleteCommand(userInput);
        case "bye":
            return new ExitCommand(userInput);
        default:
            return new InvalidCommand(userInput);
        }
    }
}
