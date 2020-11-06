package seedu.duke.command.help;

import seedu.duke.command.Command;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

public class TaskHelpCommand extends Command {
    public TaskHelpCommand(Hashtable<String, String> parameters) {
        super(parameters, false);
    }

    @Override
    public void execute() {
        Ui.showToUserLn("1. Add tasks ");
        Ui.showToUserLn("   Format: task /add -title <title> -desc <description> -priority <category>");
        Ui.showToUserLn("   Example: task /add -title Add UI -desc add an interactive UI -priority HIGH");
        Ui.showToUserLn("2. Delete tasks");
        Ui.showToUserLn("   Format: task /del <taskid> [<taskid>...]");
        Ui.showToUserLn("   Example: task /del 5 7 9");
        Ui.showToUserLn("3. View task");
        Ui.showToUserLn("   Format: task /view <taskid>");
        Ui.showToUserLn("   Example: task /view 3");
        Ui.showToUserLn("4. Change task priority");
        Ui.showToUserLn("   Format: task /priority -priority <category> -id <taskid>");
        Ui.showToUserLn("   Example: task /priority -priority HIGH -id 1");
        Ui.showToUserLn("5. Mark task as complete");
        Ui.showToUserLn("   Format: task /done <taskid>");
        Ui.showToUserLn("   Example: task /done 1");
        Ui.showToUserLn("6. View tasks in descending priority");
        Ui.showToUserLn("   Format: task /priorityview");
    }
}
