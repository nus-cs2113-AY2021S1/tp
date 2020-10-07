package seedu.duke.command;

import seedu.duke.exception.DukeException;

import java.util.ArrayList;
import java.util.Hashtable;

public class TaskCommand {
    public void addTaskCommand(String title, String desc, String priority,
                               Hashtable<String, String> tasks) throws DukeException {
        /*
        Example of how to use the hashtable and how to throw the exception.
         */
        if (tasks.get(title) != null) {
            System.out.println(tasks.get(title));
        } else {
            throw new DukeException("no title");
        }
        if (tasks.get(desc) != null) {
            System.out.println(tasks.get(desc));
        } else {
            throw new DukeException("no description");
        }
        if (tasks.get(priority) != null) {
            System.out.println(tasks.get(priority));
        } else {
            throw new DukeException("no priority");
        }

        /* Insert actual code for adding tasks here.
        .
        .
        .
         */
    }

    public void deleteTaskCommand(ArrayList<String> tasks) {
        /*
           For testing purposes only, to be deleted.
         */
        String task =  "";
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
        String task =  "";
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

        /* Insert actual code for changing seedu.duke.task priority here.
        .
        .
        .
         */
    }

    public void doneTaskCommand(ArrayList<String> tasks) {
        /*
           For testing purposes only, to be deleted.
         */
        String task =  "";
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
