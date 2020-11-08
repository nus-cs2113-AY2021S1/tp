// @@author Cao-Zeyu

package seedu.duke.commands;

import seedu.duke.DukeException;
import seedu.duke.common.Messages;
import seedu.duke.model.Model;
import seedu.duke.model.item.Book;
import seedu.duke.model.itemlist.BookList;
import seedu.duke.model.itemlist.LinkList;
import seedu.duke.model.ListType;
import seedu.duke.model.itemlist.TaskList;
import seedu.duke.model.item.Task;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Lists all tasks in the task list to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all items in the list with index numbers.\n"
            + "     Example: " + COMMAND_WORD + " all\n"
            + "     1. Displays all the tasks in the task list.\n"
            + "          Optional parameter 1: tasks p/PRIORITY\n"
            + "             Displays all the tasks with priority PRIORITY as a list.\n"
            + "             Example: " + COMMAND_WORD + " tasks p/1\n"
            + "          Optional parameter 2: tasks c/CATEGORY\n"
            + "             Displays all the tasks with CATEGORY as a list.\n"
            + "             Example: " + COMMAND_WORD + " tasks c/cs2113\n"
            + "          Optional parameter 3: tasks sorted\n"
            + "             Displays all the tasks sorted by priority"
            + "     2. Displays all the books in the book list.\n"
            + "             Example: " + COMMAND_WORD + " books\n"
            + "     3. Displays all the links in the link list.\n"
            + "             Example: " + COMMAND_WORD + " links\n"
            + "     4. Displays all the modules in the module list.\n"
            + "             Example: " + COMMAND_WORD + " modules\n"
            + "     5. Displays all the expense items in the expense list.\n"
            + "          Optional parameter 1: currency/CURRENCY\n"
            + "             Displays all the expense items with currency CURRENCY as a list.\n"
            + "             Example: " + COMMAND_WORD + " expenses currency/SGD\n"
            + "          Optional parameter 2: date/yyyy-MM-dd\n"
            + "             Displays all the expense items with date in the format of yyyy-MM-dd.\n"
            + "             Example: " + COMMAND_WORD + " expenses date/2020-11-07\n"
            + "          Optional parameter 3: for/{DAY, WEEK, MONTH, YEAR}\n"
            + "             Displays all the expense items for today/this week/this month/this year.\n"
            + "             Example: " + COMMAND_WORD + " expenses for/YEAR";
    private boolean hasPriority;
    private boolean hasCategory;
    private int priority;
    private String category;
    private boolean isSorted;
    private boolean isBook;
    private boolean isLink;

    public ListCommand() {
        this.hasPriority = false;
        this.hasCategory = false;
    }

    public ListCommand(int priority) {
        this.hasPriority = true;
        this.hasCategory = false;
        this.isSorted = false;
        this.priority = priority;
    }

    public ListCommand(String category) {
        this.hasPriority = false;
        this.hasCategory = true;
        this.isSorted = false;
        this.category = category;
    }

    public ListCommand(boolean isSorted, boolean isLink) {
        if (isSorted) {
            this.hasPriority = false;
            this.hasCategory = false;
            this.isSorted = true;
        } else if (isLink) {
            this.hasPriority = false;
            this.hasCategory = false;
            this.isLink = true;
        }
    }

    public ListCommand(boolean isSorted, boolean isLink, boolean isBook) {
        if (isSorted) {
            this.hasPriority = false;
            this.hasCategory = false;
            this.isSorted = isSorted;
        } else if (isLink) {
            this.hasPriority = false;
            this.hasCategory = false;
            this.isLink = isLink;
        } else if (isBook) {
            this.hasPriority = false;
            this.hasCategory = false;
            this.isBook = isBook;
        }
    }

    @Override
    public void execute(Model model) throws DukeException {
        TaskList tasks = (TaskList) model.getList(ListType.TASK_LIST);
        BookList books = (BookList) model.getList(ListType.BOOK_LIST);
        LinkList links = (LinkList) model.getList(ListType.LINK_LIST);

        ArrayList<Task> newTasks = new ArrayList<Task>();
        ArrayList<Book> newBooks = new ArrayList<Book>();
        if (hasPriority) {
            if (priority < 0) {
                throw new DukeException(Messages.EXCEPTION_INVALID_PRIORITY);
            }
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getPriority() == priority) {
                    newTasks.add(tasks.get(i));
                }
            }
            TaskList newTaskList = new TaskList(newTasks);
            newTaskList.listTask(priority);
        } else if (hasCategory) {
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getCategory() == null) {
                    continue;
                }
                if (tasks.get(i).getCategory().equals(category)) {
                    newTasks.add((Task) tasks.get(i));
                }
            }
            TaskList newTaskList = new TaskList(newTasks);
            newTaskList.listTask(category);
        } else if (isSorted) {
            newTasks = tasks.getTaskList();
            Collections.sort(newTasks);
            TaskList newTaskList = new TaskList(newTasks);
            newTaskList.listTask();
        } else if (isBook) {
            newBooks = books.getBookList();
            BookList newBookList = new BookList(newBooks);
            newBookList.listBook();
        } else if (isLink) {
            links.listLink();
        } else {
            tasks.listTask();
        }
    }
}
