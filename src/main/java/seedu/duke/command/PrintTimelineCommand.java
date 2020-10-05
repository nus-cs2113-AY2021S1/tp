package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.task.TaskList;
import seedu.duke.task.Task;


public class PrintTimelineCommand extends Command {
    public PrintTimelineCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        TaskList timelineList = new TaskList();
        TaskList todoList = new TaskList();

        for (int i = 0; i < taskList.getTotalTask(); i++) {
            Task temp = taskList.getTask(i);
            switch (taskList.getTask(i).getTaskType()) {
            case "E":
            case "D":
                timelineList.addTask(temp);
                break;
            case "T":
                todoList.addTask(temp);
                break;
            default:
                break;
            }
        }

        TaskList sortedList = sortByDate (timelineList );
        System.out.println("Here is your timeline:");
        int numberOfItems = sortedList.getTotalTask();
        System.out.println("Timeline \n|" );
        for (int i = 0; i < numberOfItems; i++ ) {
            if (i == 0 || (sortedList.getTask(i-1).getTime() != sortedList.getTask(i).getTime())) {
                System.out.println("|__ " + sortedList.getTask(i).getTime() );
            }
            System.out.println("|        |__ " + sortedList.getTask(i).toString() );

        }
        //System.out.println("Here are your todos:");
    }

    public TaskList sortByDate(TaskList taskList) {
        TaskList sortingList = taskList;

        for (int i = 0; i < taskList.getTotalTask(); i++ ) {
            for (int j = i + 1; j < taskList.getTotalTask(); j++ ) {
                if (taskList.getTask(i).getTime() != null && taskList.getTask(j).getTime() != null) {
                    if (taskList.getTask(j).getTime().isBefore(taskList.getTask(i).getTime())) {
                        sortingList.swapTasks(i, j);
                    }
                }
            }
        }
        return sortingList;
    }
}
