import bookmark.commands.AddLinkCommand;
import bookmark.commands.BackCommand;
import bookmark.commands.BookmarkCommand;
import bookmark.commands.ChangeModeCommand;
import bookmark.commands.ListCommand;
import bookmark.commands.RemoveLinkCommand;
import exceptions.InvalidCommandException;

public class BookmarkParser extends CommandParser {
    private static int chosenCategory;

    public BookmarkParser() {
    }

    public BookmarkCommand evaluateInput(String command) throws InvalidCommandException {
        if (command == null) {
            StudyItLog.logger.finest("Empty command");
            throw new InvalidCommandException();
        }
        String commandModified = CommandParser.standardizeCommand(command);
        if (commandModified.startsWith("bm")) {
            getChosenCategory(command);
            return new ChangeModeCommand(chosenCategory);
        } else if (commandModified.startsWith("add")) {
            return new AddLinkCommand(command, chosenCategory);
        } else if (commandModified.startsWith("rm")) {
            return new RemoveLinkCommand(command, chosenCategory);
        } else if (commandModified.startsWith("list")) {
            return new ListCommand(chosenCategory);
        } else if (commandModified.startsWith("back")) {
            String backCommand = updateChosenCategory();
            return new BackCommand(backCommand);
        } else {
            StudyItLog.logger.info("Cannot understand bookmark command");
            throw new InvalidCommandException();
        }
    }

    private String updateChosenCategory() {
        if (chosenCategory == 0) {
            return "Goodbye";
        } else {
            resetBookmarkCategory();
            return "Category";
        }
    }

    public static void resetBookmarkCategory() {
        chosenCategory = 0;
    }

    private void getChosenCategory(String line) {
        chosenCategory = Integer.parseInt(line.substring(2).trim());
    }

}