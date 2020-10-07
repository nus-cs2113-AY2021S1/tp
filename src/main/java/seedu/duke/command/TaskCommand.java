package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.project.Project;
import seedu.duke.task.Task;

import java.util.ArrayList;
import java.util.Hashtable;

import static seedu.duke.command.CommandSummary.*;

public class TaskCommand {
    public void addTaskCommand(Hashtable<String, String> tasks) throws DukeException {
        /*
        Example of how to use the hashtable and how to throw the exception.
         */
        String title, description, priority;

        if (tasks.get(TITLE) != null) {
            title = tasks.get(TITLE);
        } else {
            throw new DukeException("no title");
        }
        if (tasks.get(DESCRIPTION) != null) {
            description = tasks.get(DESCRIPTION);
        } else {
            throw new DukeException("no description");
        }
        if (tasks.get(PRIORITY) != null) {
            priority = tasks.get(PRIORITY);
        } else {
            throw new DukeException("no priority");
        }

        Task task = new Task(title, description, priority);
        Project.backlog.addTask(task);
    }

    public void deleteTaskCommand(ArrayList<String> tasks) {
        /*
           For testing purposes only, to be deleted.
         */
        String task = "";
        for (String t : tasks) {
            task += t + " ";
        }
        System.out.println("Tasks deleted: " + task);

        /* Insert actual code for deleting tasks here.
        .
        .
        .
         */
    }

    public void viewTaskCommand(ArrayList<String> tasks) {
        /*
           For testing purposes only, to be deleted.
         */
        String task = "";
        for (String t : tasks) {
            task += t + System.lineSeparator();
        }
        System.out.println("Tasks in list: " + task);

        /* Insert actual code for viewing tasks here.
        .
        .
        .
         */
    }

    public void changeTaskPriorityCommand(String taskId, String priority,
                                          Hashtable<String, String> tasks) throws DukeException {
        /*
        Example of how to use the hashtable and how to throw the exception.
         */
        if (tasks.get(taskId) != null) {
            System.out.println(tasks.get(taskId));
        } else {
            throw new DukeException("no title");
        }
        if (tasks.get(priority) != null) {
            System.out.println(tasks.get(priority));
        } else {
            throw new DukeException("no priority");
        }

        /* Insert actual code for changing task priority here.
        .
        .
        .
         */
    }

    public void doneTaskCommand(ArrayList<String> tasks) {
        /*
           For testing purposes only, to be deleted.
         */
        String task = "";
        for (String t : tasks) {
            task += t + " ";
        }
        System.out.println("Tasks done: " + task);

        /* Insert actual code for completing tasks here.
        .
        .
        .
         */
    }
}
