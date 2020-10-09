package seedu.duke;

public class Parser {
    public static Command parse(String userInput) {
        String[] fullCommand = userInput.split("\\s+");
        switch (fullCommand[0].toLowerCase()) {
        case "food":
            return new AddCommand(userInput);
        case "exercise":
            return new AddCommand(userInput);
        case "view":
            return new ViewCommand(userInput);
        case "delete":
            return new DeleteCommand(userInput);
        case "bye":
            return new ExitCommand(userInput);
        default:
            return new InvalidCommand(userInput);
        }
    }
}
