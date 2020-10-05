package seedu.duke.command.taskcommand;

import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

public class FindTaskCommand extends TaskCommand {
    private String fullcommand;

    public FindTaskCommand(String fullcommand){
        this.fullcommand = fullcommand;
    }

    public void execute(TaskList taskList) {
        String[] message = this.fullcommand.split(" ");
        Ui.printFindTask(taskList,message[1]);
    }

    /**
     * Checks whether the the user exits the program
     * @return <code>true</code> if user exits the program
     */
    public boolean isExit(){
        return false;
    }
}
