package seedu.duke.command;

import seedu.duke.task.Task;
import seedu.duke.list.TaskList;
import seedu.duke.ui.Ui;

public class DeleteCommand extends Command {
    private String fullCommand;

    public DeleteCommand(String fullCommand){
        this.fullCommand = fullCommand;
    }

    /**
     * Deletes a Task in a <code>taskList</code>
     * @param taskList the <code>TaskList</code> instance of the TaskList class for the user to delete from
     */
    public void execute(TaskList taskList) throws NumberFormatException{
        String[] message = this.fullCommand.split(" ");
        int number = Integer.valueOf(message[1]);
        Task task = taskList.getList().get(number-1);
        taskList.getList().remove(number-1);
        Ui.printDelete(task,taskList.getList().size());
    }

    /**
     * Checks whether the the user exits the program
     * @return <code>true</code> if user exits the program
     */
    public boolean isExit(){
        return false;
    }
}