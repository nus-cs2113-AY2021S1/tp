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
        Ui.showToUserLn("   Format: sprint /create -goal <goal_input> [optional tags]");
        Ui.showToUserLn("   Example 1: sprint /create Shopping Cart -start 20201010");
        Ui.showToUserLn("   Example 2: sprint /create -goal UI -start 20201010");
        Ui.showToUserLn("   Example 3: sprint /create -goal UI -start 20201010 -project 3");
        Ui.showToUserLn("2. View sprint");
        Ui.showToUserLn("   Format: sprint /view [optional tags]");
        Ui.showToUserLn("   Example 1: sprint /view");
        Ui.showToUserLn("   Example 2: sprint /view 2");
        Ui.showToUserLn("   Example 3: sprint /view -project 2");
        Ui.showToUserLn("   Example 4: sprint /view -project 2 -sprint 3");
        Ui.showToUserLn("3. Edit sprint");
        Ui.showToUserLn("   Format: sprint /edit -goal <goal_input> [optional tags]");
        Ui.showToUserLn("   Example 1: sprint /edit -goal Add Documentation");
        Ui.showToUserLn("   Example 2: sprint /edit -project 2 -goal Add Documentation");
        Ui.showToUserLn("   Example 3: sprint /edit -project 2 -sprint 3 -goal Add Documentation");
        Ui.showToUserLn("4. Add tasks to sprint");
        Ui.showToUserLn("   Format: sprint /addtask <task_id> [<task_id> ...] [optional tags]");
        Ui.showToUserLn("   Example 1: sprint /addtask 1 2");
        Ui.showToUserLn("   Example 2: sprint /addtask -task 1 2");
        Ui.showToUserLn("   Example 3: sprint /addtask -project 2 -task 1 2");
        Ui.showToUserLn("   Example 4: sprint /addtask -project 2 -sprint 3 -task 1 2");
        Ui.showToUserLn("5. Delete tasks from sprint");
        Ui.showToUserLn("   Format: sprint /removetask <task_id> [<task_id> ...] [optional tags]");
        Ui.showToUserLn("   Example 1: sprint /removetask 1 2");
        Ui.showToUserLn("   Example 2: sprint /removetask -project 2 -task 1 2");
        Ui.showToUserLn("   Example 3: sprint /removetask -project 2 -sprint 3 -task 1 2");
        Ui.showToUserLn("6. Allocate task to team members");
        Ui.showToUserLn("   Format: sprint /allocate -task <task_id> -user <user_id> [<user_id> ...] [optional tags]");
        Ui.showToUserLn("   Example 1: sprint /allocate -task 1 2 -user johntan mary jane");
        Ui.showToUserLn("   Example 2: sprint /allocate -project 2 -task 1 -user mary");
        Ui.showToUserLn("   Example 3: sprint /allocate -project 2 -sprint 3 -task 1 -user mary");
        Ui.showToUserLn("7. Deallocate tasks from team members");
        Ui.showToUserLn("   Format: sprint /deallocate -task <task_id> -user <user_id> [<user_id> ...] [optional tags]");
        Ui.showToUserLn("   Example 1: sprint /deallocate -task 1 2 -user johntan mary jane");
        Ui.showToUserLn("   Example 2: sprint /deallocate -project 2 -task 1 -user mary");
        Ui.showToUserLn("   Example 3: sprint /deallocate -project 2 -sprint 3 -task 1 -user mary");
    }
}
