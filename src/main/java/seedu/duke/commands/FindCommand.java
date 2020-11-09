package seedu.duke.commands;

import seedu.duke.DukeException;
import seedu.duke.common.Messages;
import seedu.duke.model.ListType;
import seedu.duke.model.Model;
import seedu.duke.model.itemlist.BookList;
import seedu.duke.model.itemlist.ExpenseList;
import seedu.duke.model.itemlist.LinkList;
import seedu.duke.model.itemlist.ModuleList;
import seedu.duke.model.itemlist.TaskList;

// @@author GuoAi

/**
 * Finds and lists all items in the item list whose description contains the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all items whose descriptions contain"
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "     Parameters: {tasks, links, books, expenses, modules} k/KEYWORDS\n"
            + "     Example: " + COMMAND_WORD + " expenses k/buy";

    private String keyword;
    private ListType listType;

    public FindCommand(ListType listType, String keyword) {
        this.listType = listType;
        this.keyword = keyword;
    }

    public static FindCommand createFindCommand(String fullCommand, String subRootCommand, String commandString)
            throws DukeException {
        if (subRootCommand.equals("")) {
            throw new DukeException(Messages.EXCEPTION_INVALID_FIND_COMMAND);
        }
        commandString = commandString.trim().toLowerCase();
        if (!commandString.startsWith("k/")) {
            throw new DukeException(Messages.EXCEPTION_NO_KEYWORD);
        }
        String keyword = commandString.substring(2);
        if (keyword.equals("")) {
            throw new DukeException(Messages.EXCEPTION_INVALID_ARGUMENTS);
        }
        switch (subRootCommand.trim().toLowerCase()) {
        case "tasks":
            return new FindCommand(ListType.TASK_LIST, keyword.toLowerCase());
        case "expenses":
            return new FindCommand(ListType.EXPENSE_LIST, keyword.toLowerCase());
        case "modules":
            return new FindCommand(ListType.MODULE_LIST, keyword.toLowerCase());
        case "links":
            return new FindCommand(ListType.LINK_LIST, keyword.toLowerCase());
        case "books":
            return new FindCommand(ListType.BOOK_LIST, keyword.toLowerCase());
        default:
            throw new DukeException(Messages.EXCEPTION_INVALID_FIND_COMMAND);
        }
    }

    @Override
    public void execute(Model model) throws DukeException {
        switch (listType) {
        case TASK_LIST:
            TaskList tasks = (TaskList) model.getList(ListType.TASK_LIST);
            tasks.findItem(keyword);
            break;
        case EXPENSE_LIST:
            ExpenseList expenses = (ExpenseList) model.getList(ListType.EXPENSE_LIST);
            expenses.findItem(keyword);
            break;
        case MODULE_LIST:
            ModuleList modules = (ModuleList) model.getList(ListType.MODULE_LIST);
            modules.findItem(keyword);
            break;
        case LINK_LIST:
            LinkList links = (LinkList) model.getList(ListType.LINK_LIST);
            links.findItem(keyword);
            break;
        case BOOK_LIST:
            BookList books = (BookList) model.getList(ListType.BOOK_LIST);
            books.findItem(keyword);
            break;
        default:
            throw new DukeException(Messages.EXCEPTION_INVALID_FIND_COMMAND);
        }
    }
}
