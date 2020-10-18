import bookmark.commands.AddLinkCommand;
import bookmark.commands.BackCommand;
import bookmark.commands.BookmarkCommand;
import bookmark.commands.ChangeModeCommand;
import bookmark.commands.ListCommand;
import bookmark.commands.RemoveLinkCommand;
import exceptions.InvalidCommandException;

public class BookmarkParser extends CommandParser {
    public BookmarkParser() {
    }

    public BookmarkCommand evaluateInput(String command, int chosenCategory) throws InvalidCommandException {
        if (command == null) {
            throw new InvalidCommandException();
        }
        String commandModified = CommandParser.standardizeCommand(command);
        if (commandModified.startsWith("bm")) {
            return new ChangeModeCommand(command, chosenCategory);
        } else if (commandModified.startsWith("add")) {
            return new AddLinkCommand(command, chosenCategory);
        } else if (commandModified.startsWith("rm")) {
            return new RemoveLinkCommand(command, chosenCategory);
        } else if (commandModified.startsWith("list")) {
            return new ListCommand(chosenCategory);
        } else if (commandModified.startsWith("back")) {
            return new BackCommand(chosenCategory);
        } else {
            throw new InvalidCommandException();
        }
    }

}