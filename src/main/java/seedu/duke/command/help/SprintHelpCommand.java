package seedu.duke.command.help;

import seedu.duke.command.Command;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

public class SprintHelpCommand extends Command {
    public SprintHelpCommand(Hashtable<String, String> parameters) {
        super(parameters, false);
    }

    @Override
    public void execute() {
        Ui.showToUserLn("1. Create sprint");
        Ui.showToUserLn("   Format: sprint /create -goal <goal_input> -start <start_date>");
        Ui.showToUserLn("   Example: sprint /create Shopping Cart -start 20201010");
        Ui.showToUserLn("2. View sprint");
        Ui.showToUserLn("   Format & example: sprint /view");
        Ui.showToUserLn("3. Edit sprint");
        Ui.showToUserLn("   Format: ");
        Ui.showToUserLn("   Example: ");
        Ui.showToUserLn("4. Add tasks to sprint");
        Ui.showToUserLn("   Format: sprint /addtask <task_id> [<task_id> ...]");
        Ui.showToUserLn("   Example: sprint /addtask 1 3 4");
        Ui.showToUserLn("5. Delete tasks from sprint");
        Ui.showToUserLn("   Format: sprint /deltask <task_id> [<task_id> ...]");
        Ui.showToUserLn("   Example: sprint /deltask 1 3 4");
        Ui.showToUserLn("6. Allocate task to team members");
        Ui.showToUserLn("   Format: sprint /assign -task <task_id> -user <user_id> [<user_id> ...]");
        Ui.showToUserLn("   Example: sprint /assign -task 1 -user johntan mary jane");
        Ui.showToUserLn("7. Deallocate tasks from team members");
        Ui.showToUserLn("   Format: ");
        Ui.showToUserLn("   Example: ");
    }
}
